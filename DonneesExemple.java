public class DonneesExemple {
    public static void peupler(Ecole e) {
        // Matières
        Ecole.Matiere maths = new Ecole.Matiere("Mathématiques", 4);
        Ecole.Matiere francais = new Ecole.Matiere("Français", 3);
        Ecole.Matiere physique = new Ecole.Matiere("Physique", 3);
        e.addMatiere(maths);
        e.addMatiere(francais);
        e.addMatiere(physique);

        // Classes
        Ecole.Classe c3A = new Ecole.Classe("3A");
        Ecole.Classe c3B = new Ecole.Classe("3B");
        e.addClasse(c3A);
        e.addClasse(c3B);

        // Élèves
        Ecole.Eleve el1 = new Ecole.Eleve("E001", "Dupont", "Jean");
        el1.setClasse(c3A);
        el1.addNote(new Ecole.Note(maths, 15.0, "Examen"));
        el1.addNote(new Ecole.Note(francais, 12.5, "Devoir"));

        Ecole.Eleve el2 = new Ecole.Eleve("E002", "Martin", "Alice");
        el2.setClasse(c3A);
        el2.addNote(new Ecole.Note(maths, 17.0, "Examen"));
        el2.addNote(new Ecole.Note(physique, 14.0, "TP"));

        Ecole.Eleve el3 = new Ecole.Eleve("E003", "Bernard", "Luc");
        el3.setClasse(c3B);
        el3.addNote(new Ecole.Note(francais, 9.0, "Devoir"));

        e.addEleve(el1);
        e.addEleve(el2);
        e.addEleve(el3);

        // Enseignants
        Ecole.Enseignant ens1 = new Ecole.Enseignant("T001", "Leroy", "Sophie");
        ens1.ajouterMatiere(maths);
        e.addEnseignant(ens1);

        Ecole.Enseignant ens2 = new Ecole.Enseignant("T002", "Moreau", "Paul");
        ens2.ajouterMatiere(francais);
        e.addEnseignant(ens2);

        // Salles
        Ecole.Salle s1 = new Ecole.Salle("S101", "classe", 30);
        Ecole.Salle s2 = new Ecole.Salle("Lab1", "labo", 20);
        e.addSalle(s1);
        e.addSalle(s2);
    }
}
