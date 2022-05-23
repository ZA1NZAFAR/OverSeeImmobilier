package servlets;

import lombok.SneakyThrows;
import models.Log;
import tools.DatabaseConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "ManagePropertyServlet", urlPatterns = "/ManagePropertyServlet")
public class ManagePropertyServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String propertyId = req.getParameter("propertyId");
        switch (action) {
            case "edit":
                resp.sendRedirect("views/biens/modif.jsp?propertyId=" + propertyId);
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Propriete WHERE numeroReference = " + propertyId);
                DatabaseConnector.log(Log.builder().idAgent(0).action("Suppression").information("Propriété " + propertyId).build());
                resp.sendRedirect("views/biens/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Propriete SET adresse = '" +
                        req.getParameter("tf_adr") + "', ville = '" +
                        req.getParameter("tf_ville") + "', codePostal = " +
                        req.getParameter("tf_cp") + ", type = '" +
                        req.getParameter("listT_biens") + "', nombre_De_Piece = " +
                        req.getParameter("stepper_nbPiece") + ", superficie = " +
                        req.getParameter("tf_superficie") + ", etat_d_habitation = '" +
                        req.getParameter("list_type") + "', garage = " +
                        req.getParameter("stepper_garage") + ", prixInitial = " +
                        req.getParameter("stepper_prix") + ", dateDisponibilite = '" +
                        req.getParameter("date") + "', idProprietaire = " +
                        req.getParameter("list_prop") + " WHERE numeroReference = " +
                        req.getParameter("refBien") + ";");
                DatabaseConnector.log(Log.builder().idAgent(0).action("Modification").information("Propriété " + propertyId).build());
                resp.sendRedirect("views/biens/gestion.jsp");
                break;
            case "add":
                DatabaseConnector.executeUpdate("INSERT INTO Propriete (" +
                        "numeroReference, " +
                        "adresse, " +
                        "ville, " +
                        "codePostal, " +
                        "type, " +
                        "nombre_De_Piece, " +
                        "superficie, " +
                        "etat_d_habitation, " +
                        "garage, " +
                        "prixInitial, " +
                        "dateDisponibilite, " +
                        "idProprietaire) VALUES ('" +
                        (DatabaseConnector.getLastId("Propriete", "numeroReference") + 1) + "', '" +
                        req.getParameter("tf_adr") + "', '" +
                        req.getParameter("tf_ville") + "', " +
                        req.getParameter("tf_cp") + ", '" +
                        req.getParameter("listT_biens") + "', " +
                        req.getParameter("stepper_nbPiece") + ", " +
                        req.getParameter("tf_superficie") + ", '" +
                        req.getParameter("list_type") + "', " +
                        req.getParameter("stepper_garage") + ", " +
                        req.getParameter("stepper_prix") + ", '" +
                        req.getParameter("date") + "', " +
                        req.getParameter("list_prop") + ");");
                DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Propriété " + propertyId).build());
                resp.sendRedirect("views/biens/gestion.jsp");
                break;
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
