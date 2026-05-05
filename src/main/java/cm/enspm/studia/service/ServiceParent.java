package cm.enspm.studia.service;

import java.util.List;
import java.util.stream.Collectors;

import cm.enspm.studia.mapper.ParentMapper;
import cm.enspm.studia.model.dto.personnes.ParentDTO;
import cm.enspm.studia.model.fx.FxParent;
import cm.enspm.studia.model.personnes.Parent;
import cm.enspm.studia.repository.ParentRepository;
import cm.enspm.studia.repository.mysql.MySQLParentRepository;
import cm.enspm.studia.repository.ConnectionBaseDonnees;
import cm.enspm.studia.session.ContexteSession;
import cm.enspm.studia.session.SessionUtilisateur;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceParent {

    private final ParentRepository repository;
    private final ParentMapper mapper;
    private final ContexteSession session;

    public ServiceParent(ParentRepository repository, ParentMapper mapper, ContexteSession session) {
        this.repository = repository;
        this.mapper = mapper;
        this.session = session;
    }

    public ServiceParent() {
        this(createDefaultRepository(), new ParentMapper(), new SessionUtilisateur());
    }

    private static ParentRepository createDefaultRepository() {
        try {
            Connection connection = ConnectionBaseDonnees.getConnection();
            return new MySQLParentRepository(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de se connecter à la base de données pour ParentRepository", e);
        }
    }

    public List<FxParent> listerParents() {
        return repository.findAllParents().stream()
                .map(mapper::toFxParent)
                .collect(Collectors.toList());
    }

    public Parent rechercherParentParId(int id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    public void enregistrerParent(Parent parent) {
        if (repository.findByCni(parent.getNumeroCNI()).isPresent()) {
            throw new IllegalArgumentException("Ce parent existe déjà dans le système.");
        }
        ParentDTO dto = mapper.toDto(parent);
        repository.save(dto);
    }

    public void modifierParent(Parent parent) {
        if (!session.estAuthentifier()) {
            throw new SecurityException("Vous devez être connecté pour modifier un parent.");
        }
        ParentDTO dto = mapper.toDto(parent);
        repository.update(dto);
    }

    public void supprimerParent(int id) {
        session.necessiteAutorisationAdministrateur();
        repository.deleteById(id);
    }
}
