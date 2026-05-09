package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.administration.ClasseDTO;

public interface ClasseRepository {
    void enregistrerClasse(ClasseDTO classeDTO);
    void modifierClasse(ClasseDTO classeDTO);
    //void desactiverClasse(Integer idClasse);
    void supprimerClasse(Integer idClasse);
    Integer rechercherIdParNom(String nomClasse, Integer anneeScolare);
    Optional<ClasseDTO> RechercherClasseParNom(String nomClasse, Integer anneeScolare);
    List<ClasseDTO> getAllClasses(Integer anneeScolare);
}
