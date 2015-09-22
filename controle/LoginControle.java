/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.UsuarioDAO;
import classe.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Pity
 */
public class LoginControle {
    
    public Usuario validarLogin(String login, String senha){
        Usuario usuario = (Usuario) UsuarioDAO.buscarPorLoginSenha(login, senha);
        if (usuario== null) {   
            JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Erro no Acesso", JOptionPane.INFORMATION_MESSAGE);
        } else {   
         System.out.println("Usuário logado");   
      }  
        return usuario;
    }
}
