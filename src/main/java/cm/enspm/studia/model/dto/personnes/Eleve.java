package cm.enspm.studia.model.dto.personnes;

import java.time.LocalDate;

/**
 * Structure Enregistrement représentant un élève dans la base de données.
 */
public record Eleve(
    /**
     * L'identifiant unique de l'élève dans la base de données.
     */
    int identifiant,

    /**
     * Le matricule de l'élève dans la base de données.
     */
    String matricule,

    /**
     * Les noms de l'élève dans la base de données.
     */
    String nom,

    /**
     * Les prénoms de l'élève dans la base de données.
    */
    String prenom,

    /**
     * La date de naissance de l'élève dans la base de données.
     */
    LocalDate dateNaissance,

    /**
     * Le lieu de naissance de l'élève dans la base de données.
     */
    String lieuNaissance,

    /**
     * Le sexe de l'élève dans la base de données.
    */
    String sexe,

    /**
     * Le chemin d'accès complet dans la base de données,
     * vers la photo de l'élève depuis la racine du projet
     */
    String photo,

    /**
     * La nationalité de l'élève dans la base de données.
     */
    String nationalite
    ) {
}
