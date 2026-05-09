package cm.enspm.studia.service;

import java.util.List;
import java.util.stream.Collectors;

import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.repository.EleveRepository;
import cm.enspm.studia.session.ContexteSession;

public class ServicesEleve {

    /** Dépôt d'accès aux données pour les opérations sur les élèves. */
    private final EleveRepository repository;

    /** Contexte de session utilisé pour vérifier les autorisations. */
    private final ContexteSession session;

    /**
     * Crée le service élève avec un dépôt et un contexte de session.
     */
    public ServicesEleve(EleveRepository repository, ContexteSession session) {
        this.repository = repository;
        this.session = session;
    }

    /**
     * Enregistre un nouvel élève en base.
     * Vérifie que le matricule n'existe pas déjà avant d'insérer.
     */
    public void enregistrerEleve(Eleve eleve) {
        if (repository.RechercherEleveParMatricule(eleve.getMatricule()).isPresent()) {
            throw new IllegalArgumentException("Le matricule existe déjà dans le système.");
        }
        Integer idEleve = repository.rechercherIdParMatricule(eleve.getMatricule());
        //eleve.setIdentifiant(idEleve != null ? idEleve : 0);
        repository.enregistrerEleve(EleveMapper.toDto(eleve));
    }

    /**
     * Met à jour un élève existant en base après vérification de la session.
     */
    public void modifierEleve(Eleve eleve) {
        if (!session.estAuthentifier()) {
            throw new SecurityException("Vous devez être connecté pour modifier un élève.");
        }

        if (repository.RechercherEleveParMatricule(eleve.getMatricule()).isEmpty()) {
            throw new IllegalArgumentException("L'élève avec ce matricule n'existe pas.");
        }

        repository.modifierEleve(EleveMapper.toDto(eleve));
    }

    /**
     * Retourne la liste de tous les élèves présents en base de données.
     */
    public List<Eleve> listerEleves() {
        return repository.getAllEleves().stream()
                .map(EleveMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Recherche un élève par son matricule.
     * @param matricule matricule de l'élève à rechercher
     * @return l'élève trouvé ou null si absent
     */
    public Eleve rechercherEleveParMatricule(String matricule) {
        return repository.RechercherEleveParMatricule(matricule)
                .map(EleveMapper::toDomain)
                .orElse(null);
    }

    /**
     * Supprime un élève identifié par son matricule.
     * Nécessite des droits administrateur.
     */
    public void SupprimerEleve(String matriculeEleve) {
        session.necessiteAutorisationAdministrateur();
        repository.supprimerEleve(matriculeEleve);
    }
}
