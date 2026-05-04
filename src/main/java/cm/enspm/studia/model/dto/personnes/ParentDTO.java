package cm.enspm.studia.model.dto.personnes;

/**
 * Structure enregistrement d'un Parent
 * similaire à la table Parent dans la base de données
 */
public record ParentDTO(
    /**
     * Identifiant unique du Parent de la BD
     */
    int id-parent,

    /**
     * Numéro de la Carte Nationale d'Identité du Parent de la BD
     */
    String numero-cni,

    /**
     * Les noms du Parent de la BD
     */
    String nom,

    /**
     * Les prénoms du Parent de la BD
     */
    String prenom,

    /**
     * La date de naissance du Parent de la BD
     */
    String date-naissance,

    /**
     * Le numéro de téléphone du Parent de la BD
     * constitué de 06 chiffres sans l'indicatif régional
     */
    String telephone,

    /**
     * L'adresse mail électronique du Parent de la BD
     */
    String email,

    /**
     * L'activité génératrice de révénus effectuée par le Parent de la BD
     */
    String profession,

    /**
     * La nationalité du Parent de la BD
     */
    String nationalite,

    /**
     * L'adresse de résidence du Parent de la BD
     */
    String adresse
) {

}

