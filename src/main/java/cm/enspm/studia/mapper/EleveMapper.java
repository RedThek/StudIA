package cm.enspm.studia.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import cm.enspm.studia.model.dto.personnes.EleveDTO;
import cm.enspm.studia.model.fx.FxEleve;
import cm.enspm.studia.model.personnes.Eleve;
/**
 * Fournit les conversions entre les objets Eleve du domaine, les DTO et les modèles JavaFX.
 */
public class EleveMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Convertit un DTO Eleve vers un modèle JavaFX pour affichage.
     */
    public static FxEleve toFxEleve(EleveDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FxEleve(dto);
    }

    /**
     * Convertit un objet domaine Eleve vers un modèle JavaFX.
     */
    public static FxEleve toFxEleve(Eleve domain) {
        if (domain == null) {
            return null;
        }
        return new FxEleve(domain);
    }

    /**
     * Convertit un objet domaine Eleve en DTO Eleve.
     */
    public static Eleve toDomain(EleveDTO eleveDTO) {
        if (eleveDTO == null) {
            return null;
        }

        String dateNaissance = eleveDTO.dateNaissance() != null ? eleveDTO.dateNaissance().format(DATE_FORMATTER) : null;
        return new cm.enspm.studia.model.personnes.Eleve(
                eleveDTO.matricule(),
                eleveDTO.nom(),
                eleveDTO.prenom(),
                dateNaissance,
                eleveDTO.lieuNaissance(),
                eleveDTO.sexe(),
                eleveDTO.photo(),
                eleveDTO.nationalite(),
                null
        );
    }

    /**
     * Convertit un objet domaine Eleve en DTO pour la persistence.
     */
    public static EleveDTO toDto(Eleve domain) {
        if (domain == null) {
            return null;
        }

        LocalDate dateNaissance = null;
        if (domain.getDateNaissance() != null && !domain.getDateNaissance().isBlank()) {
            dateNaissance = LocalDate.parse(domain.getDateNaissance(), DATE_FORMATTER);
        }

        return new EleveDTO(
                0,
                domain.getMatricule(),
                domain.getNom(),
                domain.getPrenom(),
                dateNaissance,
                domain.getLieuNaissance(),
                domain.getSexe(),
                domain.getPhoto(),
                domain.getNationalite()
        );
    }

    /**
     * Convertit un modèle JavaFX Eleve en DTO pour la persistence.
     */
    public static EleveDTO toDto(FxEleve fxEleve) {
        if (fxEleve == null) {
            return null;
        }
        return fxEleve.toDto();
    }

    /**
     * Convertit une liste de DTO Eleve en objets domaine Eleve.
     */
    public static List<Eleve> toDomainList(List<EleveDTO> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(EleveMapper::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTO Eleve en modèles JavaFX pour l'affichage.
     */
    public static List<FxEleve> toFxList(List<EleveDTO> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(EleveMapper::toFxEleve)
                .collect(Collectors.toList());
    }
}
