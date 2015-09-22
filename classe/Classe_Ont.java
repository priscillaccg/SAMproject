package classe;

public class Classe_Ont {

    private Ontologia ontologia;
    private String classe;
    private String label;
    private int id_classe;

    public Classe_Ont(Ontologia ont, String classe, String label) {
        this.ontologia = ont;
        this.classe = classe;
        this.label = label;
    }
    
    public Classe_Ont(Ontologia ont, String classe, String label, int id_classe) {
        this.ontologia = ont;
        this.classe = classe;
        this.label = label;
        this.id_classe = id_classe;
    }

    public Ontologia getOntologia() {
        return ontologia;
    }

    public void setOntologia(Ontologia ontologia) {
        this.ontologia = ontologia;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }
}
