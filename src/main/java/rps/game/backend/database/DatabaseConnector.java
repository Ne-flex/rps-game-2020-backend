package rps.game.backend.database;


import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {

    private final String host;
    private final String database;
    private final String user;
    private final String password;

    public DatabaseConnector(String host, String database, String user, String password) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");

            Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":5432/" + database + "?user=" + user + "&password=" + password);
            return connection;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
