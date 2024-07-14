import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
public class ConsultaAPI {
    private Map<String, Double> tasasCambio = new HashMap<>();
    private String apiKey = "d3ae56ced23ad68a0e6ba0c6";
    private String url = "https://v6.exchangerate-api.com/v6/d3ae56ced23ad68a0e6ba0c6/latest/USD";
    private static final String[] MONEDAS_FILTRADAS = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

    public void actualizarTasas() {
        try {
            URI direccion = URI.create(url + "?access_key=" + apiKey);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");

                for (String moneda : MONEDAS_FILTRADAS) {
                    if (tasas.has(moneda)) {
                        double tasa = tasas.get(moneda).getAsDouble();
                        tasasCambio.put(moneda, tasa);
                    }
                }
            } else {
                throw new RuntimeException("Error HTTP: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar las tasas de cambio", e);
        }
    }
    public double convertir(double cantidad, String nombreMonedaOrigen, String nombreMonedaDestino) {
        if (tasasCambio.containsKey(nombreMonedaOrigen) && tasasCambio.containsKey(nombreMonedaDestino)) {
            double tasaOrigen = tasasCambio.get(nombreMonedaOrigen);
            double tasaDestino = tasasCambio.get(nombreMonedaDestino);
            double cantidadEnBase = cantidad / tasaOrigen;
            return cantidadEnBase * tasaDestino;
        } else {
            throw new IllegalArgumentException("Una de las monedas no está registrada en el conversor");
        }
    }
}
