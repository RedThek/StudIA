package cm.enspm.studia.repository.mysql;

import cm.enspm.studia.model.dto.Eleve;
import cm.enspm.studia.repository.EleveRepository;
import java.sql.*;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySQLEleveRepository implements EleveRepository{
    
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
            // In a real app, wrap this in a custom DataAccessException
            throw new RuntimeException("Erreur Base de données lors de l'enregistrement de l'élève", e);
        }
    }

    @Override
    public Optional<Eleve> RechercherEleveParMatricule(String matriculeEleve) {
        String sql = "SELECT * FROM eleves WHERE matricule = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(2, matriculeEleve);
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
            rs.getDate("dateNaissance").toLocalDate(),
            rs.getString("lieuNaissance"),
            rs.getString("sexe"), 
            rs.getString("photo"), 
            rs.getString("nationalite")
        );
    }

    @Override
    public void modifierEleve(Eleve eleve) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierEleve'");
    }

    @Override
    public void desactiverEleve(String matriculeEleve) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'desactiver'");
    }

    @Override
    public List<Eleve> RechercherEleveParNom(String mot_cle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RechercherEleveParNom'");
    }

    @Override
    public void supprimerEleve(String matriculeEleve) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerEleve'");
    }

}
