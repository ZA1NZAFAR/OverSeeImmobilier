package models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Personne {

    private long idPersonne;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String numeroTel;
    private String email;
    private String adresse;
    private String ville;
    private long codePostal;

    public String getNomComplet() {
        return this.prenom + " " + this.nom;
    }
}
