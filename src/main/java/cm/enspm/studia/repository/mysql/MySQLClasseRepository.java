package cm.enspm.studia.repository.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.administration.ClasseDTO;
import cm.enspm.studia.repository.ClasseRepository;

public class MySQLClasseRepository implements ClasseRepository {

    private final Connection connection;

    public MySQLClasseRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void enregistrerClasse(ClasseDTO classeDTO) {
        String sql = """
            INSERT INTO classe (
                `id-classe`,
                `nom-classe`,
                `id-niveau-etude-classe`,
                `id-annee-scolaire-classe`
            ) VALUES (?, ?, ?, ?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, classeDTO.identifiantClasse());
            stmt.setString(2, classeDTO.nomClasse());
            stmt.setInt(3, classeDTO.niveauEtude());
            stmt.setInt(4, classeDTO.anneeScolare());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de l'enregistrement de la classe", e);
        }
    }

    @Override
    public void modifierClasse(ClasseDTO classeDTO) {
        String sql = """
            UPDATE classe
            SET `nom-classe` = ?,
                `id-niveau-etude-classe` = ?,
                `id-annee-scolaire-classe` = ?
            WHERE `id-classe` = ?
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, classeDTO.nomClasse());
            stmt.setInt(2, classeDTO.niveauEtude());
            stmt.setInt(3, classeDTO.anneeScolare());
            stmt.setInt(4, classeDTO.identifiantClasse());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la modification de la classe", e);
        }
    }

    @Override
    public void supprimerClasse(Integer idClasse) {
        String sql = "DELETE FROM classe WHERE `id-classe` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idClasse);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la suppression de la classe", e);
        }
    }

    @Override
    public Integer rechercherIdParNom(String nomClasse, Integer anneeScolare) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rechercherIdParNom'");
    }

    private ClasseDTO appliquerResultatDansClasse(ResultSet rs) throws SQLException {
        return new ClasseDTO(
            rs.getInt("id-classe"),
            rs.getString("nom-classe"),
            rs.getInt("id-niveau-etude-classe"),
            rs.getInt("id-annee-scolaire-classe")
        );
    }

    @Override
    public Optional<ClasseDTO> RechercherClasseParNom(String nomClasse, Integer anneeScolare) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RechercherClasseParNom'");
    }

    @Override
    public List<ClasseDTO> getAllClasses(Integer anneeScolare) {
        String sql = "SELECT * FROM classe WHERE `id-annee-scolaire-classe` = ?";
        List<ClasseDTO> classes = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                classes.add(appliquerResultatDansClasse(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la récupération des classes", e);
        }
        return classes;
    }
    
}
