import java.util.*;

/**
 * Classe utilitaire représentant une école minimale
 * Contient des modèles internes (Personne, Eleve, Enseignant, Matiere, Classe, Salle, Note)
 */
public class Ecole {
	private String nom;
	private List<Eleve> eleves = new ArrayList<>();
	private List<Enseignant> enseignants = new ArrayList<>();
	private List<Matiere> matieres = new ArrayList<>();
	private List<Classe> classes = new ArrayList<>();
	private List<Salle> salles = new ArrayList<>();

	public Ecole(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void addEleve(Eleve e) {
		eleves.add(e);
		if (e.classe != null && !e.classe.eleves.contains(e)) {
			e.classe.eleves.add(e);
		}
	}

	public void addEnseignant(Enseignant ens) { enseignants.add(ens); }
	public void addMatiere(Matiere m) { matieres.add(m); }
	public void addClasse(Classe c) { classes.add(c); }
	public void addSalle(Salle s) { salles.add(s); }

	public List<Eleve> getEleves() { return Collections.unmodifiableList(eleves); }

	public Optional<Eleve> trouverEleveParMatricule(String matricule) {
		return eleves.stream().filter(e -> e.matricule.equalsIgnoreCase(matricule)).findFirst();
	}

	public List<Eleve> topEleves(int n) {
		return eleves.stream()
				.sorted(Comparator.comparingDouble(Eleve::moyenne).reversed())
				.limit(n)
				.toList();
	}

	public double moyenneGeneraleEcole() {
		return eleves.stream().mapToDouble(Eleve::moyenne).filter(d -> !Double.isNaN(d)).average().orElse(Double.NaN);
	}

	public int totalEleves() { return eleves.size(); }

	// Modèles minimaux pour permettre compilation et démonstration
	public static abstract class Personne {
		protected String nom;
		protected String prenom;

		public Personne(String nom, String prenom) {
			this.nom = nom;
			this.prenom = prenom;
		}

		public String getNom() { return nom; }
		public String getPrenom() { return prenom; }

		public String getNomComplet() { return prenom + " " + nom; }
	}

	public static class Eleve extends Personne {
		private String matricule;
		private List<Note> notes = new ArrayList<>();
		private Classe classe;

		public Eleve(String matricule, String nom, String prenom) {
			super(nom, prenom);
			this.matricule = matricule;
		}

		public String getMatricule() { return matricule; }

		public void setClasse(Classe classe) { this.classe = classe; }

		public void addNote(Note n) { notes.add(n); }

		public double moyenne() {
			if (notes.isEmpty()) return Double.NaN;
			double somme = 0;
			double totalCoef = 0;
			for (Note n : notes) {
				double coef = Math.max(1, n.matiere == null ? 1 : n.matiere.coefficient);
				somme += n.valeur * coef;
				totalCoef += coef;
			}
			return totalCoef == 0 ? Double.NaN : somme / totalCoef;
		}

		public List<Note> getNotes() { return Collections.unmodifiableList(notes); }

		@Override
		public String toString() {
			return String.format("%s (%s) - Classe: %s - Moy: %s",
					getNomComplet(), matricule, classe == null ? "-" : classe.nom,
					Double.isNaN(moyenne()) ? "N/A" : String.format("%.2f", moyenne()));
		}
	}

	public static class Enseignant extends Personne {
		private String numeroEmploye;
		private List<Matiere> matieres = new ArrayList<>();

		public Enseignant(String numeroEmploye, String nom, String prenom) {
			super(nom, prenom);
			this.numeroEmploye = numeroEmploye;
		}

		public void ajouterMatiere(Matiere m) { matieres.add(m); }
	}

	public static class Matiere {
		private String nom;
		private int coefficient;

		public Matiere(String nom, int coefficient) {
			this.nom = nom;
			this.coefficient = coefficient;
		}
	}

	public static class Classe {
		private String nom;
		private List<Eleve> eleves = new ArrayList<>();

		public Classe(String nom) { this.nom = nom; }
	}

	public static class Salle {
		private String id;
		private String type;
		private int capacite;

		public Salle(String id, String type, int capacite) {
			this.id = id; this.type = type; this.capacite = capacite;
		}
	}

	public static class Note {
		private Matiere matiere;
		private double valeur;
		private String type; // devoir, examen, TP...

		public Note(Matiere matiere, double valeur, String type) {
			this.matiere = matiere; this.valeur = valeur; this.type = type;
		}
	}
}
