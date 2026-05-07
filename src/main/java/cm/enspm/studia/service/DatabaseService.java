package cm.enspm.studia.service;

import cm.enspm.studia.repository.ConnectionBaseDonnees;
import cm.enspm.studia.repository.EleveRepository;
import cm.enspm.studia.repository.ParentRepository;
import cm.enspm.studia.repository.mysql.MySQLEleveRepository;
import cm.enspm.studia.repository.mysql.MySQLParentRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseService {
    
    private static DatabaseService instance;
    private Connection connection;
    private EleveRepository eleveRepository;
    private ParentRepository parentRepository;
    
    private DatabaseService() throws SQLException {
        this.connection = ConnectionBaseDonnees.getConnection();
        this.eleveRepository = new MySQLEleveRepository(connection);
        this.parentRepository = new MySQLParentRepository(connection);
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

    public ParentRepository getParentRepository() {
        return parentRepository;
    }
    
    public void close() throws SQLException {
        ConnectionBaseDonnees.closeConnection();
    }
}