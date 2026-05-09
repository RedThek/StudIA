package cm.enspm.studia.model.dto.syllabus;

//import java.util.Date;

/**
 * Classe représentant une année scolaire, qui contient des informations sur l'année académique en cours,
 * telles que les dates de début et de fin, ainsi que le libellé de l'année.
 * Cette classe est utilisée pour gérer les informations liées à l'année scolaire
 * dans le contexte de la gestion des syllabus et des programmes d'études.
 */
public record AnneeScolaireDTO (

    /*
     * Identifiant unique de l'année scolaire ex: 20242025
     */
    int idAnneeScolaire,
    /**
     * Libellé de l'année scolaire, généralement au format "AAAA-AAAA" (ex: "2023-2024")
     */
    String libelle,
    /**
     * Date de début de l'année scolaire, utilisées pour déterminer les périodes d'enseignement.
     */
    String dateDebut,
    /**
     * Date de fin de l'année scolaire, utilisée pour déterminer le debut des vacances.
     */
    String dateFin
){

}
