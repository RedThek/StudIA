package cm.enspm.studia.repository;

import java.util.Optional;

import cm.enspm.studia.model.dto.personnes.ParentDTO;

public interface ParentRepository {
    
    public Optional<ParentDTO> findById(String parentId);
    public void save(ParentDTO parentDTO);
}
