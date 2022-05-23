package tools;

import models.IDsDTO;

import java.util.Arrays;
import java.util.List;

public class Helper {
    public static IDsDTO visitStringToDTO(String visitString) {
        List<String> l = Arrays.asList(visitString.split("#"));
        return IDsDTO.builder().numeroReferenceBien(Long.parseLong(l.get(0))).idAgentImmobilier(Long.parseLong(l.get(1))).idProprietaire(Long.parseLong(l.get(2))).idClient(Long.parseLong(l.get(3))).build();
    }
}
