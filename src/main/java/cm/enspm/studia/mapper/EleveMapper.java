package cm.enspm.studia.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import cm.enspm.studia.model.dto.personnes.EleveDTO;
import cm.enspm.studia.model.fx.FxEleve;
import cm.enspm.studia.model.personnes.Eleve;
public class EleveMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static FxEleve toFxEleve(EleveDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FxEleve(dto);
    }

    public static FxEleve toFxEleve(Eleve domain) {
        if (domain == null) {
            return null;
        }
        return new FxEleve(domain);
    }

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

    public static EleveDTO toDto(FxEleve fxEleve) {
        if (fxEleve == null) {
            return null;
        }
        return fxEleve.toDto();
    }

    public static List<Eleve> toDomainList(List<EleveDTO> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(EleveMapper::toDomain)
                .collect(Collectors.toList());
    }

    public static List<FxEleve> toFxList(List<EleveDTO> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(EleveMapper::toFxEleve)
                .collect(Collectors.toList());
    }
}
