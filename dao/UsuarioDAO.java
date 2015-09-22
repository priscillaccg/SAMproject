package DAO;


import classe.SingletonUtil;
import classe.Usuario;
import controle.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    public Usuario inserir(Usuario u)
    {
        Usuario usuario = null;
        try {
            // Criação do insert
            String sql = "INSERT INTO usuario(login, senha, nome) VALUES(?,?,?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
			
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, u.getLogin());
            comando.setString(2, u.getSenha());
            comando.setString(3, u.getNome());
            comando.executeUpdate();
	} catch(Exception e) {
            System.out.println(e.getMessage());
        }
	usuario = new Usuario(u.getLogin(), u.getSenha(), u.getNome());
	return usuario;
    }

    public static Usuario buscarPorLoginSenha(String login, String senha)
    {
	Usuario usuario = null;
	try {
            String sql =    "Select * from usuario where login = ? and senha = ?";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, login);
            comando.setString(2, senha);
            ResultSet rs = comando.executeQuery();
            if (rs.next()){
		usuario = new Usuario(rs.getString("login"),rs.getString("senha"),rs.getString("nome"),
                         rs.getInt("id_usuario"));
            }
            rs.close();
            comando.close();
            con.close();
	} catch(Exception e) {
            System.out.println(e.getMessage());
	}
        SingletonUtil.getInstance().setUsuario(usuario);
	return usuario;
    }

    public Usuario buscarPorLogin(String login)
    {
	Usuario usuario = null;
	try {
            String sql =    "Select * from usuario where login = ?";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, login);
            ResultSet rs = comando.executeQuery();
            if (rs.next()){
		usuario = new Usuario(rs.getString("login"),rs.getString("senha"),rs.getString("nome"),
                        rs.getInt("id_usuario"));
            }
            rs.close();
            comando.close();
            con.close();
	} catch(Exception e) {
            System.out.println(e.getMessage());
	}
	return usuario;
    }
   
}   
