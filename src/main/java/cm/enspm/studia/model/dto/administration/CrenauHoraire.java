package cm.enspm.studia.model.dto.administration;

public class CrenauHoraire {
    private int id;             // Identifiant unique du créneau
    private String jour;         // Jour de la semaine (ex: "Lundi")
    private String heureDebut;   // Heure de début (ex: "08:00")
    private String heureFin;     // Heure de fin (ex: "10:00")
    private int duree;  // Durée du créneau (en minutes)

    public CrenauHoraire(int id, String jour, String heureDebut, String heureFin, int duree) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = duree;
    }

    public int getId() { return id; }
    public String getJour() { return jour; }
    public String getHeureDebut() { return heureDebut; }
    public String getHeureFin() { return heureFin; }
    public int getDuree() { return duree; }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return String.format("%s %s-%s", jour, heureDebut, heureFin);
    }
}
