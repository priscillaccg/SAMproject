package DAO;

import classe.Classe_Ont;
import controle.Conexao;

import java.sql.*;

public class ClasseDAO {

    public static Classe_Ont inserir(Classe_Ont c) {
        try {
            // Criação do insert
            String sql = "INSERT INTO classe(id_ontologia, classe, label)"
                    + "VALUES(?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            PreparedStatement comando = con.prepareStatement(sql);

            comando.setInt(1, c.getOntologia().getId());
            comando.setString(2, c.getClasse());
            comando.setString(3, c.getLabel());
            comando.executeUpdate();

            Classe_Ont clas = new Classe_Ont(c.getOntologia(), c.getClasse(), c.getLabel());
            comando.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public static Classe_Ont buscarClasse(Integer ontologia, String label) {
        Classe_Ont c = null;
        try {
            String sql = "Select * from classe where id_ontologia = " + ontologia + " and "
                    + "(label='" + label + "' or classe ='" + label + "')";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                c = new Classe_Ont(
                        OntologiaDAO.buscarPorId(rs.getInt("id_ontologia")),
                        rs.getString("classe"),
                        rs.getString("label"),
                        rs.getInt("id_classe"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c;
    }

    public static Classe_Ont buscarClassePorLabel(Integer ontologia, String label) {
        Classe_Ont c = null;
        try {
            String sql = "Select * from classe where id_ontologia = " + ontologia + " and ("
                    + "label='" + label + "' or classe='" + label + "')";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                c = new Classe_Ont(
                        OntologiaDAO.buscarPorId(rs.getInt("id_ontologia")),
                        rs.getString("classe"),
                        rs.getString("label"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}
