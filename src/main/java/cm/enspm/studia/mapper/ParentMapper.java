package cm.enspm.studia.mapper;

import java.util.ArrayList;
import java.util.List;

import cm.enspm.studia.model.dto.personnes.ParentDTO;
import cm.enspm.studia.model.fx.FxParent;
import cm.enspm.studia.model.personnes.Parent;

public class ParentMapper {

    public FxParent toFxParent(ParentDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FxParent(dto);
    }

    public Parent toDomain(ParentDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Parent(
            dto.numeroCNI(),
            dto.nom(),
            dto.prenom(),
            dto.telephone(),
            dto.email(),
            "M",
            dto.profession(),
            dto.adresse(),
            dto.nationalite(),
            "Pere",
            new ArrayList<>()
        );
    }

    public ParentDTO toDto(Parent parent) {
        if (parent == null) {
            return null;
        }
        return new ParentDTO(
            0,
            parent.getNumeroCNI(),
            parent.getNom(),
            parent.getPrenom(),
            parent.getDateNaissance(),
            parent.getTelephone(),
            parent.getEmail(),
            parent.getProfession(),
            parent.getNationalite(),
            parent.getAdresse()
        );
    }

    public ParentDTO toDto(FxParent fxParent) {
        if (fxParent == null) {
            return null;
        }
        return new ParentDTO(
            fxParent.identifiantParent().get(),
            fxParent.numeroCNIParent().get(),
            fxParent.nomParent().get(),
            fxParent.prenomParent().get(),
            fxParent.dateNaissanceParent().get(),
            fxParent.telephoneParent().get(),
            fxParent.emailParent().get(),
            fxParent.professionParent().get(),
            fxParent.nationaliteParent().get(),
            fxParent.adresseParent().get()
        );
    }
}
