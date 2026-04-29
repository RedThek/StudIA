package interfaces;

import modeles.*;
import services.*;
import utilitaires.*;

import java.util.List;

/**
 * Classe principale de l'application de gestion de l'école secondaire.
 * Contient la boucle principale et les interactions utilisateur via le menu console.
 *
 * LANCEMENT : javac -d out/production src/**\/*.java && java -cp out/production interfaces.Application
 *   (ou via un IDE comme IntelliJ IDEA / Eclipse)
 */
public class Application {

    // Instance centrale de l'école
    private static Ecole ecole;

    // =========================================================================
    //  POINT D'ENTRÉE DU PROGRAMME
    // =========================================================================

    public static void main(String[] args) {

        // Initialisation de l'école
        ecole = new Ecole(
                "Lycée du Goupe 8",
                "Palar, Maroua",
                "699-095-247",
                "@dibalalatortue@gmail.com",
                2026
        );

        // Proposition de charger des données de démonstration
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║  Bienvenue dans le système de gestion scolaire ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        boolean charger = MenuConsole.lireOuiNon("Charger des données d'exemple pour la démonstration ?");
        if (charger) {
            DonneesExemple.chargerDonnees(ecole);
        }

        // Boucle principale du menu
        boolean continuer = true;
        while (continuer) {
            MenuConsole.afficherMenuPrincipal();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1  -> menuEleves();
                case 2  -> menuEnseignants();
                case 3  -> menuAdministration();
                case 4  -> menuMatieres();
                case 5  -> menuSalles();
                case 6  -> menuClasses();
                case 7  -> menuStatistiques();
                case 0  -> continuer = false;
                default -> System.out.println("  ⚠ Choix invalide. Veuillez recommencer.");
            }
        }

        System.out.println("\nAu revoir ! Merci d'avoir utilisé le système de gestion scolaire.");
        MenuConsole.fermer();
    }

    // =========================================================================
    //  MENU ÉLÈVES
    // =========================================================================

    private static void menuEleves() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuEleves();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1  -> ajouterEleve();
                case 2  -> modifierEleve();
                case 3  -> supprimerEleve();
                case 4  -> rechercherEleveParMatricule();
                case 5  -> rechercherEleveParNom();
                case 6  -> afficherElevesClasse();
                case 7  -> ecole.getGestionEleves().afficherTousLesEleves();
                case 8  -> menuTriEleves();
                case 9  -> ajouterNoteEleve();
                case 10 -> afficherBulletin();
                case 0  -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterEleve() {
        System.out.println("\n--- AJOUTER UN ÉLÈVE ---");
        String id        = MenuConsole.lireChaine("Identifiant");
        String nom       = MenuConsole.lireChaine("Nom");
        String prenom    = MenuConsole.lireChaine("Prénom");
        String dateNaiss = MenuConsole.lireChaine("Date de naissance (JJ/MM/AAAA)");
        String adresse   = MenuConsole.lireChaine("Adresse");
        String tel       = MenuConsole.lireChaine("Téléphone");
        String email     = MenuConsole.lireChaine("Email");
        String matricule = MenuConsole.lireChaine("Numéro de matricule");
        String classe    = MenuConsole.lireChaine("Classe actuelle (ex: 3ème A)");
        String nomParent = MenuConsole.lireChaine("Nom du parent/tuteur");
        String telParent = MenuConsole.lireChaine("Téléphone du parent/tuteur");

        Eleve nouvelEleve = new Eleve(id, nom, prenom, dateNaiss, adresse, tel, email,
                                      matricule, classe, nomParent, telParent);
        ecole.getGestionEleves().ajouterEleve(nouvelEleve);
    }

    private static void modifierEleve() {
        System.out.println("\n--- MODIFIER UN ÉLÈVE ---");
        String matricule = MenuConsole.lireChaine("Numéro de matricule de l'élève à modifier");
        System.out.println("(Laissez vide pour ne pas modifier un champ)");
        String nom     = MenuConsole.lireChaine("Nouveau nom");
        String prenom  = MenuConsole.lireChaine("Nouveau prénom");
        String classe  = MenuConsole.lireChaine("Nouvelle classe");
        String email   = MenuConsole.lireChaine("Nouvel email");
        String tel     = MenuConsole.lireChaine("Nouveau téléphone");

        ecole.getGestionEleves().modifierEleve(
                matricule,
                nom.isEmpty()    ? null : nom,
                prenom.isEmpty() ? null : prenom,
                classe.isEmpty() ? null : classe,
                email.isEmpty()  ? null : email,
                tel.isEmpty()    ? null : tel
        );
    }

    private static void supprimerEleve() {
        System.out.println("\n--- SUPPRIMER UN ÉLÈVE ---");
        String matricule = MenuConsole.lireChaine("Numéro de matricule de l'élève à supprimer");
        boolean confirme = MenuConsole.lireOuiNon("Confirmer la suppression ?");
        if (confirme) {
            ecole.getGestionEleves().supprimerEleve(matricule);
        } else {
            System.out.println("Suppression annulée.");
        }
    }

    private static void rechercherEleveParMatricule() {
        String matricule = MenuConsole.lireChaine("Numéro de matricule");
        Eleve eleve = ecole.getGestionEleves().rechercherParMatricule(matricule);
        if (eleve != null) System.out.println("\n" + eleve);
        else System.out.println("  ✗ Aucun élève trouvé avec ce matricule.");
    }

    private static void rechercherEleveParNom() {
        String motCle = MenuConsole.lireChaine("Nom ou prénom à rechercher");
        List<Eleve> resultats = ecole.getGestionEleves().rechercherParNom(motCle);
        if (resultats.isEmpty()) {
            System.out.println("  ✗ Aucun élève trouvé.");
        } else {
            System.out.println("  " + resultats.size() + " résultat(s) :");
            for (Eleve e : resultats) System.out.println("  " + e);
        }
    }

    private static void afficherElevesClasse() {
        String classe = MenuConsole.lireChaine("Nom de la classe (ex: 3ème A)");
        List<Eleve> eleves = ecole.getGestionEleves().rechercherParClasse(classe);
        if (eleves.isEmpty()) {
            System.out.println("  ✗ Aucun élève dans cette classe.");
        } else {
            System.out.println("  " + eleves.size() + " élève(s) en " + classe + " :");
            for (Eleve e : eleves) System.out.println("  " + e);
        }
    }

    private static void menuTriEleves() {
        System.out.println("\nTrier par : 1-Nom  2-Classe  3-Moyenne  4-Matricule");
        int choix = MenuConsole.lireEntier("Choix du tri");
        List<Eleve> trie = switch (choix) {
            case 1  -> ecole.getGestionEleves().trierParNom();
            case 2  -> ecole.getGestionEleves().trierParClasse();
            case 3  -> ecole.getGestionEleves().trierParMoyenne();
            case 4  -> ecole.getGestionEleves().trierParMatricule();
            default -> ecole.getGestionEleves().getTousLesEleves();
        };
        System.out.println("  Résultats triés :");
        for (Eleve e : trie) System.out.println("  " + e);
    }

    private static void ajouterNoteEleve() {
        System.out.println("\n--- AJOUTER UNE NOTE ---");
        String matricule = MenuConsole.lireChaine("Matricule de l'élève");
        String matiereId = MenuConsole.lireChaine("Identifiant de la matière (ex: MAT001)");
        double valeur    = MenuConsole.lireDecimal("Note (sur 20)");
        String type      = MenuConsole.lireChaine("Type d'évaluation (Devoir/Examen/Interro/TP)");
        String date      = MenuConsole.lireChaine("Date (JJ/MM/AAAA)");
        String commentaire = MenuConsole.lireChaine("Commentaire (optionnel)");

        Note note = new Note(matiereId, valeur, type, date, commentaire);
        ecole.getGestionEleves().ajouterNoteEleve(matricule, note);
    }

    private static void afficherBulletin() {
        String matricule = MenuConsole.lireChaine("Matricule de l'élève");
        ecole.getGestionEleves().afficherBulletinEleve(matricule);
    }

    // =========================================================================
    //  MENU ENSEIGNANTS
    // =========================================================================

    private static void menuEnseignants() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuEnseignants();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1 -> ajouterEnseignant();
                case 2 -> modifierEnseignant();
                case 3 -> supprimerEnseignant();
                case 4 -> {
                    String num = MenuConsole.lireChaine("N° employé");
                    Enseignant e = ecole.getGestionEnseignants().rechercherParNumeroEmploye(num);
                    System.out.println(e != null ? "\n" + e : "  ✗ Non trouvé.");
                }
                case 5 -> {
                    String motCle = MenuConsole.lireChaine("Nom ou prénom");
                    List<Enseignant> res = ecole.getGestionEnseignants().rechercherParNom(motCle);
                    res.forEach(e -> System.out.println("  " + e));
                }
                case 6 -> {
                    String spec = MenuConsole.lireChaine("Spécialité");
                    ecole.getGestionEnseignants().rechercherParSpecialite(spec)
                         .forEach(e -> System.out.println("  " + e));
                }
                case 7 -> assignerMatiereEnseignant();
                case 8 -> ecole.getGestionEnseignants().afficherTousLesEnseignants();
                case 9 -> trierEnseignants();
                case 0 -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterEnseignant() {
        System.out.println("\n--- AJOUTER UN ENSEIGNANT ---");
        String id          = MenuConsole.lireChaine("Identifiant");
        String nom         = MenuConsole.lireChaine("Nom");
        String prenom      = MenuConsole.lireChaine("Prénom");
        String dateNaiss   = MenuConsole.lireChaine("Date de naissance (JJ/MM/AAAA)");
        String adresse     = MenuConsole.lireChaine("Adresse");
        String tel         = MenuConsole.lireChaine("Téléphone");
        String email       = MenuConsole.lireChaine("Email");
        String numEmploye  = MenuConsole.lireChaine("Numéro d'employé");
        String specialite  = MenuConsole.lireChaine("Spécialité");
        String diplome     = MenuConsole.lireChaine("Diplôme");
        double salaire     = MenuConsole.lireDecimal("Salaire mensuel (€)");
        String dateEmb     = MenuConsole.lireChaine("Date d'embauche (JJ/MM/AAAA)");

        ecole.getGestionEnseignants().ajouterEnseignant(
                new Enseignant(id, nom, prenom, dateNaiss, adresse, tel, email,
                               numEmploye, specialite, diplome, salaire, dateEmb));
    }

    private static void modifierEnseignant() {
        System.out.println("\n--- MODIFIER UN ENSEIGNANT ---");
        String numEmploye = MenuConsole.lireChaine("N° employé");
        System.out.println("(Laissez vide pour ne pas modifier)");
        String nom       = MenuConsole.lireChaine("Nouveau nom");
        String prenom    = MenuConsole.lireChaine("Nouveau prénom");
        String spec      = MenuConsole.lireChaine("Nouvelle spécialité");
        String salStr    = MenuConsole.lireChaine("Nouveau salaire (€)");
        String email     = MenuConsole.lireChaine("Nouvel email");

        Double salaire = salStr.isEmpty() ? null : Double.parseDouble(salStr.replace(",", "."));
        ecole.getGestionEnseignants().modifierEnseignant(numEmploye,
                nom.isEmpty()   ? null : nom,
                prenom.isEmpty()? null : prenom,
                spec.isEmpty()  ? null : spec,
                salaire,
                email.isEmpty() ? null : email);
    }

    private static void supprimerEnseignant() {
        String num = MenuConsole.lireChaine("N° employé de l'enseignant à supprimer");
        if (MenuConsole.lireOuiNon("Confirmer ?")) {
            ecole.getGestionEnseignants().supprimerEnseignant(num);
        }
    }

    private static void assignerMatiereEnseignant() {
        String num      = MenuConsole.lireChaine("N° employé de l'enseignant");
        String matiere  = MenuConsole.lireChaine("Identifiant de la matière (ex: MAT001)");
        Enseignant ens  = ecole.getGestionEnseignants().rechercherParNumeroEmploye(num);
        if (ens != null) {
            ens.ajouterMatiere(matiere);
            System.out.println("Matière " + matiere + " assignée à " + ens.getNomComplet());
        } else {
            System.out.println("  ✗ Enseignant non trouvé.");
        }
    }

    private static void trierEnseignants() {
        System.out.println("Trier par : 1-Nom  2-Spécialité  3-Salaire");
        int choix = MenuConsole.lireEntier("Choix");
        List<Enseignant> trie = switch (choix) {
            case 1  -> ecole.getGestionEnseignants().trierParNom();
            case 2  -> ecole.getGestionEnseignants().trierParSpecialite();
            case 3  -> ecole.getGestionEnseignants().trierParSalaire();
            default -> ecole.getGestionEnseignants().getTousLesEnseignants();
        };
        trie.forEach(e -> System.out.println("  " + e));
    }

    // =========================================================================
    //  MENU ADMINISTRATION
    // =========================================================================

    private static void menuAdministration() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuAdministration();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1 -> ajouterAdministrateur();
                case 2 -> modifierAdministrateur();
                case 3 -> supprimerAdministrateur();
                case 4 -> {
                    String num = MenuConsole.lireChaine("N° employé");
                    Administrateur a = ecole.getGestionAdministration().rechercherParNumeroEmploye(num);
                    System.out.println(a != null ? "\n" + a : "  ✗ Non trouvé.");
                }
                case 5 -> {
                    String motCle = MenuConsole.lireChaine("Nom ou prénom");
                    ecole.getGestionAdministration().rechercherParNom(motCle)
                         .forEach(a -> System.out.println("  " + a));
                }
                case 6 -> {
                    String service = MenuConsole.lireChaine("Nom du service");
                    ecole.getGestionAdministration().rechercherParService(service)
                         .forEach(a -> System.out.println("  " + a));
                }
                case 7 -> ecole.getGestionAdministration().afficherTousLesAdministrateurs();
                case 8 -> {
                    System.out.println("Trier par : 1-Nom  2-Poste  3-Salaire");
                    int t = MenuConsole.lireEntier("Choix");
                    List<Administrateur> trie = switch (t) {
                        case 1  -> ecole.getGestionAdministration().trierParNom();
                        case 2  -> ecole.getGestionAdministration().trierParPoste();
                        case 3  -> ecole.getGestionAdministration().trierParSalaire();
                        default -> ecole.getGestionAdministration().getTousLesAdministrateurs();
                    };
                    trie.forEach(a -> System.out.println("  " + a));
                }
                case 0 -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterAdministrateur() {
        System.out.println("\n--- AJOUTER UN MEMBRE DE L'ADMINISTRATION ---");
        String id        = MenuConsole.lireChaine("Identifiant");
        String nom       = MenuConsole.lireChaine("Nom");
        String prenom    = MenuConsole.lireChaine("Prénom");
        String dateNaiss = MenuConsole.lireChaine("Date de naissance (JJ/MM/AAAA)");
        String adresse   = MenuConsole.lireChaine("Adresse");
        String tel       = MenuConsole.lireChaine("Téléphone");
        String email     = MenuConsole.lireChaine("Email");
        String numEmp    = MenuConsole.lireChaine("Numéro d'employé");
        String poste     = MenuConsole.lireChaine("Poste (ex: Secrétaire)");
        String service   = MenuConsole.lireChaine("Service (ex: Scolarité)");
        double salaire   = MenuConsole.lireDecimal("Salaire mensuel (€)");
        String dateEmb   = MenuConsole.lireChaine("Date d'embauche (JJ/MM/AAAA)");
        boolean directeur= MenuConsole.lireOuiNon("Est-il/elle directeur(trice) ?");

        ecole.getGestionAdministration().ajouterAdministrateur(
                new Administrateur(id, nom, prenom, dateNaiss, adresse, tel, email,
                                   numEmp, poste, service, salaire, dateEmb, directeur));
    }

    private static void modifierAdministrateur() {
        String num = MenuConsole.lireChaine("N° employé");
        System.out.println("(Laissez vide pour ne pas modifier)");
        String poste   = MenuConsole.lireChaine("Nouveau poste");
        String service = MenuConsole.lireChaine("Nouveau service");
        String salStr  = MenuConsole.lireChaine("Nouveau salaire (€)");
        String email   = MenuConsole.lireChaine("Nouvel email");

        Double salaire = salStr.isEmpty() ? null : Double.parseDouble(salStr.replace(",", "."));
        ecole.getGestionAdministration().modifierAdministrateur(num,
                poste.isEmpty()   ? null : poste,
                service.isEmpty() ? null : service,
                salaire,
                email.isEmpty()   ? null : email);
    }

    private static void supprimerAdministrateur() {
        String num = MenuConsole.lireChaine("N° employé à supprimer");
        if (MenuConsole.lireOuiNon("Confirmer ?")) {
            ecole.getGestionAdministration().supprimerAdministrateur(num);
        }
    }

    // =========================================================================
    //  MENU MATIÈRES
    // =========================================================================

    private static void menuMatieres() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuMatieres();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1 -> ajouterMatiere();
                case 2 -> modifierMatiere();
                case 3 -> {
                    String id = MenuConsole.lireChaine("Identifiant de la matière");
                    if (MenuConsole.lireOuiNon("Confirmer la suppression ?")) {
                        ecole.getGestionMatieres().supprimerMatiere(id);
                    }
                }
                case 4 -> {
                    String motCle = MenuConsole.lireChaine("Mot-clé (nom de la matière)");
                    ecole.getGestionMatieres().rechercherParNom(motCle)
                         .forEach(m -> System.out.println("  " + m));
                }
                case 5 -> ecole.getGestionMatieres().afficherToutesLesMatieres();
                case 6 -> {
                    System.out.println("Trier par : 1-Nom  2-Coefficient  3-Heures/semaine");
                    int t = MenuConsole.lireEntier("Choix");
                    List<Matiere> trie = switch (t) {
                        case 1  -> ecole.getGestionMatieres().trierParNom();
                        case 2  -> ecole.getGestionMatieres().trierParCoefficient();
                        case 3  -> ecole.getGestionMatieres().trierParHeuresSemaine();
                        default -> ecole.getGestionMatieres().getToutesLesMatieres();
                    };
                    trie.forEach(m -> System.out.println("  " + m));
                }
                case 0 -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterMatiere() {
        System.out.println("\n--- AJOUTER UNE MATIÈRE ---");
        String id    = MenuConsole.lireChaine("Identifiant (ex: MAT001)");
        String nom   = MenuConsole.lireChaine("Nom complet");
        String code  = MenuConsole.lireChaine("Code court (ex: MATH)");
        String desc  = MenuConsole.lireChaine("Description");
        int coeff    = MenuConsole.lireEntier("Coefficient à l'examen");
        int heures   = MenuConsole.lireEntier("Heures par semaine");

        ecole.getGestionMatieres().ajouterMatiere(new Matiere(id, nom, code, desc, coeff, heures));
    }

    private static void modifierMatiere() {
        String id = MenuConsole.lireChaine("Identifiant de la matière");
        System.out.println("(Laissez vide pour ne pas modifier)");
        String nom   = MenuConsole.lireChaine("Nouveau nom");
        String desc  = MenuConsole.lireChaine("Nouvelle description");
        String cStr  = MenuConsole.lireChaine("Nouveau coefficient");
        String hStr  = MenuConsole.lireChaine("Nouvelles heures/semaine");
        String ensId = MenuConsole.lireChaine("Nouvel identifiant enseignant");

        Integer coeff  = cStr.isEmpty()  ? null : Integer.parseInt(cStr);
        Integer heures = hStr.isEmpty()  ? null : Integer.parseInt(hStr);
        ecole.getGestionMatieres().modifierMatiere(id,
                nom.isEmpty()  ? null : nom,
                desc.isEmpty() ? null : desc,
                coeff, heures,
                ensId.isEmpty()? null : ensId);
    }

    // =========================================================================
    //  MENU SALLES
    // =========================================================================

    private static void menuSalles() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuSalles();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1 -> ajouterSalle();
                case 2 -> modifierSalle();
                case 3 -> {
                    String id = MenuConsole.lireChaine("Identifiant de la salle");
                    if (MenuConsole.lireOuiNon("Confirmer ?")) {
                        ecole.getGestionSalles().supprimerSalle(id);
                    }
                }
                case 4 -> {
                    String id = MenuConsole.lireChaine("Identifiant de la salle");
                    Salle s = ecole.getGestionSalles().rechercherParIdentifiant(id);
                    System.out.println(s != null ? "\n" + s : "  ✗ Non trouvée.");
                }
                case 5 -> {
                    List<Salle> dispo = ecole.getGestionSalles().rechercherSallesDisponibles();
                    System.out.println("  " + dispo.size() + " salle(s) disponible(s) :");
                    dispo.forEach(s -> System.out.println("  " + s));
                }
                case 6 -> ecole.getGestionSalles().afficherToutesLesSalles();
                case 7 -> {
                    System.out.println("Trier par : 1-Identifiant  2-Capacité  3-Type");
                    int t = MenuConsole.lireEntier("Choix");
                    List<Salle> trie = switch (t) {
                        case 1  -> ecole.getGestionSalles().trierParIdentifiant();
                        case 2  -> ecole.getGestionSalles().trierParCapacite();
                        case 3  -> ecole.getGestionSalles().trierParType();
                        default -> ecole.getGestionSalles().getToutesLesSalles();
                    };
                    trie.forEach(s -> System.out.println("  " + s));
                }
                case 0 -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterSalle() {
        System.out.println("\n--- AJOUTER UNE SALLE ---");
        String id    = MenuConsole.lireChaine("Identifiant (ex: A101)");
        String nom   = MenuConsole.lireChaine("Nom de la salle");
        System.out.println("Types : Salle de classe / Laboratoire de sciences / Salle informatique / Salle de sport / Bibliothèque");
        String type  = MenuConsole.lireChaine("Type de salle");
        int cap      = MenuConsole.lireEntier("Capacité maximale");
        int bat      = MenuConsole.lireEntier("Numéro du bâtiment");
        int etage    = MenuConsole.lireEntier("Numéro d'étage (0 = RDC)");

        Salle s = new Salle(id, nom, type, cap, bat, etage);
        s.setAProjecteur(MenuConsole.lireOuiNon("Dispose d'un vidéoprojecteur ?"));
        s.setATableauxBlancs(MenuConsole.lireOuiNon("Dispose de tableaux blancs interactifs ?"));
        ecole.getGestionSalles().ajouterSalle(s);
    }

    private static void modifierSalle() {
        String id = MenuConsole.lireChaine("Identifiant de la salle");
        System.out.println("(Laissez vide pour ne pas modifier)");
        String nom     = MenuConsole.lireChaine("Nouveau nom");
        String capStr  = MenuConsole.lireChaine("Nouvelle capacité");

        Integer capacite = capStr.isEmpty() ? null : Integer.parseInt(capStr);
        ecole.getGestionSalles().modifierSalle(id,
                nom.isEmpty() ? null : nom, capacite, null, null, null);
    }

    // =========================================================================
    //  MENU CLASSES
    // =========================================================================

    private static void menuClasses() {
        boolean retour = false;
        while (!retour) {
            MenuConsole.afficherMenuClasses();
            int choix = MenuConsole.lireEntier("Votre choix");

            switch (choix) {
                case 1 -> ajouterClasse();
                case 2 -> modifierClasse();
                case 3 -> {
                    String id = MenuConsole.lireChaine("Identifiant de la classe");
                    if (MenuConsole.lireOuiNon("Confirmer ?")) {
                        ecole.getGestionClasses().supprimerClasse(id);
                    }
                }
                case 4 -> {
                    String classId  = MenuConsole.lireChaine("Identifiant de la classe");
                    String eleveId  = MenuConsole.lireChaine("Identifiant de l'élève");
                    Classe c = ecole.getGestionClasses().rechercherParIdentifiant(classId);
                    if (c != null) {
                        c.inscrireEleve(eleveId);
                        System.out.println("Élève " + eleveId + " inscrit dans " + c.getNom());
                    } else {
                        System.out.println("  ✗ Classe non trouvée.");
                    }
                }
                case 5 -> {
                    String classId  = MenuConsole.lireChaine("Identifiant de la classe");
                    String eleveId  = MenuConsole.lireChaine("Identifiant de l'élève");
                    Classe c = ecole.getGestionClasses().rechercherParIdentifiant(classId);
                    if (c != null) {
                        c.desinscrireEleve(eleveId);
                        System.out.println("Élève " + eleveId + " désinscrit de " + c.getNom());
                    }
                }
                case 6 -> ecole.getGestionClasses().afficherToutesLesClasses();
                case 7 -> {
                    String niveau = MenuConsole.lireChaine("Niveau (ex: 3ème, Terminale)");
                    ecole.getGestionClasses().rechercherParNiveau(niveau)
                         .forEach(c -> System.out.println("  " + c));
                }
                case 0 -> retour = true;
                default -> System.out.println("  ⚠ Choix invalide.");
            }
            if (!retour) MenuConsole.attendreEntree();
        }
    }

    private static void ajouterClasse() {
        System.out.println("\n--- AJOUTER UNE CLASSE ---");
        String id      = MenuConsole.lireChaine("Identifiant (ex: CL006)");
        String nom     = MenuConsole.lireChaine("Nom (ex: 3ème C)");
        String niveau  = MenuConsole.lireChaine("Niveau (ex: 3ème)");
        String section = MenuConsole.lireChaine("Section (ex: Général)");
        int annee      = MenuConsole.lireEntier("Année scolaire (ex: 2024)");

        ecole.getGestionClasses().ajouterClasse(new Classe(id, nom, niveau, section, annee));
    }

    private static void modifierClasse() {
        String id = MenuConsole.lireChaine("Identifiant de la classe");
        System.out.println("(Laissez vide pour ne pas modifier)");
        String nom      = MenuConsole.lireChaine("Nouveau nom");
        String section  = MenuConsole.lireChaine("Nouvelle section");
        String salleId  = MenuConsole.lireChaine("Nouvel identifiant de salle");
        String profId   = MenuConsole.lireChaine("Nouvel identifiant du prof principal");

        ecole.getGestionClasses().modifierClasse(id,
                nom.isEmpty()     ? null : nom,
                section.isEmpty() ? null : section,
                salleId.isEmpty() ? null : salleId,
                profId.isEmpty()  ? null : profId);
    }

    // =========================================================================
    //  MENU STATISTIQUES
    // =========================================================================

    private static void menuStatistiques() {
        MenuConsole.afficherTitre("TABLEAU DE BORD ET STATISTIQUES");
        ecole.afficherTableauDeBord();

        System.out.println("\nOptions :");
        System.out.println("  1. Classement des meilleurs élèves");
        System.out.println("  2. Synthèse par classe");
        System.out.println("  0. Retour");

        int choix = MenuConsole.lireEntier("Votre choix");
        switch (choix) {
            case 1 -> {
                int n = MenuConsole.lireEntier("Nombre d'élèves à afficher dans le classement");
                ecole.afficherClassementEleves(n);
            }
            case 2 -> ecole.afficherSyntheseParClasse();
        }
        MenuConsole.attendreEntree();
    }
}
