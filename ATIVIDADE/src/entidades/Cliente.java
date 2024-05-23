package entidades;

import java.util.Random;
import java.util.Scanner;

public class Cliente extends Thread {
    private Conta conta;
    private static final double SALDO_INICIAL = 1000.0; // Saldo inicial da conta do cliente
    private static final double[] VALORES_COMPRA = {100.0, 200.0}; // Valores possíveis das compras
    private static final Random random = new Random();

    public Cliente(String nome, Conta conta) {
        super(nome);
        this.conta = conta;
        this.conta.depositar(SALDO_INICIAL); // Deposita o saldo inicial na conta
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (conta.getSaldo() > 0) {
            double valorCompra = VALORES_COMPRA[random.nextInt(VALORES_COMPRA.length)]; // Escolhe um valor de compra aleatoriamente
            synchronized (conta) {
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra); // Realiza a compra
                    System.out.println(this.getName() + " realizou uma compra de R$ " + valorCompra + ". Saldo restante: R$ " + conta.getSaldo());
                } else {
                    System.out.println(this.getName() + " não possui saldo suficiente para realizar mais compras.");
                    break;
                }
            }
            try {
                Thread.sleep(100); // Aguarda um curto intervalo entre as compras
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}

