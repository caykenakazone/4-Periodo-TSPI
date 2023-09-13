public class Mensagem {
    private String tipoMensagem;
    private String numeroCartao;
    private String valorTransacao;
    private String horaLocalTransacao;
    private String dataTransacao;
    private String redeTransmissora;
    private String numeroCartaoContinuacao;
    private String formaPagamento;

    public Mensagem(String tipoMensagem, String numeroCartao, String valorTransacao,
                           String horaLocalTransacao, String dataTransacao, String redeTransmissora,
                           String numeroCartaoContinuacao, String formaPagamento) {
        this.tipoMensagem = tipoMensagem;
        this.numeroCartao = numeroCartao;
        this.valorTransacao = valorTransacao;
        this.horaLocalTransacao = horaLocalTransacao;
        this.dataTransacao = dataTransacao;
        this.redeTransmissora = redeTransmissora;
        this.numeroCartaoContinuacao = numeroCartaoContinuacao;
        this.formaPagamento = formaPagamento;
    }

    public String construirMensagem() {
        String mensagemCorpo = tipoMensagem +
                numeroCartao +
                valorTransacao +
                horaLocalTransacao +
                dataTransacao +
                redeTransmissora +
                numeroCartaoContinuacao +
                formaPagamento;

        String tamanhoCabecalho = String.format("%04d", mensagemCorpo.length());

        return tamanhoCabecalho + mensagemCorpo;
    }
}