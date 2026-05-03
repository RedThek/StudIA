package cm.enspm.studia.service;

import cm.enspm.studia.repository.ConnectionBaseDonnees;
import cm.enspm.studia.repository.EleveRepository;
import cm.enspm.studia.repository.mysql.MySQLEleveRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseService {
    
    private static DatabaseService instance;
    private Connection connection;
    private EleveRepository eleveRepository;
    
    private DatabaseService() throws SQLException {
        this.connection = ConnectionBaseDonnees.getConnection();
        this.eleveRepository = new MySQLEleveRepository(connection);
    }
    
    public static DatabaseService getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }
    
    public EleveRepository getEleveRepository() {
        return eleveRepository;
    }
    
    public void close() throws SQLException {
        ConnectionBaseDonnees.closeConnection();
    }
}