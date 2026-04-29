package services;

import modeles.Classe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des classes de l'école.
 */
public class GestionClasses {

    private List<Classe> listeClasses;

    public GestionClasses() {
        this.listeClasses = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute une nouvelle classe.
     */
    public boolean ajouterClasse(Classe classe) {
        if (rechercherParIdentifiant(classe.getIdentifiant()) != null) {
            System.out.println("Erreur : classe [" + classe.getIdentifiant() + "] existe déjà.");
            return false;
        }
        listeClasses.add(classe);
        System.out.println("Classe '" + classe.getNom() + "' ajoutée.");
        return true;
    }

    /**
     * Modifie les informations d'une classe.
     */
    public boolean modifierClasse(String identifiant, String nouveauNom, String nouvelleSection,
                                   String nouvelleSalleId, String nouveauProfPrincipalId) {
        Classe classe = rechercherParIdentifiant(identifiant);
        if (classe == null) {
            System.out.println("Erreur : classe [" + identifiant + "] non trouvée.");
            return false;
        }

        if (nouveauNom            != null) classe.setNom(nouveauNom);
        if (nouvelleSection       != null) classe.setSection(nouvelleSection);
        if (nouvelleSalleId       != null) classe.setSalleId(nouvelleSalleId);
        if (nouveauProfPrincipalId != null) classe.setProfesseurPrincipalId(nouveauProfPrincipalId);

        System.out.println("Classe '" + classe.getNom() + "' modifiée.");
        return true;
    }

    /**
     * Supprime une classe par son identifiant.
     */
    public boolean supprimerClasse(String identifiant) {
        Classe classe = rechercherParIdentifiant(identifiant);
        if (classe == null) {
            System.out.println("Erreur : classe [" + identifiant + "] non trouvée.");
            return false;
        }
        listeClasses.remove(classe);
        System.out.println("Classe '" + classe.getNom() + "' supprimée.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche une classe par son identifiant.
     */
    public Classe rechercherParIdentifiant(String identifiant) {
        for (Classe c : listeClasses) {
            if (c.getIdentifiant().equalsIgnoreCase(identifiant)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Recherche une classe par son nom exact.
     */
    public Classe rechercherParNom(String nom) {
        for (Classe c : listeClasses) {
            if (c.getNom().equalsIgnoreCase(nom)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Retourne toutes les classes d'un niveau donné.
     */
    public List<Classe> rechercherParNiveau(String niveau) {
        List<Classe> resultats = new ArrayList<>();
        for (Classe c : listeClasses) {
            if (c.getNiveau().equalsIgnoreCase(niveau)) {
                resultats.add(c);
            }
        }
        return resultats;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie les classes par niveau puis par nom.
     */
    public List<Classe> trierParNiveauEtNom() {
        List<Classe> trie = new ArrayList<>(listeClasses);
        trie.sort(Comparator.comparing(Classe::getNiveau)
                            .thenComparing(Classe::getNom));
        return trie;
    }

    /**
     * Trie les classes par effectif décroissant.
     */
    public List<Classe> trierParEffectif() {
        List<Classe> trie = new ArrayList<>(listeClasses);
        trie.sort(Comparator.comparingInt(Classe::getNombreEleves).reversed());
        return trie;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    public void afficherToutesLesClasses() {
        if (listeClasses.isEmpty()) {
            System.out.println("Aucune classe enregistrée.");
            return;
        }
        System.out.println("\n========== CLASSES (" + listeClasses.size() + ") ==========");
        for (Classe c : listeClasses) {
            System.out.println(c);
        }
        System.out.println("======================================");
    }

    public List<Classe> getToutesLesClasses() {
        return listeClasses;
    }

    public int getNombreClasses() {
        return listeClasses.size();
    }
}
