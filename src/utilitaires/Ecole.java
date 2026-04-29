package utilitaires;

import modeles.*;
import services.*;

import java.util.List;

/**
 * Classe centrale représentant l'école secondaire.
 * Coordonne tous les services de gestion et fournit des statistiques globales.
 */
public class Ecole {

    // Informations de l'école
    private String nom;
    private String adresse;
    private String telephone;
    private String email;
    private int anneeCreation;

    // Tous les services de gestion
    private GestionEleves         gestionEleves;
    private GestionEnseignants    gestionEnseignants;
    private GestionAdministration gestionAdministration;
    private GestionMatieres       gestionMatieres;
    private GestionSalles         gestionSalles;
    private GestionClasses        gestionClasses;

    /**
     * Constructeur de l'école.
     */
    public Ecole(String nom, String adresse, String telephone, String email, int anneeCreation) {
        this.nom              = nom;
        this.adresse          = adresse;
        this.telephone        = telephone;
        this.email            = email;
        this.anneeCreation    = anneeCreation;

        // Initialisation de tous les services
        this.gestionEleves         = new GestionEleves();
        this.gestionEnseignants    = new GestionEnseignants();
        this.gestionAdministration = new GestionAdministration();
        this.gestionMatieres       = new GestionMatieres();
        this.gestionSalles         = new GestionSalles();
        this.gestionClasses        = new GestionClasses();
    }

    // ----------------------------- Getters des services -----------------------------

    public GestionEleves         getGestionEleves()         { return gestionEleves; }
    public GestionEnseignants    getGestionEnseignants()    { return gestionEnseignants; }
    public GestionAdministration getGestionAdministration() { return gestionAdministration; }
    public GestionMatieres       getGestionMatieres()       { return gestionMatieres; }
    public GestionSalles         getGestionSalles()         { return gestionSalles; }
    public GestionClasses        getGestionClasses()        { return gestionClasses; }

    // ----------------------------- Informations de l'école -----------------------------

    public String getNom()       { return nom; }
    public String getAdresse()   { return adresse; }

    // =========================================================================
    //  STATISTIQUES GLOBALES
    // =========================================================================

    /**
     * Affiche un tableau de bord complet avec les statistiques de l'école.
     */
    public void afficherTableauDeBord() {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║         TABLEAU DE BORD — " + nom.toUpperCase());
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf( "║  📍 Adresse    : %-33s║%n", adresse);
        System.out.printf( "║  📞 Téléphone  : %-33s║%n", telephone);
        System.out.printf( "║  📧 Email      : %-33s║%n", email);
        System.out.printf( "║  📅 Fondée en  : %-33d║%n", anneeCreation);
        System.out.println("╠══════════════════════════════════════════════════╣");
        System.out.printf( "║  👨‍🎓 Élèves          : %-28d║%n", gestionEleves.getNombreEleves());
        System.out.printf( "║  👨‍🏫 Enseignants      : %-28d║%n", gestionEnseignants.getNombreEnseignants());
        System.out.printf( "║  🏢 Administration   : %-28d║%n", gestionAdministration.getNombreAdministrateurs());
        System.out.printf( "║  📚 Matières         : %-28d║%n", gestionMatieres.getNombreMatieres());
        System.out.printf( "║  🏫 Classes          : %-28d║%n", gestionClasses.getNombreClasses());
        System.out.printf( "║  🚪 Salles           : %-28d║%n", gestionSalles.getNombreSalles());

        // Afficher le directeur s'il existe
        Administrateur directeur = gestionAdministration.getDirecteur();
        String nomDirecteur = (directeur != null) ? directeur.getNomComplet() : "Non défini";
        System.out.printf( "║  ⭐ Directeur(trice) : %-28s║%n", nomDirecteur);

        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    /**
     * Affiche le classement des meilleurs élèves de l'école.
     * @param topN nombre d'élèves à afficher dans le classement
     */
    public void afficherClassementEleves(int topN) {
        List<Eleve> classement = gestionEleves.trierParMoyenne();
        int limite = Math.min(topN, classement.size());

        System.out.println("\n===== CLASSEMENT DES " + limite + " MEILLEURS ÉLÈVES =====");
        for (int i = 0; i < limite; i++) {
            Eleve eleve = classement.get(i);
            System.out.printf("  %2d. %-25s | Classe: %-12s | Moy: %.2f/20%n",
                    i + 1, eleve.getNomComplet(), eleve.getClasseActuelle(),
                    eleve.calculerMoyenneGenerale());
        }
        System.out.println("================================================");
    }

    /**
     * Affiche une synthèse par classe : effectif et moyenne de classe.
     */
    public void afficherSyntheseParClasse() {
        List<Classe> classes = gestionClasses.getToutesLesClasses();
        if (classes.isEmpty()) {
            System.out.println("Aucune classe définie.");
            return;
        }

        System.out.println("\n===== SYNTHÈSE PAR CLASSE =====");
        for (Classe classe : classes) {
            List<Eleve> eleves = gestionEleves.rechercherParClasse(classe.getNom());
            double moyenneClasse = 0;
            if (!eleves.isEmpty()) {
                double somme = 0;
                for (Eleve e : eleves) somme += e.calculerMoyenneGenerale();
                moyenneClasse = somme / eleves.size();
            }
            System.out.printf("  %-15s | Effectif: %3d | Moyenne classe: %.2f/20%n",
                    classe.getNom(), eleves.size(), moyenneClasse);
        }
        System.out.println("================================");
    }
}
