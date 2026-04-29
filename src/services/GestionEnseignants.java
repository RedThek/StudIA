package services;

import modeles.Enseignant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des enseignants.
 * Permet d'ajouter, modifier, supprimer, rechercher et trier les enseignants.
 */
public class GestionEnseignants {

    private List<Enseignant> listeEnseignants;

    public GestionEnseignants() {
        this.listeEnseignants = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute un nouvel enseignant dans le système.
     * @return true si l'ajout a réussi, false si le numéro d'employé existe déjà
     */
    public boolean ajouterEnseignant(Enseignant enseignant) {
        if (rechercherParNumeroEmploye(enseignant.getNumeroEmploye()) != null) {
            System.out.println("Erreur : un enseignant avec le n° employé " +
                    enseignant.getNumeroEmploye() + " existe déjà.");
            return false;
        }
        listeEnseignants.add(enseignant);
        System.out.println("Enseignant " + enseignant.getNomComplet() + " ajouté avec succès.");
        return true;
    }

    /**
     * Modifie les informations d'un enseignant existant.
     */
    public boolean modifierEnseignant(String numeroEmploye, String nouveauNom, String nouveauPrenom,
                                       String nouvelleSpecialite, Double nouveauSalaire, String nouvelEmail) {
        Enseignant enseignant = rechercherParNumeroEmploye(numeroEmploye);
        if (enseignant == null) {
            System.out.println("Erreur : enseignant n°" + numeroEmploye + " non trouvé.");
            return false;
        }

        if (nouveauNom         != null) enseignant.setNom(nouveauNom);
        if (nouveauPrenom      != null) enseignant.setPrenom(nouveauPrenom);
        if (nouvelleSpecialite != null) enseignant.setSpecialite(nouvelleSpecialite);
        if (nouveauSalaire     != null) enseignant.setSalaire(nouveauSalaire);
        if (nouvelEmail        != null) enseignant.setEmail(nouvelEmail);

        System.out.println("Enseignant " + enseignant.getNomComplet() + " modifié avec succès.");
        return true;
    }

    /**
     * Supprime un enseignant par son numéro d'employé.
     */
    public boolean supprimerEnseignant(String numeroEmploye) {
        Enseignant enseignant = rechercherParNumeroEmploye(numeroEmploye);
        if (enseignant == null) {
            System.out.println("Erreur : enseignant n°" + numeroEmploye + " non trouvé.");
            return false;
        }
        listeEnseignants.remove(enseignant);
        System.out.println("Enseignant " + enseignant.getNomComplet() + " supprimé avec succès.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche un enseignant par son numéro d'employé.
     */
    public Enseignant rechercherParNumeroEmploye(String numeroEmploye) {
        for (Enseignant e : listeEnseignants) {
            if (e.getNumeroEmploye().equalsIgnoreCase(numeroEmploye)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Recherche des enseignants par nom ou prénom.
     */
    public List<Enseignant> rechercherParNom(String motCle) {
        List<Enseignant> resultats = new ArrayList<>();
        String motCleLower = motCle.toLowerCase();
        for (Enseignant e : listeEnseignants) {
            if (e.getNom().toLowerCase().contains(motCleLower) ||
                e.getPrenom().toLowerCase().contains(motCleLower)) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    /**
     * Recherche les enseignants d'une spécialité donnée.
     */
    public List<Enseignant> rechercherParSpecialite(String specialite) {
        List<Enseignant> resultats = new ArrayList<>();
        for (Enseignant e : listeEnseignants) {
            if (e.getSpecialite().toLowerCase().contains(specialite.toLowerCase())) {
                resultats.add(e);
            }
        }
        return resultats;
    }

    /**
     * Retourne l'enseignant assigné à une matière donnée.
     */
    public Enseignant rechercherParMatiere(String matiereId) {
        for (Enseignant e : listeEnseignants) {
            if (e.getMatieresEnseignees().contains(matiereId)) {
                return e;
            }
        }
        return null;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie les enseignants par nom alphabétique.
     */
    public List<Enseignant> trierParNom() {
        List<Enseignant> trie = new ArrayList<>(listeEnseignants);
        trie.sort(Comparator.comparing(Enseignant::getNom)
                            .thenComparing(Enseignant::getPrenom));
        return trie;
    }

    /**
     * Trie les enseignants par spécialité puis par nom.
     */
    public List<Enseignant> trierParSpecialite() {
        List<Enseignant> trie = new ArrayList<>(listeEnseignants);
        trie.sort(Comparator.comparing(Enseignant::getSpecialite)
                            .thenComparing(Enseignant::getNom));
        return trie;
    }

    /**
     * Trie les enseignants par salaire décroissant.
     */
    public List<Enseignant> trierParSalaire() {
        List<Enseignant> trie = new ArrayList<>(listeEnseignants);
        trie.sort(Comparator.comparingDouble(Enseignant::getSalaire).reversed());
        return trie;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    /**
     * Affiche tous les enseignants de l'école.
     */
    public void afficherTousLesEnseignants() {
        if (listeEnseignants.isEmpty()) {
            System.out.println("Aucun enseignant enregistré.");
            return;
        }
        System.out.println("\n========== LISTE DES ENSEIGNANTS (" + listeEnseignants.size() + ") ==========");
        for (Enseignant e : listeEnseignants) {
            System.out.println(e);
            System.out.println("   Matières : " + e.getMatieresEnseignees());
            System.out.println("   Classes  : " + e.getClassesAssignees());
        }
        System.out.println("===================================================");
    }

    public List<Enseignant> getTousLesEnseignants() {
        return listeEnseignants;
    }

    public int getNombreEnseignants() {
        return listeEnseignants.size();
    }
}
