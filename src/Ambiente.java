import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ambiente {

    private String nombre;
    private int piso;
    private ArrayList<Elemento> elementos = new ArrayList<>();

    public Ambiente(String nombre, int piso) {
        this.nombre = nombre;
        this.piso = piso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPiso() {
        return piso;
    }

    public List<Elemento> getElementos() {
        return Collections.unmodifiableList(elementos);
    }

    public Elemento buscarElemento(String nombreElemento) {
        if (nombreElemento == null) {
            return null;
        }
        String buscado = nombreElemento.trim();
        for (Elemento e : elementos) {
            if (e.getNombre().equalsIgnoreCase(buscado)) {
                return e;
            }
        }
        return null;
    }

    public void agregarElemento(Elemento elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("El elemento no puede ser nulo.");
        }
        
        // Validar que las cantidades no sean negativas (doble seguridad)
        if (elemento.getCantidadActual() < 0 || elemento.getCantidadEsperada() < 0) {
            throw new IllegalArgumentException("Las cantidades no pueden ser negativas.");
        }
        
        // Validar que no exista ya un elemento con el mismo nombre en este ambiente
        if (buscarElemento(elemento.getNombre()) != null) {
            throw new IllegalArgumentException("Ya existe un elemento con el nombre '" + elemento.getNombre() + "' en este ambiente.");
        }
        
        // Si pasa todas las validaciones, lo agregamos al ArrayList
        elementos.add(elemento);
    }
}