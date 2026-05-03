package cm.enspm.studia.model.personnes;

/**
 * Classe représentant le personnel de l'école secondaire.
 * Un membre du personnel peut enseigner plusieurs matières dans plusieurs classes ou pas.
 */
public class Employe extends Personne {

    /**
     * Carte Nationale d'Identité (CNI) de l'employé.
     */
    private String numeroCNI;

    /**
     * Numéro de téléphone de l'employé.
     */
    private String telephone;

    /**
     * Adresse email de l'employé.
     */
    private String email;

    /**
     * Poste occupé par l'employé au sein de l'école secondaire (ex: enseignant, surveillant, etc.).
     */
    private String poste;

    /**
     * Diplôme le plus élevé de l'employé.
     */
    private String grade;

    /**
     * Adresse physique de l'employé.
     */
    private String adresse;


    /**
     * Constructeur de la classe Employe.
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param sexe
     * @param nationalite
     * @param cni
     * @param poste
     * @param telephone
     * @param email
     * @param adresse
     * @param grade
     */
    public Employe(
        String nom, String prenom, String dateNaissance, String sexe,
        String nationalite, String cni, String poste, 
        String telephone, String email, String adresse, String grade
    ) {
        super(nom, prenom, dateNaissance, "", sexe, nationalite);
        this.numeroCNI = cni;
        this.poste = poste;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.grade = grade;
    }

    //------------------ Getters ------------------

    public String getNumeroCNI() {
        return numeroCNI;
    }

    public String getPoste() {
        return poste;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getGrade() {
        return grade;
    }

    //------------------ Setters ------------------

    public void setCni(String cni) {
        this.numeroCNI = cni;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String getRole() {
        return "Employé";
    }

    /**
     * @return
     */
    public Boolean estEnseignant() {
        // TODO implement here
        return null;
    }
}
