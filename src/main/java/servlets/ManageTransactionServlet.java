package servlets;

import lombok.SneakyThrows;
import models.IDsDTO;
import models.Log;
import tools.DatabaseConnector;
import tools.Helper;

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
        if (!action.equals("add"))
            visitDTO = Helper.visitStringToDTO(req.getParameter("visitString"));
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
                        req.getParameter("dateVente") + "', " +
                        req.getParameter("tf_commision") + ", " +
                        req.getParameter("tf_prixVente") + ", " +
                        req.getParameter("list_ref") + ", " +
                        req.getParameter("list_client") + ", " +
                        req.getParameter("list_prop") + ", " +
                        req.getParameter("list_agent") + ")");
                DatabaseConnector.log(Log.builder().idAgent(Long.parseLong(req.getParameter("list_agent"))).action("Ajout").information("Visite").build());
                resp.sendRedirect("views/transactions/gestion.jsp");
                break;
            case "edit":
                resp.sendRedirect("views/visites/modif.jsp?visitString=" + visitDTO.toString());
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Visite " +
                        "WHERE " +
                        "numeroReference = " + visitDTO.getNumeroReferenceBien() + "" +
                        " AND " +
                        "idClient = '" + visitDTO.getIdClient() + "'" +
                        " AND " +
                        "idAgentImmobilier = '" + visitDTO.getIdAgentImmobilier() + "'" +
                        " AND " +
                        "idProprietaire = " + visitDTO.getIdProprietaire() + ";");
                DatabaseConnector.log(Log.builder().idAgent(visitDTO.getIdAgentImmobilier()).action("Suppression").information("Visite : " + visitDTO).build());
                resp.sendRedirect("views/visites/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Visite " +
                        "SET " +
                        "dateVisite = '" + req.getParameter("dateVisite") + "', " +
                        "heureVisite = '" + req.getParameter("heureVisite") + "', " +
                        "numeroReference = " + req.getParameter("list_ref") + ", " +
                        "idClient = " + req.getParameter("list_client") + ", " +
                        "idProprietaire = " + req.getParameter("list_prop") + ", " +
                        "idAgentImmobilier = " + req.getParameter("list_agent") + " " +
                        "WHERE " +
                        "numeroReference = " + visitDTO.getNumeroReferenceBien() + "" +
                        " AND " +
                        "idClient = '" + visitDTO.getIdClient() + "'" +
                        " AND " +
                        "idAgentImmobilier = '" + visitDTO.getIdAgentImmobilier() + "'" +
                        " AND " +
                        "idProprietaire = " + visitDTO.getIdProprietaire() + ";");
                DatabaseConnector.log(Log.builder().idAgent(visitDTO.getIdAgentImmobilier()).action("Modification").information("Visite : " + visitDTO).build());
                resp.sendRedirect("views/visites/gestion.jsp");
                break;

        }
    }
}
