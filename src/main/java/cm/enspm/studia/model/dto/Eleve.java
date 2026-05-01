package cm.enspm.studia.model.dto;

import java.time.LocalDate;

public record Eleve(
    int identifiant, String matricule, String nom,
    String prenom, LocalDate dateNaissance, String lieuNaissance,
    String sexe, String photo, String nationalite) {
}
