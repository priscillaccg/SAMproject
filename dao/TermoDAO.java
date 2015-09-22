package DAO;

import classe.Termo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controle.Conexao;

public class TermoDAO 
{
    public static Termo inserir(String descricao)
    {
        Termo termo = null;
        try {
            // Criação do insert
            String sql = "INSERT INTO termo(descricao) VALUES(?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
			
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, descricao);
            comando.executeUpdate();
	} catch(Exception e) {
            System.out.println(e.getMessage());
        }
	termo = new Termo(descricao);
	return termo;
    }
	
    public static Termo buscarPorId(int id)
    {
	Termo termo = null;
	try{
            // Select
            String sql =  "Select * from termo where id = " + id;
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);

            if (rs.next()){
                    termo = new Termo(rs.getString("descricao"));
            }

            rs.close();
            comando.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return termo;
    }

}