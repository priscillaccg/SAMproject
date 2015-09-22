package DAO;

import controle.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CorpusDAO {

    public static void inserir(int doc, String termo, int frequencia) {
        try {
            //Inserção do termo lido no documento
            String termo_ok = "";
            termo_ok = termo.trim();
            if (termo.contains("'")) {
                termo_ok = termo.replace("'", "");
            }
         
            // Criação do insert
            String sql = "INSERT INTO corpus(termo, freq_doc, id_doc)"
                    + "VALUES(?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver","postgres","123456");
            Connection con = conex.obterConexao();

            PreparedStatement comando = con.prepareStatement(sql);

            comando.setString(1, termo_ok);
            comando.setInt(2, frequencia);
            comando.setInt(3, doc);
            comando.executeUpdate();
            comando.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //buscar quantidade de repetições do termo no texto
    public static int buscarRepeticoes(String termo, int doc) {
        int qnt = 0;
        try {
            String sql = "Select freq_doc from corpus where termo = '" + termo + "' and id_doc = '" + doc + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                qnt = rs.getInt("freq_doc");
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return qnt;
    }

    //buscar quantidade de termos no doc
    public static int buscarqtdTermos(int doc) {
        int qnt = 0;
        try {
            String sql = "Select sum(freq_doc) as total from corpus where id_doc = '" + doc + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                qnt = rs.getInt("total");
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return qnt;
    }

    public static void atualizar(int doc, String termo, double tf) {
        try {
            //Inserção do termo lido no documento
            double tef = tf;
            // Criação do insert
            String sql = "UPDATE corpus set tf = ? where termo = '" + termo + "' and id_doc = '" + doc + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            PreparedStatement comando = con.prepareStatement(sql);

            comando.setDouble(1, tf);
            comando.executeUpdate();

            comando.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void atualizaridf(String termo, double idf) {
        try {
            //Inserção do termo lido no documento

            // Criação do insert
            String sql = "UPDATE corpus set idf = ? where termo = '" + termo + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            PreparedStatement comando = con.prepareStatement(sql);

            comando.setDouble(1, idf);
            comando.executeUpdate();

            comando.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //buscar quantidade documentos que contem o termo
    public static Map buscarQntDocs() {
        Map<String, Integer> mymap = new HashMap<String, Integer>();
        try {
            String sql = "Select count(id_doc) as total, termo from corpus group by termo";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                mymap.put(rs.getString("termo"), rs.getInt("total"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mymap;
    }

      
    //calcular TF-IDF
    public static void calculo() {
        try {
            String sql = "Update corpus set tf_idf = (tf * idf) ";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //listar os termos mais relevantes
    public static ArrayList termosRelevantes(ArrayList lista) {
        //O parâmetro é um arraylist com a lista dos termos relevantes para o documento atual
        //Limitei a 5 termos relevantes
        ArrayList<String> termos = new ArrayList();
        try {
            String sql = "Select distinct termo, idf from corpus "
                    + "where termo =";
            for (int i = 0; i < lista.size(); i++) {
                if (sql.endsWith("=")) {
                    sql += "'" + lista.get(i).toString() + "'";
                } else {
                    sql += " or termo ='" + lista.get(i).toString() + "'";
                }        
            }
            sql += " order by idf desc limit 50";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                termos.add(rs.getString("termo"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return termos;
    }
    
    public static int buscarIdPorTermo(Integer documento, String termo) {
        int c = 0;
        try {
            String sql = "Select * from corpus where id_doc = " + documento + " and termo='" + termo.trim() + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();

            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                c= rs.getInt("id_termo");
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
