package models;


import interfaces.SQLable;

public class Proprietaire extends Personne implements SQLable {

    private long idPropriétaire;
    private long idPersonne;


    public long getIdPropriétaire() {
        return idPropriétaire;
    }

    public void setIdPropriétaire(long idPropriétaire) {
        this.idPropriétaire = idPropriétaire;
    }


    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO `proprietaire` (`idPropriétaire`, `idPersonne`) VALUES (" + idPropriétaire + ", " + idPersonne + ");";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE `proprietaire` SET `idPropriétaire` = " + idPropriétaire + ", `idPersonne` = " + idPersonne + " WHERE `proprietaire`.`idPropriétaire` = " + idPropriétaire + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM `proprietaire` WHERE `proprietaire`.`idPropriétaire` = " + idPropriétaire + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM `proprietaire` WHERE `proprietaire`.`idPropriétaire` = " + idPropriétaire + ";";
    }
}
