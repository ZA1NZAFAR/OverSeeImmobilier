package models;


import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
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
}
