package util;

public class ConversorMoeda {
    private static final double TAXA_CONVERSAO = 5.2; // Exemplo de taxa de conversão (1 Real = 5.2 Dólares)

    public static double converterParaDolar(double valorReal) {
        return valorReal / TAXA_CONVERSAO;
    }

    public static double converterParaReal(double valorDolar) {
        return valorDolar * TAXA_CONVERSAO;
    }
}
