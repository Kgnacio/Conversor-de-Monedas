import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
public class GeneradorDeArchivo {
    public void guardarJson(Conversion conversion) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter(conversion.getMonedaOrigen() + "_a_" + conversion.getMonedaDestino() + ".json");
        escritura.write(gson.toJson(conversion));
        escritura.close();
    }
}
