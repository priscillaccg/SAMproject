package DAO;

import classe.Ferramenta;
import controle.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FerramentaDAO {

    public static Ferramenta inserir(Ferramenta f)
    {
        Ferramenta ferr = null;
        try {
            // Criação do insert
            String sql = "INSERT INTO configuracao(repositorio_doc, repositorio_ontologia) VALUES(?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
			
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, f.getRepositorio_doc());
            comando.setString(2, f.getRepositorio_ontologia());
            comando.executeUpdate();
	} catch(Exception e) {
            System.out.println(e.getMessage());
        }
	ferr = new Ferramenta(f.getRepositorio_doc(),f.getRepositorio_ontologia());
	return ferr;
    }
    
    public static Ferramenta buscar()
    {
	Ferramenta f = null;
	try {
            String sql =    "Select * from configuracao" ;
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste","org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()){
		f = new Ferramenta(rs.getString("repositorio_doc"),
                                    rs.getString("repositorio_ontologia"));
            }
            rs.close();
            comando.close();
            con.close();
	} catch(Exception e) {
            System.out.println(e.getMessage());
	}
	return f;
    }
 
    
}
