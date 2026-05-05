package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.personnes.ParentDTO;

public interface ParentRepository {

    List<ParentDTO> findAllParents();

    Optional<ParentDTO> findById(int identifiant);

    Optional<ParentDTO> findByCni(String numeroCNI);

    void save(ParentDTO parentDTO);

    void update(ParentDTO parentDTO);

    void deleteById(int identifiant);
}
