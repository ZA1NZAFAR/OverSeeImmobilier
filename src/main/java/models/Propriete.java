package models;


import interfaces.HTMLable;
import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
public class Propriete implements SQLable, HTMLable {

    private long numeroReference;
    private String adresse;
    private String ville;
    private long codePostal;
    private String type;
    private long nombre_de_piece;
    private double superficie;
    private String etat_d_habitation;
    private long garage;
    private double prixInitial;
    private Date dateDisponibilite;
    private long idProprietaire;


    @Override
    public String getSQLInsert() {
        return "INSERT INTO Propriete (numeroReference, adresse, ville, codePostal, type, nombreDePiece, superficie, etatDHabitation, garage, prixInitial, dateDisponibilite, idProprietaire) VALUES (" +
                numeroReference + ", '" +
                adresse + "', '" +
                ville + "', " +
                codePostal + ", '" +
                type + "', " +
                nombre_de_piece + ", " +
                superficie + ", '" +
                etat_d_habitation + "', " +
                garage + ", " +
                prixInitial + ", '" +
                dateDisponibilite + "', " +
                idProprietaire + ");";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE Propriete SET numeroReference = " +
                numeroReference + ", adresse = '" +
                adresse + "', ville = '" +
                ville + "', codePostal = " +
                codePostal + ", type = '" +
                type + "', nombreDePiece = " +
                nombre_de_piece + ", superficie = " +
                superficie + ", etatDHabitation = '" +
                etat_d_habitation + "', garage = " +
                garage + ", prixInitial = " +
                prixInitial + ", dateDisponibilite = '" +
                dateDisponibilite + "', idProprietaire = " +
                idProprietaire + " WHERE numeroReference = " +
                numeroReference + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM Propriete WHERE numeroReference = " +
                numeroReference + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM Propriete WHERE numeroReference = " +
                numeroReference + ";";
    }

    @Override
    public String toHTML() {
        return "<tr>" +
                "<td><input type=\"checkbox\" name=\"" + numeroReference + "\" onclick=\"updateCheckBoxes();\"></td>" +
                "<td>" + numeroReference + "</td>" +
                "<td>" + type + "</td>" +
                "<td>" + nombre_de_piece + "</td>" +
                "<td>" + superficie + "</td>" +
                "<td>" + etat_d_habitation + "</td>" +
                "<td>" + garage + "</td>" +
                "<td>" + prixInitial + "</td>" +
                "<td>" + Objects.requireNonNull(DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) idProprietaire).getIdPersonne())).getNomComplet() + "</td>" +
                "<td>" + adresse + "</td>" +
                "<td>" + codePostal + "</td>" +
                "<td>" + ville + "</td>" +
                "<td>" + dateDisponibilite + "</td>" +
                "</tr>";
    }

    public String getAdressComplet() {
        return adresse + ", " + codePostal + " " + ville;
    }
}
