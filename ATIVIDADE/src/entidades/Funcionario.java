package entidades;

import java.util.Scanner;

public class Funcionario extends Thread {
    private Conta contaLoja;
    private Conta contaInvestimento;
    private static final double PERCENTUAL_INVESTIMENTO = 0.20; // 20% do salário será investido
    private static final double SALARIO_FUNCIONARIO = 1400.0; // Valor do salário dos funcionários

    public Funcionario(String nome, Conta contaLoja, Conta contaInvestimento) {
        super(nome);
        this.contaLoja = contaLoja;
        this.contaInvestimento = contaInvestimento;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            synchronized (contaLoja) {
                if (contaLoja.getSaldo() >= SALARIO_FUNCIONARIO) {
                    contaLoja.sacar(SALARIO_FUNCIONARIO); // Recebe o salário da loja
                    double valorInvestimento = SALARIO_FUNCIONARIO * PERCENTUAL_INVESTIMENTO;
                    synchronized (contaInvestimento) {
                        contaInvestimento.depositar(valorInvestimento); // Investe 20% do salário na conta de investimento
                        System.out.println(this.getName() + " investiu R$ " + valorInvestimento + " na conta de investimentos.");
                    }
                    System.out.println(this.getName() + " recebeu um salário de R$ " + SALARIO_FUNCIONARIO + " da loja.");
                } else {
                    System.out.println("Salário insuficiente para " + this.getName() + " neste momento.");
                }
            }
            try {
                Thread.sleep(500); // Aguarda um curto intervalo entre os pagamentos de salário
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


