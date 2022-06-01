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
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static final Connection dbConnection = ConnectionManager.getConnection();


    public static ResultSet executeQuery(String query) throws SQLException {
        return dbConnection.createStatement().executeQuery(query);
    }

    public static void executeUpdate(String query) throws SQLException {
        dbConnection.createStatement().executeUpdate(query);
    }

    public static void executeDelete(String query) throws SQLException {
        dbConnection.createStatement().executeUpdate(query);
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

    public static Client getClientById(int clientId) {
        Client client = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Client> h = new BeanHandler<>(Client.class);
            client = run.query("SELECT * FROM Client WHERE idClient = " + clientId, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all clients", e);
        }
        return client;
    }

    public static Proprietaire getProprietaireByPersonneId(int idPersonne) {
        Proprietaire prop = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Proprietaire> h = new BeanHandler<>(Proprietaire.class);
            prop = run.query("SELECT * FROM Proprietaire WHERE idPersonne = " + idPersonne, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return prop;
    }

    public static Client getClientByPersonneId(int clientId) {
        Client client = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Client> h = new BeanHandler<>(Client.class);
            client = run.query("SELECT * FROM Client WHERE idPersonne = " + clientId, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all clients", e);
        }
        return client;
    }

    public static List<AgentImmobilier> getAllAgentImmobiliers() {
        List<AgentImmobilier> agentImmobiliers = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<AgentImmobilier>> h = new BeanListHandler<>(AgentImmobilier.class);
            agentImmobiliers = run.query("SELECT * FROM AgentImmobilier", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all agentImmobiliers", e);
        }
        return agentImmobiliers;
    }

    public static AgentImmobilier getAgentImmobilierByPersonneId(int idPersonne) {
        AgentImmobilier agentImmobilier = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<AgentImmobilier> h = new BeanHandler<>(AgentImmobilier.class);
            agentImmobilier = run.query("SELECT * FROM AgentImmobilier WHERE idPersonne = " + idPersonne, h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all agentImmobiliers", e);
        }
        return agentImmobilier;
    }

    public static List<Visite> getAllVisites() {
        List<Visite> visites = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Visite>> h = new BeanListHandler<>(Visite.class);
            visites = run.query("SELECT * FROM Visite", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all visites", e);
        }
        return visites;
    }

    public static Visite getVisitUsingDTO(IDsDTO dto) {
        Visite visite = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Visite> h = new BeanHandler<>(Visite.class);
            visite = run.query("SELECT * FROM Visite " +
                            "WHERE numeroReference = " + dto.getNumeroReferenceBien() +
                            " AND idClient = " + dto.getIdClient() +
                            " AND idProprietaire = " + dto.getIdProprietaire() +
                            " AND idAgentImmobilier = " + dto.getIdAgentImmobilier()
                    , h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all visites", e);
        }
        return visite;
    }

    public static List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<List<Transaction>> h = new BeanListHandler<>(Transaction.class);
            transactions = run.query("SELECT * FROM Transaction", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all transactions", e);
        }
        return transactions;
    }

    public static Transaction getTransactionUsing(IDsDTO v) {
        Transaction transaction = null;
        try {
            QueryRunner run = new QueryRunner(ConnectionManager.getDataSource());
            ResultSetHandler<Transaction> h = new BeanHandler<>(Transaction.class);
            transaction = run.query("SELECT * FROM Transaction " +
                            "WHERE numeroReference = " + v.getNumeroReferenceBien() +
                            " AND idClient = " + v.getIdClient() +
                            " AND idProprietaire = " + v.getIdProprietaire() +
                            " AND idAgentImmobilier = " + v.getIdAgentImmobilier()
                    , h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all transactions", e);
        }
        return transaction;
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