package models;

import lombok.Getter;
import lombok.Setter;

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

}
