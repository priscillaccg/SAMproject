/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.CorpusDAO;
import DAO.DocumentoDAO;
import classe.Documento;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.discursive.jccook.util.LogInit;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.store.FSDirectory;

public class CorpusControle {

    private static Logger logger = Logger.getLogger(CorpusControle.class);

    static {
        LogInit.init();
    }

    public void calcularCorpus() throws IOException {
        //fileDir is the directory that contains the text files to be indexed
        //Repositório de documentos
        FerramentaControle f = new FerramentaControle();
        String repositorio_doc = f.buscar().getRepositorio_doc();
        File fileDir = new File(repositorio_doc);

        //indexDir is the directory that hosts Lucene's index files
        String indexDir = "C:\\testes\\docs\\LuceneIndex";
        FSDirectory dir = FSDirectory.getDirectory(new File(indexDir), true);
        IndexWriter indexWriter = new IndexWriter(dir, new StandardAnalyzer(), true);
        File[] textFiles = fileDir.listFiles();
        //Adicionar documentos ao index
        for (int i = 0; i < textFiles.length; i++) {
            if (textFiles[i].isFile() && textFiles[i].getName().endsWith(".txt")) {
                Reader textReader = new FileReader(textFiles[i]);
                Document doc = new Document();
                doc.add(new Field("path", textFiles[i].getPath(), Store.YES, Index.UN_TOKENIZED));
                doc.add(new Field("content", textReader, TermVector.WITH_POSITIONS_OFFSETS));
                indexWriter.addDocument(doc);
            }
        }
        indexWriter.optimize();
        indexWriter.close();
        //Arquivos textos indexados

        //Abrir o index gerado a partir da leitura
        IndexReader reader = IndexReader.open(dir);

        //Definição de Threshold
        logger.info("Threshold is indexreader.maxDoc() / 10;");
        Integer threshold = new Integer(0);

        IndexSearcher searcher = new IndexSearcher(dir);

        //Número total de documentos
        double num_docs = reader.numDocs();
        DecimalFormat df = new DecimalFormat("##.000");
        df.setMaximumFractionDigits(3);
        double tf, idf = 0;

        for (int i = 0; i < num_docs; i++) {
            Document doc = reader.document(i);

            int lastDot = doc.toString().lastIndexOf("\\");
            String arquivo_nm = doc.toString().substring(lastDot, doc.toString().length());
            arquivo_nm = arquivo_nm.replace(">>", "");
            arquivo_nm = arquivo_nm.replace("\\", "");
            Date data = new Date();
            DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
            DocumentoControle docu = new DocumentoControle();
            Documento docum = docu.buscarDocumento(arquivo_nm);
            String id_doc = docum.getId();
            //Insere os termos e o doc
            TermFreqVector vector = reader.getTermFreqVector(i, "content");
            //Cria um array de termos de um determinado documento
            String[] terms = vector.getTerms();
            //Cria um array de frequencia dos termos de um determinado documento
            int[] frequencies = vector.getTermFrequencies();
            double total_termos = 0;
            for (int k = 0; k < frequencies.length; k++) {
                total_termos += frequencies[k];
            }
            int documento = Integer.parseInt(id_doc);

            //Varre os termos de um determinado documento
            for (int j = 0; j < terms.length; j++) {
                //Frequencia do termo no referido documento
                if ((frequencies[j] >= threshold.intValue()) && (AnotacaoControle.stopWord(terms[j]) == true) && (verificaNumero(terms[j]) == true)) {
                    // Insere na tabela corpus o termos e suas respectivas frequencias
                    CorpusDAO.inserir(documento, terms[j].replace("'", ""), frequencies[j]);
                    // Calcular o TF
                    // Quantas vezes o termo aparece no texto/Total de palavras do texto
                    //Buscar qnt de repetições do termo no texto
                    double freq = CorpusDAO.buscarRepeticoes(terms[j], documento);
                    tf = freq / total_termos;

                    //Atualizar o valor de tf para cada termo do documento
                    CorpusDAO.atualizar(documento, terms[j], tf);
                }
            }
        }

        // Calcular o IDF
        //número de documentos que contém o termo e total de documentos da coleção que esse termo aparece

        HashMap<String, Integer> dados = (HashMap<String, Integer>) CorpusDAO.buscarQntDocs();
        Set<String> keys = dados.keySet();

        //Verificar o número total de documentos no bd
        int qnt_docs = DocumentoDAO.buscarTotalDocs();

        //Ler o map e para cada termo calcular o idf. Em seguida salvar no bd 
        double t = 0;
        for (String key : keys) {
            t = dados.get(key);
            idf = (Math.log10(qnt_docs/t))+1;

            //Atualizar idf
            CorpusDAO.atualizaridf(key, idf);
        }
        //Fazer cálculo de tf-idf = tf x idf
        CorpusDAO.calculo();
        reader.close();
    }

     public static boolean verificaNumero(String termo) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher match = pattern.matcher(termo);
        if (match.find()) {
            //System.out.println("É número");
            return false;
        }
        return true;
    }
}
