package cm.enspm.studia.model.examens;

import java.time.LocalDate;

public class Sequence {
    /**
     * 
     */
    private Integer numero;

    /**
     * 
     */
    private String libelle;

    /**
     * 
     */
    private String designation;

    /**
     * 
     */
    private LocalDate debut;

    /**
     * 
     */
    private LocalDate fin;
    
    /**
     * 
     */
    public Sequence(Integer numero, String libelle, String designation, LocalDate debut, LocalDate fin) {
        this.numero = numero;
        this.libelle = libelle;
        this.designation = designation;
        this.debut = debut;
        this.fin = fin;
    }


    public String getLibelle() {
        return libelle;
    }


    public String getDesignation() {
        return designation;
    }

    public Integer getNumeroSequence() {
        return numero;
    }


    public LocalDate getDebut() {
        return debut;
    }


    public LocalDate getFin() {
        return fin;
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public void setNumero(Integer numero) {
        this.numero = numero;
    }


    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }


    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
    
}
