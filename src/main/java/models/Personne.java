package models;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Personne {

    private long idPersonne;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String adresse;
    private String ville;
    private long codePostal;


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

    public String getNomComplet() {
        return this.prenom + " " + this.nom;
    }
}
