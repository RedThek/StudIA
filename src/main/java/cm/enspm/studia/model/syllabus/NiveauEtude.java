package cm.enspm.studia.model.syllabus;

import java.util.List;

/**
 * Classe représentant un modèle du niveau d'étude dans le système éducatif
 */
public class NiveauEtude {
/**
     * 
     */
    private String designation;

    /**
     * 
     */
    private List<Matiere> matieres;

    /**
     * 
     */
    private List<Integer> heuresMatieres;

    public NiveauEtude(String designation, List<Matiere> matieres, List<Integer> heuresMatieres) {
        this.designation = designation;
        this.matieres = matieres;
        this.heuresMatieres = heuresMatieres;
    }

    public String getDesignation() {
        return designation;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public List<Integer> getHeuresMatieres() {
        return heuresMatieres;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public void setHeuresMatieres(List<Integer> heuresMatieres) {
        this.heuresMatieres = heuresMatieres;
    }

}
