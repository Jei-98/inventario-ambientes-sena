import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorInventario gestor = new GestorInventario();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Elige una opcion: ");

            switch (opcion) {
                case 1:
                    registrarAmbiente(gestor, sc);
                    break;
                case 2:
                    agregarElemento(gestor, sc);
                    break;
                case 3:
                    gestor.mostrarInventarioAmbiente(leerTexto(sc, "Nombre del ambiente: "));
                    break;
                case 4:
                    actualizarElemento(gestor, sc);
                    break;
                case 5:
                    reportarObservacion(gestor, sc);
                    break;
                case 6:
                    gestor.mostrarAmbientesPorPiso(leerEntero(sc, "Piso: "));
                    break;
                case 7:
                    verObservaciones(gestor, sc);
                    break;
                case 8:
                    resolverObservacion(gestor, sc);
                    break;
                case 9:
                    gestor.mostrarReporteFaltantes();
                    break;
                    
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                    
                default:
                    System.out.println("Opcion no valida. Intenta de nuevo.");
                    System.out.println("9. Reporte de faltantes por piso");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== INVENTARIO DE AMBIENTES SENA =====");
        System.out.println("1. Registrar ambiente");
        System.out.println("2. Agregar elemento a un ambiente");
        System.out.println("3. Consultar inventario de un ambiente");
        System.out.println("4. Actualizar cantidad o estado de un elemento");
        System.out.println("5. Reportar faltante, perdida o dano");
        System.out.println("6. Filtrar ambientes por piso");
        System.out.println("7. Ver observaciones de un ambiente");
        System.out.println("8. Resolver una observacion");
        System.out.println("0. Salir");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida: escribe un numero entero.");
            }
        }
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

        private static void registrarAmbiente(GestorInventario gestor, Scanner sc) {
        String nombre = leerTexto(sc, "Nombre del ambiente: ");
        int piso = leerEntero(sc, "Piso: ");

        try {
            gestor.crearAmbiente(nombre, piso);
            System.out.println("Ambiente registrado.");
        } catch (DatoInvalidoException e) {
            System.out.println("No se registro: " + e.getMessage());
        }
    }

    private static void agregarElemento(GestorInventario gestor, Scanner sc) {
        String ambiente = leerTexto(sc, "Ambiente: ");
        String elemento = leerTexto(sc, "Nombre del elemento: ");
        int actual = leerEntero(sc, "Cantidad actual: ");
        int esperada = leerEntero(sc, "Cantidad esperada: ");
        String estado = leerTexto(sc, "Estado (BUENO, REGULAR, DANADO): ");

        try {
            gestor.agregarElemento(ambiente, elemento, actual, esperada, estado);
            System.out.println("Elemento agregado.");
        } catch (DatoInvalidoException e) {
            System.out.println("No se agrego: " + e.getMessage());
        }
    }

    private static void actualizarElemento(GestorInventario gestor, Scanner sc) {
        String ambiente = leerTexto(sc, "Ambiente: ");
        String elemento = leerTexto(sc, "Nombre del elemento: ");
        int cantidad = leerEntero(sc, "Nueva cantidad: ");
        String estado = leerTexto(sc, "Nuevo estado (BUENO, REGULAR, DANADO): ");

        try {
            gestor.actualizarElemento(ambiente, elemento, cantidad, estado);
            System.out.println("Elemento actualizado.");
        } catch (DatoInvalidoException e) {
            System.out.println("No se actualizo: " + e.getMessage());
        }
    }

    private static void reportarObservacion(GestorInventario gestor, Scanner sc) {
        String ambiente = leerTexto(sc, "Ambiente: ");
        String elemento = leerTexto(sc, "Elemento: ");
        String tipo = leerTexto(sc, "Tipo (Faltante, Perdida, Dano): ");
        int cantidad = leerEntero(sc, "Cantidad afectada: ");
        String descripcion = leerTexto(sc, "Descripcion: ");

        try {
            Observacion o = gestor.crearObservacion(ambiente, elemento, tipo, cantidad, descripcion);
            if (gestor.registrarObservacion(o)) {
                System.out.println("Observacion registrada en estado " + o.getEstado() + ".");
            } else {
                System.out.println("Ese reporte ya estaba registrado.");
            }
        } catch (DatoInvalidoException e) {
            System.out.println("No se registro: " + e.getMessage());
        }
    }

    private static void verObservaciones(GestorInventario gestor, Scanner sc) {
        String ambiente = leerTexto(sc, "Ambiente: ");
        String respuesta = leerTexto(sc, "Solo pendientes? (s/n): ");
        boolean soloPendientes = respuesta.equalsIgnoreCase("s");
        gestor.mostrarObservacionesAmbiente(ambiente, soloPendientes);
    }

    private static void resolverObservacion(GestorInventario gestor, Scanner sc) {
        List<Observacion> todas = gestor.getObservaciones();
        boolean hayPendientes = false;

        System.out.println("\n--- OBSERVACIONES PENDIENTES ---");
        for (int i = 0; i < todas.size(); i++) {
            Observacion o = todas.get(i);
            if (!o.estaResuelta()) {
                hayPendientes = true;
                System.out.println((i + 1) + ". " + o.getAmbiente().getNombre()
                        + " | " + o.getElemento().getNombre()
                        + " | " + o.getTipo() + " | " + o.getDescripcion());
            }
        }

        if (!hayPendientes) {
            System.out.println("No hay observaciones pendientes.");
            return;
        }

        int numero = leerEntero(sc, "Numero de la observacion a resolver: ");
        if (numero < 1 || numero > todas.size()) {
            System.out.println("Numero fuera de rango.");
            return;
        }

        try {
            todas.get(numero - 1).marcarComoResuelta();
            System.out.println("Observacion marcada como Resuelta.");
        } catch (DatoInvalidoException e) {
            System.out.println("No se pudo resolver: " + e.getMessage());
        }
    }
}