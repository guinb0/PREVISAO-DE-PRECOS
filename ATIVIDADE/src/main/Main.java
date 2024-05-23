package main;

import entidades.*;

public class Main {
    public static void main(String[] args) {
        Conta conta1 = new Conta(1000); // Saldo inicial da conta 1: R$ 1000
        Conta conta2 = new Conta(2000); // Saldo inicial da conta 2: R$ 2000

        Cliente cliente1 = new Cliente("Cliente 1", conta1);
        Cliente cliente2 = new Cliente("Cliente 2", conta2);

        Funcionario funcionario1 = new Funcionario("Funcionário 1", conta1, new Conta(0)); // Funcionário 1 não possui conta de investimentos inicialmente
        Funcionario funcionario2 = new Funcionario("Funcionário 2", conta1, new Conta(0)); // Funcionário 2 não possui conta de investimentos inicialmente

        

        Loja loja = new Loja("Minha Loja", new Conta(0), new Funcionario[]{funcionario1, funcionario2}); // Loja inicialmente sem saldo

        Banco banco = new Banco();

        cliente1.start();
        cliente2.start();
        funcionario1.start();
        funcionario2.start();
        
        loja.start();

        try {
            cliente1.join();
            cliente2.join();
            funcionario1.join();
            funcionario2.join();
            
            loja.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibir saldos finais das contas
        banco.exibirSaldoFinal(conta1);
        banco.exibirSaldoFinal(conta2);
        
    }
}

