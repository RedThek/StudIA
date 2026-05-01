package cm.enspm.studia.repository;

import cm.enspm.studia.model.dto.Eleve;
import java.util.List;
import java.util.Optional;

public interface EleveRepository {
    
    void enregistrerEleve(Eleve eleve);
    void modifierEleve(Eleve eleve);
    void desactiverEleve(String matriculeEleve);
    void supprimerEleve(String matriculeEleve);
    Optional<Eleve> RechercherEleveParMatricule(String matriculeEleve);
    List<Eleve> RechercherEleveParNom(String mot_cle);
}
