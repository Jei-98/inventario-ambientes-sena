public class Ambiente {

    private String nombre;
    private int piso;

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
}