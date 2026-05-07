package cm.enspm.studia.model.examens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cm.enspm.studia.model.administration.Classe;
import cm.enspm.studia.model.administration.Salle;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.personnes.Employe;
import cm.enspm.studia.model.syllabus.Matiere;

/**
 * Classe représentant une note attribuée à un élève dans une matière.
 */
public class Evaluation {

    /**
     * 
     */
    private Set<Trimestre> trimestre;

    /**
     * 
     */
    private Set<Salle> salles;

    /**
     * 
     */
    private Set<Employe> surveillants;

    /**
     * 
     */
    private Set<Classe> classes;

    /**
     * 
     */
    private Set<Double> notes;

    /**
     * 
     */
    private Set<String> commentaire;

    public Evaluation(Set<Trimestre> trimestre, Set<Salle> salles,
                    Set<Employe> surveillants, Set<Classe> classes,
                    Set<Double> notes, Set<String> commentaire) {
        this.trimestre = trimestre;
        this.salles = salles;
        this.surveillants = surveillants;
        this.classes = classes;
        this.notes = notes;
        this.commentaire = commentaire;
    }

    public Set<Trimestre> getTrimestre() {
        return trimestre;
    }

    public Set<Salle> getSalles() {
        return salles;
    }

    public Set<Employe> getSurveillants() {
        return surveillants;
    }

    public Set<Classe> getClasses() {
        return classes;
    }

    public Set<Double> getNotes() {
        return notes;
    }

    public Set<String> getCommentaire() {
        return commentaire;
    }

    public void setTrimestre(Set<Trimestre> trimestre) {
        this.trimestre = trimestre;
    }

    public void setSalles(Set<Salle> salles) {
        this.salles = salles;
    }

    public void setSurveillants(Set<Employe> surveillants) {
        this.surveillants = surveillants;
    }

    public void setClasses(Set<Classe> classes) {
        this.classes = classes;
    }

    public void setNotes(Set<Double> notes) {
        this.notes = notes;
    }

    public void setCommentaire(Set<String> commentaire) {
        this.commentaire = commentaire;
    }
    
    /**
     * @return
     */
    public List<Evaluation> getEmploiDeTempsClasse() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Evaluation> getEmploiDeTempsEnseignant() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Evaluation> getEmploiDeTempsSalle() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getProgressionExamens() {
        // TODO implement here
        return "";
    }

    public List<Eleve> getListeEleves(Classe cle){

        List<Eleve> liste = new ArrayList<Eleve>();

        for (Classe classe : classes) {
            if (classe.getEleves().equals(cle)) {
                liste = classe.getEleves();
            }
        };

        return liste;
    }

    public Eleve getUniqueEleve(Eleve cle){

        Eleve eleve = null;
        int index = 0;
        for (Classe classe : this.classes) {
            if (classe.getEleves().contains(cle)) {
                index = classe.getEleves().indexOf(cle);
                eleve = classe.getEleves().get(index);
            }
        };

        return eleve;
    }



    public List<Matiere> getListeMatieres(Classe cle){

        List<Matiere> matieres = new ArrayList<Matiere>();
        
        for (Classe classe : classes) {
            if (classe.getNiveauEtude().equals(cle.getNiveauEtude())) {
                matieres = classe.getNiveauEtude().getMatieres();
            }
        };

        return matieres;
    }


    public Matiere getUniqueMatiere(Matiere cle){
        
        Matiere matiere = null;
        int index = 0;
        for (Classe classe : this.classes) {
            if (classe.getNiveauEtude().getMatieres().contains(matiere)) {
                index = classe.getNiveauEtude().getMatieres().indexOf(matiere);
                matiere = classe.getNiveauEtude().getMatieres().get(index);
            }
        };

        return matiere;
    }

    public Map<String, Double> enregistrerNotesEleve(List<Matiere> matieres, List<Double> notes){
        
        Map<String, Double> pv = new HashMap<>();

        int max = 0;
        if (matieres.size() >= notes.size()) {
            max = matieres.size();
        }

        for (int i = 0; i < max; i++) {
            pv.put(matieres.get(i).getDesignation(), notes.get(i));
        }

        return pv;
    }

    public Map<Eleve, Map<String, Double>> enregistrerNotesEleves(List<Eleve> eleves, Map<String, Double> PV) {
        int max = eleves.size();
        Map<Eleve, Map<String, Double>> pv = new HashMap<>();
        for (int i = 0; i < max; i++) {
            pv.put(eleves.get(i), PV);
        }
        return pv;
    }

    public Map<String,String> enregistrerObservationsEleve(List<Matiere> matieres, List<String> observations){
        
       Map<String,String> pv = new HashMap<>();

        int max = 0;
        if (matieres.size() >= observations.size()) {
            max = matieres.size();
        }

        for (int i = 0; i < max; i++) {
            pv.put(matieres.get(i).getDesignation(), observations.get(i));
        }

        return pv;
    }

    public List<Double> getListeNotes(Eleve cle, Sequence sequence){
        List<Double> notes = new ArrayList<Double>();
        return notes;
    }

    public double getUniqueNote(Eleve cle, List<Double> notes){
        double note = 0;
        return note;
    }
    
}