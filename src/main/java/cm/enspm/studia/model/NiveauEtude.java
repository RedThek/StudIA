package cm.enspm.studia.model;

public class NiveauEtude {
    
    private int idNiveauEtude;
    private String libelle;
    private SystemeEducatif systemeEducatif;
    private Cycle cycle;

    public NiveauEtude(int idNiveauEtude, String libelle, SystemeEducatif systemeEducatif, Cycle cycle) {
        this.idNiveauEtude = idNiveauEtude;
        this.libelle = libelle;
        this.systemeEducatif = systemeEducatif;
        this.cycle = cycle;
    }

    public int getIdNiveauEtude() {
        return idNiveauEtude;
    }

    public String getLibelle() {
        return libelle;
    }

    public SystemeEducatif getSystemeEducatif() {
        return systemeEducatif;
    }

    public Cycle getCycle() {
        return cycle;
    }
}
