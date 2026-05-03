package cm.enspm.studia.repository.mysql;

import cm.enspm.studia.model.dto.personnes.Eleve;
import cm.enspm.studia.repository.EleveRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLEleveRepository implements EleveRepository {

    private final Connection connection;

    public MySQLEleveRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void enregistrerEleve(Eleve eleve) {
        String sql = """
            INSERT INTO eleves (identifiant, matricule, nom, prenom, date_de_naissance, lieu_de_naissance, sexe, photo, nationalite)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, eleve.identifiant());
            stmt.setString(2, eleve.matricule());
            stmt.setString(3, eleve.nom());
            stmt.setString(4, eleve.prenom());
            stmt.setDate(5, Date.valueOf(eleve.dateNaissance()));
            stmt.setString(6, eleve.lieuNaissance());
            stmt.setString(7, eleve.sexe());
            stmt.setString(8, eleve.photo());
            stmt.setString(9, eleve.nationalite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de l'enregistrement de l'élève", e);
        }
    }

    @Override
    public Optional<Eleve> RechercherEleveParMatricule(String matriculeEleve) {
        String sql = "SELECT * FROM eleves WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(appliquerResultatDansEleve(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    private Eleve appliquerResultatDansEleve(ResultSet rs) throws SQLException {
        return new Eleve(
            rs.getInt("identifiant"),
            rs.getString("matricule"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getDate("date_de_naissance").toLocalDate(),
            rs.getString("lieu_de_naissance"),
            rs.getString("sexe"),
            rs.getString("photo"),
            rs.getString("nationalite")
        );
    }

    @Override
    public void modifierEleve(Eleve eleve) {
        String sql = """
            UPDATE eleves SET nom = ?, prenom = ?, date_de_naissance = ?, lieu_de_naissance = ?, sexe = ?, photo = ?, nationalite = ?
            WHERE matricule = ?
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eleve.nom());
            stmt.setString(2, eleve.prenom());
            stmt.setDate(3, Date.valueOf(eleve.dateNaissance()));
            stmt.setString(4, eleve.lieuNaissance());
            stmt.setString(5, eleve.sexe());
            stmt.setString(6, eleve.photo());
            stmt.setString(7, eleve.nationalite());
            stmt.setString(8, eleve.matricule());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la modification de l'élève", e);
        }
    }

    @Override
    public void desactiverEleve(String matriculeEleve) {
        String sql = "UPDATE eleves SET status = 'inactive' WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la désactivation de l'élève", e);
        }
    }

    @Override
    public List<Eleve> RechercherEleveParNom(String mot_cle) {
        String sql = "SELECT * FROM eleves WHERE nom LIKE ? OR prenom LIKE ?";
        List<Eleve> eleves = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            String pattern = "%" + mot_cle + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eleves.add(appliquerResultatDansEleve(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la recherche d'élèves par nom", e);
        }
        return eleves;
    }

    @Override
    public void supprimerEleve(String matriculeEleve) {
        String sql = "DELETE FROM eleves WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la suppression de l'élève", e);
        }
    }

    @Override
    public List<Eleve> getAllEleves() {
        String sql = "SELECT * FROM eleves";
        List<Eleve> eleves = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                eleves.add(appliquerResultatDansEleve(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la récupération des élèves", e);
        }
        return eleves;
    }
}

