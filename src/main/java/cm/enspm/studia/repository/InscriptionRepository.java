package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.administration.ClasseDTO;
import cm.enspm.studia.model.dto.administration.InscriptionDTO;
import cm.enspm.studia.model.dto.personnes.EleveDTO;

public interface InscriptionRepository {
    
    void enregistrerInscription(InscriptionDTO inscriptionDTO);
    void modifierInscription(InscriptionDTO inscriptionDTO);
    void supprimerInscription(Integer idInscription);
    String getStatutEleve(Integer idEleve, Integer idClasse);
    Optional<EleveDTO> rechercherEleveParClasse(Integer idEleve, Integer idClasse);
    Optional<EleveDTO> rechercherEleveParClasse2(String matriculeEleve, Integer idClasse);
    Optional<ClasseDTO> rechercherClasseParEleve(Integer idEleve, Integer idAnneeScolaire);
    List<EleveDTO> getAllElevesDuneClasse(Integer idClasse);
}
