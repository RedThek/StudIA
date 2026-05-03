package cm.enspm.studia.model.examens;

import java.time.LocalDate;
import java.util.Collection;

public class Trimestre {

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String libelle;

    /**
     * 
     */
    private String désignation;

    /**
     * 
     */
    private LocalDate début;

    /**
     * 
     */
    private LocalDate fin;

    /**
     * 
     */
    private Collection<Sequence> sequences;

    /**
     * 
     */
    public Trimestre(
        String code, String libelle, String désignation,
        LocalDate début, LocalDate fin, Collection<Sequence> sequences
    ) {
        this.code = code;
        this.libelle = libelle;
        this.désignation = désignation;
        this.début = début;
        this.fin = fin;
        this.sequences = sequences;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getDésignation() {
        return désignation;
    }

    public LocalDate getDébut() {
        return début;
    }

    public LocalDate getFin() {
        return fin;
    }

    public Collection<Sequence> getSequences() {
        return sequences;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDésignation(String désignation) {
        this.désignation = désignation;
    }

    public void setDébut(LocalDate début) {
        this.début = début;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public void setSequences(Collection<Sequence> sequences) {
        this.sequences = sequences;
    }
  
}
