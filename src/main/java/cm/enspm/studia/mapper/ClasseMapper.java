package cm.enspm.studia.mapper;

import java.time.format.DateTimeFormatter;

import cm.enspm.studia.model.administration.Classe;
import cm.enspm.studia.model.dto.administration.ClasseDTO;
import cm.enspm.studia.model.fx.FxClasses;

public class ClasseMapper {
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static FxClasses toFxClasses(ClasseDTO dto) {
        if (dto == null) {
            return null;
        }
        //return new FxClasses(dto);
        return null;
    }

    public static FxClasses toFxClasses(Classe domain) {
        if (domain == null) {
            return null;
        }
        //return new FxClasses(domain);
        return null;
    }

    public static Classe toDomain(ClasseDTO classeDTO) {
        if (classeDTO == null) {
            return null;
        }

        /*return new Classe(
                classeDTO.identifiantClasse()
        );*/
        return null;
    }

    public static ClasseDTO toDto(Classe domain) {
        if (domain == null) {
            return null;
        }

        /*return new ClasseDTO(
                0,
                domain.getLibelle(),
                domain.getDescription()
        );*/
        return null;
    }
}
