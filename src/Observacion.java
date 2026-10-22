import java.time.LocalDate;

public class Observacion {

    private Ambiente ambiente;
    private Elemento elemento;
    private String tipo;
    private int cantidadAfectada;
    private String descripcion;
    private String fecha;
    private String estado;
    private String fechaResolucion; 

    public Observacion(Ambiente ambiente, Elemento elemento, String tipo, int cantidadAfectada, String descripcion, String fecha) {
        this.ambiente = ambiente;
        this.elemento = elemento;
        this.tipo = tipo;
        this.cantidadAfectada = cantidadAfectada;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = "Pendiente"; 
    }

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

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public boolean estaResuelta() {
        return "Resuelta".equals(estado);
    }

    
    public void marcarComoResuelta() throws DatoInvalidoException {
        if (estaResuelta()) {
            throw new DatoInvalidoException(
                "La observacion ya esta resuelta (fecha de resolucion: " + fechaResolucion + ").");
        }
        this.estado = "Resuelta";
        this.fechaResolucion = LocalDate.now().toString();
    }
}