import java.util.HashMap;
import java.util.Map;

public class GestorInventario {

    private Map<String, Ambiente> ambientes = new HashMap<>();

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
}