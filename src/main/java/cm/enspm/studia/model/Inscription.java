package cm.enspm.studia.model;

public class Inscription {
    private Eleve eleve;
    private Classe classe;
    private String dateInscription;
    private String statut;

    public Inscription(Eleve eleve, Classe classe, String dateInscription, String statut) {
        this.eleve = eleve;
        this.classe = classe;
        this.dateInscription = dateInscription;
        this.statut = statut;
    }

    // Getters and Setters
    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
