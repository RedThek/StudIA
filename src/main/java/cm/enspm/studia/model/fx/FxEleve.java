package cm.enspm.studia.model.fx;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.dto.personnes.EleveDTO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class FxEleve {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final IntegerProperty identifiant = new SimpleIntegerProperty();
    private final StringProperty matricule = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty prenom = new SimpleStringProperty();
    private final StringProperty dateNaissance = new SimpleStringProperty();
    private final StringProperty lieuNaissance = new SimpleStringProperty();
    private final StringProperty sexe = new SimpleStringProperty();
    private final StringProperty photo = new SimpleStringProperty();
    private final StringProperty nationalite = new SimpleStringProperty();
    private final StringProperty statut = new SimpleStringProperty();
    private final StringProperty classe = new SimpleStringProperty();

    public FxEleve(EleveDTO eleveDto) {
        this.identifiant.set(eleveDto.identifiant());
        this.matricule.set(eleveDto.matricule());
        this.nom.set(eleveDto.nom());
        this.prenom.set(eleveDto.prenom());
        this.dateNaissance.set(eleveDto.dateNaissance() != null ? eleveDto.dateNaissance().format(DATE_FORMATTER) : "");
        this.lieuNaissance.set(eleveDto.lieuNaissance());
        this.sexe.set(eleveDto.sexe());
        this.photo.set(eleveDto.photo());
        this.nationalite.set(eleveDto.nationalite());
    }

    public FxEleve(Eleve eleveDomain) {
        this.identifiant.set(0);
        this.matricule.set(eleveDomain.getMatricule());
        this.nom.set(eleveDomain.getNom());
        this.prenom.set(eleveDomain.getPrenom());
        this.dateNaissance.set(eleveDomain.getDateNaissance());
        this.lieuNaissance.set(eleveDomain.getLieuNaissance());
        this.sexe.set(eleveDomain.getSexe());
        this.photo.set(eleveDomain.getPhoto());
        this.nationalite.set(eleveDomain.getNationalite());
    }

    public EleveDTO toDto() {
        LocalDate date = null;
        if (!dateNaissance.get().isEmpty()) {
            date = LocalDate.parse(dateNaissance.get(), DATE_FORMATTER);
        }
        return new EleveDTO(
            identifiant.get(),
            matricule.get(),
            nom.get(),
            prenom.get(), 
            date,
            lieuNaissance.get(),
            sexe.get(), 
            photo.get(),
            nationalite.get()); 
    }

    public Eleve toDomain() {
        return new Eleve(
            matricule.get(),
            nom.get(),
            prenom.get(),
            dateNaissance.get(),
            lieuNaissance.get(),
            sexe.get(),
            photo.get(),
            nationalite.get(),
            classe.get()
        );
    }

    // Getters for Property Binding in TableViews
    public IntegerProperty identifiantEleve() { return identifiant; }
    public StringProperty matriculeEleve() { return matricule; }
    public StringProperty nomEleve() { return nom; }
    public StringProperty prenomEleve() { return prenom; }
    public StringProperty dateNaissanceEleve() { return dateNaissance; }
    public StringProperty lieuNaissanceEleve() { return lieuNaissance; }
    public StringProperty sexeEleve() { return sexe; }
    public StringProperty photoEleve() { return photo; }
    public StringProperty nationaliteEleve() { return nationalite; }
    public StringProperty statutEleve() { return statut; }
    public StringProperty classeEleve() { return classe; }
}
