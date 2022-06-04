package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogDisplay {
    private long idLog;
    private String action;
    private String information;
    private long idAgent;

    public String getSQLInsert() {
        return "INSERT INTO Log (idAgent, action, information) VALUES ('" + idAgent + "', '" + action + "', '" + information + "')";
    }

    public String toHTML() {
        return "<tr>" +
                "<td>" + idLog + "</td>" +
                "<td>" + idAgent + "</td>" +
                "<td>" + action + "</td>" +
                "<td>" + information + "</td>" +
                "</tr>";
    }
}
