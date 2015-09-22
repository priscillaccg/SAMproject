/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;

import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import java.io.*;

/**
 *
 * @author Priscilla
 */
public class Extrai_label {

    static final String ontologia = "EventOntology.owl";
    static final String caminho = "C://Users//Priscilla//Documents//NetBeansProjects//Extrai_label//src//extrai_label//";
    
    public static void salvar(String arquivo, String conteudo) throws IOException {
        FileWriter fw = new FileWriter(arquivo, true);
        conteudo += System.getProperty("line.separator");
        fw.write(conteudo);
        fw.close();
    }

    public static String carregar(String arquivo) throws FileNotFoundException, IOException {
        File file = new File(arquivo);
        if (!file.exists()) {
            return null;
        }
        BufferedReader br = new BufferedReader(new FileReader(arquivo));
        StringBuffer bufSaida = new StringBuffer();
        String linha;
        while ((linha = br.readLine()) != null) {
            bufSaida.append(linha + "\n");
        }
        br.close();
        return bufSaida.toString();
    }

    public static void main(String[] args) {
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
                    File arquivo = new File(caminho + "EventOntology.txt");
                    String texto = label.toList().toString();
                    if (!texto.equals("[]")) {
                        texto = texto.replace("[", "");
                        texto = texto.replace("]", "");
                        salvar(caminho + "EventOntology.txt", texto);
                        carregar(caminho + "EventOntology.txt");
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        }
    }
}