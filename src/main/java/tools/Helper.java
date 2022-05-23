package tools;

import models.VisitInfoDTO;

import java.util.Arrays;
import java.util.List;

public class Helper {
    public static VisitInfoDTO visitStringToDTO(String visitString) {
        List<String> l = Arrays.asList(visitString.split("#"));
        return VisitInfoDTO.builder().numeroReferenceBien(Long.parseLong(l.get(0))).idAgentImmobilier(Long.parseLong(l.get(1))).idProprietaire(Long.parseLong(l.get(2))).idClient(Long.parseLong(l.get(3))).build();
    }
}
