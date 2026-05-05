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
     * Le numéro de la Carte Nationale d'Identité du parent.
     */
    String numeroCNI,

    /**
     * Le nom du parent.
     */
    String nom,

    /**
     * Le prénom du parent.
     */
    String prenom,

    /**
     * La date de naissance du parent.
     */
    String dateNaissance,

    /**
     * Le numéro de téléphone du parent.
     */
    String telephone,

    /**
     * L'adresse e-mail du parent.
     */
    String email,

    /**
     * La profession du parent.
     */
    String profession,

    /**
     * La nationalité du parent.
     */
    String nationalite,

    /**
     * L'adresse de résidence du parent.
     */
    String adresse

    /**
     * Le lien parental avec l'élève (père, mère, tuteur, etc.).
     
    String lienParental */
) {
}

