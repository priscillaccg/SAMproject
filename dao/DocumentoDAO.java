package DAO;

import classe.Documento;
import controle.Conexao;

import java.sql.*;

public class DocumentoDAO {

    public static Documento inserir(Documento d) {
        Documento doc = null;
        try {
            // Criação do insert
            String sql = "INSERT INTO documento(caminho_fisico, titulo, criador, assunto, descricao, data, idioma, fonte_public, nome_arq, dt_publicacao, end_web) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            //Pegar data atual
            java.util.Date today = new java.util.Date();
            java.sql.Date date = new java.sql.Date(today.getTime());


            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, d.getCaminho_fisico());
            comando.setString(2, d.getTitulo());
            comando.setString(3, d.getCriador());
            comando.setString(4, d.getAssunto());
            comando.setString(5, d.getDescricao());
            comando.setDate(6, date);
            comando.setString(7, d.getIdioma());
            comando.setString(8, d.getFonte_public());
            comando.setString(9, d.getNomeArq());
            //Date data = (Date) controle.Util.formataData(d.getDt_publicacao());
            //comando.setDate(10, data);
            comando.setDate(10, date);
            comando.setString(11, d.getEnd_web());
            comando.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        doc = new Documento(d.getCaminho_fisico(), d.getTitulo(), d.getCriador(), d.getAssunto(), d.getDescricao(), d.getData(), d.getIdioma(), d.getFonte_public(), d.getNomeArq(), d.getDt_publicacao(), d.getEnd_web());
        return doc;
    }

    public static Documento buscarPorid(Integer id_doc) {
        Documento doc = null;
        try {
            String sql = "Select * from documento where id_doc = " + id_doc;
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                doc = new Documento(
                        rs.getString("id_doc"),
                        rs.getString("caminho_fisico"),
                        rs.getString("titulo"),
                        rs.getString("criador"),
                        rs.getString("assunto"),
                        rs.getString("descricao"),
                        rs.getString("data"),
                        rs.getString("idioma"),
                        rs.getString("fonte_public"),
                        rs.getString("nome_arq"),
                        rs.getString("dt_publicacao"),
                        rs.getString("end_web"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }

    public static Documento buscarId(String arquivo) {
        Documento doc = null;
        try {
            String sql = "Select * from documento where nome_arq = '" + arquivo + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                doc = new Documento(
                        rs.getString("id_doc"),
                        rs.getString("caminho_fisico"),
                        rs.getString("titulo"),
                        rs.getString("criador"),
                        rs.getString("assunto"),
                        rs.getString("descricao"),
                        rs.getString("data"),
                        rs.getString("idioma"),
                        rs.getString("fonte_public"),
                        rs.getString("nome_arq"),
                        rs.getString("dt_publicacao"),
                        rs.getString("end_web"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return doc;
    }
    //buscar quantidade total de documentos armazenados
    public static Integer buscarTotalDocs() {
        Integer total = 0;
        try {
            String sql = "Select count(id_doc) as total from documento";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
             if (rs.next()) {
                total = rs.getInt("total");
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return total;
    }
}
