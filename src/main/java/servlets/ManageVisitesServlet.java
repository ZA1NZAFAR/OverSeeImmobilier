package servlets;

import lombok.SneakyThrows;
import models.Log;
import tools.DatabaseConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageVisitesServlet", urlPatterns = "/ManageVisitesServlet")
public class ManageVisitesServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String propertyId = req.getParameter("propertyId");
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
                        req.getParameter("dateVisite") + "', '" +
                        req.getParameter("heureVisite") + "', " +
                        req.getParameter("list_ref") + ", " +
                        req.getParameter("list_client") + ", " +
                        req.getParameter("list_prop") + ", " +
                        req.getParameter("list_agent") + ")");
                DatabaseConnector.log(Log.builder().idAgent(Long.parseLong(req.getParameter("list_agent"))).action("Ajout").information("Visite").build());
                resp.sendRedirect("views/visites/gestion.jsp");
                break;
            case "edit":
                resp.sendRedirect("views/biens/modif.jsp?propertyId=" + propertyId);

        }
    }
}