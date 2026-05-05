package cm.enspm.studia.model.dto.personnes;

/**
 * Classe représentant le personnel de l'école secondaire.
 * Un membre du personnel peut enseigner plusieurs matières dans plusieurs classes ou pas.
 */
public record Employe (

    /**
     * L'identifiant d'un employé de l'école secondaire dans la base de données..
     */
    String identifiant,

    /**
     * Carte Nationale d'Identité (CNI) de l'employé dans la base de données..
     */
    String numeroCNI,

    /**
     * Les noms de l'employé dans la base de données.
     */
    String nom,

    /**
     * Les prénoms de l'employé dans la base de données.
     */
    String prenom,

    /**
     * La date de naissance de l'employé dans la base de données.
     */
    String dateNaissance,

    /**
     * Le sexe de l'employé dans la base de données.
     */
    String sexe,

    /**
     * Numéro de téléphone de l'employé.
     */
    String telephone,

    /**
     * Adresse email de l'employé.
     */
    String email,

    /**
     * Poste occupé par l'employé au sein de l'école secondaire (ex: enseignant, surveillant, etc.).
     */
    String poste,

    /**
     * Diplôme le plus élevé de l'employé dans la base de données.
     */
    String grade,

    /**
     * Adresse physique de l'employé dans la base de données.
     */
    String adresse,

    /**
     * La nationalité de l'employé dans la base de données
     */
    String nationalite

    ) {

}