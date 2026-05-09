package cm.enspm.studia.model.dto.administration;

public record InscriptionDTO (
    
    int idEleve,

    int idClasse,

    String statut,

    String dateInscription
) {
}
