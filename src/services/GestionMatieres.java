package services;

import modeles.Matiere;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des matières enseignées dans l'école.
 */
public class GestionMatieres {

    private List<Matiere> listeMatieres;

    public GestionMatieres() {
        this.listeMatieres = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute une nouvelle matière.
     */
    public boolean ajouterMatiere(Matiere matiere) {
        if (rechercherParIdentifiant(matiere.getIdentifiant()) != null) {
            System.out.println("Erreur : matière [" + matiere.getIdentifiant() + "] existe déjà.");
            return false;
        }
        listeMatieres.add(matiere);
        System.out.println("Matière '" + matiere.getNom() + "' ajoutée avec succès.");
        return true;
    }

    /**
     * Modifie une matière existante.
     */
    public boolean modifierMatiere(String identifiant, String nouveauNom,
                                    String nouvelleDescription, Integer nouveauCoefficient,
                                    Integer nouvellesHeures, String nouvelEnseignantId) {
        Matiere matiere = rechercherParIdentifiant(identifiant);
        if (matiere == null) {
            System.out.println("Erreur : matière [" + identifiant + "] non trouvée.");
            return false;
        }

        if (nouveauNom          != null) matiere.setNom(nouveauNom);
        if (nouvelleDescription != null) matiere.setDescription(nouvelleDescription);
        if (nouveauCoefficient  != null) matiere.setCoefficientExamen(nouveauCoefficient);
        if (nouvellesHeures     != null) matiere.setHeuresParSemaine(nouvellesHeures);
        if (nouvelEnseignantId  != null) matiere.setEnseignantId(nouvelEnseignantId);

        System.out.println("Matière '" + matiere.getNom() + "' modifiée avec succès.");
        return true;
    }

    /**
     * Supprime une matière par son identifiant.
     */
    public boolean supprimerMatiere(String identifiant) {
        Matiere matiere = rechercherParIdentifiant(identifiant);
        if (matiere == null) {
            System.out.println("Erreur : matière [" + identifiant + "] non trouvée.");
            return false;
        }
        listeMatieres.remove(matiere);
        System.out.println("Matière '" + matiere.getNom() + "' supprimée.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche une matière par son identifiant exact.
     */
    public Matiere rechercherParIdentifiant(String identifiant) {
        for (Matiere m : listeMatieres) {
            if (m.getIdentifiant().equalsIgnoreCase(identifiant)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Recherche des matières par mot-clé dans le nom.
     */
    public List<Matiere> rechercherParNom(String motCle) {
        List<Matiere> resultats = new ArrayList<>();
        String motCleLower = motCle.toLowerCase();
        for (Matiere m : listeMatieres) {
            if (m.getNom().toLowerCase().contains(motCleLower)) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    /**
     * Retourne toutes les matières enseignées par un enseignant donné.
     */
    public List<Matiere> rechercherParEnseignant(String enseignantId) {
        List<Matiere> resultats = new ArrayList<>();
        for (Matiere m : listeMatieres) {
            if (m.getEnseignantId().equalsIgnoreCase(enseignantId)) {
                resultats.add(m);
            }
        }
        return resultats;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie les matières par nom alphabétique.
     */
    public List<Matiere> trierParNom() {
        List<Matiere> trie = new ArrayList<>(listeMatieres);
        trie.sort(Comparator.comparing(Matiere::getNom));
        return trie;
    }

    /**
     * Trie les matières par coefficient décroissant.
     */
    public List<Matiere> trierParCoefficient() {
        List<Matiere> trie = new ArrayList<>(listeMatieres);
        trie.sort(Comparator.comparingInt(Matiere::getCoefficientExamen).reversed());
        return trie;
    }

    /**
     * Trie les matières par nombre d'heures par semaine décroissant.
     */
    public List<Matiere> trierParHeuresSemaine() {
        List<Matiere> trie = new ArrayList<>(listeMatieres);
        trie.sort(Comparator.comparingInt(Matiere::getHeuresParSemaine).reversed());
        return trie;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    /**
     * Affiche toutes les matières du programme.
     */
    public void afficherToutesLesMatieres() {
        if (listeMatieres.isEmpty()) {
            System.out.println("Aucune matière enregistrée.");
            return;
        }
        System.out.println("\n========== MATIÈRES (" + listeMatieres.size() + ") ==========");
        for (Matiere m : listeMatieres) {
            System.out.println(m);
        }
        System.out.println("========================================");
    }

    public List<Matiere> getToutesLesMatieres() {
        return listeMatieres;
    }

    public int getNombreMatieres() {
        return listeMatieres.size();
    }
}
