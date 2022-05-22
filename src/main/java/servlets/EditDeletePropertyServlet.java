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
            req.getRequestDispatcher("/views/biens/gestion.jsp").forward(req, resp);
        } else if (action.equals("update")) {
            DatabaseConnector.executeUpdate("UPDATE Propriete SET adresse = '" +
                    req.getAttribute("tf_adr") + "', ville = '" +
                    req.getAttribute("tf_ville") + "', codePostal = " +
                    req.getAttribute("tf_cp") + ", type = '" +
                    req.getAttribute("listT_biens") + "', nombreDePiece = " +
                    req.getAttribute("stepper_nbPiece") + ", superficie = " +
                    req.getAttribute("tf_superficie") + ", etatDHabitation = '" +
                    req.getAttribute("list_type") + "', garage = " +
                    req.getAttribute("stepper_garage") + ", prixInitial = " +
                    req.getAttribute("???") + ", dateDisponibilite = '" +
                    req.getAttribute("date") + "', idProprietaire = " +
                    req.getAttribute("???") + " WHERE numeroReference = " +
                    req.getAttribute("ref_bien") + ";");
            DatabaseConnector.log(Log.builder().idAgent(0).action("Modification d'un bien").information("TODO! Add query here!").build());
            resp.sendRedirect("views/accueil.jsp");
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
