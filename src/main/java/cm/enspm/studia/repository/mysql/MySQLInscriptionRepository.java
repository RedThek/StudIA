package cm.enspm.studia.repository.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cm.enspm.studia.model.dto.administration.ClasseDTO;
import cm.enspm.studia.model.dto.administration.InscriptionDTO;
import cm.enspm.studia.model.dto.personnes.EleveDTO;
import cm.enspm.studia.repository.InscriptionRepository;

public class MySQLInscriptionRepository implements InscriptionRepository {

    private final Connection connection;

    public MySQLInscriptionRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void enregistrerInscription(InscriptionDTO inscriptionDTO) {
        String sql = """
            INSERT INTO inscription (
                `id-eleve-inscription`,
                `id-classe-inscription`,
                `statut`,
                `date-inscription`
            ) VALUES (?, ?, ?, ?)
            """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, inscriptionDTO.idEleve());
            stmt.setInt(2, inscriptionDTO.idClasse());
            stmt.setString(3, inscriptionDTO.statut());
            stmt.setString(4, inscriptionDTO.dateInscription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de l'enregistrement de l'inscription", e);
        }
    }

    @Override
    public void modifierInscription(InscriptionDTO inscriptionDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifierInscription'");
    }

    @Override
    public void supprimerInscription(Integer idInscription) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerInscription'");
    }

    @Override
    public String getStatutEleve(Integer idEleve, Integer idClasse) {
        String sql = """
        SELECT * FROM inscription WHERE `id-eleve-inscription` = ? AND `id-classe-inscription` = ?
        """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEleve);
            stmt.setInt(2, idClasse);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getString("statut");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la récupération du statut de l'élève", e);
        }
    }

    @Override
    public Optional<EleveDTO> rechercherEleveParClasse(Integer idEleve, Integer idClasse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rechercherEleveParClasse'");
    }

    @Override
    public Optional<EleveDTO> rechercherEleveParClasse2(String matriculeEleve, Integer idClasse) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rechercherEleveParClasse2'");
    }

    @Override
    public Optional<ClasseDTO> rechercherClasseParEleve(Integer idEleve, Integer idAnneeScolaire) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rechercherClasseParEleve'");
    }

    @Override
    public List<EleveDTO> getAllElevesDuneClasse(Integer idClasse) {
        String sql = "SELECT * FROM inscription WHERE `id-classe-inscription` = ?";
        
        
        List<EleveDTO> eleves = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idClasse);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    //eleves.add(rs);
            }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur Base de données lors de la récupération des élèves", e);
        }
        return eleves;
        }
}
