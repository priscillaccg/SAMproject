package classe;

public class Ontologia {

    private Integer id;
    private String uri;
    private String caminho_fisico;
    private String descricao;
    private String idioma;
    private String nome_arq;
    private String tam_arq;
    private String nome_ontologia;
    private String id_pai;

    public Ontologia(Integer cod, String uri, String caminho, String desc, String idioma, String nm_arq, String tam_arq, String nome_ont, String id_pai) {
        this.id = cod;
        this.nome_ontologia = nome_ont;
        this.caminho_fisico = caminho;
        this.descricao = desc;
        this.idioma = idioma;
        this.nome_arq = nm_arq;
        this.tam_arq = tam_arq;
        this.uri = uri;
        this.id_pai = id_pai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCaminho_fisico() {
        return caminho_fisico;
    }

    public void setCaminho_fisico(String caminho_fisico) {
        this.caminho_fisico = caminho_fisico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNome_arq() {
        return nome_arq;
    }

    public void setNome_arq(String nome_arq) {
        this.nome_arq = nome_arq;
    }

    public String getTam_arq() {
        return tam_arq;
    }

    public void setTam_arq(String tam_arq) {
        this.tam_arq = tam_arq;
    }

    public String getNome_ontologia() {
        return nome_ontologia;
    }

    public void setNome_ontologia(String nome_ontologia) {
        this.nome_ontologia = nome_ontologia;
    }

    public String getId_pai() {
        return id_pai;
    }

    public void setId_pai(String id_pai) {
        this.id_pai = id_pai;
    }
}
