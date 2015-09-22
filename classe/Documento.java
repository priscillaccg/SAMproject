package classe;

public class Documento {

    private String id;
    private String caminho_fisico;
    private String titulo;
    private String criador;
    private String assunto;
    private String descricao;
    private String data;
    private String idioma;
    private String fonte_public;
    private String nomeArq;
    private String dt_publicacao;
    private String end_web;

    public Documento(String caminho_fisico, String titulo, String criador, String assunto, String descricao, String data, String idioma, String fonte_public, String nomeArq, String dt_publicacao, String end_web) {
        this.caminho_fisico = caminho_fisico;
        this.titulo = titulo;
        this.criador = criador;
        this.assunto = assunto;
        this.descricao = descricao;
        this.data = data;
        this.idioma = idioma;
        this.fonte_public = fonte_public;
        this.dt_publicacao = dt_publicacao;
        this.end_web = end_web;
    }
    
    public Documento(String id, String caminho_fisico, String titulo, String criador, String assunto, String descricao, String data, String idioma, String fonte_public, String nomeArq, String dt_publicacao, String end_web) {
        this.id = id;
        this.caminho_fisico = caminho_fisico;
        this.titulo = titulo;
        this.criador = criador;
        this.assunto = assunto;
        this.descricao = descricao;
        this.data = data;
        this.idioma = idioma;
        this.fonte_public = fonte_public;
        this.dt_publicacao = dt_publicacao;
        this.end_web = end_web;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaminho_fisico() {
        return caminho_fisico;
    }

    public void setCaminho_fisico(String caminho_fisico) {
        this.caminho_fisico = caminho_fisico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFonte_public() {
        return fonte_public;
    }

    public void setFonte_public(String fonte_public) {
        this.fonte_public = fonte_public;
    }

    public String getNomeArq() {
        return nomeArq;
    }

    public void setNomeArq(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public String getDt_publicacao() {
        return dt_publicacao;
    }

    public void setDt_publicacao(String dt_publicacao) {
        this.dt_publicacao = dt_publicacao;
    }

    public String getEnd_web() {
        return end_web;
    }

    public void setEnd_web(String end_web) {
        this.end_web = end_web;
    }
}
