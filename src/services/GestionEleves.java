package services;

import modeles.Eleve;
import modeles.Note;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service de gestion des élèves.
 * Permet d'ajouter, modifier, supprimer, rechercher et trier les élèves.
 */
public class GestionEleves {

    // Liste principale stockant tous les élèves de l'école
    private List<Eleve> listeEleves;

    public GestionEleves() {
        this.listeEleves = new ArrayList<>();
    }

    // =========================================================================
    //  OPÉRATIONS CRUD
    // =========================================================================

    /**
     * Ajoute un nouvel élève dans le système.
     * @return true si l'ajout a réussi, false si le matricule existe déjà
     */
    public boolean ajouterEleve(Eleve eleve) {
        // Vérifier qu'aucun élève avec le même matricule n'existe déjà
        if (rechercherParMatricule(eleve.getNumeroMatricule()) != null) {
            System.out.println("Erreur : un élève avec le matricule " + eleve.getNumeroMatricule() + " existe déjà.");
            return false;
        }
        listeEleves.add(eleve);
        System.out.println("Élève " + eleve.getNomComplet() + " ajouté avec succès.");
        return true;
    }

    /**
     * Modifie les informations d'un élève existant.
     * @param matricule numéro de matricule de l'élève à modifier
     * @param nouveauNom nouveau nom (null pour ne pas modifier)
     * @param nouveauPrenom nouveau prénom (null pour ne pas modifier)
     * @param nouvelleClasse nouvelle classe (null pour ne pas modifier)
     * @param nouvelEmail nouvel email (null pour ne pas modifier)
     * @param nouveauTelephone nouveau téléphone (null pour ne pas modifier)
     */
    public boolean modifierEleve(String matricule, String nouveauNom, String nouveauPrenom,
                                  String nouvelleClasse, String nouvelEmail, String nouveauTelephone) {
        Eleve eleve = rechercherParMatricule(matricule);
        if (eleve == null) {
            System.out.println("Erreur : élève avec le matricule " + matricule + " non trouvé.");
            return false;
        }

        // On ne modifie que les champs non-null fournis
        if (nouveauNom      != null) eleve.setNom(nouveauNom);
        if (nouveauPrenom   != null) eleve.setPrenom(nouveauPrenom);
        if (nouvelleClasse  != null) eleve.setClasseActuelle(nouvelleClasse);
        if (nouvelEmail     != null) eleve.setEmail(nouvelEmail);
        if (nouveauTelephone != null) eleve.setTelephone(nouveauTelephone);

        System.out.println("Élève " + eleve.getNomComplet() + " modifié avec succès.");
        return true;
    }

    /**
     * Supprime un élève par son numéro de matricule.
     * @return true si l'élève a été trouvé et supprimé
     */
    public boolean supprimerEleve(String matricule) {
        Eleve eleve = rechercherParMatricule(matricule);
        if (eleve == null) {
            System.out.println("Erreur : élève avec le matricule " + matricule + " non trouvé.");
            return false;
        }
        listeEleves.remove(eleve);
        System.out.println("Élève " + eleve.getNomComplet() + " supprimé avec succès.");
        return true;
    }

    // =========================================================================
    //  RECHERCHE
    // =========================================================================

    /**
     * Recherche un élève par son numéro de matricule (recherche exacte).
     */
    public Eleve rechercherParMatricule(String matricule) {
        for (Eleve eleve : listeEleves) {
            if (eleve.getNumeroMatricule().equalsIgnoreCase(matricule)) {
                return eleve;
            }
        }
        return null;  // Non trouvé
    }

    /**
     * Recherche des élèves par nom ou prénom (recherche partielle, insensible à la casse).
     */
    public List<Eleve> rechercherParNom(String motCle) {
        List<Eleve> resultats = new ArrayList<>();
        String motCleLower = motCle.toLowerCase();

        for (Eleve eleve : listeEleves) {
            if (eleve.getNom().toLowerCase().contains(motCleLower) ||
                eleve.getPrenom().toLowerCase().contains(motCleLower)) {
                resultats.add(eleve);
            }
        }
        return resultats;
    }

    /**
     * Retourne tous les élèves d'une classe donnée.
     */
    public List<Eleve> rechercherParClasse(String nomClasse) {
        List<Eleve> resultats = new ArrayList<>();
        for (Eleve eleve : listeEleves) {
            if (eleve.getClasseActuelle().equalsIgnoreCase(nomClasse)) {
                resultats.add(eleve);
            }
        }
        return resultats;
    }

    // =========================================================================
    //  TRI
    // =========================================================================

    /**
     * Trie les élèves par nom (ordre alphabétique A→Z).
     */
    public List<Eleve> trierParNom() {
        List<Eleve> trie = new ArrayList<>(listeEleves);
        trie.sort(Comparator.comparing(Eleve::getNom)
                            .thenComparing(Eleve::getPrenom));
        return trie;
    }

    /**
     * Trie les élèves par classe puis par nom.
     */
    public List<Eleve> trierParClasse() {
        List<Eleve> trie = new ArrayList<>(listeEleves);
        trie.sort(Comparator.comparing(Eleve::getClasseActuelle)
                            .thenComparing(Eleve::getNom));
        return trie;
    }

    /**
     * Trie les élèves par moyenne générale (ordre décroissant – meilleure moyenne d'abord).
     */
    public List<Eleve> trierParMoyenne() {
        List<Eleve> trie = new ArrayList<>(listeEleves);
        trie.sort(Comparator.comparingDouble(Eleve::calculerMoyenneGenerale).reversed());
        return trie;
    }

    /**
     * Trie les élèves par numéro de matricule (ordre croissant).
     */
    public List<Eleve> trierParMatricule() {
        List<Eleve> trie = new ArrayList<>(listeEleves);
        trie.sort(Comparator.comparing(Eleve::getNumeroMatricule));
        return trie;
    }

    // =========================================================================
    //  GESTION DES NOTES
    // =========================================================================

    /**
     * Ajoute une note à un élève identifié par son matricule.
     */
    public boolean ajouterNoteEleve(String matricule, Note note) {
        Eleve eleve = rechercherParMatricule(matricule);
        if (eleve == null) {
            System.out.println("Erreur : élève " + matricule + " non trouvé.");
            return false;
        }
        eleve.ajouterNote(note);
        System.out.println("Note ajoutée pour " + eleve.getNomComplet() + " : " + note);
        return true;
    }

    // =========================================================================
    //  AFFICHAGE
    // =========================================================================

    /**
     * Affiche la liste complète de tous les élèves.
     */
    public void afficherTousLesEleves() {
        if (listeEleves.isEmpty()) {
            System.out.println("Aucun élève enregistré.");
            return;
        }
        System.out.println("\n========== LISTE DES ÉLÈVES (" + listeEleves.size() + ") ==========");
        for (Eleve eleve : listeEleves) {
            System.out.println(eleve);
        }
        System.out.println("==============================================");
    }

    /**
     * Affiche les notes et la moyenne d'un élève.
     */
    public void afficherBulletinEleve(String matricule) {
        Eleve eleve = rechercherParMatricule(matricule);
        if (eleve == null) {
            System.out.println("Élève non trouvé.");
            return;
        }
        System.out.println("\n===== BULLETIN DE " + eleve.getNomComplet().toUpperCase() + " =====");
        System.out.println("Classe : " + eleve.getClasseActuelle());
        if (eleve.getNotes().isEmpty()) {
            System.out.println("Aucune note enregistrée.");
        } else {
            for (Note note : eleve.getNotes()) {
                System.out.println("  " + note);
            }
            System.out.printf("  ➜ Moyenne générale : %.2f / 20%n", eleve.calculerMoyenneGenerale());
        }
        System.out.println("======================================");
    }

    /**
     * Retourne la liste complète des élèves.
     */
    public List<Eleve> getTousLesEleves() {
        return listeEleves;
    }

    /**
     * Retourne le nombre total d'élèves.
     */
    public int getNombreEleves() {
        return listeEleves.size();
    }
}
