package models;


import interfaces.HTMLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Transaction implements HTMLable {

    private long numeroReference;
    private long idAgentImmobilier;
    private long idProprietaire;
    private long idClient;
    private java.sql.Date datevente;
    private long commission;
    private long montantTotalTransaction;
    private String typeTransaction;
    private long prixVente;

    public String toHTML() {
        Propriete p = DatabaseConnector.getProprieteById((int) numeroReference);
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + numeroReference + "~" + idAgentImmobilier + "~" + idProprietaire + "~" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + datevente + "</td>" +
                "<td>" + p.getAdressComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) idProprietaire).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) idClient).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + p.getLocationOuVente() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) idAgentImmobilier).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + p.getPrixInitial() + "</td>" +
                "<td>" + prixVente + "</td>" +
                "<td>" + commission + "</td>" +
                "<td>" + ((prixVente + ((prixVente / 100) * 3)) + 1000) + "</td>" +
                "</tr>";
    }
}
