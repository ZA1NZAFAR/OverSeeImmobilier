package models;


import interfaces.SQLable;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tools.DatabaseConnector;

@Getter
@Setter
public class AgentImmobilier extends Personne implements SQLable {

    private long idAgentImmobilier;
    private String estAdministrateur;
    private String mdp;
    private java.sql.Date dateEmbauche;
    private String salaire;
    private long idPersonne;


    public long getIdAgentImmobilier() {
        return idAgentImmobilier;
    }

    public void setIdAgentImmobilier(long idAgentImmobilier) {
        this.idAgentImmobilier = idAgentImmobilier;
    }


    public String getEstAdministrateur() {
        return estAdministrateur;
    }

    public void setEstAdministrateur(String estAdministrateur) {
        this.estAdministrateur = estAdministrateur;
    }


    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


    public java.sql.Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(java.sql.Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }


    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }


    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO agent_immobilier (idAgentImmobilier, estAdministrateur, mdp, dateEmbauche, salaire, idPersonne) VALUES (" +
                idAgentImmobilier + ", '" + estAdministrateur + "', '" + mdp + "', '" + dateEmbauche + "', '" + salaire + "', " + idPersonne + ")";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE agent_immobilier SET estAdministrateur = '" + estAdministrateur + "', mdp = '" + mdp + "', dateEmbauche = '" + dateEmbauche + "', salaire = '" + salaire + "', idPersonne = " + idPersonne + " WHERE idAgentImmobilier = " + idAgentImmobilier;
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM agent_immobilier WHERE idAgentImmobilier = " + idAgentImmobilier;
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM agent_immobilier WHERE idAgentImmobilier = " + idAgentImmobilier;
    }

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
