/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.UsuarioDAO;
import classe.Usuario;

public class UsuarioControle {
    UsuarioDAO udao = new UsuarioDAO();
    
    public void incluir(String login, String senha, String nome){
        try{
            Usuario u = new Usuario(login, senha, nome);
        
            u.setLogin(login);
            u.setSenha(senha);
            u.setNome(nome);
            udao.inserir(u);
        } catch(Exception e){
            System.out.println("Erro na inclusão do usuário");             
        }
    }

    public Usuario buscaUsuarioid(String login){
        Usuario usuario = null;
        usuario = udao.buscarPorLogin(login);
        return usuario;
    }

}
