package cm.enspm.studia.model.dto.syllabus;

public class SystemeEducatif {

    /**
     * identifiant du système éducatif (ex: "SE001")
     */
    private int identifiantSysteme;
    /**
     * libellé du système éducatif (ex: FR ou EN)
     */
    private String libelle;
    /**
     * designation du système éducatif (ex: Système éducatif francophone ou Système éducatif anglophone)
     */
    private String designation;

    /**
     * Constructeur de la classe SystemeEducatif
     * @param identifiantSysteme
     * @param libelle
     * @param designation
     */
    public SystemeEducatif(int identifiantSysteme, String libelle, String designation) {
        this.identifiantSysteme = identifiantSysteme;
        this.libelle = libelle;
        this.designation = designation;
    }

    public int getIdentifiantSysteme() {
        return identifiantSysteme;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setIdentifiantSysteme(int identifiantSysteme) {
        this.identifiantSysteme = identifiantSysteme;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
