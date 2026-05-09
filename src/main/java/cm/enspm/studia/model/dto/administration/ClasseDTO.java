package cm.enspm.studia.model.dto.administration;

/**
 * Classe représentant une classe (groupe d'élèves) de l'école secondaire pour une année scolaire donnée.
 * Ex : "3ème A 2022-2023", "Terminale S 2019-2020", "2nde B 2020-2021"
 */
public record ClasseDTO(

    /**
     * Identifiant unique de la classe (ex: "001")
     */
     int identifiantClasse,

    /**
     * Libellé de la classe (ex: "3ème A4", "Terminale D1", "2nde B2")
     */
     String nomClasse,

    /**
     * Niveau d'étude de la classe (ex: "3ème", "Terminale D", "2nde B")
     */
     Integer niveauEtude,

    /**
     * Année scolaire de la classe (ex: "2022-2023", "2019-2020", "2020-2021")
     */
     Integer anneeScolare

    /**
     * l'enseignant principal de la classe
     
    Integer professeurPrincipal */
    
){
}
