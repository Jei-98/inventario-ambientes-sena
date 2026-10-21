public class Observacion {

    private Ambiente ambiente;
    private Elemento elemento;
    private String tipo;
    private int cantidadAfectada;
    private String descripcion;
    private String fecha;
    private String estado;

    // Constructor: aquí aplicamos el criterio de que toda observación nueva nace en "Pendiente"
    public Observacion(Ambiente ambiente, Elemento elemento, String tipo, int cantidadAfectada, String descripcion, String fecha) {
        this.ambiente = ambiente;
        this.elemento = elemento;
        this.tipo = tipo;
        this.cantidadAfectada = cantidadAfectada;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = "Pendiente"; // Cumple con el criterio de aceptación
    }

    // Getters para consultar la información
    public Ambiente getAmbiente() {
        return ambiente;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidadAfectada() {
        return cantidadAfectada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    // Setter para permitir cambiar el estado de la observación más adelante (ej. de Pendiente a Resuelto)
    public void setEstado(String estado) {
        this.estado = estado;
    }
}