import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorInventario gestor = new GestorInventario();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Elige una opción: ");

            switch (opcion) {
                case 1:
                    registrarAmbiente(gestor, sc);
                    break;
                case 0:
                    System.out.println("Hasta pronto.");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n===== INVENTARIO DE AMBIENTES SENA =====");
        System.out.println("1. Registrar ambiente");
        System.out.println("0. Salir");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: escribe un número entero.");
            }
        }
    }

    private static void registrarAmbiente(GestorInventario gestor, Scanner sc) {
        System.out.print("Nombre del ambiente: ");
        String nombre = sc.nextLine();
        int piso = leerEntero(sc, "Piso: ");

        if (gestor.registrarAmbiente(new Ambiente(nombre, piso))) {
            System.out.println("Ambiente registrado.");
        } else {
            System.out.println("Ya existe un ambiente con ese nombre.");
        }
    }
}