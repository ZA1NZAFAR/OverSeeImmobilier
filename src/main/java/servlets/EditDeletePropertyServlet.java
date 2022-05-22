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


@WebServlet(name = "EditDeletePropertyServlet", urlPatterns = "/EditDeletePropertyServlet")
public class EditDeletePropertyServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        String propertyId = req.getParameter("propertyId");
        if (action.equals("edit")) {
            resp.sendRedirect("views/biens/modif.jsp?propertyId=" + propertyId);
        } else if (action.equals("delete")) {
            DatabaseConnector.executeUpdate("DELETE FROM Propriete WHERE numeroReference = " + propertyId);
            resp.sendRedirect("views/biens/gestion.jsp");
        } else if (action.equals("update")) {
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
            DatabaseConnector.log(Log.builder().idAgent(0).action("Modification").information("TODO! Add query here!").build());
            resp.sendRedirect("views/biens/gestion.jsp");
        }else if (action.equals("add")) {

        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
