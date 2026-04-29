package utilitaires;

import java.util.Scanner;

/**
 * Classe utilitaire pour gérer les menus interactifs en console.
 * Fournit des méthodes pour afficher des menus et lire les entrées utilisateur.
 */
public class MenuConsole {

    private static Scanner scanner = new Scanner(System.in);

    // ----------------------------- Lecture des entrées -----------------------------

    /**
     * Lit une chaîne de caractères entrée par l'utilisateur.
     * @param message le message à afficher à l'utilisateur
     */
    public static String lireChaine(String message) {
        System.out.print(message + " : ");
        return scanner.nextLine().trim();
    }

    /**
     * Lit un nombre entier entré par l'utilisateur.
     * Redemande en cas de saisie invalide.
     */
    public static int lireEntier(String message) {
        while (true) {
            System.out.print(message + " : ");
            String saisie = scanner.nextLine().trim();
            try {
                return Integer.parseInt(saisie);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Saisie invalide. Veuillez entrer un nombre entier.");
            }
        }
    }

    /**
     * Lit un nombre décimal entré par l'utilisateur.
     */
    public static double lireDecimal(String message) {
        while (true) {
            System.out.print(message + " : ");
            String saisie = scanner.nextLine().trim();
            try {
                return Double.parseDouble(saisie.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Saisie invalide. Veuillez entrer un nombre décimal.");
            }
        }
    }

    /**
     * Lit une confirmation oui/non de l'utilisateur.
     * @return true si l'utilisateur répond "o" ou "oui"
     */
    public static boolean lireOuiNon(String message) {
        System.out.print(message + " (o/n) : ");
        String saisie = scanner.nextLine().trim().toLowerCase();
        return saisie.equals("o") || saisie.equals("oui");
    }

    /**
     * Affiche un titre de menu encadré.
     */
    public static void afficherTitre(String titre) {
        int largeur = titre.length() + 4;
        String ligne = "═".repeat(largeur);
        System.out.println("\n╔" + ligne + "╗");
        System.out.println("║  " + titre + "  ║");
        System.out.println("╚" + ligne + "╝");
    }

    /**
     * Affiche un séparateur simple.
     */
    public static void afficherSeparateur() {
        System.out.println("──────────────────────────────────────────");
    }

    /**
     * Attend que l'utilisateur appuie sur Entrée pour continuer.
     */
    public static void attendreEntree() {
        System.out.print("\n  [Appuyez sur Entrée pour continuer...]");
        scanner.nextLine();
    }

    /**
     * Affiche le menu principal de l'application.
     */
    public static void afficherMenuPrincipal() {
        afficherTitre("MENU PRINCIPAL — GESTION ÉCOLE SECONDAIRE");
        System.out.println("  1. Gestion des Élèves");
        System.out.println("  2. Gestion des Enseignants");
        System.out.println("  3. Gestion de l'Administration");
        System.out.println("  4. Gestion des Matières");
        System.out.println("  5. Gestion des Salles");
        System.out.println("  6. Gestion des Classes");
        System.out.println("  7. Tableau de bord et statistiques");
        System.out.println("  0. Quitter");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion des élèves.
     */
    public static void afficherMenuEleves() {
        afficherTitre("GESTION DES ÉLÈVES");
        System.out.println("  1. Ajouter un élève");
        System.out.println("  2. Modifier un élève");
        System.out.println("  3. Supprimer un élève");
        System.out.println("  4. Rechercher un élève (par matricule)");
        System.out.println("  5. Rechercher des élèves (par nom)");
        System.out.println("  6. Afficher les élèves d'une classe");
        System.out.println("  7. Afficher tous les élèves");
        System.out.println("  8. Trier les élèves");
        System.out.println("  9. Ajouter une note à un élève");
        System.out.println(" 10. Afficher le bulletin d'un élève");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion des enseignants.
     */
    public static void afficherMenuEnseignants() {
        afficherTitre("GESTION DES ENSEIGNANTS");
        System.out.println("  1. Ajouter un enseignant");
        System.out.println("  2. Modifier un enseignant");
        System.out.println("  3. Supprimer un enseignant");
        System.out.println("  4. Rechercher un enseignant (par n° employé)");
        System.out.println("  5. Rechercher des enseignants (par nom)");
        System.out.println("  6. Rechercher par spécialité");
        System.out.println("  7. Assigner une matière à un enseignant");
        System.out.println("  8. Afficher tous les enseignants");
        System.out.println("  9. Trier les enseignants");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion de l'administration.
     */
    public static void afficherMenuAdministration() {
        afficherTitre("GESTION DE L'ADMINISTRATION");
        System.out.println("  1. Ajouter un membre de l'administration");
        System.out.println("  2. Modifier un membre");
        System.out.println("  3. Supprimer un membre");
        System.out.println("  4. Rechercher par n° employé");
        System.out.println("  5. Rechercher par nom");
        System.out.println("  6. Rechercher par service");
        System.out.println("  7. Afficher tout le personnel administratif");
        System.out.println("  8. Trier le personnel");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion des matières.
     */
    public static void afficherMenuMatieres() {
        afficherTitre("GESTION DES MATIÈRES");
        System.out.println("  1. Ajouter une matière");
        System.out.println("  2. Modifier une matière");
        System.out.println("  3. Supprimer une matière");
        System.out.println("  4. Rechercher une matière");
        System.out.println("  5. Afficher toutes les matières");
        System.out.println("  6. Trier les matières");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion des salles.
     */
    public static void afficherMenuSalles() {
        afficherTitre("GESTION DES SALLES");
        System.out.println("  1. Ajouter une salle");
        System.out.println("  2. Modifier une salle");
        System.out.println("  3. Supprimer une salle");
        System.out.println("  4. Rechercher une salle");
        System.out.println("  5. Voir les salles disponibles");
        System.out.println("  6. Afficher toutes les salles");
        System.out.println("  7. Trier les salles");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Affiche le sous-menu de gestion des classes.
     */
    public static void afficherMenuClasses() {
        afficherTitre("GESTION DES CLASSES");
        System.out.println("  1. Ajouter une classe");
        System.out.println("  2. Modifier une classe");
        System.out.println("  3. Supprimer une classe");
        System.out.println("  4. Inscrire un élève dans une classe");
        System.out.println("  5. Désinscrire un élève");
        System.out.println("  6. Afficher toutes les classes");
        System.out.println("  7. Rechercher les classes d'un niveau");
        System.out.println("  0. Retour au menu principal");
        afficherSeparateur();
    }

    /**
     * Ferme le scanner à la fin du programme.
     */
    public static void fermer() {
        scanner.close();
    }
}
