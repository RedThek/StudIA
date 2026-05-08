package cm.enspm.studia.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Représente un cours planifié dans l'emploi du temps.
 * Un cours est assuré par un ou plusieurs enseignants, dans une salle,
 * pour une classe donnée, à un créneau précis (jour/semaine/mois/heure).
 */
public class Cours {

    private int idCours;
    private int jour;           // Jour de la semaine (1=Lundi ... 6=Samedi)
    private int semaine;        // Numéro de semaine
    private int mois;           // Numéro du mois (1-12)
    private Date debut;         // Heure/date de début
    private Date fin;           // Heure/date de fin
    private List<Employe> enseignants; // 1..*
    private Classe classe;      // 1
    private Salle salle;        // 1

    public Cours(int idCours, int jour, int semaine, int mois,
                 Date debut, Date fin,
                 List<Employe> enseignants, Classe classe, Salle salle) {
        this.idCours      = idCours;
        this.jour         = jour;
        this.semaine      = semaine;
        this.mois         = mois;
        this.debut        = debut;
        this.fin          = fin;
        this.enseignants  = enseignants != null ? enseignants : new ArrayList<>();
        this.classe       = classe;
        this.salle        = salle;
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getIdCours()              { return idCours; }
    public int getJour()                 { return jour; }
    public int getSemaine()              { return semaine; }
    public int getMois()                 { return mois; }
    public Date getDebut()               { return debut; }
    public Date getFin()                 { return fin; }
    public List<Employe> getEnseignants(){ return enseignants; }
    public Classe getClasse()            { return classe; }
    public Salle getSalle()              { return salle; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setIdCours(int idCours)              { this.idCours = idCours; }
    public void setJour(int jour)                    { this.jour = jour; }
    public void setSemaine(int semaine)              { this.semaine = semaine; }
    public void setMois(int mois)                    { this.mois = mois; }
    public void setDebut(Date debut)                 { this.debut = debut; }
    public void setFin(Date fin)                     { this.fin = fin; }
    public void setEnseignants(List<Employe> e)      { this.enseignants = e; }
    public void setClasse(Classe classe)             { this.classe = classe; }
    public void setSalle(Salle salle)                { this.salle = salle; }

    // ── Méthodes métier ───────────────────────────────────────────────────────

    /**
     * Retourne le nom du jour sous forme lisible.
     */
    public String getNomJour() {
        return switch (jour) {
            case 1 -> "Lundi";
            case 2 -> "Mardi";
            case 3 -> "Mercredi";
            case 4 -> "Jeudi";
            case 5 -> "Vendredi";
            case 6 -> "Samedi";
            default -> "Inconnu";
        };
    }

    /**
     * Retourne les noms des enseignants sous forme de chaîne.
     */
    public String getEnseignantsNoms() {
        if (enseignants == null || enseignants.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < enseignants.size(); i++) {
            sb.append(enseignants.get(i).getNomComplet());
            if (i < enseignants.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Retourne la matière principale (via le NiveauEtude de la classe).
     * Note : dans ce modèle, la matière est portée par NiveauEtude.
     */
    public String getResume() {
        String classeNom = classe != null ? classe.getLibelle() : "-";
        String salleCode = salle != null ? salle.getCode() : "-";
        return getNomJour() + " | " + classeNom + " | " + salleCode
                + " | " + getEnseignantsNoms();
    }

    @Override
    public String toString() {
        return getResume();
    }
}
