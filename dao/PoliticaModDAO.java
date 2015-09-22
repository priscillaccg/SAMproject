/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classe.Politica_Modularizacao;
import controle.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Priscilla
 */
public class PoliticaModDAO {
    public static Politica_Modularizacao inserir(Politica_Modularizacao p)
    {
        Politica_Modularizacao pol = null;
        try {
            // Criação do insert
            String sql = "INSERT INTO politica_modularizacao VALUES(?,?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
			
            PreparedStatement comando = con.prepareStatement(sql);
                    //
            comando.setInt(1, p.getUsuario().getId());
            comando.setInt(2, Integer.parseInt(p.getDocumento().getId()));
            comando.setString(3, p.getTp_modularizacao());
            comando.setString(4, p.getQtd_palavras());
            comando.executeUpdate();
	} catch(Exception e) {
            System.out.println(e.getMessage());
        }
	pol = new Politica_Modularizacao(p.getUsuario(),p.getDocumento(),
                p.getTp_modularizacao(),p.getQtd_palavras());
	return pol;
    }
}
