package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public class Personne {

    private long idPersonne;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String adresse;
    private String ville;
    private long codePostal;

    public String getNomComplet() {
        return this.prenom + " " + this.nom;
    }
}
