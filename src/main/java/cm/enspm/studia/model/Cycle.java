package cm.enspm.studia.model;

public class Cycle {
    
    private int identifiantCycle;
    private String libelle;
    private String nom;

    public Cycle(int identifiantCycle, String libelle, String nom) {
        this.identifiantCycle = identifiantCycle;
        this.libelle = libelle;
        this.nom = nom;
    }

    public int getIdentifiantCycle() {
        return identifiantCycle;
    }

    public String getLibelleCycle() {
        return libelle;
    }

    public String getNomCycle() {
        return nom;
    }

    public void setNomCycle(String nom) {
        this.nom = nom;
    }
}
