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

    /** Identifiant unique de l'élève dans les vues JavaFX. */
    private final IntegerProperty identifiant = new SimpleIntegerProperty();

    /** Matricule de l'élève, utilisé pour l'affichage et la recherche. */
    private final StringProperty matricule = new SimpleStringProperty();

    /** Nom de l'élève pour l'affichage dans les tables et formulaires. */
    private final StringProperty nom = new SimpleStringProperty();

    /** Prénom de l'élève pour l'affichage dans les tables et formulaires. */
    private final StringProperty prenom = new SimpleStringProperty();

    /** Date de naissance de l'élève stockée en chaîne formatée. */
    private final StringProperty dateNaissance = new SimpleStringProperty();

    /** Lieu de naissance de l'élève pour l'affichage. */
    private final StringProperty lieuNaissance = new SimpleStringProperty();

    /** Sexe de l'élève, utilisé pour l'affichage et le filtrage. */
    private final StringProperty sexe = new SimpleStringProperty();

    /** Chemin ou identifiant de la photo de l'élève. */
    private final StringProperty photo = new SimpleStringProperty();

    /** Nationalité de l'élève pour l'affichage et la persistence. */
    private final StringProperty nationalite = new SimpleStringProperty();

    /** Statut administratif de l'élève pour l'affichage. */
    private final StringProperty statut = new SimpleStringProperty();

    /** Classe ou groupe d'appartenance de l'élève dans l'établissement. */
    private final StringProperty classe = new SimpleStringProperty();

    /**
     * Crée un modèle FX à partir d'un DTO Eleve.
     */
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

    /**
     * Crée un modèle FX à partir d'un objet domaine Eleve.
     */
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

    /**
     * Convertit le modèle FX en DTO pour enregistrement.
     */
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

    /**
     * Convertit le modèle FX en objet domaine Eleve.
     */
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
    /** Retourne la propriété identifiant pour liaison TableView. */
    public IntegerProperty identifiantEleve() { return identifiant; }

    /** Retourne la propriété matricule pour liaison TableView. */
    public StringProperty matriculeEleve() { return matricule; }

    /** Retourne la propriété nom pour liaison TableView. */
    public StringProperty nomEleve() { return nom; }

    /** Retourne la propriété prénom pour liaison TableView. */
    public StringProperty prenomEleve() { return prenom; }

    /** Retourne la propriété date de naissance pour liaison TableView. */
    public StringProperty dateNaissanceEleve() { return dateNaissance; }

    /** Retourne la propriété lieu de naissance pour liaison TableView. */
    public StringProperty lieuNaissanceEleve() { return lieuNaissance; }

    /** Retourne la propriété sexe pour liaison TableView. */
    public StringProperty sexeEleve() { return sexe; }

    /** Retourne la propriété photo pour liaison TableView. */
    public StringProperty photoEleve() { return photo; }

    /** Retourne la propriété nationalité pour liaison TableView. */
    public StringProperty nationaliteEleve() { return nationalite; }

    /** Retourne la propriété statut pour liaison TableView. */
    public StringProperty statutEleve() { return statut; }

    /** Retourne la propriété classe pour liaison TableView. */
    public StringProperty classeEleve() { return classe; }
}
