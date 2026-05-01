package cm.enspm.studia.model.dto.syllabus;

import java.util.Date;

/**
 * Classe représentant une année scolaire, qui contient des informations sur l'année académique en cours,
 * telles que les dates de début et de fin, ainsi que le libellé de l'année.
 * Cette classe est utilisée pour gérer les informations liées à l'année scolaire
 * dans le contexte de la gestion des syllabus et des programmes d'études.
 */
public class AnneeScolaire {

    /*
     * Identifiant unique de l'année scolaire ex: 20242025
     */
    private int idAnneeScolaire;
    /**
     * Libellé de l'année scolaire, généralement au format "AAAA-AAAA" (ex: "2023-2024")
     */
    private String libelle;
    /**
     * Date de début de l'année scolaire, utilisées pour déterminer les périodes d'enseignement.
     */
    private Date dateDebut;
    /**
     * Date de fin de l'année scolaire, utilisée pour déterminer le debut des vacances.
     */
    private Date dateFin;

    /**
     * Constructeur de la classe AnneeScolaire
     * @param idAnneeScolaire
     * @param libelle
     * @param dateDebut
     * @param dateFin
     */
    public AnneeScolaire(int idAnneeScolaire, String libelle, Date dateDebut, Date dateFin) {
        this.idAnneeScolaire = idAnneeScolaire;
        this.libelle = libelle;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    /**
     * Getter pour l'identifiant de l'année scolaire
     * @return identifiant de l'année scolaire
     */
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

    /**
     * Setter pour l'identifiant de l'année scolaire
     * @param idAnneeScolaire
     */
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
