import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;

public class GestorInventario {

    public static final String TIPO_FALTANTE = "Faltante";
    public static final String TIPO_PERDIDA = "P\u00E9rdida";
    public static final String TIPO_DANIO = "Da\u00F1o";
    private Map<String, Ambiente> ambientes = new HashMap<>();
    

    private ArrayList<Observacion> observaciones = new ArrayList<>();
    private Set<String> reportesRegistrados = new HashSet<>();

    public boolean registrarAmbiente(Ambiente ambiente) {
        String clave = ambiente.getNombre().trim().toLowerCase();

        if (ambientes.containsKey(clave)) {
            return false;
        }

        ambientes.put(clave, ambiente);
        return true;
    }

    public Ambiente buscarAmbiente(String nombre) {
        return ambientes.get(nombre.trim().toLowerCase());
    }

    public boolean registrarObservacion(Observacion observacion) {
        if (observacion == null) {
            return false;
        }
        if (!reportesRegistrados.add(claveReporte(observacion))) {
            return false;
        }
        observaciones.add(observacion);
        return true;
    }

    public List<Observacion> getObservaciones() {
        return Collections.unmodifiableList(observaciones);
    }

    private String claveReporte(Observacion o) {
        String nombreAmbiente = (o.getAmbiente() == null) ? "" : o.getAmbiente().getNombre();
        String nombreElemento = (o.getElemento() == null) ? "" : o.getElemento().getNombre();
        return normalizar(nombreAmbiente) + "|" + normalizar(nombreElemento) + "|"
                + normalizar(o.getTipo()) + "|" + o.getCantidadAfectada() + "|"
                + normalizar(o.getDescripcion()) + "|" + normalizar(o.getFecha());
    }

    private String normalizar(String texto) {
        return (texto == null) ? "" : texto.trim().toLowerCase();
    }

    
    public void agregarElemento(String nombreAmbiente, String nombreElemento,
                                int cantidadActual, int cantidadEsperada, String estado)
            throws DatoInvalidoException {

        Ambiente ambiente = (nombreAmbiente == null) ? null : buscarAmbiente(nombreAmbiente);
        if (ambiente == null) {
            throw new DatoInvalidoException(
                "El ambiente '" + nombreAmbiente + "' no existe.");
        }

        if (nombreElemento == null || nombreElemento.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre del elemento no puede estar vacio.");
        }
        if (ambiente.buscarElemento(nombreElemento) != null) {
            throw new DatoInvalidoException(
                "Ya existe un elemento llamado '" + nombreElemento.trim()
                + "' en el ambiente '" + ambiente.getNombre() + "'.");
        }
        if (cantidadActual < 0) {
            throw new DatoInvalidoException(
                "La cantidad actual no puede ser negativa. Valor recibido: " + cantidadActual + ".");
        }
        if (cantidadEsperada < 0) {
            throw new DatoInvalidoException(
                "La cantidad esperada no puede ser negativa. Valor recibido: " + cantidadEsperada + ".");
        }

        Elemento elemento;
        try {
            elemento = new Elemento(nombreElemento, cantidadActual, cantidadEsperada, estado);
        } catch (IllegalArgumentException e) {
            throw new DatoInvalidoException(e.getMessage());
        }

        ambiente.agregarElemento(elemento);
    }
    public void mostrarInventarioAmbiente(String nombreAmbiente) {
        if (nombreAmbiente == null || nombreAmbiente.trim().isEmpty()) {
            System.out.println("El nombre del ambiente no puede estar vacío.");
            return;
        }

        Ambiente ambiente = buscarAmbiente(nombreAmbiente);
        
        if (ambiente == null) {
            System.out.println("El ambiente '" + nombreAmbiente + "' no existe en el sistema.");
            return;
        }

        List<Elemento> elementos = ambiente.getElementos();
        
        if (elementos.isEmpty()) {
            System.out.println("El ambiente '" + ambiente.getNombre() + "' no tiene elementos registrados actualmente.");
            return;
        }

        System.out.println("\n--- INVENTARIO DEL AMBIENTE: " + ambiente.getNombre() + " (Piso: " + ambiente.getPiso() + ") ---");
        for (Elemento e : elementos) {
            System.out.println("• Nombre: " + e.getNombre() 
                    + " | Cant. Actual: " + e.getCantidadActual() 
                    + " | Cant. Esperada: " + e.getCantidadEsperada() 
                    + " | Estado: " + e.getEstado());
        }
        System.out.println("------------------------------------------------------------------");
    }
    
    public Observacion crearObservacion(String nombreAmbiente, String nombreElemento,
            String tipo, int cantidadAfectada, String descripcion)
            throws DatoInvalidoException {

        Ambiente ambiente = (nombreAmbiente == null) ? null : buscarAmbiente(nombreAmbiente);
        if (ambiente == null) {
            throw new DatoInvalidoException(
                "El ambiente '" + nombreAmbiente + "' no existe.");
        }

        Elemento elemento = ambiente.buscarElemento(nombreElemento);
        if (elemento == null) {
            throw new DatoInvalidoException(
                "El elemento '" + nombreElemento + "' no existe en el ambiente '"
                + ambiente.getNombre() + "'.");
        }

        String tipoNormalizado = normalizarTipo(tipo);

        if (cantidadAfectada <= 0) {
            throw new DatoInvalidoException("La cantidad afectada debe ser mayor que cero.");
        }
        if (cantidadAfectada > elemento.getCantidadActual()) {
            throw new DatoInvalidoException(
                "La cantidad afectada (" + cantidadAfectada
                + ") supera la cantidad registrada (" + elemento.getCantidadActual() + ").");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new DatoInvalidoException("La descripcion no puede estar vacia.");
        }

        // Todas las validaciones pasaron: recien ahora se modifica el inventario.
        if (tipoNormalizado.equals(TIPO_PERDIDA) || tipoNormalizado.equals(TIPO_DANIO)) {
            elemento.setCantidadActual(elemento.getCantidadActual() - cantidadAfectada);
        }

        return new Observacion(ambiente, elemento, tipoNormalizado,
                cantidadAfectada, descripcion.trim(), LocalDate.now().toString());
    }

    private String normalizarTipo(String tipo) throws DatoInvalidoException {
        if (tipo != null) {
            String limpio = tipo.trim();
            if (limpio.equalsIgnoreCase(TIPO_FALTANTE)) {
                return TIPO_FALTANTE;
            }
            if (limpio.equalsIgnoreCase(TIPO_PERDIDA) || limpio.equalsIgnoreCase("Perdida")) {
                return TIPO_PERDIDA;
            }
            if (limpio.equalsIgnoreCase(TIPO_DANIO) || limpio.equalsIgnoreCase("Dano")) {
                return TIPO_DANIO;
            }
        }
        throw new DatoInvalidoException(
            "Tipo no valido: '" + tipo + "'. Use Faltante, Perdida o Dano.");
    }
}