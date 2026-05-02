package cm.enspm.studia.model.dto.syllabus;

/**
 * Classe représentant un modèle du niveau d'étude dans le système éducatif
 */
public class NiveauEtude {
    
    /**
     * identifiant unique du niveau d'étude
     * ex: 111 pour premier systeme educatif, premier cycle et première année du secondaire = 6eme
     */
    private int idNiveauEtude;

    /**
     * libellé du niveau d'étude ex : Terminale D, 6eme A
     */
    private String libelle;

    /**
     * système éducatif auquel appartient le niveau d'étude
     */
    private SystemeEducatif systemeEducatif;

    /**
     * cycle d'enseignement auquel appartient le niveau d'étude
     */
    private Cycle cycle;

    /**
     * Constructeur de la classe NiveauEtude
     * @param idNiveauEtude
     * @param libelle
     * @param systemeEducatif
     * @param cycle
     */
    public NiveauEtude(int idNiveauEtude, String libelle, SystemeEducatif systemeEducatif, Cycle cycle) {
        this.idNiveauEtude = idNiveauEtude;
        this.libelle = libelle;
        this.systemeEducatif = systemeEducatif;
        this.cycle = cycle;
    }

    public int getIdNiveauEtude() {
        return idNiveauEtude;
    }

    public String getLibelle() {
        return libelle;
    }

    public SystemeEducatif getSystemeEducatif() {
        return systemeEducatif;
    }

    public Cycle getCycle() {
        return cycle;
    }
}
