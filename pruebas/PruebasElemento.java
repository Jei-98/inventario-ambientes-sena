public class PruebasElemento {

    private static int fallos = 0;

    public static void main(String[] args) {
        Elemento a = new Elemento("Computador", 8, 10, Elemento.BUENO);
        check(a.getNombre().equals("Computador"), "guarda el nombre");
        check(a.getCantidadActual() == 8, "guarda la cantidad actual");
        check(a.getCantidadEsperada() == 10, "guarda la cantidad esperada");
        check(a.getEstado().equals(Elemento.BUENO), "estado BUENO");
        check(a.tieneFaltante(), "8 de 10 es faltante");

        check(new Elemento("Silla", 5, 5, Elemento.REGULAR).getEstado().equals(Elemento.REGULAR), "estado REGULAR");
        check(new Elemento("Mesa", 2, 2, Elemento.DANADO).getEstado().equals(Elemento.DANADO), "estado DA\u00D1ADO");
        check(!new Elemento("Mesa", 2, 2, Elemento.BUENO).tieneFaltante(), "2 de 2 no es faltante");

        rechazaEstado("PERFECTO");
        rechazaEstado("");
        rechazaEstado("   ");
        rechazaEstado(null);
        rechazaEstado("malo");

        try {
            a.setEstado("ROTO");
            check(false, "setEstado('ROTO') debia rechazarse");
        } catch (IllegalArgumentException e) {
            check(a.getEstado().equals(Elemento.BUENO), "setEstado invalido conserva el estado anterior");
        }
        a.setEstado(Elemento.DANADO);
        check(a.getEstado().equals(Elemento.DANADO), "setEstado valido cambia el estado");

        rechazaCrear("", 1, 1, "nombre vacio");
        rechazaCrear("X", -1, 1, "cantidad actual negativa");
        rechazaCrear("X", 1, -1, "cantidad esperada negativa");

        System.out.println(fallos == 0 ? "\nTODAS LAS PRUEBAS PASARON" : "\nFALLOS: " + fallos);
        if (fallos > 0) System.exit(1);
    }

    private static void rechazaEstado(String estado) {
        try {
            new Elemento("Prueba", 1, 1, estado);
            check(false, "estado '" + estado + "' debia rechazarse");
        } catch (IllegalArgumentException e) {
            check(true, "estado '" + estado + "' rechazado");
        }
    }

    private static void rechazaCrear(String nombre, int actual, int esperada, String motivo) {
        try {
            new Elemento(nombre, actual, esperada, Elemento.BUENO);
            check(false, motivo + " debia rechazarse");
        } catch (IllegalArgumentException e) {
            check(true, motivo + " rechazado: " + e.getMessage());
        }
    }

    private static void check(boolean ok, String descripcion) {
        System.out.println((ok ? "OK   " : "FALLA ") + descripcion);
        if (!ok) fallos++;
    }
}