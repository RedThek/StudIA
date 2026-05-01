package cm.enspm.studia.model.dto.personnes;

import java.time.LocalDate;

/**
 * Enregistrement représentant un élève dans la base de données.
 */
public record Eleve(
    int identifiant,
    String matricule,
    String nom,
    String prenom,
    LocalDate dateNaissance,
    String lieuNaissance,
    String sexe,
    String photo,
    String nationalite
    ) {
}
