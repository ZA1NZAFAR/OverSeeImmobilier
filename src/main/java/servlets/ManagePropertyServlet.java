package servlets;

import lombok.SneakyThrows;
import models.Log;
import tools.DatabaseConnector;
import tools.HtmlDisplayer;

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

        // Import des données
        String adr = req.getParameter("tf_adr");
        String vll = req.getParameter("tf_ville");
        String cp = req.getParameter("tf_cp");
        String type = req.getParameter("listT_biens");
        String piece = req.getParameter("stepper_nbPiece");
        String sprf = req.getParameter("tf_superficie");
        String etat = req.getParameter("list_type");
        String garage = req.getParameter("stepper_garage");
        String prx = req.getParameter("stepper_prix");
        String dte = req.getParameter("date");
        String idPr = req.getParameter("list_prop");
        String locVe = req.getParameter("tf_locVent");

        // Les checks
        if (action.equals("add") && (adr == null || vll == null || cp == null || type == null || piece == null || sprf == null || etat == null || garage == null || prx == null || dte == null || locVe == null)) {
            HtmlDisplayer.warning(req, resp, "Veuillez remplir tous les champs!");
        } else if (cp.length() != 5) {
            HtmlDisplayer.warning(req, resp, "Le code postal doit être composé de 5 chiffres!");
        } else if (Integer.parseInt(piece) < 1)
            HtmlDisplayer.warning(req, resp, "Le nombre de pièces doit être supérieur à 0!");
        else if (Integer.parseInt(sprf) < 1)
            HtmlDisplayer.warning(req, resp, "La superficie doit être supérieur à 0!");
        else if (Integer.parseInt(garage) < 0)
            HtmlDisplayer.warning(req, resp, "Le nombre de garage doit être supérieur ou égal à 0!");
        else if (Integer.parseInt(prx) < 0)
            HtmlDisplayer.warning(req, resp, "Le prix doit être supérieur ou égal à 0!");


        String propertyId = req.getParameter("propertyId");
        switch (action) {
            case "edit":
                resp.sendRedirect("views/biens/modif.jsp?propertyId=" + propertyId);
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Propriete WHERE numeroReference = " + propertyId);
                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Suppression").information("Propriété " + propertyId).build());
                resp.sendRedirect("views/biens/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Propriete " +
                        "SET " +
                        "adresse = '" + adr + "', " +
                        "ville = '" + vll + "', " +
                        "codePostal = " + cp + ", " +
                        "type = '" + type + "', " +
                        "nombre_De_Piece = " + piece + ", " +
                        "superficie = " + sprf + ", " +
                        "etat_d_habitation = '" + etat + "', " +
                        "garage = " + garage + ", " +
                        "prixInitial = " + prx + ", " +
                        "dateDisponibilite = '" + dte + "', " +
                        "idProprietaire = " + idPr + ", " +
                        "locationOuVente = '" + locVe + "' " +
                        "WHERE numeroReference = " + req.getParameter("refBien") + ";");
                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Modification").information("Propriété " + propertyId).build());
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
                        "locationOuVente, " +
                        "dateDisponibilite, " +
                        "idProprietaire) VALUES ('" +
                        (DatabaseConnector.getLastId("Propriete", "numeroReference") + 1) + "', '" +
                        adr + "', '" +
                        vll + "', " +
                        cp + ", '" +
                        type + "', " +
                        piece + ", " +
                        sprf + ", '" +
                        etat + "', " +
                        garage + ", " +
                        prx + ", '" +
                        locVe + "', '" +
                        dte + "', " +
                        idPr + ");");
                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Ajout").information("Propriété " + propertyId).build());
                resp.sendRedirect("views/biens/gestion.jsp");
                break;
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
