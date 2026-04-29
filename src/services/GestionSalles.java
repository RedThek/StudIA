package services;

import modeles.Salle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des salles de l'école.
 */
public class GestionSalles {

    private List<Salle> listeSalles;

    public GestionSalles() {
        this.listeSalles = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute une nouvelle salle dans le système.
     */
    public boolean ajouterSalle(Salle salle) {
        if (rechercherParIdentifiant(salle.getIdentifiant()) != null) {
            System.out.println("Erreur : salle [" + salle.getIdentifiant() + "] existe déjà.");
            return false;
        }
        listeSalles.add(salle);
        System.out.println("Salle [" + salle.getIdentifiant() + "] " + salle.getNom() + " ajoutée.");
        return true;
    }

    /**
     * Modifie les informations d'une salle existante.
     */
    public boolean modifierSalle(String identifiant, String nouveauNom,
                                  Integer nouvelleCapacite, Boolean disponible,
                                  Boolean aProjecteur, Boolean aTableauxBlancs) {
        Salle salle = rechercherParIdentifiant(identifiant);
        if (salle == null) {
            System.out.println("Erreur : salle [" + identifiant + "] non trouvée.");
            return false;
        }

        if (nouveauNom       != null) salle.setNom(nouveauNom);
        if (nouvelleCapacite != null) salle.setCapaciteMax(nouvelleCapacite);
        if (disponible       != null) salle.setEstDisponible(disponible);
        if (aProjecteur      != null) salle.setAProjecteur(aProjecteur);
        if (aTableauxBlancs  != null) salle.setATableauxBlancs(aTableauxBlancs);

        System.out.println("Salle [" + identifiant + "] modifiée.");
        return true;
    }

    /**
     * Supprime une salle par son identifiant.
     */
    public boolean supprimerSalle(String identifiant) {
        Salle salle = rechercherParIdentifiant(identifiant);
        if (salle == null) {
            System.out.println("Erreur : salle [" + identifiant + "] non trouvée.");
            return false;
        }
        listeSalles.remove(salle);
        System.out.println("Salle [" + identifiant + "] supprimée.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche une salle par son identifiant.
     */
    public Salle rechercherParIdentifiant(String identifiant) {
        for (Salle s : listeSalles) {
            if (s.getIdentifiant().equalsIgnoreCase(identifiant)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Retourne toutes les salles disponibles (non occupées).
     */
    public List<Salle> rechercherSallesDisponibles() {
        List<Salle> resultats = new ArrayList<>();
        for (Salle s : listeSalles) {
            if (s.isEstDisponible()) {
                resultats.add(s);
            }
        }
        return resultats;
    }

    /**
     * Retourne les salles d'un type donné.
     */
    public List<Salle> rechercherParType(String typeSalle) {
        List<Salle> resultats = new ArrayList<>();
        for (Salle s : listeSalles) {
            if (s.getTypeSalle().equalsIgnoreCase(typeSalle)) {
                resultats.add(s);
            }
        }
        return resultats;
    }

    /**
     * Retourne les salles pouvant accueillir au moins N élèves.
     */
    public List<Salle> rechercherParCapaciteMin(int capaciteMin) {
        List<Salle> resultats = new ArrayList<>();
        for (Salle s : listeSalles) {
            if (s.getCapaciteMax() >= capaciteMin) {
                resultats.add(s);
            }
        }
        return resultats;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie les salles par identifiant (ordre alphabétique).
     */
    public List<Salle> trierParIdentifiant() {
        List<Salle> trie = new ArrayList<>(listeSalles);
        trie.sort(Comparator.comparing(Salle::getIdentifiant));
        return trie;
    }

    /**
     * Trie les salles par capacité croissante.
     */
    public List<Salle> trierParCapacite() {
        List<Salle> trie = new ArrayList<>(listeSalles);
        trie.sort(Comparator.comparingInt(Salle::getCapaciteMax));
        return trie;
    }

    /**
     * Trie les salles par type puis par identifiant.
     */
    public List<Salle> trierParType() {
        List<Salle> trie = new ArrayList<>(listeSalles);
        trie.sort(Comparator.comparing(Salle::getTypeSalle)
                            .thenComparing(Salle::getIdentifiant));
        return trie;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    /**
     * Affiche toutes les salles de l'école.
     */
    public void afficherToutesLesSalles() {
        if (listeSalles.isEmpty()) {
            System.out.println("Aucune salle enregistrée.");
            return;
        }
        System.out.println("\n========== SALLES (" + listeSalles.size() + ") ==========");
        for (Salle s : listeSalles) {
            System.out.println(s);
        }
        System.out.println("======================================");
    }

    public List<Salle> getToutesLesSalles() {
        return listeSalles;
    }

    public int getNombreSalles() {
        return listeSalles.size();
    }
}
