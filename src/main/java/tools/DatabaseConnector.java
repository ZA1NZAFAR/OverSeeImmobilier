package tools;

import models.AgentImmobilier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection dbConnection;

    public DatabaseConnector() {
        try {
            dbConnection = ConnectionManager.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to database", e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return dbConnection.createStatement().executeQuery(query);
    }


    public void close() throws SQLException {
        dbConnection.close();
    }

    public AgentImmobilier login(String username, String password) {
        try {
            ResultSet rs = executeQuery("SELECT * FROM users WHERE AgentImmobilier = '" + username + "' AND password = '" + password + "'");
            if (rs.next()) {
                return new AgentImmobilier();
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException("Could not login", e);
        }
    }
}
