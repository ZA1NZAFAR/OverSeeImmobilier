package tools;

import models.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

    public AgentImmobilier login(String username, String password) throws SQLException {
        try {
            ResultSet rs = executeQuery("SELECT * FROM AgentImmobilier WHERE idAgentImmobilier = '" + username + "' AND mdp = '" + password + "'");
            if (rs.next()) {
                return AgentImmobilier
                        .builder()
                        .idAgentImmobilier(rs.getInt("idAgentImmobilier"))
                        .estAdministrateur(rs.getString("estAdministrateur"))
                        .idPersonne(rs.getInt("idPersonne"))
                        .build();
            } else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException("Could not login", e);
        }
    }

    public static Personne getPersonneById(int idPersonne) {
        try {
            ResultSet rs = executeQuery("SELECT * FROM Personne WHERE idPersonne = " + idPersonne);
            if (rs.next()) {
                return Personne.builder().idPersonne(idPersonne).nom(rs.getString("nom")).prenom(rs.getString("prenom")).build();
            } else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException("Could not login", e);
        }
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
        List<Proprietaire> proprietes = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Proprietaire>> h = new BeanListHandler<>(Proprietaire.class);
            proprietes = run.query("SELECT * FROM Proprietaire ", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return proprietes;
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
        executeQuery(log.getSQLInsert());
    }
}