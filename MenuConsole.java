import java.util.Scanner;

public class MenuConsole {
    private Ecole ecole;
    private Scanner sc = new Scanner(System.in);

    public MenuConsole(Ecole ecole) {
        this.ecole = ecole;
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("--- Menu StudIA ---");
            System.out.println("1) Lister les élèves");
            System.out.println("2) Voir bulletin (matricule)");
            System.out.println("3) Top N élèves");
            System.out.println("4) Statistiques école");
            System.out.println("0) Quitter");
            System.out.print("Choix: ");
            String choix = sc.nextLine().trim();
            switch (choix) {
                case "1" -> listerEleves();
                case "2" -> voirBulletin();
                case "3" -> topN();
                case "4" -> stats();
                case "0" -> exit = true;
                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println("Au revoir.");
    }

    private void listerEleves() {
        System.out.println("Liste des élèves :");
        for (Ecole.Eleve e : ecole.getEleves()) {
            System.out.println(" - " + e.toString());
        }
    }

    private void voirBulletin() {
        System.out.print("Matricule de l'élève: ");
        String mat = sc.nextLine().trim();
        var opt = ecole.trouverEleveParMatricule(mat);
        if (opt.isEmpty()) {
            System.out.println("Élève non trouvé.");
            return;
        }
        Ecole.Eleve e = opt.get();
        System.out.println("Bulletin de " + e.getNomComplet() + " (" + e.getMatricule() + ")");
        if (e.getNotes().isEmpty()) {
            System.out.println("Aucune note enregistrée.");
        } else {
            for (Ecole.Note n : e.getNotes()) {
                String matiere = n.matiere == null ? "(sans matière)" : n.matiere.nom;
                System.out.printf(" - %s : %.2f (%s)\n", matiere, n.valeur, n.type);
            }
            System.out.printf("Moyenne générale: %.2f\n", e.moyenne());
        }
    }

    private void topN() {
        System.out.print("Nombre d'élèves à afficher (N): ");
        String s = sc.nextLine().trim();
        int n = 5;
        try { n = Integer.parseInt(s); } catch (Exception ex) { System.out.println("Valeur invalide, affichage 5 par défaut."); }
        var top = ecole.topEleves(n);
        System.out.println("Top " + top.size() + " élèves :");
        int rank = 1;
        for (Ecole.Eleve e : top) {
            System.out.printf("%d) %s - Moy: %.2f\n", rank++, e.getNomComplet(), e.moyenne());
        }
    }

    private void stats() {
        System.out.println("Statistiques de l'école: " + ecole.getNom());
        System.out.println(" - Total élèves: " + ecole.totalEleves());
        double moy = ecole.moyenneGeneraleEcole();
        System.out.println(" - Moyenne générale de l'école: " + (Double.isNaN(moy) ? "N/A" : String.format("%.2f", moy)));
    }

    public static void main(String[] args) {
        Ecole ecole = new Ecole("Lycée Exemple");
        DonneesExemple.peupler(ecole);
        MenuConsole menu = new MenuConsole(ecole);
        menu.run();
    }
}
