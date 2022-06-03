package models;


import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class AgentImmobilier extends Personne {

    private long idAgentImmobilier;
    private String estAdministrateur;
    private String mdp;
    private java.sql.Date dateEmbauche;
    private String salaire;
    private long idPersonne;

    public String toHTML() {
        Personne tmp = DatabaseConnector.getPersonneById((int) this.idPersonne);
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + idAgentImmobilier + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + idAgentImmobilier + "</td>" +
                "<td>" + tmp.getNom() + "</td>" +
                "<td>" + tmp.getPrenom() + "</td>" +
                "<td>" + tmp.getAdresse() + "</td>" +
                "<td>" + tmp.getCodePostal() + "</td>" +
                "<td>" + tmp.getVille() + "</td>" +
                "<td>" + dateEmbauche + "</td>" +
                "<td>" + salaire + "</td>" +
                "<td>" + estAdministrateur + "</td>" +
                "</tr>";
    }
}
