package cm.enspm.studia.session;

import java.util.Optional;

import cm.enspm.studia.model.dto.comptes.Utilisateur;

public interface ContexteSession {

    void connexionUtilisateur(Utilisateur utilisateur);
    void deconnexionUtilisateur();
    Optional<Utilisateur> getUtilisateurCourant();
    boolean estAuthentifier();
    void necessiteAutorisationAdministrateur(); // Génère une exception si l'utilisateur n'est pas un administrateur
}
