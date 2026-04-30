# 🏫 StudIA - Gestion École Secondaire

Projet Java complet de gestion d'une école secondaire réalisé dans le cadre d'un TP de programmation orientée objet.

---

## 📁 Structure du projet

```
EcoleSecondaire/
└── src/
    ├── modeles/            ← Classes de données (modèles)
    │   ├── Personne.java       (classe abstraite de base)
    │   ├── Eleve.java          (élève avec notes)
    │   ├── Enseignant.java     (enseignant avec matières)
    │   ├── Administrateur.java (personnel admin)
    │   ├── Matiere.java        (matière enseignée)
    │   ├── Classe.java         (groupe d'élèves)
    │   ├── Salle.java          (salle de l'école)
    │   └── Note.java           (note d'un élève)
    │
    ├── services/           ← Services de gestion (logique métier)
    │   ├── GestionEleves.java
    │   ├── GestionEnseignants.java
    │   ├── GestionAdministration.java
    │   ├── GestionMatieres.java
    │   ├── GestionSalles.java
    │   └── GestionClasses.java
    │
    ├── utilitaires/        ← Outils transverses
    │   ├── Ecole.java          (classe centrale + statistiques)
    │   ├── MenuConsole.java    (affichage et lecture utilisateur)
    │   └── DonneesExemple.java (données de démonstration)
    │
    └── interfaces/         ← Point d'entrée de l'application
        └── Application.java    (main + menus interactifs)
```

---

## ✅ Fonctionnalités implémentées

### Gestion des Élèves
- Ajouter, modifier, supprimer un élève
- Rechercher par matricule ou par nom (partiel)
- Lister les élèves d'une classe
- Ajouter des notes (devoir, examen, interro, TP)
- Afficher le bulletin avec moyenne générale
- Trier par nom, classe, moyenne ou matricule

### Gestion des Enseignants
- Ajouter, modifier, supprimer un enseignant
- Rechercher par numéro d'employé, nom ou spécialité
- Assigner/retirer des matières et des classes
- Trier par nom, spécialité ou salaire

### Gestion de l'Administration
- Ajouter, modifier, supprimer un membre
- Rechercher par numéro d'employé, nom ou service
- Gestion du directeur de l'école (unicité garantie)
- Trier par nom, poste ou salaire

### Gestion des Matières
- Ajouter, modifier, supprimer une matière
- Rechercher par nom ou par enseignant responsable
- Trier par nom, coefficient ou heures hebdomadaires

### Gestion des Salles
- Ajouter, modifier, supprimer une salle
- Types : classe, labo, informatique, sport, bibliothèque, art
- Filtrer par disponibilité, type ou capacité minimale
- Trier par identifiant, capacité ou type

### Gestion des Classes
- Ajouter, modifier, supprimer une classe
- Inscrire/désinscrire des élèves
- Rechercher par niveau (3ème, Terminale…)
- Assigner salle et professeur principal

### Tableau de bord
- Statistiques globales de l'école
- Classement des meilleurs élèves
- Synthèse par classe (effectif + moyenne)

---

## 🚀 Compilation et lancement

### Avec un IDE (recommandé)
1. Ouvrir le dossier `EcoleSecondaire` dans **IntelliJ IDEA**, **Eclipse** ou **VS Code**
2. Marquer `src/` comme dossier source
3. Lancer `interfaces/Application.java`

### En ligne de commande
```bash
# Se placer à la racine du projet
cd EcoleSecondaire

# Créer le dossier de sortie
mkdir -p out

# Compiler tous les fichiers Java
javac -d out -sourcepath src src/interfaces/Application.java

# Lancer le programme
java -cp out interfaces.Application
```

> ⚠️ Requiert **Java 14 ou supérieur** (utilisation des switch expressions avec `->`)

---

## 🧩 Concepts POO utilisés

| Concept              | Où dans le code                                      |
|----------------------|------------------------------------------------------|
| **Héritage**         | `Eleve`, `Enseignant`, `Administrateur` → `Personne` |
| **Abstraction**      | Classe abstraite `Personne` + méthode `getRole()`    |
| **Encapsulation**    | Attributs `private` + getters/setters publics        |
| **Polymorphisme**    | Méthode `toString()` surchargée dans chaque classe   |
| **Collections**      | `ArrayList<>` pour toutes les listes                 |
| **Comparator**       | Tri multi-critères dans les services                 |
| **Séparation MVC**   | Modèles / Services (logique) / Interface console     |

---
