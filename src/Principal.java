import java.io.IOException;
import java.util.Scanner;
public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaAPI consulta = new ConsultaAPI();
        try {
            consulta.actualizarTasas();
            System.out.println("********************************************** ");
            System.out.println(" ");
            System.out.println("Sea bienvenido al conversor de Monedas ");
            System.out.println("1) Dolar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dolar");
            System.out.println("3) Dolar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dolar");
            System.out.println("5) Dolar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dolar");
            System.out.println("7) Dólar =>>  Boliviano");
            System.out.println("8) Boliviano =>>  Dólar");
            System.out.println("9) Salir");
            System.out.println("Elija una opcion valida:");
            System.out.println("********************************************** ");
            int opcion = Integer.parseInt(teclado.nextLine());
            if (opcion == 9) {
                System.out.println("Saliendo...");
                return;
            }
            System.out.println("Ingrese la cantidad:");
            double cantidad = Double.parseDouble(teclado.nextLine());
            String monedaOrigen = null;
            String monedaDestino = null;
            switch (opcion) {
                case 1:
                    monedaOrigen = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaOrigen = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaOrigen = "USD";
                    monedaDestino = "BRL";
                    break;
                case 4:
                    monedaOrigen = "BRL";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaOrigen = "USD";
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaOrigen = "COP";
                    monedaDestino = "USD";
                    break;
                case 7:
                    monedaOrigen = "USD";
                    monedaDestino = "BOB";
                    break;
                case 8:
                    monedaOrigen = "BOB";
                    monedaDestino = "USD";
                    break;
                default:
                    System.out.println("Opción no válida.");
                    return;
            }
            double resultado = consulta.convertir(cantidad, monedaOrigen, monedaDestino);
            Conversion conversion = new Conversion(cantidad, monedaOrigen, resultado, monedaDestino);
            System.out.println(conversion);
            GeneradorDeArchivo generador = new GeneradorDeArchivo();
            generador.guardarJson(conversion);
        } catch (NumberFormatException e) {
            System.out.println("Número no válido: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            System.out.println("Finalizando la aplicación.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
