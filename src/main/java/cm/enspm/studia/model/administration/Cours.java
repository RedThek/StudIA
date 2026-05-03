package cm.enspm.studia.model.administration;

import java.time.LocalTime;
import java.util.*;

import cm.enspm.studia.model.personnes.Employe;

/**
 * 
 */
public class Cours {

    /**
     * 
     */
    private Integer jour;

    /**
     * 
     */
    private Integer semaine;

    /**
     * 
     */
    private Integer mois;

    /**
     * 
     */
    private LocalTime debut;

    /**
     * 
     */
    private LocalTime fin;

    /**
     * 
     */
    private List<Employe> enseignants;

    /**
     * 
     */
    private Classe classe;

    /**
     * 
     */
    private Salle salle;

    /**
     * Default constructor
     */
    public Cours() {
    }

    

    public Integer getJour() {
        return jour;
    }



    public Integer getSemaine() {
        return semaine;
    }



    public Integer getMois() {
        return mois;
    }



    public LocalTime getDebut() {
        return debut;
    }



    public LocalTime getFin() {
        return fin;
    }



    public List<Employe> getEnseignants() {
        return enseignants;
    }



    public Classe getClasse() {
        return classe;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setJour(Integer jour) {
        this.jour = jour;
    }

    public void setSemaine(Integer semaine) {
        this.semaine = semaine;
    }



    public void setMois(Integer mois) {
        this.mois = mois;
    }



    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }



    public void setFin(LocalTime fin) {
        this.fin = fin;
    }



    public void setEnseignants(List<Employe> enseignants) {
        this.enseignants = enseignants;
    }



    public void setClasse(Classe classe) {
        this.classe = classe;
    }



    public void setSalle(Salle salle) {
        this.salle = salle;
    }



    /**
     * @return
     */
    public List<Cours> getEmploiDeTempsClasse() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Cours> getEmploiDeTempsEnseignant() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Cours> getEmploiDeTempsSalle() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public String getProgressionEnseignement() {
        // TODO implement here
        return "";
    }

    public Integer getDuree(){
        return (int) java.time.Duration.between(debut, fin).toMinutes();
    }

}