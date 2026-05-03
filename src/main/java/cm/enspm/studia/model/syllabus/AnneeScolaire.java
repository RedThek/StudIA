package cm.enspm.studia.model.syllabus;

import java.time.LocalDate;

/**
 * Classe représentant une année scolaire, qui contient des informations sur l'année académique en cours,
 * telles que les dates de début et de fin, ainsi que le libellé de l'année.
 * Cette classe est utilisée pour gérer les informations liées à l'année scolaire
 * dans le contexte de la gestion des syllabus et des programmes d'études.
 */
public class AnneeScolaire {

    /**
     * Date de début de l'année scolaire, utilisées pour déterminer les périodes d'enseignement.
     */
    private LocalDate dateDebut;
    /**
     * Date de fin de l'année scolaire, utilisée pour déterminer le debut des vacances.
     */
    private LocalDate dateFin;

    /**
     * Constructeur de la classe AnneeScolaire
     * @param dateDebut
     * @param dateFin
     */
    public AnneeScolaire(LocalDate dateDebut, LocalDate dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getLibelle() {
        // Générer le libellé de l'année scolaire au format "YYYY-YYYY"
        int anneeDebut = dateDebut.getYear() + 1900; // getYear() retourne l'année depuis 1900
        int anneeFin = dateFin.getYear() + 1900;
        return anneeDebut + "-" + anneeFin;
    }
    
}
