package classe;

public class Politica_Modularizacao {

    private String tp_modularizacao;
    private String qtd_palavras;
    private Documento documento;
    private Usuario usuario;

    public Politica_Modularizacao(Usuario usuario, Documento documento, String tp_modularizacao, String qtd_palavras) {
        this.tp_modularizacao = tp_modularizacao;
        this.qtd_palavras = qtd_palavras;
        this.usuario = usuario;
        this.documento = documento;
    }

    public String getTp_modularizacao() {
        return tp_modularizacao;
    }

    public void setTp_modularizacao(String tp_modularizacao) {
        this.tp_modularizacao = tp_modularizacao;
    }

    public String getQtd_palavras() {
        return qtd_palavras;
    }

    public void setQtd_palavras(String qtd_palavras) {
        this.qtd_palavras = qtd_palavras;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
