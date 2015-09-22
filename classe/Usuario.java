/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

/**
 *
 * @author Priscilla
 */
public class Usuario {

    private String nome;
    private String login;
    private String senha;
    private Integer id;

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario(String login, String senha, String nome, Integer id) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
