public class PruebasFaltante {

    private static int fallos = 0;

    public static void main(String[] args) {
        // calcularFaltante en un solo elemento
        check(new Elemento("Computador", 8, 10, Elemento.BUENO).calcularFaltante() == 2, "8 de 10 falta 2");
        check(new Elemento("Silla", 5, 5, Elemento.BUENO).calcularFaltante() == 0, "5 de 5 falta 0");
        check(new Elemento("Mesa", 12, 10, Elemento.BUENO).calcularFaltante() == 0, "12 de 10 (sobrante) falta 0");
        check(new Elemento("Tablero", 0, 3, Elemento.BUENO).calcularFaltante() == 3, "0 de 3 falta 3");

        // obtenerElementosConFaltante en un ambiente
        Ambiente aula = new Ambiente("Aula 101", 1);
        Elemento computador = new Elemento("Computador", 8, 10, Elemento.BUENO);
        Elemento silla = new Elemento("Silla", 5, 5, Elemento.REGULAR);
        Elemento mesa = new Elemento("Mesa", 12, 10, Elemento.BUENO);
        Elemento tablero = new Elemento("Tablero", 0, 3, Elemento.DANADO);
        aula.agregarElemento(computador);
        aula.agregarElemento(silla);
        aula.agregarElemento(mesa);
        aula.agregarElemento(tablero);

        java.util.List<Elemento> faltantes = aula.obtenerElementosConFaltante();
        check(faltantes.size() == 2, "solo 2 elementos tienen faltante");
        check(faltantes.contains(computador), "incluye Computador (8 de 10)");
        check(faltantes.contains(tablero), "incluye Tablero (0 de 3)");
        check(!faltantes.contains(silla), "excluye Silla (completa)");
        check(!faltantes.contains(mesa), "excluye Mesa (sobrante)");
        check(aula.getElementos().size() == 4, "el inventario original sigue con 4 elementos");

        // ambiente sin elementos
        check(new Ambiente("Aula 102", 1).obtenerElementosConFaltante().isEmpty(), "ambiente vacio devuelve lista vacia");

        System.out.println(fallos == 0 ? "\nTODAS LAS PRUEBAS PASARON" : "\nFALLOS: " + fallos);
        if (fallos > 0) System.exit(1);
    }

    private static void check(boolean ok, String descripcion) {
        System.out.println((ok ? "OK   " : "FALLA ") + descripcion);
        if (!ok) fallos++;
    }
}