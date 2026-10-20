/**
 * Pruebas manuales de T-04 (sin JUnit).
 * Compilar y ejecutar desde la raiz del proyecto:
 *   javac -d out src/*.java pruebas/PruebasValidaciones.java
 *   java -cp out PruebasValidaciones
 */
public class PruebasValidaciones {

    private static int fallos = 0;

    public static void main(String[] args) {
        rechazaNombre("");
        rechazaNombre("   ");
        rechazaNombre(null);
        rechazaPiso("0");
        rechazaPiso("-3");
        rechazaPiso("abc");
        rechazaPiso("2a");
        rechazaPiso("2.5");
        rechazaPiso("");
        rechazaPiso(null);

        try {
            check(Validaciones.validarNombre("  Sala 1 ").equals("Sala 1"), "nombre valido");
            check(Validaciones.validarPiso("3") == 3, "piso valido");
        } catch (DatoInvalidoException e) {
            check(false, "datos validos no deben fallar: " + e.getMessage());
        }

        GestorInventario gestor = new GestorInventario();
        intentarRegistrar(gestor, "", "2");
        intentarRegistrar(gestor, "Taller", "0");
        intentarRegistrar(gestor, "Taller", "-1");
        intentarRegistrar(gestor, "Taller", "uno");
        check(gestor.buscarAmbiente("Taller") == null, "no se registro Taller con datos invalidos");

        intentarRegistrar(gestor, "Taller", "2");
        check(gestor.buscarAmbiente("Taller") != null, "Taller si se registra con datos validos");

        System.out.println(fallos == 0 ? "\nTODAS LAS PRUEBAS PASARON" : "\nFALLOS: " + fallos);
        if (fallos > 0) System.exit(1);
    }

    private static void intentarRegistrar(GestorInventario gestor, String nombre, String piso) {
        try {
            String n = Validaciones.validarNombre(nombre);
            int p = Validaciones.validarPiso(piso);
            gestor.registrarAmbiente(new Ambiente(n, p));
        } catch (DatoInvalidoException e) {
            System.out.println("  Rechazado -> " + e.getMessage());
        }
    }

    private static void rechazaNombre(String valor) {
        try {
            Validaciones.validarNombre(valor);
            check(false, "nombre '" + valor + "' debia rechazarse");
        } catch (DatoInvalidoException e) {
            check(true, "nombre '" + valor + "' rechazado");
        }
    }

    private static void rechazaPiso(String valor) {
        try {
            Validaciones.validarPiso(valor);
            check(false, "piso '" + valor + "' debia rechazarse");
        } catch (DatoInvalidoException e) {
            check(true, "piso '" + valor + "' rechazado: " + e.getMessage());
        }
    }

    private static void check(boolean ok, String descripcion) {
        System.out.println((ok ? "OK   " : "FALLA ") + descripcion);
        if (!ok) fallos++;
    }
}