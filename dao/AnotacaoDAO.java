package DAO;

import classe.Anotacao;
import controle.Conexao;
import java.sql.*;

public class AnotacaoDAO {

    public static Anotacao inserir(Anotacao a) {
        Anotacao anot = null;
        //Pegar data atual
        java.util.Date today = new java.util.Date();
        java.sql.Date date = new java.sql.Date(today.getTime());
        try {
            // Criação do insert
            String sql = "INSERT INTO anotacao(id_termo, termo_papel, id_classe, classe_papel, linha_termo, id_usuario, data_anot, propriedade) "
                    + "VALUES(?,?,?,?,?,?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setInt(1, a.getTermo());
            comando.setString(2, a.getTermo_papel());
            comando.setInt(3, a.getClasse());
            comando.setString(4, a.getClasse_papel());
            comando.setString(5, a.getLinha());
            comando.setInt(6, a.getUsuario().getId());
            comando.setDate(7, date);
            comando.setString(8, a.getPropriedade());
            comando.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        anot = new Anotacao(a.getTermo(), a.getTermo_papel(),a.getClasse(), a.getClasse_papel(), a.getLinha(), a.getUsuario(),a.getPropriedade());
        return anot;
    }
    
    
}
