package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.personnes.Eleve;

public interface EleveRepository {
    
    void enregistrerEleve(Eleve eleve);
    void modifierEleve(Eleve eleve);
    void desactiverEleve(String matriculeEleve);
    void supprimerEleve(String matriculeEleve);
    Optional<Eleve> RechercherEleveParMatricule(String matriculeEleve);
    List<Eleve> getAllEleves();
}
