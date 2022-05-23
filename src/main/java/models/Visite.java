package models;


import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Visite implements SQLable {

    private long numeroReference;
    private long idClient;
    private long idProprietaire;
    private long idAgentImmobilier;
    private java.sql.Date dateVisite;
    private java.sql.Time heureVisite;


    public long getNumeroReference() {
        return numeroReference;
    }

    public void setNumeroReference(long numeroReference) {
        this.numeroReference = numeroReference;
    }


    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }


    public long getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(long idProprietaire) {
        this.idProprietaire = idProprietaire;
    }


    public long getIdAgentImmobilier() {
        return idAgentImmobilier;
    }

    public void setIdAgentImmobilier(long idAgentImmobilier) {
        this.idAgentImmobilier = idAgentImmobilier;
    }


    public java.sql.Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(java.sql.Date dateVisite) {
        this.dateVisite = dateVisite;
    }


    public java.sql.Time getHeureVisite() {
        return heureVisite;
    }

    public void setHeureVisite(java.sql.Time heureVisite) {
        this.heureVisite = heureVisite;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO visite (numeroReference, idClient, idProprietaire, idAgentImmobilier, dateVisite, heureVisite) VALUES (" +
                numeroReference + ", " +
                idClient + ", " +
                idProprietaire + ", " +
                idAgentImmobilier + ", " +
                "\"" + dateVisite + "\", " +
                "\"" + heureVisite + "\"" +
                ");";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE visite SET numeroReference = " +
                numeroReference + ", idClient = " +
                idClient + ", idProprietaire = " +
                idProprietaire + ", idAgentImmobilier = " +
                idAgentImmobilier + ", dateVisite = " +
                "\"" + dateVisite + "\", heureVisite = " +
                "\"" + heureVisite + "\"" +
                " WHERE numeroReference = " + numeroReference + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM visite WHERE numeroReference = " + numeroReference + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM visite WHERE numeroReference = " + numeroReference + ";";
    }

    public String toHTML() {
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + numeroReference + "$" + idAgentImmobilier + "$" + idProprietaire + "$" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + dateVisite + "</td>" +
                "<td>" + numeroReference + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) idAgentImmobilier).getIdPersonne()).getNomComplet()+ "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) idClient).getIdPersonne()).getNomComplet()+ "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) idProprietaire).getIdPersonne()).getNomComplet() + "</td>" +
                "</tr>";
    }
}
