package models;


import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Transaction implements SQLable {

    private long numeroReference;
    private long idAgentImmobilier;
    private long idProprietaire;
    private long idClient;
    private java.sql.Date datevente;
    private long commission;
    private long montantTotalTransaction;
    private String typeTransaction;
    private double prixVente;

    @Override
    public String getSQLInsert() {
        return "INSERT INTO transactiontable (numeroReference, idAgentImmobilier, idPropriétaire, idClient, datevente, commission, montantTotalTransaction, typeTransaction, prixVente) VALUES (" +
                numeroReference + ", " +
                idAgentImmobilier + ", " +
                idProprietaire + ", " +
                idClient + ", " +
                "\"" + datevente + "\", " +
                "\"" + commission + "\", " +
                montantTotalTransaction + ", " +
                "\"" + typeTransaction + "\", " +
                prixVente + ");";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE transactiontable SET numeroReference = " +
                numeroReference + ", idAgentImmobilier = " +
                idAgentImmobilier + ", idPropriétaire = " +
                idProprietaire + ", idClient = " +
                idClient + ", datevente = " +
                "\"" + datevente + "\", " +
                "\"" + commission + "\", " +
                montantTotalTransaction + ", " +
                "\"" + typeTransaction + "\", " +
                prixVente + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM transactiontable WHERE numeroReference = " + numeroReference + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM transactiontable WHERE numeroReference = " + numeroReference + ";";
    }

    public String toHTML() {
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + numeroReference + "#" + idAgentImmobilier + "#" + idProprietaire + "#" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + datevente + "</td>" +
                "<td>" + DatabaseConnector.getProprieteById((int) numeroReference).getAdressComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) idProprietaire).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) idClient).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getProprieteById((int) numeroReference).getType() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) idAgentImmobilier).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getProprieteById((int) numeroReference).getPrixInitial() + "</td>" +
                "<td>" + prixVente + "</td>" +
                "<td>" + commission + "</td>" +
                "<td>" + ((prixVente + ((prixVente/100) *3)) + 1000) + "</td>" +
                "</tr>";
    }
}
