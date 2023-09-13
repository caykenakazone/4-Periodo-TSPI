public class Cartao {
    private String numero;
    private String nomeDoCliente;
    private double saldo;

    public Cartao(String numero, String nomeDoCliente, double saldo) {
        this.numero = numero;
        this.nomeDoCliente = nomeDoCliente;
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public synchronized boolean realizarDebito(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }



}
