package entidades;

public class Banco {
    public synchronized void transferir(Conta origem, Conta destino, double valor) {
        synchronized (origem) {
            synchronized (destino) {
                if (origem.getSaldo() >= valor) {
                    origem.sacar(valor);
                    destino.depositar(valor);
                    System.out.println("Transferência de R$ " + valor + " realizada com sucesso de " + origem + " para " + destino);
                } else {
                    System.out.println("Saldo insuficiente para transferir R$ " + valor + " de " + origem + " para " + destino);
                }
            }
        }
    }

    public synchronized void exibirSaldoFinal(Conta conta) {
        System.out.println("Saldo final da conta " + conta + ": R$ " + conta.getSaldo());
    }
}

