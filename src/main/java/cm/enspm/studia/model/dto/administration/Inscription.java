package cm.enspm.studia.model.dto.administration;

import cm.enspm.studia.model.personnes.Eleve;

public class Inscription {
    private String numeroInscription;
    private Eleve eleve;
    private Classe classe;
    private String dateInscription;
    private String statut;

    public Inscription(
        String numeroInscription, Eleve eleve, Classe classe,
        String dateInscription, String statut
    ) {
        this.numeroInscription = numeroInscription;
        this.eleve = eleve;
        this.classe = classe;
        this.dateInscription = dateInscription;
        this.statut = statut;
    }

    // Getters and Setters
    public String getNumeroInscription() {
        return numeroInscription;
    }

    public void setNumeroInscription(String numeroInscription) {
        this.numeroInscription = numeroInscription;
    }

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
