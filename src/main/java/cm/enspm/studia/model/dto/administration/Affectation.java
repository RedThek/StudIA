package cm.enspm.studia.model.dto.administration;

import cm.enspm.studia.model.personnes.Employe;
import cm.enspm.studia.model.syllabus.Matiere;

public class Affectation {

    private int idAffectation;                     // Identifiant unique de l'affectation
    private Employe enseignant;   // Référence à l'enseignant
    private Matiere matiere;         // Référence à la matière
    private Classe classe;           // Référence à la classe

    public Affectation(int idAffectation, Employe enseignant, Matiere matiere, Classe classe) {
        this.idAffectation = idAffectation;
        this.enseignant = enseignant;
        this.matiere = matiere;
        this.classe = classe;
    }

    public int getIdAffectation() {
        return idAffectation;
    }

    public Employe getEnseignant() {
        return enseignant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setEnseignant(Employe enseignant) {
        this.enseignant = enseignant;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
}
