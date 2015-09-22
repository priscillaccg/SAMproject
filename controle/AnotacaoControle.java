/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.AnotacaoDAO;
import DAO.ClasseDAO;
import DAO.CorpusDAO;
import classe.Anotacao;
import classe.Classe_Ont;
import classe.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Priscilla
 */
public class AnotacaoControle {

    public void incluirAnotacao(int termo, String termo_papel, int classe, String classe_papel, String linha, Usuario u, String propriedade) {
        try {
            Anotacao a = new Anotacao(termo, termo_papel, classe, classe_papel, linha, u, propriedade);

            a.setTermo(termo);
            a.setTermo_papel(termo_papel);
            a.setClasse(classe);
            a.setClasse_papel(classe_papel);
            a.setLinha(linha);
            a.setUsuario(u);
            a.setPropriedade(propriedade);
            AnotacaoDAO.inserir(a);
        } catch (Exception e) {
            System.out.println("Erro na inclusão da anotação");
        }
    }
    
    public ArrayList identifica_relevantes(ArrayList rel) {
        ArrayList doc = new ArrayList();
        for (int i = 0; i < rel.size(); i++) {
            if (rel.get(i).toString().contains("<<") && rel.get(i).toString().contains(">>")) {
                //Encontrei a linha com a palavra anotada
                //5 linhas anteriores
                for (int k = 1; k <= 5; k++) {
                    int pos = i - k;
                    if (pos >= 0) {
                        String[] split = rel.get(pos).toString().split(" ");
                        for (int j = 0; j < split.length; j++) {
                            //Verificar se não são stopwords
                            String palavra = removeLixo(split[j].trim());
                            if (palavra.length() >= 2) {
                                if (stopWord(palavra.toLowerCase().trim()) == true) {
                                    doc.add(palavra.toLowerCase().trim());
                                }
                            }
                        }
                    }
                }
                //Listar termos das 5 linhas posteriores
                for (int k = 1; k <= 5; k++) {
                    int pos = i + k;
                    if (pos < rel.size()) {
                        String[] split = rel.get(pos).toString().split(" ");
                        for (int j = 0; j < split.length; j++) {
                            //Verificar se não são stopwords
                            String palavra = removeLixo(split[j].trim());
                            if (palavra.length() >= 2) {
                                if (stopWord(palavra.toLowerCase().trim()) == true) {
                                    doc.add(palavra.toLowerCase().trim());
                                }
                            }
                        }
                    }
                }
            }
        }
        return doc;
    }

    public int retornaClasse(String classe, int id_ont){
        int id_classe = 0;
        Classe_Ont classec = ClasseDAO.buscarClasse(id_ont, classe);    
        return classec.getId_classe();   
    }
    
    public int retornaTermo(String termo, int id_doc){
        int id_termo = 0;
        id_termo = CorpusDAO.buscarIdPorTermo(id_doc, termo);
        return id_termo;
    }
    
    public static boolean stopWord(String palavra) {
        //Stopwords
        ArrayList stopwords = new ArrayList();
        // By PubMed http://www.ncbi.nlm.nih.gov/books/NBK3827/table/pubmedhelp.T43/
        stopwords.add(0, "a");
        stopwords.add(1, "about");
        stopwords.add(2, "again");
        stopwords.add(3, "all");
        stopwords.add(4, "almost");
        stopwords.add(5, "also");
        stopwords.add(6, "although");
        stopwords.add(7, "always");
        stopwords.add(8, "among");
        stopwords.add(9, "an");
        stopwords.add(10, "and");
        stopwords.add(11, "another");
        stopwords.add(12, "any");
        stopwords.add(13, "are");
        stopwords.add(14, "as");
        stopwords.add(15, "at");
        stopwords.add(16, "be");
        stopwords.add(17, "because");
        stopwords.add(18, "been");
        stopwords.add(19, "before");
        stopwords.add(20, "being");
        stopwords.add(21, "between");
        stopwords.add(22, "both");
        stopwords.add(23, "but");
        stopwords.add(24, "by");
        stopwords.add(25, "can");
        stopwords.add(26, "could");
        stopwords.add(27, "did");
        stopwords.add(28, "do");
        stopwords.add(29, "does");
        stopwords.add(30, "done");
        stopwords.add(31, "due");
        stopwords.add(32, "during");
        stopwords.add(33, "each");
        stopwords.add(34, "either");
        stopwords.add(35, "enough");
        stopwords.add(36, "especially");
        stopwords.add(37, "etc");
        stopwords.add(38, "for");
        stopwords.add(39, "found");
        stopwords.add(40, "from");
        stopwords.add(41, "further");
        stopwords.add(42, "had");
        stopwords.add(43, "has");
        stopwords.add(44, "have");
        stopwords.add(45, "having");
        stopwords.add(46, "here");
        stopwords.add(47, "how");
        stopwords.add(48, "however");
        stopwords.add(49, "i");
        stopwords.add(50, "if");
        stopwords.add(51, "in");
        stopwords.add(52, "into");
        stopwords.add(53, "is");
        stopwords.add(54, "it");
        stopwords.add(55, "its");
        stopwords.add(56, "itself");
        stopwords.add(57, "just");
        stopwords.add(58, "kg");
        stopwords.add(59, "km");
        stopwords.add(60, "made");
        stopwords.add(61, "mainly");
        stopwords.add(62, "make");
        stopwords.add(63, "may");
        stopwords.add(64, "mg");
        stopwords.add(65, "might");
        stopwords.add(66, "ml");
        stopwords.add(67, "mm");
        stopwords.add(68, "most");
        stopwords.add(69, "mostly");
        stopwords.add(70, "must");
        stopwords.add(71, "nearly");
        stopwords.add(72, "neither");
        stopwords.add(73, "no");
        stopwords.add(74, "nor");
        stopwords.add(75, "obtained");
        stopwords.add(76, "of");
        stopwords.add(77, "often");
        stopwords.add(78, "on");
        stopwords.add(79, "our");
        stopwords.add(80, "overall");
        stopwords.add(81, "perhaps");
        stopwords.add(82, "pmid");
        stopwords.add(83, "quite");
        stopwords.add(84, "rather");
        stopwords.add(85, "really");
        stopwords.add(86, "regarding");
        stopwords.add(87, "seem");
        stopwords.add(88, "seen");
        stopwords.add(89, "several");
        stopwords.add(90, "should");
        stopwords.add(91, "show");
        stopwords.add(92, "showed");
        stopwords.add(93, "shown");
        stopwords.add(94, "shows");
        stopwords.add(95, "significantly");
        stopwords.add(96, "since");
        stopwords.add(97, "so");
        stopwords.add(98, "some");
        stopwords.add(99, "such");
        stopwords.add(100, "than");
        stopwords.add(101, "that");
        stopwords.add(102, "the");
        stopwords.add(103, "their");
        stopwords.add(104, "theirs");
        stopwords.add(105, "them");
        stopwords.add(106, "then");
        stopwords.add(107, "there");
        stopwords.add(108, "therefore");
        stopwords.add(109, "these");
        stopwords.add(110, "they");
        stopwords.add(111, "this");
        stopwords.add(112, "those");
        stopwords.add(113, "through");
        stopwords.add(114, "thus");
        stopwords.add(115, "to");
        stopwords.add(116, "upon");
        stopwords.add(117, "use");
        stopwords.add(118, "used");
        stopwords.add(119, "using");
        stopwords.add(120, "various");
        stopwords.add(121, "very");
        stopwords.add(122, "was");
        stopwords.add(123, "we");
        stopwords.add(124, "were");
        stopwords.add(125, "what");
        stopwords.add(126, "when");
        stopwords.add(127, "which");
        stopwords.add(128, "while");
        stopwords.add(129, "with");
        stopwords.add(130, "within");
        stopwords.add(131, "without");
        stopwords.add(132, "would");
        stopwords.add(133, "fig");
        stopwords.add(134, "et. al.");
        stopwords.add(135, "et");
        stopwords.add(136, "al");

        for (int i = 0; i < stopwords.size(); i++) {
            if (stopwords.get(i).toString().equals(palavra.toLowerCase().trim())) {
                //System.out.println("É stopword");
                return false;
            }
        }
        return true;
    }

    public static String removeLixo(String palavra) {
        palavra = palavra.replace(".", "");
        palavra = palavra.replace(",", "");
        palavra = palavra.replace("#", "");
        palavra = palavra.replace("(", "");
        palavra = palavra.replace(")", "");
        if (palavra.endsWith("-")) {
            palavra = palavra.replace("-", "");
        }
        return palavra;
    }
}
