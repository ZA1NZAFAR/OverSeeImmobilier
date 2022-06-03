package models;

import interfaces.HTMLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Proprietaire extends Personne implements HTMLable {

    private long idProprietaire;
    private long idPersonne;
    private long nbBienPossedes;


    public String toHTML() {
        Personne tmp = DatabaseConnector.getPersonneById((int) this.idPersonne);
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + idProprietaire + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + idProprietaire + "</td>" +
                "<td>" + tmp.getNom() + "</td>" +
                "<td>" + tmp.getPrenom() + "</td>" +
                "<td>" + tmp.getDateNaissance() + "</td>" +
                "<td>" + tmp.getAdresse() + "</td>" +
                "<td>" + tmp.getCodePostal() + "</td>" +
                "<td>" + tmp.getVille() + "</td>" +
                "<td>" + tmp.getNumeroTel() + "</td>" +
                "<td>" + tmp.getEmail() + "</td>" +
                "<td>" + DatabaseConnector.getNbPropertiesOfAProprietaire(idProprietaire) + "</td>" +
                "</tr>";
    }
}
