import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class VerificacionRechazos {

    private static GestorInventario gestor;
    private static int fallos = 0;

    interface Operacion {
        void ejecutar() throws Exception;
    }

    public static void main(String[] args) throws Exception {
        prepararInventario();

        System.out.println("--- Crear ambientes ---");
        rechazada("ambiente: nombre vacio", () -> gestor.crearAmbiente("   ", 1));
        rechazada("ambiente: piso cero", () -> gestor.crearAmbiente("Bodega", 0));
        rechazada("ambiente: piso negativo", () -> gestor.crearAmbiente("Bodega", -2));
        rechazada("ambiente: nombre repetido", () -> gestor.crearAmbiente("Sala 1", 5));
        rechazada("ambiente: nombre repetido con otras mayusculas", () -> gestor.crearAmbiente("  SALA 1 ", 3));

        System.out.println("--- Agregar elementos ---");
        rechazada("agregar: ambiente inexistente",
            () -> gestor.agregarElemento("Bodega", "Mesa", 1, 1, Elemento.BUENO));
        rechazada("agregar: nombre vacio",
            () -> gestor.agregarElemento("Sala 1", "  ", 1, 1, Elemento.BUENO));
        rechazada("agregar: nombre repetido",
            () -> gestor.agregarElemento("Sala 1", "computador", 1, 1, Elemento.BUENO));
        rechazada("agregar: cantidad actual negativa",
            () -> gestor.agregarElemento("Sala 1", "Mesa", -1, 5, Elemento.BUENO));
        rechazada("agregar: cantidad esperada negativa",
            () -> gestor.agregarElemento("Sala 1", "Mesa", 1, -5, Elemento.BUENO));
        rechazada("agregar: estado invalido",
            () -> gestor.agregarElemento("Sala 1", "Mesa", 1, 1, "ROTO"));

        System.out.println("--- Actualizar elementos ---");
        rechazada("actualizar: ambiente inexistente",
            () -> gestor.actualizarElemento("Bodega", "Computador", 5, Elemento.REGULAR));
        rechazada("actualizar: elemento inexistente",
            () -> gestor.actualizarElemento("Sala 1", "Proyector", 5, Elemento.REGULAR));
        rechazada("actualizar: cantidad negativa",
            () -> gestor.actualizarElemento("Sala 1", "Computador", -3, Elemento.REGULAR));
        rechazada("actualizar: estado invalido con cantidad valida",
            () -> gestor.actualizarElemento("Sala 1", "Computador", 5, "ROTO"));
        rechazada("actualizar: cantidad negativa y estado invalido",
            () -> gestor.actualizarElemento("Sala 1", "Computador", -3, "ROTO"));

        System.out.println("--- Reportar observaciones ---");
        rechazada("observacion: ambiente inexistente",
            () -> reportar("Bodega", "Computador", "Dano", 1, "Pantalla rota"));
        rechazada("observacion: elemento inexistente",
            () -> reportar("Sala 1", "Proyector", "Dano", 1, "No enciende"));
        rechazada("observacion: tipo invalido",
            () -> reportar("Sala 1", "Computador", "Robo", 1, "Se lo llevaron"));
        rechazada("observacion: cantidad cero",
            () -> reportar("Sala 1", "Computador", "Dano", 0, "Sin cantidad"));
        rechazada("observacion: cantidad negativa",
            () -> reportar("Sala 1", "Computador", "Perdida", -1, "Cantidad negativa"));
        rechazada("observacion: cantidad mayor a la registrada",
            () -> reportar("Sala 1", "Computador", "Dano", 99, "Demasiados"));
        rechazada("observacion: descripcion vacia",
            () -> reportar("Sala 1", "Computador", "Dano", 1, "   "));
        rechazada("observacion: reporte duplicado (mismo dia, mismos datos)",
            () -> reportar("Sala 1", "Computador", "Dano", 2, "Pantallas rotas"));
        rechazada("observacion: registrar una observacion nula", () -> {
            if (!gestor.registrarObservacion(null)) {
                throw new Exception("rechazada (registrarObservacion devolvio false)");
            }
        });

        System.out.println("--- Resolver observaciones ---");
        gestor.getObservaciones().get(0).marcarComoResuelta();   // operacion valida
        rechazada("resolver una observacion que ya estaba resuelta",
            () -> gestor.getObservaciones().get(0).marcarComoResuelta());

        System.out.println(fallos == 0
            ? "\nTODO RECHAZO CONSERVA EL INVENTARIO"
            : "\nFALLOS: " + fallos);
        if (fallos > 0) System.exit(1);
    }

    private static void prepararInventario() throws Exception {
        gestor = new GestorInventario();
        gestor.crearAmbiente("Sala 1", 2);
        gestor.crearAmbiente("Taller", 1);
        gestor.agregarElemento("Sala 1", "Computador", 8, 10, Elemento.BUENO);
        gestor.agregarElemento("Sala 1", "Silla", 20, 20, Elemento.REGULAR);
        gestor.agregarElemento("Taller", "Taladro", 2, 3, Elemento.DANADO);
        reportar("Sala 1", "Computador", "Dano", 2, "Pantallas rotas");   // valida: Computador queda en 6
    }

    private static void reportar(String ambiente, String elemento, String tipo,
                                 int cantidad, String descripcion) throws Exception {
        Observacion o = gestor.crearObservacion(ambiente, elemento, tipo, cantidad, descripcion);
        if (!gestor.registrarObservacion(o)) {
            throw new Exception("Ese reporte ya estaba registrado.");
        }
    }

    private static void rechazada(String nombre, Operacion operacion) {
        String antes = fotoDelInventario();
        String mensaje = "(no lanzo ningun error)";
        boolean rechazada = false;
        try {
            operacion.ejecutar();
        } catch (Exception e) {
            rechazada = true;
            mensaje = e.getMessage();
        }
        String despues = fotoDelInventario();
        boolean igual = antes.equals(despues);
        System.out.println((igual && rechazada ? "OK    " : "FALLA ") + nombre + "  ->  " + mensaje);
        if (!rechazada) {
            fallos++;
            System.out.println("      La operacion NO fue rechazada.");
        } else if (!igual) {
            fallos++;
            System.out.println("      ANTES:\n" + antes + "      DESPUES:\n" + despues);
        }
    }

    private static String fotoDelInventario() {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(buffer, true, "UTF-8"));
            gestor.mostrarInventarioAmbiente("Sala 1");
            gestor.mostrarInventarioAmbiente("Taller");
            gestor.mostrarInventarioAmbiente("Bodega");   // no debe haberse creado
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(original);
        }
        StringBuilder foto = new StringBuilder(buffer.toString());
        foto.append("Observaciones: ").append(gestor.getObservaciones().size()).append("\n");
        for (Observacion o : gestor.getObservaciones()) {
            foto.append("  ").append(o.getTipo()).append(" | ").append(o.getCantidadAfectada())
                .append(" | ").append(o.getDescripcion()).append(" | ").append(o.getFecha())
                .append(" | ").append(o.getEstado()).append(" | ").append(o.getFechaResolucion())
                .append("\n");
        }
        return foto.toString();
    }
}