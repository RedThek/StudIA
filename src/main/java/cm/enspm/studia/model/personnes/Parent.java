package cm.enspm.studia.model.personnes;

import java.util.List;

public class Parent extends Personne {
    /**
     * Le numero de CNI du parent
     */
    private String numeroCNI;

    /**
     * Le numero de telephone du parent.
     */
    private String telephone;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String profession;

    /**
     * 
     */
    private String adresse;

    /**
     * 
     */
    private String lienParental;

    /**
     * 
     */
    private List<Eleve> enfants;

    public Parent(
        String cni, String nom, String prenom, 
        String telephone, String email, String sexe, 
        String profession, String adresse, 
        String nationalite, String lienParental, List<Eleve> enfants) {
        super(nom, prenom, "", "", sexe, nationalite);
        this.numeroCNI = cni;
        this.telephone = telephone;
        this.email = email;
        this.profession = profession;
        this.adresse = adresse;
        this.lienParental = lienParental;
        this.enfants = enfants ;
    }

    //---------------------- Getters ----------------------

    public String getNumeroCNI() {
        return numeroCNI;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfession() {
        return profession;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getLienParental() {
        return lienParental;
    }

    public List<Eleve> getEnfants() {
        return enfants;
    }


    //---------------------- Setters ----------------------

    public void setNumeroCNI(String cni) {
        this.numeroCNI = cni;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setLienParental(String lienParental) {
        this.lienParental = lienParental;
    }

    public void setEnfants(List<Eleve> enfants) {
        this.enfants = enfants;
    }

    @Override
    public String getRole() {
        return "Parent";
    }

}
