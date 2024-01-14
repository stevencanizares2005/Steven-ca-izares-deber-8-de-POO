import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CitasAleatorias {
    private List<String> citas;
    private Random random;

    public CitasAleatorias() {
        random = new Random();
        cargarCitasDesdeArchivo("citas.txt");
    }

    private void cargarCitasDesdeArchivo(String nombreArchivo) {
        citas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(nombreArchivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                citas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtenerCitaAleatoria() {
        if (!citas.isEmpty()) {
            return citas.get(random.nextInt(citas.size()));
        } else {
            return "No hay citas disponibles";
        }
    }
}

