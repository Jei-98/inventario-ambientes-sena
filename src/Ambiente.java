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

    void agregarElemento(Elemento elemento) {
        elementos.add(elemento);
    }
}