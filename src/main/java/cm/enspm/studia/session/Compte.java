package cm.enspm.studia.session;

public class Compte {
    private String nom;
    private String motDePasse;
    private String role;
    private Integer autorisation;

    public Compte(String nom, String motDePasse, String role, Integer autorisation) {
        this.nom = nom;
        this.motDePasse = motDePasse;
        this.role = role;
        this.autorisation = autorisation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Integer autorisation) {
        this.autorisation = autorisation;
    }
}
