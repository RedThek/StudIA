package cm.enspm.studia.model;

public class Trimestre {

    private int identifiantTrimestre;
    private String libelle;
    private AnneeScolaire anneeScolaire;

    public Trimestre(int identifiantTrimestre, String libelle, AnneeScolaire anneeScolaire) {
        this.identifiantTrimestre = identifiantTrimestre;
        this.libelle = libelle;
        this.anneeScolaire = anneeScolaire;
    }

    public int getIdentifiantTrimestre() {
        return identifiantTrimestre;
    }

    public String getLibelleTrimestre() {
        return libelle;
    }

    public AnneeScolaire getAnneeScolaire() {
        return anneeScolaire;
    }
}
