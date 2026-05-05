package cm.enspm.studia.repository.mysql;

import cm.enspm.studia.model.dto.personnes.ParentDTO;
import cm.enspm.studia.repository.ParentRepository;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLParentRepository implements ParentRepository {

    private final Connection connection;

    public MySQLParentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ParentDTO> findAllParents() {
        String sql = "SELECT * FROM parent";
        List<ParentDTO> parents = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                parents.add(mapResultSetToParent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des parents", e);
        }
        return parents;
    }

    @Override
    public Optional<ParentDTO> findById(int identifiant) {
        String sql = "SELECT * FROM parent WHERE id-parent = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, identifiant);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToParent(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du parent par identifiant", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ParentDTO> findByCni(String numeroCNI) {
        String sql = "SELECT * FROM parent WHERE numero-cni-parent = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, numeroCNI);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToParent(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du parent par CNI", e);
        }
        return Optional.empty();
    }

    @Override
    public void save(ParentDTO parentDTO) {
        String sql = """
        INSERT INTO parent (
        numero-cni-parent, 
        nom-parent, 
        prenom-parent, 
        date-naissance-parent, 
        telephone-parent, 
        email-parent, 
        profession-parent, 
        nationalite-parent, 
        adresse-parent
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, parentDTO.numeroCNI());
            stmt.setString(2, parentDTO.nom());
            stmt.setString(3, parentDTO.prenom());
            stmt.setString(4, parentDTO.dateNaissance());
            stmt.setString(5, parentDTO.telephone());
            stmt.setString(6, parentDTO.email());
            stmt.setString(7, parentDTO.profession());
            stmt.setString(8, parentDTO.nationalite());
            stmt.setString(9, parentDTO.adresse());
            //stmt.setString(10, parentDTO.lienParental());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'enregistrement du parent", e);
        }
    }

    @Override
    public void update(ParentDTO parentDTO) {
        String sql = """
        UPDATE parent SET 
        nom-parent = ?, 
        prenom-parent = ?, 
        date-naissance-parent = ?, 
        telephone-parent = ?, 
        email-parent = ?, 
        profession-parent = ?, 
        nationalite-parent = ?, 
        adresse-parent = ?, 
        WHERE numero-cni-parent = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, parentDTO.nom());
            stmt.setString(2, parentDTO.prenom());
            stmt.setString(3, parentDTO.dateNaissance());
            stmt.setString(4, parentDTO.telephone());
            stmt.setString(5, parentDTO.email());
            stmt.setString(6, parentDTO.profession());
            stmt.setString(7, parentDTO.nationalite());
            stmt.setString(8, parentDTO.adresse());
            //stmt.setString(9, parentDTO.lienParental());
            stmt.setString(10, parentDTO.numeroCNI());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la mise à jour du parent", e);
        }
    }

    @Override
    public void deleteById(int identifiant) {
        String sql = "DELETE FROM parent WHERE id-parent = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, identifiant);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la suppression du parent", e);
        }
    }

    private ParentDTO mapResultSetToParent(ResultSet rs) throws SQLException {
        return new ParentDTO(
            rs.getInt("id-parent"),
            rs.getString("numero-cni-parent"),
            rs.getString("nom-parent"),
            rs.getString("prenom-parent"),
            rs.getString("date-naissance-parent"),
            rs.getString("telephone-parent"),
            rs.getString("email-parent"),
            rs.getString("profession-parent"),
            rs.getString("nationalite-parent"),
            rs.getString("adresse-parent")
        );
    }
}
