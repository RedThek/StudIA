package cm.enspm.studia.model.dto.administration;

public class Programme {

    private int id;
    private Affectation affectation;
    private Salle salle;
    private CrenauHoraire crenauHoraire;

    
    public Programme(int id, Affectation affectation, Salle salle, CrenauHoraire crenauHoraire) {
        this.id = id;
        this.affectation = affectation;
        this.salle = salle;
        this.crenauHoraire = crenauHoraire;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Affectation getAffectation() {
        return affectation;
    }


    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }


    public Salle getSalle() {
        return salle;
    }


    public void setSalle(Salle salle) {
        this.salle = salle;
    }


    public CrenauHoraire getCrenauHoraire() {
        return crenauHoraire;
    }


    public void setCrenauHoraire(CrenauHoraire crenauHoraire) {
        this.crenauHoraire = crenauHoraire;
    }

    
}
