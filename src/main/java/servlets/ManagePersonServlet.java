package servlets;

import lombok.SneakyThrows;
import models.Log;
import tools.DatabaseConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManagePersonServlet", urlPatterns = "/ManagePersonServlet")
public class ManagePersonServlet extends HttpServlet {

    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String id = req.getParameter("clientId");
        switch (action) {
            case "edit":
                resp.sendRedirect("views/clients/modif.jsp?clientId=" + id);
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Personne WHERE idPersonne = " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById(Integer.parseInt(id)).getIdPersonne()).getIdPersonne());
                DatabaseConnector.log(Log.builder().idAgent(0).action("Suppression").information("Personne " + id).build());
                resp.sendRedirect("views/clients/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Personne SET " +
                        "nom = '" + req.getParameter("tf_nom") + "', " +
                        "prenom = '" + req.getParameter("tf_prenom") + "', " +
                        "dateNaissance = '" + req.getParameter("dateNaissance") + "', " +
                        "adresse = '" + req.getParameter("tf_adr") + "', " +
                        "ville = '" + req.getParameter("tf_ville") + "', " +
                        "codePostal = '" + req.getParameter("tf_cp") + "', " +
                        "numeroTel = '" + req.getParameter("tf_tel") + "', " +
                        "email = '" + req.getParameter("email") + "' " +
                        "WHERE idPersonne = " + req.getParameter("idPersonne"));
                DatabaseConnector.log(Log.builder().idAgent(0).action("Update").information("Personne " + id).build());
                resp.sendRedirect("views/clients/gestion.jsp");
                break;
            case "add":
                DatabaseConnector.executeUpdate("INSERT INTO Personne (" +
                        "idPersonne, " +
                        "nom, " +
                        "prenom," +
                        "numeroTel," +
                        "adresse," +
                        "ville," +
                        "codePostal," +
                        "dateNaissance)" +
                        "VALUES (" +
                        (DatabaseConnector.getLastId("Personne", "idPersonne") + 1) + ", '" +
                        req.getParameter("tf_nom") + "', '" +
                        req.getParameter("tf_prenom") + "','" +
                        req.getParameter("tf_tel") + "','" +
                        req.getParameter("tf_adr") + "','" +
                        req.getParameter("tf_ville") + "','" +
                        req.getParameter("tf_cp") + "','" +
                        req.getParameter("dateNaissance") + "')");

                DatabaseConnector.executeUpdate("INSERT INTO Client (" +
                        "idClient, " +
                        "idPersonne)" +
                        "VALUES (" +
                        (DatabaseConnector.getLastId("Client", "idClient") + 1) + ", " +
                        (DatabaseConnector.getLastId("Personne", "idPersonne")) + ")");

                if (req.getParameter("estProp") != null) {
                    DatabaseConnector.executeUpdate("INSERT INTO Proprietaire (" +
                            "idProprietaire, " +
                            "idPersonne, " +
                            "nbBienPossedes)" +
                            "VALUES (" +
                            (DatabaseConnector.getLastId("Proprietaire", "idProprietaire") + 1) + ", " +
                            (DatabaseConnector.getLastId("Personne", "idPersonne")) + ", " +
                            0 + ")");
                }

                DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Client " + (DatabaseConnector.getLastId("Client", "idClient"))).build());
                resp.sendRedirect("views/clients/gestion.jsp");
                break;
        }
    }
}
