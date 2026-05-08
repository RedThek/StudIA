package cm.enspm.studia.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un niveau d'étude (ex: 6ème, 3ème, Terminale).
 * Un niveau est associé à une liste de matières et leurs volumes horaires.
 */
public class NiveauEtude {

    private int idNiveauEtude;
    private String designation;             // Ex: "3ème", "Terminale C"
    private SystemeEducatif systemeEducatif;
    private Cycle cycle;
    private List<Matiere> matieres;         // Matières enseignées à ce niveau (1..*)
    private List<Integer> heuresMatieres;   // Heures hebdomadaires par matière (index aligné)

    public NiveauEtude(int idNiveauEtude, String designation,
                       SystemeEducatif systemeEducatif, Cycle cycle) {
        this.idNiveauEtude   = idNiveauEtude;
        this.designation     = designation;
        this.systemeEducatif = systemeEducatif;
        this.cycle           = cycle;
        this.matieres        = new ArrayList<>();
        this.heuresMatieres  = new ArrayList<>();
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getIdNiveauEtude()               { return idNiveauEtude; }
    public String getDesignation()              { return designation; }
    public String getLibelle()                  { return designation; } // alias compat
    public SystemeEducatif getSystemeEducatif() { return systemeEducatif; }
    public Cycle getCycle()                     { return cycle; }
    public List<Matiere> getMatieres()          { return matieres; }
    public List<Integer> getHeuresMatieres()    { return heuresMatieres; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setDesignation(String designation)  { this.designation = designation; }
    public void setMatieres(List<Matiere> matieres) { this.matieres = matieres; }
    public void setHeuresMatieres(List<Integer> h)  { this.heuresMatieres = h; }

    /**
     * Ajoute une matière avec son volume horaire hebdomadaire.
     */
    public void addMatiere(Matiere matiere, int heuresParSemaine) {
        this.matieres.add(matiere);
        this.heuresMatieres.add(heuresParSemaine);
    }

    /**
     * Retourne le volume horaire d'une matière donnée (-1 si non trouvée).
     */
    public int getHeuresPourMatiere(Matiere matiere) {
        for (int i = 0; i < matieres.size(); i++) {
            if (matieres.get(i).getIdentifiantMatiere() == matiere.getIdentifiantMatiere()) {
                return i < heuresMatieres.size() ? heuresMatieres.get(i) : -1;
            }
        }
        return -1;
    }

    @Override
    public String toString() { return designation; }
}
