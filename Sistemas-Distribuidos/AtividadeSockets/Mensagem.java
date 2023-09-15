public class Mensagem {
    private String tipoMensagem;
    private String valorTransacao;
    private String horaLocalTransacao;
    private String dataTransacao;
    private String redeTransmissora;
    private String numeroCartao;
    private String formaPagamento;

    public Mensagem(String tipoMensagem, String valorTransacao, String horaLocalTransacao, String dataTransacao, String redeTransmissora, String numeroCartao, String formaPagamento) {
        this.tipoMensagem = tipoMensagem;
        this.valorTransacao = valorTransacao;
        this.horaLocalTransacao = horaLocalTransacao;
        this.dataTransacao = dataTransacao;
        this.redeTransmissora = redeTransmissora;
        this.numeroCartao = numeroCartao;
        this.formaPagamento = formaPagamento;
    }

    public String construirMensagem() {
        String mensagemCorpo = tipoMensagem +
                valorTransacao +
                horaLocalTransacao +
                dataTransacao +
                redeTransmissora +
                numeroCartao +
                formaPagamento;
        return mensagemCorpo;
    }
}