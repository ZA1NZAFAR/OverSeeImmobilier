package models;


import interfaces.HTMLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Client extends Personne implements HTMLable {

    private long idClient;
    private long idPersonne;


    public String toHTML() {
        Personne tmp = DatabaseConnector.getPersonneById((int) this.idPersonne);
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + idClient + "</td>" +
                "<td>" + tmp.getNom() + "</td>" +
                "<td>" + tmp.getPrenom() + "</td>" +
                "<td>" + tmp.getDateNaissance() + "</td>" +
                "<td>" + tmp.getAdresse() + "</td>" +
                "<td>" + tmp.getCodePostal() + "</td>" +
                "<td>" + tmp.getVille() + "</td>" +
                "<td>" + tmp.getNumeroTel() + "</td>" +
                "<td>" + tmp.getEmail() + "</td>" +
                "<td>" + DatabaseConnector.NbBuysClient(idClient) + "</td>" +
                "</tr>";
    }
}
