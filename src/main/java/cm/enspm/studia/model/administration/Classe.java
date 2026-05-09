package cm.enspm.studia.model.administration;

import java.util.List;

import cm.enspm.studia.model.personnes.Eleve;
import cm.enspm.studia.model.personnes.Employe;
import cm.enspm.studia.model.syllabus.AnneeScolaire;
import cm.enspm.studia.model.syllabus.NiveauEtude;

/**
 * Classe représentant une classe (groupe d'élèves) de l'école secondaire pour une année scolaire donnée.
 * Ex : "3ème A 2022-2023", "Terminale S 2019-2020", "2nde B 2020-2021"
 */
public class Classe {

    /**
     * Niveau d'étude de la classe (ex: "3ème", "Terminale D", "2nde B")
     */
    private NiveauEtude niveauEtude;

    /**
     * 
     */
    private List<Eleve> eleves;

    /**
     * 
     */
    private Salle salle;

    /**
     * Année scolaire de la classe (ex: "2022-2023", "2019-2020", "2020-2021")
     */
    private AnneeScolaire anneeScolare;

    /**
     * l'enseignant principal de la classe
     */
    private Employe professeurPrincipal;

    public Classe(NiveauEtude niveauEtude, List<Eleve> eleves, Salle salle,
        AnneeScolaire anneeScolare, Employe professeurPrincipal) {
        this.niveauEtude = niveauEtude;
        this.eleves = eleves;
        this.salle = salle;
        this.anneeScolare = anneeScolare;
        this.professeurPrincipal = professeurPrincipal;
    }

    public String getLibelle() {
        return niveauEtude.getDesignation() + " " + anneeScolare.getLibelleAnneeScolaire();
    }


    public NiveauEtude getNiveauEtude() {
        return niveauEtude;
    }


    public AnneeScolaire getAnneeScolare() {
        return anneeScolare;
    }


    public Employe getProfesseurPrincipal() {
        return professeurPrincipal;
    }

    public void setNiveauEtude(NiveauEtude niveauEtude) {
        this.niveauEtude = niveauEtude;
    }


    public void setAnneeScolare(AnneeScolaire anneeScolare) {
        this.anneeScolare = anneeScolare;
    }


    public void setProfesseurPrincipal(Employe professeurPrincipal) {
        this.professeurPrincipal = professeurPrincipal;
    }

    /**
     * @return
     
    public Integer getEffectif() {
        // TODO implement here
        return null;
    }*/

    /**
     * @return
     
    public Integer getNombreFilles() {
        // TODO implement here
        return null;
    }*/

    /**
     * @return
     
    public Integer getNombreGarcons() {
        // TODO implement here
        return null;
    }*/

    /**
     * @return
     
    public Double getAgeMoyen() {
        // TODO implement here
        return null;
    }*/

    /**
     * @return
     
    public Integer getNouveaux() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     
    public Integer getRedoublants() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     
    public Eleve getPlusJeune() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     
    public Eleve getPlusVieux() {
        // TODO implement here
        return null;
    }**/

    public List<Eleve> getEleves() {
        return eleves;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

}
