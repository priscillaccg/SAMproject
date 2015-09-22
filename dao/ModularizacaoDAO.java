package DAO;

import controle.Conexao;
import java.sql.*;
import java.util.ArrayList;

public class ModularizacaoDAO {
    // Olhar interesses DAO
    public static ArrayList buscarPorlabel(Integer id_ontologia, String label_procurado) {
        ArrayList classes = new ArrayList();
        try {
            String sql = "Select * from classe where id_ontologia = " + id_ontologia + 
                    " and (label like '%" + label_procurado + "%' or classe like '%" + label_procurado + "%')";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            java.sql.Statement comando = con.createStatement();
            ResultSet rs = (ResultSet) comando.executeQuery(sql);
            while (rs.next()) {
                classes.add(rs.getString("classe"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }
}
