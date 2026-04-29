package services;

import modeles.Administrateur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des membres de l'administration.
 */
public class GestionAdministration {

    private List<Administrateur> listeAdministrateurs;

    public GestionAdministration() {
        this.listeAdministrateurs = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute un membre de l'administration.
     * @return false si le numéro d'employé existe déjà ou si un directeur est déjà enregistré
     */
    public boolean ajouterAdministrateur(Administrateur admin) {
        if (rechercherParNumeroEmploye(admin.getNumeroEmploye()) != null) {
            System.out.println("Erreur : employé n°" + admin.getNumeroEmploye() + " existe déjà.");
            return false;
        }
        // Un seul directeur autorisé
        if (admin.isEstDirecteur() && getDirecteur() != null) {
            System.out.println("Erreur : un directeur est déjà enregistré. Retirez-lui d'abord ce rôle.");
            return false;
        }
        listeAdministrateurs.add(admin);
        System.out.println("Membre de l'administration " + admin.getNomComplet() + " ajouté.");
        return true;
    }

    /**
     * Modifie les informations d'un membre de l'administration.
     */
    public boolean modifierAdministrateur(String numeroEmploye, String nouveauPoste,
                                           String nouveauService, Double nouveauSalaire, String nouvelEmail) {
        Administrateur admin = rechercherParNumeroEmploye(numeroEmploye);
        if (admin == null) {
            System.out.println("Erreur : employé n°" + numeroEmploye + " non trouvé.");
            return false;
        }

        if (nouveauPoste   != null) admin.setPoste(nouveauPoste);
        if (nouveauService != null) admin.setService(nouveauService);
        if (nouveauSalaire != null) admin.setSalaire(nouveauSalaire);
        if (nouvelEmail    != null) admin.setEmail(nouvelEmail);

        System.out.println("Administrateur " + admin.getNomComplet() + " modifié avec succès.");
        return true;
    }

    /**
     * Supprime un membre de l'administration par son numéro d'employé.
     */
    public boolean supprimerAdministrateur(String numeroEmploye) {
        Administrateur admin = rechercherParNumeroEmploye(numeroEmploye);
        if (admin == null) {
            System.out.println("Erreur : employé n°" + numeroEmploye + " non trouvé.");
            return false;
        }
        listeAdministrateurs.remove(admin);
        System.out.println("Administrateur " + admin.getNomComplet() + " supprimé.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche par numéro d'employé.
     */
    public Administrateur rechercherParNumeroEmploye(String numeroEmploye) {
        for (Administrateur a : listeAdministrateurs) {
            if (a.getNumeroEmploye().equalsIgnoreCase(numeroEmploye)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Recherche par nom ou prénom.
     */
    public List<Administrateur> rechercherParNom(String motCle) {
        List<Administrateur> resultats = new ArrayList<>();
        String motCleLower = motCle.toLowerCase();
        for (Administrateur a : listeAdministrateurs) {
            if (a.getNom().toLowerCase().contains(motCleLower) ||
                a.getPrenom().toLowerCase().contains(motCleLower)) {
                resultats.add(a);
            }
        }
        return resultats;
    }

    /**
     * Retourne tous les membres d'un service donné.
     */
    public List<Administrateur> rechercherParService(String service) {
        List<Administrateur> resultats = new ArrayList<>();
        for (Administrateur a : listeAdministrateurs) {
            if (a.getService().equalsIgnoreCase(service)) {
                resultats.add(a);
            }
        }
        return resultats;
    }

    /**
     * Retourne le directeur de l'école, ou null s'il n'est pas encore défini.
     */
    public Administrateur getDirecteur() {
        for (Administrateur a : listeAdministrateurs) {
            if (a.isEstDirecteur()) return a;
        }
        return null;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie par nom alphabétique.
     */
    public List<Administrateur> trierParNom() {
        List<Administrateur> trie = new ArrayList<>(listeAdministrateurs);
        trie.sort(Comparator.comparing(Administrateur::getNom));
        return trie;
    }

    /**
     * Trie par poste puis par nom.
     */
    public List<Administrateur> trierParPoste() {
        List<Administrateur> trie = new ArrayList<>(listeAdministrateurs);
        trie.sort(Comparator.comparing(Administrateur::getPoste)
                            .thenComparing(Administrateur::getNom));
        return trie;
    }

    /**
     * Trie par salaire décroissant.
     */
    public List<Administrateur> trierParSalaire() {
        List<Administrateur> trie = new ArrayList<>(listeAdministrateurs);
        trie.sort(Comparator.comparingDouble(Administrateur::getSalaire).reversed());
        return trie;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    /**
     * Affiche tous les membres de l'administration.
     */
    public void afficherTousLesAdministrateurs() {
        if (listeAdministrateurs.isEmpty()) {
            System.out.println("Aucun membre de l'administration enregistré.");
            return;
        }
        System.out.println("\n========== ADMINISTRATION (" + listeAdministrateurs.size() + ") ==========");
        for (Administrateur a : listeAdministrateurs) {
            System.out.println(a);
        }
        System.out.println("=============================================");
    }

    public List<Administrateur> getTousLesAdministrateurs() {
        return listeAdministrateurs;
    }

    public int getNombreAdministrateurs() {
        return listeAdministrateurs.size();
    }
}
