package cm.enspm.studia.model;

public class Parent extends Personne {
    private String cni;
    private String telephone;
    private String email;
    private String profession;
    private String adresse;
    private String lienParental;

    public Parent(
        String cni, String nom, String prenom, 
        String telephone, String email, String sexe, 
        String profession, String adresse, 
        String nationalite, String lienParental) {
        super(nom, prenom, "", "", sexe, nationalite);
        this.cni = cni;
        this.telephone = telephone;
        this.email = email;
        this.profession = profession;
        this.adresse = adresse;
        this.lienParental = lienParental;
    }

    //---------------------- Getters ----------------------

    public String getCni() {
        return cni;
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

    //---------------------- Setters ----------------------

    public void setCni(String cni) {
        this.cni = cni;
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

    @Override
    public String getRole() {
        return "Parent";
    }

    

}
