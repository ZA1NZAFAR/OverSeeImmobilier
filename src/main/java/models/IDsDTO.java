package models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IDsDTO {
    long idAgentImmobilier;
    long idClient;
    long idProprietaire;
    long numeroReferenceBien;

    @Override
    public String toString() {
        return numeroReferenceBien + "~" + idAgentImmobilier + "~" + idProprietaire + "~" + idClient;
    }
}
