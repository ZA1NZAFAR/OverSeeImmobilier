package models;


import interfaces.HTMLable;
import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
public class Propriete extends Personne implements HTMLable {

    private long numeroReference;
    private String adresse;
    private String ville;
    private long codePostal;
    private String type;
    private long nombre_de_piece;
    private long superficie;
    private String etat_d_habitation;
    private long garage;
    private long prixInitial;
    private String locationOuVente;
    private Date dateDisponibilite;
    private long idProprietaire;



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
                "<td>" + locationOuVente + "</td>" +
                "<td>" + dateDisponibilite + "</td>" +
                "</tr>";
    }

    public String getAdressComplet() {
        return adresse + ", " + codePostal + " " + ville;
    }
}
