package cm.enspm.studia.session;

import java.util.Optional;

import cm.enspm.studia.model.dto.comptes.Role;
import cm.enspm.studia.model.dto.comptes.Utilisateur;

public class SessionUtilisateur implements ContexteSession {
    
    private Utilisateur utilisateurActuel;

    @Override
    public void connexionUtilisateur(Utilisateur utilisateur) {
        this.utilisateurActuel = utilisateur;
    }

    @Override
    public void deconnexionUtilisateur() {
        this.utilisateurActuel = null;
    }

    @Override
    public Optional<Utilisateur> getUtilisateurCourant() {
        return Optional.ofNullable(utilisateurActuel);
    }

    @Override
    public boolean estAuthentifier() {
        return utilisateurActuel != null;
    }

    @Override
    public void necessiteAutorisationAdministrateur() {
        if (!estAuthentifier() || getUtilisateurCourant().get().role() != Role.ADMINISTRATEUR) {
            throw new SecurityException("Accèss Non Autorisé: Connexion Administrateur requise.");
        }
    }
}
