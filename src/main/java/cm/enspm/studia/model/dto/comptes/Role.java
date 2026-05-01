package cm.enspm.studia.model.dto.comptes;

public enum Role {
    ADMINISTRATEUR,
    UTILISATEUR;

    public boolean gestionUtilisateurs() {
        return this == ADMINISTRATEUR;
    }

    public boolean autorisationSuppressionEleves() {
        return this == ADMINISTRATEUR;
    }
}
