package models;


import interfaces.HTMLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Visite implements HTMLable {

    private long numeroReference;
    private long idClient;
    private long idProprietaire;
    private long idAgentImmobilier;
    private java.sql.Date dateVisite;
    private java.sql.Time heureVisite;
    
    public String toHTML() {
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + numeroReference + "~" + idAgentImmobilier + "~" + idProprietaire + "~" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + dateVisite + "</td>" +
                "<td>" + heureVisite + "</td>" +
                "<td>" + DatabaseConnector.getProprieteById((int) numeroReference).getAdressComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) idAgentImmobilier).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) idClient).getIdPersonne()).getNomComplet() + "</td>" +
                "<td>" + DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) idProprietaire).getIdPersonne()).getNomComplet() + "</td>" +
                "</tr>";
    }
}
