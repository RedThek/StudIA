package cm.enspm.studia.repository.mysql;

import cm.enspm.studia.model.dto.personnes.EleveDTO;
import cm.enspm.studia.repository.EleveRepository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation MySQL du dépôt d'élèves.
 * Traduits les opérations EleveRepository en requêtes SQL.
 */
public class MySQLEleveRepository implements EleveRepository {

    private final Connection connection;

    public MySQLEleveRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Insère un nouvel élève dans la table MySQL `eleve`.
     */
    @Override
    public void enregistrerEleve(EleveDTO eleveDTO) {
        String sql = """
            INSERT INTO eleve (
                `matricule-eleve`,
                `nom-eleve`,
                `prenom-eleve`,
                `date-naissance-eleve`,
                `lieu-naissance-eleve`,
                `sexe-eleve`,
                `photo-eleve`,
                `nationalite-eleve`
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eleveDTO.matricule());
            stmt.setString(2, eleveDTO.nom());
            stmt.setString(3, eleveDTO.prenom());
            if (eleveDTO.dateNaissance() != null) {
                stmt.setDate(4, Date.valueOf(eleveDTO.dateNaissance()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.setString(5, eleveDTO.lieuNaissance());
            stmt.setString(6, eleveDTO.sexe());
            stmt.setString(7, eleveDTO.photo());
            stmt.setString(8, eleveDTO.nationalite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de l'enregistrement de l'élève", e);
        }
    }

    /**
     * Retourne un élève correspondant au matricule fourni.
     */
    @Override
    public Optional<EleveDTO> RechercherEleveParMatricule(String matriculeEleve) {
        String sql = """
        SELECT * FROM eleve WHERE `matricule-eleve` = ?
        """;
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

    /**
     * Recherche l'identifiant interne d'un élève par son matricule.
     */
    @Override
    public Integer rechercherIdParMatricule(String matriculeEleve) {
        String sql = "SELECT `id-eleve` FROM eleve WHERE `matricule-eleve` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id-eleve");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la recherche de l'ID de l'élève", e);
        }
        return null;
    }

    /**
     * Transforme une ligne SQL ResultSet en DTO Eleve.
     */
    private EleveDTO appliquerResultatDansEleve(ResultSet rs) throws SQLException {
        java.sql.Date sqlDate = rs.getDate("date-naissance-eleve");
        java.time.LocalDate dateNaissance = sqlDate != null ? sqlDate.toLocalDate() : null;
        return new EleveDTO(
            rs.getInt("id-eleve"),
            rs.getString("matricule-eleve"),
            rs.getString("nom-eleve"),
            rs.getString("prenom-eleve"),
            dateNaissance,
            rs.getString("lieu-naissance-eleve"),
            rs.getString("sexe-eleve"),
            rs.getString("photo-eleve"),
            rs.getString("nationalite-eleve")
        );
    }

    /**
     * Met à jour les informations d'un élève existant dans la base.
     */
    @Override
    public void modifierEleve(EleveDTO eleve) {
        String sql = """
            UPDATE eleve
            SET `nom-eleve` = ?,
                `prenom-eleve` = ?,
                `date-naissance-eleve` = ?,
                `lieu-naissance-eleve` = ?,
                `sexe-eleve` = ?,
                `photo-eleve` = ?,
                `nationalite-eleve` = ?
            WHERE `matricule-eleve` = ?
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eleve.nom());
            stmt.setString(2, eleve.prenom());
            if (eleve.dateNaissance() != null) {
                stmt.setDate(3, Date.valueOf(eleve.dateNaissance()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
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

    /**@Override
    public void desactiverEleve(String matriculeEleve) {
        String sql = "UPDATE eleve SET status = 'inactive' WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la désactivation de l'élève", e);
        }
    }**/

    /**
     * Recherche des élèves dont le nom ou le prénom correspond à un mot clé.
     */
    public List<EleveDTO> RechercherEleveParNom(String mot_cle) {
        String sql = """
            SELECT * FROM eleve
            WHERE `nom-eleve` LIKE ? OR `prenom-eleve` LIKE ?
            """;
        List<EleveDTO> eleves = new ArrayList<>();
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

    /**
     * Supprime un élève de la base par matricule.
     */
    @Override
    public void supprimerEleve(String matriculeEleve) {
        String sql = "DELETE FROM eleve WHERE `matricule-eleve` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, matriculeEleve);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la suppression de l'élève", e);
        }
    }

    /**
     * Retourne tous les élèves stockés dans la table MySQL.
     */
    @Override
    public List<EleveDTO> getAllEleves() {
        String sql = "SELECT * FROM eleve";
        List<EleveDTO> eleves = new ArrayList<>();
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

