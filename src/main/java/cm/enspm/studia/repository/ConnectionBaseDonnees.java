package cm.enspm.studia.repository;

public class ConnectionBaseDonnees {
    private static ConnectionBaseDonnees instance;

    private ConnectionBaseDonnees() {
    }

    public static ConnectionBaseDonnees getInstance() {
        if (instance == null) {
            instance = new ConnectionBaseDonnees();
        }
        return instance;
    }
}
