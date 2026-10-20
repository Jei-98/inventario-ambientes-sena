import java.util.HashMap;
import java.util.Map;

public class GestorInventario {

    private Map<String, Ambiente> ambientes = new HashMap<>();

    public boolean registrarAmbiente(Ambiente ambiente) {
        String clave = ambiente.getNombre().trim().toLowerCase();

        if (ambientes.containsKey(clave)) {
            return false;
        }

        ambientes.put(clave, ambiente);
        return true;
    }

    public Ambiente buscarAmbiente(String nombre) {
        return ambientes.get(nombre.trim().toLowerCase());
    }
}