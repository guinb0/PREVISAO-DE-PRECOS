package entidades;

import java.util.Scanner;

public class Loja extends Thread {
    private String nome;
    private Conta contaLoja; // Adicionando uma conta para a loja
    private Funcionario[] funcionarios;
    private static final double SALARIO_FUNCIONARIO = 1400.0; // Valor do salário dos funcionários

    public Loja(String nome, Conta contaLoja, Funcionario[] funcionarios) {
        this.nome = nome;
        this.contaLoja = contaLoja;
        this.funcionarios = funcionarios;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Digite o valor das compras realizadas na " + nome + " (ou 0 para parar): ");
            double valorCompras = scanner.nextDouble();
            if (valorCompras == 0) {
                break; // Sai do loop se o valor for 0
            }
            synchronized (contaLoja) {
                contaLoja.depositar(valorCompras); // Recebe o pagamento das compras
                System.out.println("A " + nome + " recebeu um pagamento de R$ " + valorCompras);
            }
            // Paga os salários dos funcionários se houver saldo suficiente na conta
            if (contaLoja.getSaldo() >= SALARIO_FUNCIONARIO * funcionarios.length) {
                for (Funcionario funcionario : funcionarios) {
                    synchronized (contaLoja) {
                        contaLoja.sacar(SALARIO_FUNCIONARIO);
                        System.out.println("A " + nome + " pagou R$ " + SALARIO_FUNCIONARIO + " para " + funcionario.getName());
                    }
                }
            } else {
                System.out.println("Saldo insuficiente para pagar os salários dos funcionários.");
            }
        }
        scanner.close();
    }
}

