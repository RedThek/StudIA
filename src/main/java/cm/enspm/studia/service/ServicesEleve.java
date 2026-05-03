package cm.enspm.studia.service;

import java.util.List;
import java.util.stream.Collectors;

import cm.enspm.studia.mapper.EleveMapper;
import cm.enspm.studia.model.personnes.Eleve;
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
        if (repository.RechercherEleveParMatricule(eleve.getMatricule()).isPresent()) {
            throw new IllegalArgumentException("Le matricule existe déjà dans le système.");
        }
        repository.enregistrerEleve(EleveMapper.toDto(eleve));
    }

    public void modifierEleve(Eleve eleve) {
        if (!session.estAuthentifier()) {
            throw new SecurityException("Vous devez être connecté pour modifier un élève.");
        }

        if (repository.RechercherEleveParMatricule(eleve.getMatricule()).isEmpty()) {
            throw new IllegalArgumentException("L'élève avec ce matricule n'existe pas.");
        }

        repository.modifierEleve(EleveMapper.toDto(eleve));
    }

    public List<Eleve> listerEleves() {
        return repository.getAllEleves().stream()
                .map(EleveMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Eleve rechercherEleveParMatricule(String matricule) {
        return repository.RechercherEleveParMatricule(matricule)
                .map(EleveMapper::toDomain)
                .orElse(null);
    }

    public void SupprimerEleve(String matriculeEleve) {
        session.necessiteAutorisationAdministrateur();
        repository.supprimerEleve(matriculeEleve);
    }
}
