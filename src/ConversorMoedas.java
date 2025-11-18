public class ConversorMoedas {

    public static double converter(double valor, double taxaOrigem, double taxaDestino) {
        return valor * (taxaDestino / taxaOrigem);
    }
}
