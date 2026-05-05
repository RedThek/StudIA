package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.personnes.EleveDTO;

public interface EleveRepository {
    
    void enregistrerEleve(EleveDTO eleveDTO);
    void modifierEleve(EleveDTO eleveDTO);
    //void desactiverEleve(String matriculeEleve);
    void supprimerEleve(String matriculeEleve);
    Optional<EleveDTO> RechercherEleveParMatricule(String matriculeEleve);
    List<EleveDTO> getAllEleves();
}
