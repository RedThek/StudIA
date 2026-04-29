package modeles;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un élève de l'école secondaire.
 * Un élève est inscrit dans une classe et peut avoir des notes dans différentes matières.
 */
public class Eleve extends Personne {

    private String numeroMatricule;
    private String classeActuelle;       // Ex: "3ème A", "Terminale B"
    private String nomParent;
    private String telephoneParent;
    private List<Note> notes;            // Liste des notes de l'élève

    /**
     * Constructeur de la classe Eleve.
     */
    public Eleve(String identifiant, String nom, String prenom, String dateNaissance,
                 String adresse, String telephone, String email,
                 String numeroMatricule, String classeActuelle,
                 String nomParent, String telephoneParent) {
        super(identifiant, nom, prenom, dateNaissance, adresse, telephone, email);
        this.numeroMatricule  = numeroMatricule;
        this.classeActuelle   = classeActuelle;
        this.nomParent        = nomParent;
        this.telephoneParent  = telephoneParent;
        this.notes            = new ArrayList<>();
    }

    // ----------------------------- Getters / Setters -----------------------------

    public String getNumeroMatricule()           { return numeroMatricule; }
    public String getClasseActuelle()            { return classeActuelle; }
    public String getNomParent()                 { return nomParent; }
    public String getTelephoneParent()           { return telephoneParent; }
    public List<Note> getNotes()                 { return notes; }

    public void setClasseActuelle(String classe) { this.classeActuelle = classe; }
    public void setNomParent(String nomParent)   { this.nomParent = nomParent; }
    public void setTelephoneParent(String tel)   { this.telephoneParent = tel; }

    // ----------------------------- Gestion des notes -----------------------------

    /**
     * Ajoute une note à la liste des notes de l'élève.
     */
    public void ajouterNote(Note note) {
        notes.add(note);
    }

    /**
     * Supprime une note selon la matière et le type d'évaluation.
     * @return true si la note a été trouvée et supprimée
     */
    public boolean supprimerNote(String matiereId, String typeEvaluation) {
        return notes.removeIf(n ->
                n.getMatiereId().equals(matiereId) &&
                n.getTypeEvaluation().equals(typeEvaluation));
    }

    /**
     * Calcule la moyenne générale de l'élève sur toutes ses notes.
     * @return la moyenne, ou 0 si aucune note enregistrée
     */
    public double calculerMoyenneGenerale() {
        if (notes.isEmpty()) return 0.0;

        double somme = 0;
        for (Note note : notes) {
            somme += note.getValeur();
        }
        return somme / notes.size();
    }

    /**
     * Calcule la moyenne de l'élève pour une matière donnée.
     * @param matiereId identifiant de la matière
     * @return la moyenne dans la matière, ou -1 si aucune note
     */
    public double calculerMoyenneParMatiere(String matiereId) {
        List<Note> notesMatiere = new ArrayList<>();
        for (Note note : notes) {
            if (note.getMatiereId().equals(matiereId)) {
                notesMatiere.add(note);
            }
        }
        if (notesMatiere.isEmpty()) return -1.0;

        double somme = 0;
        for (Note note : notesMatiere) {
            somme += note.getValeur();
        }
        return somme / notesMatiere.size();
    }

    @Override
    public String getRole() {
        return "Élève";
    }

    @Override
    public String toString() {
        return String.format("ÉLÈVE | Matricule: %s | %s | Classe: %s | Parent: %s (%s) | Moy: %.2f/20",
                numeroMatricule, getNomComplet(), classeActuelle,
                nomParent, telephoneParent, calculerMoyenneGenerale());
    }
}
