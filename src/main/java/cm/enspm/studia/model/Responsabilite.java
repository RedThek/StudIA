package cm.enspm.studia.model;

public class Responsabilite {
    private Eleve eleve;
    private Parent parent;
    private String lien;

    public Responsabilite(Eleve eleve, Parent parent, String lien) {
        this.eleve = eleve;
        this.parent = parent;
        this.lien = lien;
    }

    // Getters and Setters
    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }
}
