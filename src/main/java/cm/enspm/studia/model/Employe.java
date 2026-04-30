package cm.enspm.studia.model;

/**
 * Classe représentant le personnel de l'école secondaire.
 * Un membre du personnel peut enseigner plusieurs matières dans plusieurs classes ou pas.
 */
public class Employe extends Personne {

    private String identifiant;
    private String cni;
    private String poste;
    private String telephone;
    private String email;
    private String adresse;
    private String grade;


    /**
     * Constructeur de la classe Employe.
     */
    public Employe(
        String nom, String prenom, String dateNaissance, String sexe,
        String nationalite, String identifiant, String cni, String poste, 
        String telephone, String email, String adresse, String grade
    ) {
        super(nom, prenom, dateNaissance, "", sexe, nationalite);
        this.identifiant = identifiant;
        this.cni = cni;
        this.poste = poste;
        this.telephone = telephone;
        this.email = email;
        this.adresse = adresse;
        this.grade = grade;
    }

    //------------------ Getters ------------------

    public String getIdentifiant() {
        return identifiant;
    }

    public String getCni() {
        return cni;
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

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setCni(String cni) {
        this.cni = cni;
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
}
