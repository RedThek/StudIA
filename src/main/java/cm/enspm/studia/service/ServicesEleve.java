package cm.enspm.studia.service;

import cm.enspm.studia.model.dto.personnes.Eleve;
import cm.enspm.studia.repository.EleveRepository;
import cm.enspm.studia.session.ContexteSession;

public class ServicesEleve {

    private final EleveRepository repository;
    private final ContexteSession session;

    // Inject the specific database implementation here
    public ServicesEleve(EleveRepository repository, ContexteSession session) {
        this.repository = repository;
        this.session = session;
    }

    public void enregistrementEleve(Eleve eleve) {

        // Appliquer les règles métier verifier avant d'enregistrer l'élève
        if (repository.RechercherEleveParMatricule(eleve.matricule()).isPresent()) {
            throw new IllegalArgumentException("Le matricule existe déjà dans le système.");
        }
        repository.enregistrerEleve(eleve);
    }

    public void modifierEleve(Eleve eleve) {
        // Basic users CAN modify a student, so we just ensure they are logged in.
        if (!session.estAuthentifier()) {
            throw new SecurityException("Vous devez être connecté pour modifier un élève.");
        }

        // Appliquer les règles métier verifier avant de modifier l'élève
        if (repository.RechercherEleveParMatricule(eleve.matricule()).isEmpty()) {
            throw new IllegalArgumentException("L'élève avec ce matricule n'existe pas.");
        }

        if (repository.RechercherEleveParMatricule(eleve.matricule()).isPresent()) {
            repository.modifierEleve(eleve);
        } else {
            repository.enregistrerEleve(eleve);
        }
        repository.modifierEleve(eleve);
    }

    public void SupprimerEleve(String matriculeEleve) {
        // Double-check authorization before touching the database
        session.necessiteAutorisationAdministrateur(); 
        
        repository.supprimerEleve(matriculeEleve);
    }
}
