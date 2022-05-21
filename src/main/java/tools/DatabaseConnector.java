package tools;

import models.AgentImmobilier;
import models.Personne;
import models.Propriete;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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


    public static void close() throws SQLException {
        dbConnection.close();
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
            ResultSetHandler<List<Propriete>> h = new BeanListHandler<Propriete>(Propriete.class);
            proprietes = run.query("SELECT * FROM Propriete", h);
        } catch (SQLException e) {
            throw new RuntimeException("Could not get all proprietes", e);
        }
        return proprietes;
    }
}