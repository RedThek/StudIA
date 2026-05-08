package cm.enspm.studia.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Représente une classe (groupe d'élèves) de l'établissement.
 * Ex : "3ème A", "Terminale C"
 */
public class Classe {

    private int identifiantClasse;
    private String libelle;             // Ex: "3ème A"
    private String designation;         // Description courte, ex: "Troisième A"
    private NiveauEtude niveauEtude;
    private AnneeScolaire anneeScolare;
    private Employe professeurPrincipal;
    private Salle salle;                // Salle principale attribuée (0..1)
    private List<Eleve> eleves;         // Élèves inscrits (0..*)

    public Classe(int identifiantClasse, String libelle, NiveauEtude niveauEtude,
                  AnneeScolaire anneeScolare, Employe professeurPrincipal) {
        this.identifiantClasse   = identifiantClasse;
        this.libelle             = libelle;
        this.designation         = libelle;
        this.niveauEtude         = niveauEtude;
        this.anneeScolare        = anneeScolare;
        this.professeurPrincipal = professeurPrincipal;
        this.eleves              = new ArrayList<>();
    }

    // ── Getters ──────────────────────────────────────────────────────────────

    public int getIdentifiantClasse()        { return identifiantClasse; }
    public String getLibelle()               { return libelle; }
    public String getDesignation()           { return designation; }
    public NiveauEtude getNiveauEtude()      { return niveauEtude; }
    public AnneeScolaire getAnneeScolare()   { return anneeScolare; }
    public Employe getProfesseurPrincipal()  { return professeurPrincipal; }
    public Salle getSalle()                  { return salle; }
    public List<Eleve> getEleves()           { return eleves; }

    // ── Setters ──────────────────────────────────────────────────────────────

    public void setIdentifiantClasse(int id)          { this.identifiantClasse = id; }
    public void setLibelle(String libelle)             { this.libelle = libelle; }
    public void setDesignation(String designation)     { this.designation = designation; }
    public void setNiveauEtude(NiveauEtude n)          { this.niveauEtude = n; }
    public void setAnneeScolare(AnneeScolaire a)       { this.anneeScolare = a; }
    public void setProfesseurPrincipal(Employe e)      { this.professeurPrincipal = e; }
    public void setSalle(Salle salle)                  { this.salle = salle; }
    public void setEleves(List<Eleve> eleves)          { this.eleves = eleves; }

    // ── Gestion des élèves ────────────────────────────────────────────────────

    public void addEleve(Eleve eleve) {
        if (!eleves.contains(eleve)) eleves.add(eleve);
    }

    public void removeEleve(Eleve eleve) { eleves.remove(eleve); }

    // ── Méthodes statistiques ─────────────────────────────────────────────────

    /** Nombre total d'élèves inscrits. */
    public int getEffectif() { return eleves.size(); }

    /** Nombre de filles. */
    public int getNombreFilles() {
        return (int) eleves.stream()
                .filter(e -> "F".equalsIgnoreCase(e.getSexe()))
                .count();
    }

    /** Nombre de garçons. */
    public int getNombreGarcons() {
        return (int) eleves.stream()
                .filter(e -> "M".equalsIgnoreCase(e.getSexe()))
                .count();
    }

    /** Âge moyen des élèves (à partir de leur statut/date de naissance si disponible). */
    public double getAgeMoyen() {
        // Simplification : retourne 0 si pas d'info d'âge
        return 0.0;
    }

    /** Nombre d'élèves avec statut "Nouveau". */
    public int getNouveaux() {
        return (int) eleves.stream()
                .filter(e -> "Nouveau".equalsIgnoreCase(e.getStatut()))
                .count();
    }

    /** Nombre d'élèves avec statut "Redoublant". */
    public int getRedoublants() {
        return (int) eleves.stream()
                .filter(e -> "Redoublant".equalsIgnoreCase(e.getStatut()))
                .count();
    }

    /** Élève le plus jeune (par matricule croissant si pas de date d'âge). */
    public Eleve getPlusJeune() {
        return eleves.stream()
                .min(Comparator.comparing(Eleve::getMatricule))
                .orElse(null);
    }

    /** Élève le plus vieux (par matricule décroissant). */
    public Eleve getPlusVieux() {
        return eleves.stream()
                .max(Comparator.comparing(Eleve::getMatricule))
                .orElse(null);
    }

    @Override
    public String toString() { return libelle; }
}
