package cm.enspm.studia.repository;

import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.personnes.EleveDTO;

public interface EleveRepository {
    
    /**
     * Insère un élève dans la base de données.
     */
    void enregistrerEleve(EleveDTO eleveDTO);

    /**
     * Met à jour les informations d'un élève existant.
     */
    void modifierEleve(EleveDTO eleveDTO);

    //void desactiverEleve(String matriculeEleve);

    /**
     * Supprime un élève identifié par son matricule.
     */
    void supprimerEleve(String matriculeEleve);

    /**
     * Recherche l'identifiant interne d'un élève par matricule.
     */
    Integer rechercherIdParMatricule(String matriculeEleve);

    /**
     * Recherche un élève par son matricule.
     */
    Optional<EleveDTO> RechercherEleveParMatricule(String matriculeEleve);

    /**
     * Récupère tous les élèves présents en base.
     */
    List<EleveDTO> getAllEleves();
}
