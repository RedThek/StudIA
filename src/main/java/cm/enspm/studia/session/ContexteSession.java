package cm.enspm.studia.session;

import cm.enspm.studia.model.dto.Utilisateur;
import java.util.Optional;

public interface ContexteSession {

    void connexionUtilisateur(Utilisateur utilisateur);
    void deconnexionUtilisateur();
    Optional<Utilisateur> getUtilisateurCourant();
    boolean estAuthentifier();
    void necessiteAutorisationAdministrateur(); // Génère une exception si l'utilisateur n'est pas un administrateur
}
