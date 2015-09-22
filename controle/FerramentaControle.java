package controle;

import DAO.FerramentaDAO;
import classe.Ferramenta;

public class FerramentaControle {
    FerramentaDAO fdao = new FerramentaDAO();
    
    public void incluir(String docs, String ontos) throws Exception {
        Ferramenta f = new Ferramenta(docs, ontos);
        
        f.setRepositorio_doc(docs);
        f.setRepositorio_ontologia(ontos);
	FerramentaDAO.inserir(f);
        throw new Exception("Inclusão realizada com sucesso.");
    }
    
    public Ferramenta buscar(){
        Ferramenta ferr = null;
        ferr = fdao.buscar();
        return ferr;
    }
}
