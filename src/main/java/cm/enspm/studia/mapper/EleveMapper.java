package cm.enspm.studia.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EleveMapper {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static cm.enspm.studia.model.personnes.Eleve toDomain(cm.enspm.studia.model.dto.personnes.Eleve dto) {
        if (dto == null) {
            return null;
        }

        String dateNaissance = dto.dateNaissance() != null ? dto.dateNaissance().format(DATE_FORMATTER) : null;
        return new cm.enspm.studia.model.personnes.Eleve(
                dto.matricule(),
                dto.nom(),
                dto.prenom(),
                dateNaissance,
                dto.lieuNaissance(),
                dto.sexe(),
                dto.photo(),
                dto.nationalite(),
                null
        );
    }

    public static cm.enspm.studia.model.dto.personnes.Eleve toDto(cm.enspm.studia.model.personnes.Eleve domain) {
        if (domain == null) {
            return null;
        }

        LocalDate dateNaissance = null;
        if (domain.getDateNaissance() != null && !domain.getDateNaissance().isBlank()) {
            dateNaissance = LocalDate.parse(domain.getDateNaissance(), DATE_FORMATTER);
        }

        return new cm.enspm.studia.model.dto.personnes.Eleve(
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

    public static List<cm.enspm.studia.model.personnes.Eleve> toDomainList(List<cm.enspm.studia.model.dto.personnes.Eleve> dtos) {
        return dtos == null ? List.of() : dtos.stream()
                .map(EleveMapper::toDomain)
                .collect(Collectors.toList());
    }
}
