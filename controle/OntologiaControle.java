package controle;

import DAO.ClasseDAO;
import DAO.FerramentaDAO;
import DAO.OntologiaDAO;
import classe.Classe_Ont;
import classe.Ontologia;
import com.hp.hpl.jena.ontology.*;
import java.io.File;
import java.io.IOException;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.RDFS;
import java.io.InputStream;
import java.util.ArrayList;

public class OntologiaControle {

    private static String s_source;
    private static String ont1;
    private static String dest;
    FerramentaDAO ferr = new FerramentaDAO();

    public void CarregarOntologia(String uri, String caminho_fisico, String descricao, String idioma, String nm_arq, String tam_arq, String nm_ontologia, String id_pai) throws Exception {

        String arquivo = nm_arq;
        // usar origem default
        s_source = getDefaultSource(uri, arquivo);
        OntologiaDAO po = new OntologiaDAO();
        //Configurar destino
        String destino = FerramentaDAO.buscar().getRepositorio_ontologia() + "\\" + nm_arq;
        //Copiar ontologia para repositorio
        copiarRepositorio(new File(caminho_fisico), new File(destino));
        inserir(uri, caminho_fisico, descricao, idioma, nm_arq, tam_arq, nm_ontologia, id_pai);
    }

    /*
     * Responder o documento de origem padrão e configurar o gerenciador de documento
     * para que possamos encontrá-lo no sistema de arquivos
     *@return URI do documento fonte padrão
     */
    private static String getDefaultSource(String namespace, String arq) {
        ont1 = namespace;
        String cam = FerramentaDAO.buscar().getRepositorio_ontologia();
        cam = cam.replace("\\", "\\\\");
        String dest = "file:\\" + cam + "\\" + arq;
        System.out.println(dest);
        try {
            // Associar a URI a uma URL
            OntDocumentManager.getInstance().addAltEntry(ont1, dest);
            return ont1;
        } catch (Exception e) {
            System.err.println("Arquivo existente no repositório.");
        }
        return null;
    }

    public void copiarRepositorio(File origem, File destino) {
        //Guardar ontologia no repositório
        try {
            CopiaArquivo.copiar(origem, destino);
        } catch (IOException ex) {
            System.err.println("Falha na cópia do arquivo: " + ex.getMessage());
        }
    }

    public void inserir(String uri, String caminho, String desc, String idioma, String nm_arq, String tam_arq, String nome_ont, String id_pai) throws Exception {
        try {
            Ontologia o = new Ontologia(0, uri, caminho, desc, idioma, nm_arq, tam_arq, nome_ont, id_pai);

            o.setUri(uri);
            o.setCaminho_fisico(caminho);
            o.setDescricao(desc);
            o.setIdioma(idioma);
            o.setNome_arq(nm_arq);
            o.setTam_arq(tam_arq);
            o.setNome_ontologia(nome_ont);
            o.setId_pai(id_pai);

            o = OntologiaDAO.inserir(o);
            Integer id = OntologiaDAO.buscar().getId();
            o.setId(id);
            //ExtraiLabel("C://Users//Priscilla//Documents//NetBeansProjects//Extrai_label//src//extrai_label//", "EventOntology.owl", "EventOntology.txt");
            String rotulo = "";
            //Inserir na tabela de classes os labels 
            OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
            InputStream in = FileManager.get().open(caminho);
            Integer contador = 0;
            // read the RDF/XML file
            model.read(in, "");
            for (ExtendedIterator i = model.listNamedClasses(); i.hasNext();) {
                OntClass classe = (OntClass) i.next();
                if (classe.getLocalName() != null) {
                    Statement label = classe.getProperty(RDFS.label);
                    if (label != null) {

                        rotulo = label.getString();
                        Classe_Ont c = new Classe_Ont(o, classe.getLocalName().toString(), rotulo);
                        c.setOntologia(o);
                        c.setClasse(classe.getLocalName().toString());
                        c.setLabel(rotulo);
                        Classe_Ont cls = ClasseDAO.inserir(c);
                    }
                }
            }
            model.close();
        } catch (Exception e) {
            System.out.println("Erro na inclusão da ontologia." + e.getMessage());
        }
    }

    public void ExtraiLabel(String caminho, String ontologia, String nm_arq) {
        // create an empty model
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
        InputStream in = FileManager.get().open(caminho + ontologia);

        if (in == null) {
            throw new IllegalArgumentException("File: " + caminho + ontologia + " not found");
        }

        // read the RDF/XML file
        model.read(in, "");

        for (ExtendedIterator i = model.listNamedClasses(); i.hasNext();) {
            OntClass classe = (OntClass) i.next();
            System.out.println("Classe: " + classe.getLocalName().toString());
            if (classe.getLocalName() != null) {
                ExtendedIterator label = classe.listLabels(null);
                try {
                    //Escrevendo as descrições em um arquivo para montar o corpus
                    File arquivo = new File(caminho + nm_arq);
                    String texto = label.toList().toString();
                    if (!texto.equals("[]")) {
                        texto = texto.replace("[", "");
                        texto = texto.replace("]", "");
                        //Extrai_label.salvar(caminho + nm_arq, texto);
                        //Extrai_label.carregar(caminho + nm_arq);
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
        model.close();
    }

    public Ontologia buscarOntologia(String nome_ont) {
        Ontologia ontologia = null;
        ontologia = OntologiaDAO.buscarId(nome_ont);
        return ontologia;
    }

    public Ontologia buscarOntologiaPorId(int id_ont) {
        Ontologia ontologia = null;
        ontologia = OntologiaDAO.buscarPorId(id_ont);
        return ontologia;
    }

    public String buscarClasse(String label, int id_ontologia) {
        String classe = "";
        classe = OntologiaDAO.buscarClasse(label, id_ontologia);
        return classe;
    }

    public Ontologia buscarOntologiaPorCaminho(String caminho_fisico) {
        Ontologia ont = OntologiaDAO.buscarPorCaminho(caminho_fisico);
        return ont;
    }

    public ArrayList buscarOntologias() {
        ArrayList ontologias = OntologiaDAO.buscarOntologias();
        return ontologias;
    }

    public void insereModulo(String caminho_ontologia, String nm_modulo) throws Exception {
        Ontologia o = null;
        //Verificar as informações da ontologia pai
        o = buscarOntologiaPorCaminho(caminho_ontologia);
        //Inserir informações do módulo da tabela Ontologia  
        String arquivo = nm_modulo;
        // usar origem default
        s_source = getDefaultSource(o.getUri(), arquivo);
        OntologiaDAO po = new OntologiaDAO();
        //Configurar destino
        String destino = FerramentaDAO.buscar().getRepositorio_ontologia() + "\\" + nm_modulo + ".owl";
        //Copiar ontologia para repositorio
        copiarRepositorio(new File(caminho_ontologia), new File(destino));
        String pai = o.getId().toString();
        inserir(o.getUri(), caminho_ontologia, o.getDescricao(), o.getIdioma(), nm_modulo + ".owl", "0", o.getNome_ontologia(), pai);
    }
}
