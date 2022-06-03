package servlets;

import lombok.SneakyThrows;
import models.IDsDTO;
import models.Log;
import tools.DatabaseConnector;
import tools.Helper;
import tools.HtmlDisplayer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageTransactionServlet", urlPatterns = "/ManageTransactionServlet")
public class ManageTransactionServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        IDsDTO visitDTO = null;
        String s1 = req.getParameter("dateVente");
        String s2 = req.getParameter("tf_commision");
        String s3 = req.getParameter("tf_prixVente");
        String s4 = req.getParameter("list_ref");
        String s5 = req.getParameter("list_client");
        String s6 = req.getParameter("list_prop");
        String s7 = req.getParameter("list_agent");

        if (!action.equals("add"))
            visitDTO = Helper.visitStringToDTO(req.getParameter("transactionString"));
        if (action.equals("add") || action.equals("update")) {
            if (s1 == null || s2 == null || s3 == null || s4 == null || s5 == null || s6 == null || s7 == null) {
                HtmlDisplayer.warning(req, resp, "Veuillez remplir tous les champs!");
            } else if (Integer.parseInt(s2) < 0 || Integer.parseInt(s3) < 0)
                HtmlDisplayer.warning(req, resp, "Veuillez entrer des valeurs positives!");
            else if (Integer.parseInt(s2) > 5 || Integer.parseInt(s2) < 3) {
                HtmlDisplayer.warning(req, resp, "Veuillez entrer une commission entre 0 et 5!");
            }
        }

        switch (action) {
            case "add":
                DatabaseConnector.executeUpdate("INSERT INTO Transaction (" +
                        "datevente, " +
                        "commission, " +
                        "prixVente, " +
                        "numeroReference, " +
                        "idClient, " +
                        "idProprietaire, " +
                        "idAgentImmobilier) " +
                        "VALUES ('" +
                        s1 + "', " +
                        s2 + ", " +
                        s3 + ", " +
                        s4 + ", " +
                        s5 + ", " +
                        s6 + ", " +
                        s7 + ")");
                DatabaseConnector.log(Log.builder().idAgent(Long.parseLong(req.getParameter("list_agent"))).action("Ajout").information("Transaction").build());
                resp.sendRedirect("views/transactions/gestion.jsp");
                break;
            case "edit":
                resp.sendRedirect("views/transactions/modif.jsp?transactionString=" + visitDTO.toString());
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Transaction " +
                        "WHERE " +
                        "numeroReference = " + visitDTO.getNumeroReferenceBien() + "" +
                        " AND " +
                        "idClient = '" + visitDTO.getIdClient() + "'" +
                        " AND " +
                        "idAgentImmobilier = '" + visitDTO.getIdAgentImmobilier() + "'" +
                        " AND " +
                        "idProprietaire = " + visitDTO.getIdProprietaire() + ";");
                DatabaseConnector.log(Log.builder().idAgent(visitDTO.getIdAgentImmobilier()).action("Suppression").information("Transaction : " + visitDTO).build());
                resp.sendRedirect("views/transactions/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Transaction " +
                        "SET " +
                        "dateVente = '" + s1 + "', " +
                        "commission = " + s2 + ", " +
                        "prixVente = " + s3 + ", " +
                        "numeroReference = " + s4 + ", " +
                        "idClient = " + s5 + ", " +
                        "idProprietaire = " + s6 + ", " +
                        "idAgentImmobilier = " + s7 + " " +
                        "WHERE " +
                        "numeroReference = " + visitDTO.getNumeroReferenceBien() + "" +
                        " AND " +
                        "idClient = '" + visitDTO.getIdClient() + "'" +
                        " AND " +
                        "idAgentImmobilier = '" + visitDTO.getIdAgentImmobilier() + "'" +
                        " AND " +
                        "idProprietaire = " + visitDTO.getIdProprietaire() + ";");
                DatabaseConnector.log(Log.builder().idAgent(visitDTO.getIdAgentImmobilier()).action("Modification").information("Transaction : " + visitDTO).build());
                resp.sendRedirect("views/transactions/gestion.jsp");
                break;

        }
    }
}
