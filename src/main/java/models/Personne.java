package models;


import interfaces.SQLable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Personne implements SQLable {

    private long idPersonne;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String adresse;
    private String ville;
    private long codePostal;
    private long nbBienPossedes;


    public long getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(long idPersonne) {
        this.idPersonne = idPersonne;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public long getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(long codePostal) {
        this.codePostal = codePostal;
    }


    public long getNbBienPossedes() {
        return nbBienPossedes;
    }

    public void setNbBienPossedes(long nbBienPossedes) {
        this.nbBienPossedes = nbBienPossedes;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO personne (nom, prenom, numeroTel, adresse, ville, codePostal, nbBienPossedes) VALUES ('" + nom + "', '" + prenom + "', '" + numeroTel + "', '" + adresse + "', '" + ville + "', " + codePostal + ", " + nbBienPossedes + ")";
    }

    @Override
    public String getSQLUpdate() {
        return "UPDATE personne SET nom = '" + nom + "', prenom = '" + prenom + "', numeroTel = '" + numeroTel + "', adresse = '" + adresse + "', ville = '" + ville + "', codePostal = " + codePostal + ", nbBienPossedes = " + nbBienPossedes + " WHERE id = " + idPersonne + ";";
    }

    @Override
    public String getSQLDelete() {
        return "DELETE FROM personne WHERE id = " + idPersonne + ";";
    }

    @Override
    public String getSQLSelect() {
        return "SELECT * FROM personne WHERE id = " + idPersonne + ";";
    }
}
