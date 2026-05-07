package cm.enspm.studia.model.dto.personnes;

/**
 * Structure Enregistrement représentant un parent dans la base de données.
 */
public record ParentDTO(
    
    /**
     * L'identifiant unique du parent dans la base de données.
     */
    int identifiant,

    /**
     * Le numéro de la Carte Nationale d'Identité du parent dans la base de données.
     */
    String numeroCNI,

    /**
     * Le nom du parent dans la base de données.
     */
    String nom,

    /**
     * Le prénom du parent dans la base de données.
     */
    String prenom,

    /**
     * La date de naissance du parent dans la base de données.
     */
    String dateNaissance,

    /**
     * Le numéro de téléphone du parent dans la base de données.
     */
    String telephone,

    /**
     * L'adresse e-mail du parent dans la base de données.
     */
    String email,

    /**
     * La profession du parent dans la base de données.
     */
    String profession,

    /**
     * La nationalité du parent dans la base de données.
     */
    String nationalite,

    /**
     * L'adresse de résidence du parent dans la base de données.
     */
    String adresse
) {
}

