package cm.enspm.studia.model;

import java.util.Date;

public class AnneeScolaire {
    private int idAnneeScolaire;
    private String libelle;
    private Date dateDebut;
    private Date dateFin;

    public AnneeScolaire(int idAnneeScolaire, String libelle, Date dateDebut, Date dateFin) {
        this.idAnneeScolaire = idAnneeScolaire;
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getIdAnneeScolaire() {
        return idAnneeScolaire;
    }

    public String getLibelle() {
        return libelle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setIdAnneeScolaire(int idAnneeScolaire) {
        this.idAnneeScolaire = idAnneeScolaire;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    
    
}
