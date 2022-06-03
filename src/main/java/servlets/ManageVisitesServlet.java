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

@WebServlet(name = "ManageVisitesServlet", urlPatterns = "/ManageVisitesServlet")
public class ManageVisitesServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");

        // Import d'informations
        String dtV = req.getParameter("dateVisite");
        String hrV = req.getParameter("heureVisite");
        String refB = req.getParameter("list_ref");
        String clnt = req.getParameter("list_client");
        String prp = req.getParameter("list_prop");
        String agnt = req.getParameter("list_agent");

        // Les checks
        //Check if above null
        if ((action.equals("add") || action.equals("update")) && (dtV == null || hrV == null || refB == null || clnt == null || prp == null || agnt == null)) {
            HtmlDisplayer.warning(req, resp, "Veuillez remplir tous les champs!");
        }


        IDsDTO visitDTO = null;
        if (!action.equals("add"))
            visitDTO = Helper.visitStringToDTO(req.getParameter("visitString"));
        switch (action) {
            case "add":
                DatabaseConnector.executeUpdate("INSERT INTO Visite (" +
                        "dateVisite, " +
                        "heureVisite, " +
                        "numeroReference, " +
                        "idClient, " +
                        "idProprietaire, " +
                        "idAgentImmobilier) " +
                        "VALUES ('" +
                        dtV + "', '" +
                        hrV + "', " +
                        refB + ", " +
                        clnt + ", " +
                        prp + ", " +
                        agnt + ")");
                DatabaseConnector.log(Log.builder().idAgent(Long.parseLong(req.getParameter("list_agent"))).action("Ajout").information("Visite").build());
                resp.sendRedirect("views/visites/gestion.jsp");
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
                        "dateVisite = '" + dtV + "', " +
                        "heureVisite = '" + hrV + "', " +
                        "numeroReference = " + refB + ", " +
                        "idClient = " + clnt + ", " +
                        "idProprietaire = " + prp + ", " +
                        "idAgentImmobilier = " + agnt + " " +
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