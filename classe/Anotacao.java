package classe;

import java.util.Date;

public class Anotacao {

    private int id_anotacao;
    private int termo; //id
    private String termo_papel;
    private int classe; //id
    private String classe_papel;
    private String linha; //linha do termo
    private Usuario usuario;
    private String propriedade;

    public Anotacao(int termo, String termo_papel, int classe, String classe_papel, String linha, Usuario usuario, String propriedade) {
        this.termo = termo;
        this.termo_papel = termo_papel;
        this.classe = classe;
        this.classe_papel = classe_papel;
        this.linha = linha;
        this.usuario = usuario;
        this.propriedade = propriedade;
    }

    public int getId_anotacao() {
        return id_anotacao;
    }

    public void setId_anotacao(int id_anotacao) {
        this.id_anotacao = id_anotacao;
    }

    public int getTermo() {
        return termo;
    }

    public void setTermo(int termo) {
        this.termo = termo;
    }

    public String getTermo_papel() {
        return termo_papel;
    }

    public void setTermo_papel(String termo_papel) {
        this.termo_papel = termo_papel;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getClasse_papel() {
        return classe_papel;
    }

    public void setClasse_papel(String classe_papel) {
        this.classe_papel = classe_papel;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

  
    
}
