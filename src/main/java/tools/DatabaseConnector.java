package tools;

import models.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static Connection dbConnection;

    public DatabaseConnector() {
        dbConnection = ConnectionManager.getConnection();
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        return dbConnection.createStatement().executeQuery(query);
    }

    public static void executeUpdate(String query) throws SQLException {
        dbConnection.createStatement().executeUpdate(query);
    }

    public static void executeDelete(String query) throws SQLException {
        // Disable foreign keys check
        Statement stmt = dbConnection.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=0");
        stmt.close();
        dbConnection.createStatement().executeUpdate(query);
        stmt = dbConnection.createStatement();
        stmt.execute("SET FOREIGN_KEY_CHECKS=1");
        stmt.close();
    }


    public static void close() throws SQLException {
        dbConnection.close();
    }

    public static boolean isAdmin(String idAgent) {
        try {
            ResultSet rs = executeQuery("SELECT estAdministrateur FROM AgentImmobilier WHERE idAgentImmobilier = " + idAgent);
            if (rs.next()) {
                return rs.getString("estAdministrateur").equals("oui");
            } else
                return false;
        } catch (SQLException e) {
            throw new RuntimeException("Could not login", e);
        }
    }

    public static AgentImmobilier getAgentById(int idAgent) {
        AgentImmobilier agent = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<AgentImmobilier> h = new BeanHandler<>(AgentImmobilier.class);
            agent = run.query("SELECT * FROM AgentImmobilier WHERE idAgentImmobilier = " + idAgent, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return agent;
    }

    public static Proprietaire getProprietaireById(int idProprietaire) {
        Proprietaire prop = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Proprietaire> h = new BeanHandler<>(Proprietaire.class);
            prop = run.query("SELECT * FROM Proprietaire WHERE idProprietaire = " + idProprietaire, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return prop;
    }

    public static List<Client> getAllClients() {
        List<Client> proprietes = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Client>> h = new BeanListHandler<>(Client.class);
            proprietes = run.query("SELECT * FROM Client", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return proprietes;
    }

    public AgentImmobilier login(String username, String password) throws SQLException {

        AgentImmobilier personne = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<AgentImmobilier> h = new BeanHandler<>(AgentImmobilier.class);
            personne = run.query("SELECT * FROM AgentImmobilier WHERE idAgentImmobilier = '" + username + "' AND mdp = '" + password + "'", h);
        } catch (SQLException e) {
            return null;
        }
        return personne;
    }

    public static Personne getPersonneById(int idPersonne) {
        Personne personne = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Personne> h = new BeanHandler<>(Personne.class);
            personne = run.query("SELECT * FROM Personne WHERE idPersonne = " + idPersonne, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return personne;
    }

    public static List<Propriete> getAllProprietes() {
        List<Propriete> proprietes = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Propriete>> h = new BeanListHandler<>(Propriete.class);
            proprietes = run.query("SELECT * FROM Propriete", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return proprietes;
    }

    public static List<Proprietaire> getAllProprietaires() {
        List<Proprietaire> proprietaires = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Proprietaire>> h = new BeanListHandler<>(Proprietaire.class);
            proprietaires = run.query("SELECT * FROM Proprietaire ", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return proprietaires;
    }

    public static Propriete getProprieteById(int idPropriete) {
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Propriete> h = new BeanHandler<>(Propriete.class);
            return run.query("SELECT * FROM Propriete WHERE numeroReference = " + idPropriete, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
    }

    public static void log(Log log) throws SQLException {
        executeUpdate(log.getSQLInsert());
    }

    public static int getLastId(String table, String column) throws SQLException {
        QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
        ResultSetHandler<Integer> h = new ScalarHandler<>();
        return run.query("SELECT MAX(" + column + ") FROM " + table, h);
    }
}