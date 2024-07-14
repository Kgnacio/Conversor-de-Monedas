public class Conversion {
    private double cantidad;
    private String monedaOrigen;
    private double resultado;
    private String monedaDestino;

    public Conversion(double cantidad, String monedaOrigen, double resultado, String monedaDestino) {
        this.cantidad = cantidad;
        this.monedaOrigen = monedaOrigen;
        this.resultado = resultado;
        this.monedaDestino = monedaDestino;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public double getResultado() {
        return resultado;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s son %.2f %s", cantidad, monedaOrigen, resultado, monedaDestino);
    }
}
