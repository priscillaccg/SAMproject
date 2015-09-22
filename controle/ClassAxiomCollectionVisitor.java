package controle;

import java.net.URI;
import org.semanticweb.owl.util.OWLAxiomVisitorAdapter;
import org.semanticweb.owl.model.*;

import java.util.HashSet;
import java.util.Set;
import org.semanticweb.owl.vocab.OWLRDFVocabulary;

public class ClassAxiomCollectionVisitor extends OWLAxiomVisitorAdapter {

    private Set<OWLClass> markedClasses;
    private OWLDataFactory factory; //factory for creating new axiom, if neceessary
    private HashSet<OWLClassAxiom> classAxiomCollection = new HashSet<OWLClassAxiom>();  //collection of all the axioms to be copied
    private HashSet<OWLAnnotationAxiom> annotationAxiomCollection = new HashSet<OWLAnnotationAxiom>();  //collection of all the axioms to be copied
    private HashSet<OWLDeclarationAxiom> declarationAxiomCollection = new HashSet<OWLDeclarationAxiom>();
    private HashSet<OWLAnnotationAxiom> labelAxiomCollection = new HashSet<OWLAnnotationAxiom>();

    /** returns all the collected class axioms */
    public Set<OWLClassAxiom> getAxioms() {
        return classAxiomCollection;
    }

    /** returns all the collected declaration axioms */
    public Set<OWLDeclarationAxiom> getDeclarations() {
        return declarationAxiomCollection;
    }

    /** returns all the collected entity annotation axioms */
    public Set<OWLAnnotationAxiom> getAnnotations() {
        return annotationAxiomCollection;
    }

    /** returns all the collected entity annotation axioms */
    public Set<OWLAnnotationAxiom> getLabel(OWLOntology oldOntology) {
        for (OWLClass cls : markedClasses) {
            URI rdfsLabelIRI = OWLRDFVocabulary.RDFS_LABEL.getURI();
            Set<OWLClassAxiom> cAxioms = oldOntology.getAxioms(cls);
            for (OWLClassAxiom axiom : cAxioms) {
                for (OWLAnnotationAxiom a : cls.getAnnotationAxioms(oldOntology)) {
                    // only add an annotation assertion if it is an rdfs:label
                    // remove this condition to add ALL annotation assertions (e.g. comments, definitions, synonyms)
                    if (a.getAnnotation().getAnnotationURI().equals(rdfsLabelIRI)) {
                        String label = a.getAnnotation().toString();
                        int indice_abre = label.indexOf('(');
                        int indice_fecha = label.lastIndexOf(')');
                        String conteudo = label.substring(indice_abre + 1, indice_fecha);
                        labelAxiomCollection.add(a);
                    }
                }
            }
        }
        return labelAxiomCollection;
    }

    public ClassAxiomCollectionVisitor(Set<OWLClass> markedClasses, OWLDataFactory factory) {
        this.markedClasses = markedClasses;
        this.factory = factory;
    }

    public void visit(OWLSubClassAxiom axiom) {
        //always include the superclass/restriction

        classAxiomCollection.add(axiom);
        /*if (!axiom.getSuperClass().isAnonymous()) {
        if (markedClasses.contains(axiom.getSuperClass())) {
        classAxiomCollection.add(axiom);
        } else {
        axiom.getSuperClass()
        
        }*/
    }

    public void visit(OWLDisjointClassesAxiom axiom) {
        boolean containedInBoth = true;
        Set<OWLDescription> desc = axiom.getDescriptions();
        for (OWLDescription d : desc) {
            if ((!d.isAnonymous()) && (!markedClasses.contains(d))) {
                containedInBoth = false;
            }
        }

        if (containedInBoth) {
            classAxiomCollection.add(axiom);
        }
    }

    public void visit(OWLDisjointUnionAxiom axiom) {
        Set<OWLDescription> newAxiomDescription = new HashSet<OWLDescription>();

        Set<OWLDescription> desc = axiom.getDescriptions();
        for (OWLDescription d : desc) {
            if (!d.isAnonymous()) {
                if (markedClasses.contains(d.asOWLClass())) {
                    newAxiomDescription.add(d);
                }
            }
        }

        OWLDisjointUnionAxiom newAxiom = factory.getOWLDisjointUnionAxiom(axiom.getOWLClass(), newAxiomDescription);
        classAxiomCollection.add(newAxiom);
    }

    public void visit(OWLEntityAnnotationAxiom axiom) {
        annotationAxiomCollection.add(axiom);
    }

    public void visit(OWLEquivalentClassesAxiom axiom) {
        classAxiomCollection.add(axiom);
    }

    /** special method to handle declaration axioms, which may be necessary for some applications */
    public void visit(OWLDeclarationAxiom axiom) {
        declarationAxiomCollection.add(axiom);
    }

    String getLabels(OWLOntology oldOntology) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}