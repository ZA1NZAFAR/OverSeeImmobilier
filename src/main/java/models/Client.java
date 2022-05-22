package models;


import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import tools.DatabaseConnector;

import java.util.Objects;

@Getter
@Setter
public class Client extends Personne implements SQLable {

    private long idClient;
    private long idPersonne;


    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }


    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO `client` (`idClient`, `idPersonne`) VALUES (" + idClient + ", " + idPersonne + ");";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE `client` SET `idClient` = " + idClient + ", `idPersonne` = " + idPersonne + " WHERE `client`.`idClient` = " + idClient + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM `client` WHERE `client`.`idClient` = " + idClient + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM `client` WHERE `client`.`idClient` = " + idClient + ";";
    }

    public String toHTML() {
        Personne tmp = DatabaseConnector.getPersonneById((int) this.idPersonne);
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + idClient + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + idClient + "</td>" +
                "<td>" + tmp.getNom() + "</td>" +
                "<td>" + tmp.getPrenom() + "</td>" +
                "<td>" + tmp.getAdresse() + "</td>" +
                "<td>" + tmp.getCodePostal() + "</td>" +
                "<td>" + tmp.getVille() + "</td>" +
                "<td>" + "TODO" + "</td>" +
                "<td>" + "TODO" + "</td>" +
                "</tr>";
    }
}
