public class Elemento {

    public static final String BUENO = "BUENO";
    public static final String REGULAR = "REGULAR";
    public static final String DANADO = "DA\u00D1ADO";

    private String nombre;
    private int cantidadActual;
    private int cantidadEsperada;
    private String estado;

    public Elemento(String nombre, int cantidadActual, int cantidadEsperada, String estado) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del elemento no puede estar vacio.");
        }
        if (cantidadActual < 0) {
            throw new IllegalArgumentException("La cantidad actual no puede ser negativa.");
        }
        if (cantidadEsperada < 0) {
            throw new IllegalArgumentException("La cantidad esperada no puede ser negativa.");
        }
        this.nombre = nombre.trim();
        this.cantidadActual = cantidadActual;
        this.cantidadEsperada = cantidadEsperada;
        this.estado = validarEstado(estado);
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public int getCantidadEsperada() {
        return cantidadEsperada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = validarEstado(estado);
    }

    /** Una cantidad negativa se rechaza y se conserva el valor anterior. */
    public void setCantidadActual(int cantidadActual) {
        if (cantidadActual < 0) {
            throw new IllegalArgumentException("La cantidad actual no puede ser negativa.");
        }
        this.cantidadActual = cantidadActual;
    }

    public boolean tieneFaltante() {
        return cantidadActual < cantidadEsperada;
    }

    private static String validarEstado(String estado) {
        if (estado != null) {
            String limpio = estado.trim();
            if (limpio.equalsIgnoreCase(BUENO)) {
                return BUENO;
            }
            if (limpio.equalsIgnoreCase(REGULAR)) {
                return REGULAR;
            }
            if (limpio.equalsIgnoreCase(DANADO) || limpio.equalsIgnoreCase("DANADO")) {
                return DANADO;
            }
        }
        throw new IllegalArgumentException(
            "Estado no valido: '" + estado + "'. Use BUENO, REGULAR o DA\u00D1ADO.");
    }
}