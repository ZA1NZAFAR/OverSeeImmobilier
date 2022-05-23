package servlets;

import lombok.SneakyThrows;
import models.Log;
import tools.DatabaseConnector;
import tools.HtmlDisplayer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManagePersonServlet", urlPatterns = "/ManagePersonServlet")
public class ManagePersonServlet extends HttpServlet {

    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        String clientId = req.getParameter("clientId");
        String proprietaireId = req.getParameter("propId");
        String personneId = req.getParameter("idPersonne");
        String idAgent = req.getParameter("idAgent");

        long personId = -1;
        if (clientId != null) {
            personId = DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById(Integer.parseInt(clientId)).getIdPersonne()).getIdPersonne();
        } else if (proprietaireId != null) {
            personId = DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById(Integer.parseInt(proprietaireId)).getIdPersonne()).getIdPersonne();
        } else if (personneId != null) {
            personId = DatabaseConnector.getPersonneById(Integer.parseInt(personneId)).getIdPersonne();
        } else if (idAgent!= null){
            personId = DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById(Integer.parseInt(idAgent)).getIdPersonne()).getIdPersonne();
        }else if (!action.equals("add")) {
            HtmlDisplayer.processRequest(req, resp, "No person selected");
        }

        switch (action) {
            case "edit":
                resp.sendRedirect("views/clients/modif.jsp?personId=" + personId);
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Personne WHERE idPersonne = " + personId);
                DatabaseConnector.log(Log.builder().idAgent(0).action("Suppression").information("Personne " + personId).build());
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
                        "WHERE idPersonne = " + personId);
                DatabaseConnector.log(Log.builder().idAgent(0).action("Update").information("Personne " + personId).build());
                resp.sendRedirect("views/clients/gestion.jsp");
                break;
            case "add":
                DatabaseConnector.executeUpdate("INSERT INTO Personne (" +
                        "idPersonne, " +
                        "nom, " +
                        "prenom," +
                        "numeroTel," +
                        "email," +
                        "adresse," +
                        "ville," +
                        "codePostal," +
                        "dateNaissance)" +
                        "VALUES (" +
                        (DatabaseConnector.getLastId("Personne", "idPersonne") + 1) + ", '" +
                        req.getParameter("tf_nom") + "', '" +
                        req.getParameter("tf_prenom") + "','" +
                        req.getParameter("tf_tel") + "','" +
                        req.getParameter("tf_email") + "','" +
                        req.getParameter("tf_adr") + "','" +
                        req.getParameter("tf_ville") + "','" +
                        req.getParameter("tf_cp") + "','" +
                        req.getParameter("dateNaissance") + "')");

                DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Client " + (DatabaseConnector.getLastId("Personne", "idPersonne"))).build());

                DatabaseConnector.executeUpdate("INSERT INTO Client (" +
                        "idClient, " +
                        "idPersonne)" +
                        "VALUES (" +
                        (DatabaseConnector.getLastId("Client", "idClient") + 1) + ", " +
                        (DatabaseConnector.getLastId("Personne", "idPersonne")) + ")");

                DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Client " + (DatabaseConnector.getLastId("Client", "idClient"))).build());


                if (req.getParameter("estProp") != null) {
                    DatabaseConnector.executeUpdate("INSERT INTO Proprietaire (" +
                            "idProprietaire, " +
                            "idPersonne, " +
                            "nbBienPossedes)" +
                            "VALUES (" +
                            (DatabaseConnector.getLastId("Proprietaire", "idProprietaire") + 1) + ", " +
                            (DatabaseConnector.getLastId("Personne", "idPersonne")) + ", " +
                            0 + ")");
                    DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Proprietaire " + (DatabaseConnector.getLastId("Proprietaire", "idProprietaire"))).build());

                }

                if (req.getParameter("estAgent") != null) {
                    DatabaseConnector.executeUpdate("INSERT INTO AgentImmobilier (" +
                            "idAgentImmobilier, " +
                            "idPersonne, " +
                            "mdp, " +
                            "estAdministrateur, " +
                            "salaire, " +
                            "dateEmbauche)" +
                            "VALUES ('" +
                            (DatabaseConnector.getLastId("AgentImmobilier", "idAgentImmobilier") + 1) + "','" +
                            (DatabaseConnector.getLastId("Personne", "idPersonne")) + "','" +
                            req.getParameter("mdp") + "','" +
                            ((req.getParameter("estAdmin") != null) ? "oui" : "non") + "','" +
                            req.getParameter("salaire") + "','" +
                            req.getParameter("dateEmb") + "')");
                    DatabaseConnector.log(Log.builder().idAgent(0).action("Ajout").information("Agent " + (DatabaseConnector.getLastId("AgentImmobilier", "idAgentImmobilier"))).build());
                }

                resp.sendRedirect("views/clients/gestion.jsp");
                break;
        }
    }
}
