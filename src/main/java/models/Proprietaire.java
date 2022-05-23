package models;

import lombok.Getter;
import lombok.Setter;
import tools.DatabaseConnector;

@Getter
@Setter
public class Proprietaire {

    private long idProprietaire;
    private long idPersonne;
    private long nbBienPossedes;


    public long getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(long idProprietaire) {
        this.idProprietaire = idProprietaire;
    }


    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }


    public long getNbBienPossedes() {
        return nbBienPossedes;
    }

    public void setNbBienPossedes(long nbBienPossedes) {
        this.nbBienPossedes = nbBienPossedes;
    }

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
                "<td>" + "TODO" + "</td>" +
                "<td>" + "TODO" + "</td>" +
                "</tr>";
    }
}
