package models;


import interfaces.SQLable;

public class Propriete implements SQLable {

    private long numeroReference;
    private String adresse;
    private String ville;
    private long codePostal;
    private String type;
    private long nombreDePiece;
    private double superficie;
    private String etatDHabitation;
    private long garage;
    private double prixInitial;
    private java.sql.Date dateDisponibilite;
    private long idProprietaire;


    public long getNumeroReference() {
        return numeroReference;
    }

    public void setNumeroReference(long numeroReference) {
        this.numeroReference = numeroReference;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getNombreDePièce() {
        return nombreDePiece;
    }

    public void setNombreDePièce(long nombreDePièce) {
        this.nombreDePiece = nombreDePièce;
    }


    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }


    public String getEtatDHabitation() {
        return etatDHabitation;
    }

    public void setEtatDHabitation(String etatDHabitation) {
        this.etatDHabitation = etatDHabitation;
    }


    public long getGarage() {
        return garage;
    }

    public void setGarage(long garage) {
        this.garage = garage;
    }


    public double getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(double prixInitial) {
        this.prixInitial = prixInitial;
    }


    public java.sql.Date getDateDisponibilite() {
        return dateDisponibilite;
    }

    public void setDateDisponibilite(java.sql.Date dateDisponibilite) {
        this.dateDisponibilite = dateDisponibilite;
    }


    public long getIdProprietaire() {
        return idProprietaire;
    }

    public void setIdProprietaire(long idProprietaire) {
        this.idProprietaire = idProprietaire;
    }

    @Override
    public String getSQLInsert() {
        return "INSERT INTO Propriete (numeroReference, adresse, ville, codePostal, type, nombreDePiece, superficie, etatDHabitation, garage, prixInitial, dateDisponibilite, idProprietaire) VALUES (" +
                numeroReference + ", '" +
                adresse + "', '" +
                ville + "', " +
                codePostal + ", '" +
                type + "', " +
                nombreDePiece + ", " +
                superficie + ", '" +
                etatDHabitation + "', " +
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
                nombreDePiece + ", superficie = " +
                superficie + ", etatDHabitation = '" +
                etatDHabitation + "', garage = " +
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
}
