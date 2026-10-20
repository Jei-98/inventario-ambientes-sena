public class Validaciones {

    public static String validarNombre(String nombre) throws DatoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException(
                "El nombre del ambiente no puede estar vacio.");
        }
        return nombre.trim();
    }

    
    public static int validarPiso(String texto) throws DatoInvalidoException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new DatoInvalidoException(
                "El piso no puede estar vacio. Ingrese un numero entero positivo.");
        }

        int piso;
        try {
            piso = Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException(
                "El piso '" + texto.trim() + "' no es valido. "
                + "Debe ser un numero entero positivo (ejemplo: 1, 2, 3).");
        }

        if (piso <= 0) {
            throw new DatoInvalidoException(
                "El piso debe ser mayor que cero. Valor recibido: " + piso + ".");
        }
        return piso;
    }
}