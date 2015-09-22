package DAO;

import classe.Ontologia;
import controle.Conexao;
import java.sql.*;
import java.util.ArrayList;

public class OntologiaDAO {
    /* Inserir a ontologia no bd
    public void loadDB(ModelMaker maker, String source) {
    // Criar modelo persistente
    Model base = maker.createModel(source, false);
    
    // Abrir modelo criado anteriormente
    //Model prvModel = maker.openModel("AnExistingModel");
    
    // Associar modelo criado a ontologia
    OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_RULE_INF, base);
    
    // Carregar o arquivo da ontologia
    InputStream in = FileManager.get().open(source);
    if (in == null) {
    throw new IllegalArgumentException("File: " + source + " not found");
    }
    // Ler o arquivo
    m.read(in, "");
    }
    
    public ModelMaker getRDBMaker(String dbURL, String dbUser, String dbPw, String dbType, boolean cleanDB) {
    try {
    
    // Conectar ao Banco
    IDBConnection conn = new DBConnection(dbURL, dbUser, dbPw, dbType);
    // Retornar objeto de conexão criado
    return ModelFactory.createModelRDBMaker(conn);
    } catch (Exception e) {
    System.out.println(e.getMessage());
    System.exit(1);
    }
    
    return null;
    }
    
    public OntModelSpec getModelSpec(ModelMaker maker) {
    // Criar SPEC para acesso ao modelo
    OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM_RULE_INF);
    spec.setImportModelMaker(maker);
    return spec;
    }
     */
    
    public static Ontologia buscar() {
        Ontologia ont = null;
        String id = null;
        try {
            String sql = "Select max(id_ontologia) as id from ontologia";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                id = rs.getString("id");
            }
            rs.close();
            comando.close();
            con.close();
            
            sql = "Select * from ontologia where id_ontologia = " + id;
            con = conex.obterConexao();
            comando = con.createStatement();
            rs = comando.executeQuery(sql);
            if (rs.next()) {
                ont = new Ontologia(rs.getInt("id_ontologia"),
                        rs.getString("nome_ontologia"),
                        rs.getString("caminho_fisico"),
                        rs.getString("descricao"),
                        rs.getString("idioma"),
                        rs.getString("nome_arq"),
                        rs.getString("tam_arq"),
                        rs.getString("uri"),
                        rs.getString("id_pai"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ont;
    }
    
    public static Ontologia inserir(Ontologia o) {
        Ontologia ont = null;
        try {
            // Criação do insert
            String sql = "INSERT into ontologia (uri, caminho_fisico, "
                    + "descricao,idioma, nome_arq, tam_arq, nome_ontologia, id_pai)"
                    + " values (?,?,?,?,?,?,?,?)";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            PreparedStatement comando = con.prepareStatement(sql);
            comando.setString(1, o.getUri());
            comando.setString(2, o.getCaminho_fisico());
            comando.setString(3, o.getDescricao());
            comando.setString(4, o.getIdioma());
            comando.setString(5, o.getNome_arq());
            comando.setString(6, o.getTam_arq());
            comando.setString(7, o.getNome_ontologia());
            comando.setInt(8, Integer.parseInt(o.getId_pai()));
            comando.executeUpdate();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Buscar id e setar o atributo de id
        Integer id = OntologiaDAO.buscar().getId();
        
        ont = new Ontologia(id, o.getNome_ontologia(), o.getCaminho_fisico(), o.getDescricao(),
                o.getIdioma(), o.getNome_arq(), o.getTam_arq(), o.getUri(), o.getId_pai());
        return ont;
    }
    
    public static Ontologia buscarPorId(Integer id) {
        Ontologia ont = null;
        try {
            String sql = "Select * from ontologia where id_ontologia = " + id;
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                ont = new Ontologia(rs.getInt("id_ontologia"),
                        rs.getString("nome_ontologia"),
                        rs.getString("caminho_fisico"),
                        rs.getString("descricao"),
                        rs.getString("idioma"),
                        rs.getString("nome_arq"),
                        rs.getString("tam_arq"),
                        rs.getString("uri"),
                        rs.getString("id_pai"));
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ont;
    }
    
    public static Ontologia buscarId(String arquivo) {
        Ontologia ont = null;
        try {
            String sql = "Select * from ontologia where nome_arq = '" + arquivo + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                ont = new Ontologia(
                        rs.getInt("id_ontologia"),
                        rs.getString("nome_ontologia"),
                        rs.getString("caminho_fisico"),
                        rs.getString("descricao"),
                        rs.getString("idioma"),
                        rs.getString("nome_arq"),
                        rs.getString("tam_arq"),
                        rs.getString("uri"),
                        rs.getString("id_pai"));
            }
            
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ont;
    }
    
    public static String buscarClasse(String label, int id_ont) {
        String classe = "";
        try {
            String sql = "Select * from classe where label = '" + label + "' and id_ontologia = " + id_ont;
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                classe = rs.getString("label");
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return classe;
    }    
    
    public static ArrayList buscarOntologias() {
        ArrayList onts = new ArrayList();
        int ontol;
        try {
            String sql = "Select id_ontologia from ontologia";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            while (rs.next()) {
                ontol = rs.getInt("id_ontologia");
                onts.add(ontol);
            }
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return onts;
    }
    
    public static Ontologia buscarPorCaminho(String caminho_fisico) {
        Ontologia ont = null;
        caminho_fisico = caminho_fisico.replace("\\", "\\\\");
        try {
            String sql = "Select * from ontologia where caminho_fisico = '" + caminho_fisico + "'";
            Conexao conex = new Conexao("jdbc:postgresql://localhost:5432/teste", "org.postgresql.Driver", "postgres","123456");
            Connection con = conex.obterConexao();
            
            Statement comando = con.createStatement();
            ResultSet rs = comando.executeQuery(sql);
            if (rs.next()) {
                ont = new Ontologia(
                        rs.getInt("id_ontologia"),
                        rs.getString("nome_ontologia"),
                        rs.getString("caminho_fisico"),
                        rs.getString("descricao"),
                        rs.getString("idioma"),
                        rs.getString("nome_arq"),
                        rs.getString("tam_arq"),
                        rs.getString("uri"),
                        rs.getString("id_pai"));
            }
            
            rs.close();
            comando.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ont;
    }
}
