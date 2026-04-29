package utilitaires;

import modeles.*;

/**
 * Classe utilitaire pour pré-remplir l'école avec des données d'exemple.
 * Utile pour tester et démontrer le fonctionnement de l'application.
 */
public class DonneesExemple {

    /**
     * Charge un ensemble de données d'exemple dans l'école fournie.
     * @param ecole l'instance d'école à peupler
     */
    public static void chargerDonnees(Ecole ecole) {
        System.out.println("\nChargement des données d'exemple...");

        chargerSalles(ecole);
        chargerClasses(ecole);
        chargerMatieres(ecole);
        chargerAdministration(ecole);
        chargerEnseignants(ecole);
        chargerEleves(ecole);

        System.out.println("✓ Données d'exemple chargées avec succès !\n");
    }

    // =========================================================================
    //  SALLES
    // =========================================================================
    private static void chargerSalles(Ecole ecole) {
        Salle s1 = new Salle("A101", "Salle Curie",      Salle.TYPE_CLASSE,        30, 1, 0);
        Salle s2 = new Salle("A102", "Salle Hugo",       Salle.TYPE_CLASSE,        30, 1, 0);
        Salle s3 = new Salle("A201", "Salle Pasteur",    Salle.TYPE_CLASSE,        28, 1, 1);
        Salle s4 = new Salle("B101", "Labo Chimie",      Salle.TYPE_LABO_SCIENCE,  20, 2, 0);
        Salle s5 = new Salle("B201", "Salle Info",       Salle.TYPE_INFORMATIQUE,  25, 2, 1);
        Salle s6 = new Salle("GYM1", "Gymnase Principal",Salle.TYPE_SPORT,         60, 3, 0);
        Salle s7 = new Salle("BIB1", "Bibliothèque",     Salle.TYPE_BIBLIOTHEQUE,  50, 1, 2);

        s1.setAProjecteur(true);  s1.setATableauxBlancs(true);
        s2.setAProjecteur(true);
        s3.setAProjecteur(true);  s3.setATableauxBlancs(true);
        s5.setAProjecteur(true);  s5.setATableauxBlancs(true);

        ecole.getGestionSalles().ajouterSalle(s1);
        ecole.getGestionSalles().ajouterSalle(s2);
        ecole.getGestionSalles().ajouterSalle(s3);
        ecole.getGestionSalles().ajouterSalle(s4);
        ecole.getGestionSalles().ajouterSalle(s5);
        ecole.getGestionSalles().ajouterSalle(s6);
        ecole.getGestionSalles().ajouterSalle(s7);
    }

    // =========================================================================
    //  CLASSES
    // =========================================================================
    private static void chargerClasses(Ecole ecole) {
        Classe c1 = new Classe("CL001", "3ème A",     "3ème",      "Général",       2024);
        Classe c2 = new Classe("CL002", "3ème B",     "3ème",      "Général",       2024);
        Classe c3 = new Classe("CL003", "2nde A",     "2nde",      "Général",       2024);
        Classe c4 = new Classe("CL004", "1ère S",     "1ère",      "Scientifique",  2024);
        Classe c5 = new Classe("CL005", "Terminale S","Terminale", "Scientifique",  2024);

        c1.setSalleId("A101");
        c2.setSalleId("A102");
        c3.setSalleId("A201");

        ecole.getGestionClasses().ajouterClasse(c1);
        ecole.getGestionClasses().ajouterClasse(c2);
        ecole.getGestionClasses().ajouterClasse(c3);
        ecole.getGestionClasses().ajouterClasse(c4);
        ecole.getGestionClasses().ajouterClasse(c5);
    }

    // =========================================================================
    //  MATIÈRES
    // =========================================================================
    private static void chargerMatieres(Ecole ecole) {
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("MAT001", "Mathématiques",       "MATH", "Algèbre, géométrie, analyse",   5, 4));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("FRA001", "Français",            "FR",   "Langue et littérature françaises", 4, 4));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("ANG001", "Anglais",             "ANG",  "Langue anglaise",                3, 3));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("PHY001", "Physique-Chimie",     "PHY",  "Sciences physiques et chimiques",  4, 3));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("SVT001", "Sciences de la Vie",  "SVT",  "Biologie et géologie",           3, 2));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("HIS001", "Histoire-Géographie", "HGE",  "Histoire et géographie",         3, 3));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("EPS001", "Éducation Physique",  "EPS",  "Sport et santé",                 2, 2));
        ecole.getGestionMatieres().ajouterMatiere(new Matiere("INF001", "Informatique",        "INFO", "Algorithmique et programmation", 2, 2));
    }

    // =========================================================================
    //  ADMINISTRATION
    // =========================================================================
    private static void chargerAdministration(Ecole ecole) {
        ecole.getGestionAdministration().ajouterAdministrateur(
            new Administrateur("ADM001", "Mohamed",   "Moktar",   "15/03/1975",
                "Palar", "0612345678", "mkr@ecole.fr",
                "EMP001", "Directeur", "Direction", 4500.0, "01/09/2010", true));

        ecole.getGestionAdministration().ajouterAdministrateur(
            new Administrateur("ADM002", "Mohamadou",   "Laminou",   "22/07/1985",
                "Pitoaren", "0698765432", "Lamine@ecole.fr",
                "EMP002", "Secrétaire", "Scolarité", 2200.0, "01/09/2015", false));

        ecole.getGestionAdministration().ajouterAdministrateur(
            new Administrateur("ADM003", "Messina",  "Mola",      "10/11/1980",
                "Missingleo", "0677654321", "messi@ecole.fr",
                "EMP003", "Comptable", "Finance", 2800.0, "01/01/2018", false));
    }

    // =========================================================================
    //  ENSEIGNANTS
    // =========================================================================
    private static void chargerEnseignants(Ecole ecole) {
        Enseignant e1 = new Enseignant("ENS001", "Leclerc", "Paul",    "20/05/1978",
                "palar", "0611223344", "p.leclerc@ecole.fr",
                "EMP010", "Mathématiques", "Master en Mathématiques", 2900.0, "01/09/2005");
        e1.ajouterMatiere("MAT001");
        e1.ajouterClasse("3ème A"); e1.ajouterClasse("1ère S");

        Enseignant e2 = new Enseignant("ENS002", "Moreau",  "Marie",   "14/09/1982",
                "Pitoaré", "0622334455", "m.moreau@ecole.fr",
                "EMP011", "Français", "Agrégation de Lettres", 3100.0, "01/09/2008");
        e2.ajouterMatiere("FRA001");
        e2.ajouterClasse("3ème A"); e2.ajouterClasse("3ème B"); e2.ajouterClasse("2nde A");

        Enseignant e3 = new Enseignant("ENS003", "Garcia",  "Carlos",  "05/12/1979",
                "Domayo", "0633445566", "c.garcia@ecole.fr",
                "EMP012", "Sciences Physiques", "Doctorat en Physique", 3200.0, "01/09/2006");
        e3.ajouterMatiere("PHY001");
        e3.ajouterClasse("1ère S"); e3.ajouterClasse("Terminale S");

        Enseignant e4 = new Enseignant("ENS004", "Lambert", "Julie",   "30/01/1990",
                "Dougoye", "0644556677", "j.lambert@ecole.fr",
                "EMP013", "Anglais", "Licence LLCE Anglais", 2600.0, "01/09/2016");
        e4.ajouterMatiere("ANG001");
        e4.ajouterClasse("3ème B"); e4.ajouterClasse("2nde A");

        ecole.getGestionEnseignants().ajouterEnseignant(e1);
        ecole.getGestionEnseignants().ajouterEnseignant(e2);
        ecole.getGestionEnseignants().ajouterEnseignant(e3);
        ecole.getGestionEnseignants().ajouterEnseignant(e4);

        // Associer les enseignants aux matières
        ecole.getGestionMatieres().modifierMatiere("MAT001", null, null, null, null, "ENS001");
        ecole.getGestionMatieres().modifierMatiere("FRA001", null, null, null, null, "ENS002");
        ecole.getGestionMatieres().modifierMatiere("PHY001", null, null, null, null, "ENS003");
        ecole.getGestionMatieres().modifierMatiere("ANG001", null, null, null, null, "ENS004");
    }

    // =========================================================================
    //  ÉLÈVES
    // =========================================================================
    private static void chargerEleves(Ecole ecole) {
        // Élèves de 3ème A
        Eleve el1 = new Eleve("ELV001", "Dubois",    "Lucas",    "15/04/2010",
                "Sekande",       "0655667788", "lucas.dubois@mail.fr",
                "MAT2024001", "3ème A", "Pierre Dubois",   "0655667700");
        el1.ajouterNote(new Note("MAT001", 15.5, "Devoir",   "10/10/2024", "Bon travail"));
        el1.ajouterNote(new Note("MAT001", 14.0, "Interro",  "15/11/2024", ""));
        el1.ajouterNote(new Note("FRA001", 12.0, "Devoir",   "12/10/2024", "Peut mieux faire"));

        Eleve el2 = new Eleve("ELV002", "Petit",     "Emma",     "22/08/2010",
                "Sekande",       "0666778899", "emma.petit@mail.fr",
                "MAT2024002", "3ème A", "Lucie Petit",     "0666778800");
        el2.ajouterNote(new Note("MAT001", 18.0, "Devoir",  "10/10/2024", "Excellent !"));
        el2.ajouterNote(new Note("FRA001", 17.5, "Devoir",  "12/10/2024", "Très bon"));
        el2.ajouterNote(new Note("ANG001", 16.0, "Examen",  "20/11/2024", ""));

        Eleve el3 = new Eleve("ELV003", "Leroy",     "Nathan",   "07/01/2010",
                "Sekande", "0677889900", "nathan.leroy@mail.fr",
                "MAT2024003", "3ème A", "Anne Leroy",      "0677889901");
        el3.ajouterNote(new Note("MAT001", 9.5,  "Devoir",  "10/10/2024", "Doit réviser"));
        el3.ajouterNote(new Note("FRA001", 11.0, "Devoir",  "12/10/2024", ""));
        el3.ajouterNote(new Note("EPS001", 16.0, "TP",      "05/10/2024", "Très actif"));

        // Élèves de 3ème B
        Eleve el4 = new Eleve("ELV004", "Simon",     "Chloé",    "30/11/2010",
                "Sekande",   "0688990011", "chloe.simon@mail.fr",
                "MAT2024004", "3ème B", "Marc Simon",      "0688990000");
        el4.ajouterNote(new Note("FRA001", 14.5, "Examen", "20/11/2024", ""));
        el4.ajouterNote(new Note("ANG001", 15.0, "Devoir", "25/11/2024", "Bon accent"));

        // Élève de Terminale S
        Eleve el5 = new Eleve("ELV005", "Roux",      "Alexis",   "12/03/2007",
                "Sekande",      "0699001122", "alexis.roux@mail.fr",
                "MAT2024005", "Terminale S", "René Roux",  "0699001100");
        el5.ajouterNote(new Note("MAT001", 19.0, "Examen", "15/11/2024", "Excellent"));
        el5.ajouterNote(new Note("PHY001", 17.0, "TP",     "08/11/2024", "Très sérieux"));

        // Enregistrement dans le service
        ecole.getGestionEleves().ajouterEleve(el1);
        ecole.getGestionEleves().ajouterEleve(el2);
        ecole.getGestionEleves().ajouterEleve(el3);
        ecole.getGestionEleves().ajouterEleve(el4);
        ecole.getGestionEleves().ajouterEleve(el5);

        // Inscrire les élèves dans leur classe
        Classe classe3A = ecole.getGestionClasses().rechercherParNom("3ème A");
        if (classe3A != null) {
            classe3A.inscrireEleve("ELV001");
            classe3A.inscrireEleve("ELV002");
            classe3A.inscrireEleve("ELV003");
        }
        Classe classe3B = ecole.getGestionClasses().rechercherParNom("3ème B");
        if (classe3B != null) {
            classe3B.inscrireEleve("ELV004");
        }
        Classe classeTermS = ecole.getGestionClasses().rechercherParNom("Terminale S");
        if (classeTermS != null) {
            classeTermS.inscrireEleve("ELV005");
        }
    }
}
