package classe;

public class Ferramenta {
    private String repositorio_doc;
    private String repositorio_ontologia;

    public Ferramenta(String repositorio_doc, String repositorio_ontologia) {
        this.repositorio_doc = repositorio_doc;
        this.repositorio_ontologia = repositorio_ontologia;
    }
    
    public String getRepositorio_doc() {
        return repositorio_doc;
    }

    public void setRepositorio_doc(String repositorio_doc) {
        this.repositorio_doc = repositorio_doc;
    }

    public String getRepositorio_ontologia() {
        return repositorio_ontologia;
    }

    public void setRepositorio_ontologia(String repositorio_ontologia) {
        this.repositorio_ontologia = repositorio_ontologia;
    }
    
}
