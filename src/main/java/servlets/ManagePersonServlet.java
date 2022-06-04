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
        // Information basique
        String action = req.getParameter("action");
        String nom = req.getParameter("tf_nom");
        String prnm = req.getParameter("tf_prenom");
        String ddn = req.getParameter("dateNaissance");
        String addr = req.getParameter("tf_adr");
        String vll = req.getParameter("tf_ville");
        String cp = req.getParameter("tf_cp");
        String tel = req.getParameter("tf_tel");
        String mel = req.getParameter("tf_email");

        // Information agent immobilier
        String mdp = req.getParameter("mdp");
        String slr = req.getParameter("salaire");
        String demb = req.getParameter("dateEmb");

        // Les checks
        if ((action.equals("add") || action.equals("update"))) {
            if (nom == null || prnm == null || ddn == null || addr == null || vll == null || cp == null || tel == null || mel == null) {
                HtmlDisplayer.warning(req, resp, "Veuillez remplir tous les champs!");
            } else if (cp.length() != 5) {
                HtmlDisplayer.warning(req, resp, "Le code postal doit être composé de 5 chiffres!");
            } else if (tel.length() != 10) {
                HtmlDisplayer.warning(req, resp, "Le numéro de téléphone doit être composé de 10 chiffres!");
            } else if (mel.length() != 0 && !mel.contains("@")) {
                HtmlDisplayer.warning(req, resp, "L'adresse email doit être valide!");
            } else if (action.equals("add") && (mdp == null || slr == null || demb == null)) {
                HtmlDisplayer.warning(req, resp, "Veuillez remplir tous les champs de l'agent immobilier!");
            }
        }

        // Identification de ka personne (si elle existe)
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
        } else if (idAgent != null) {
            personId = DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById(Integer.parseInt(idAgent)).getIdPersonne()).getIdPersonne();
        } else if (!action.equals("add")) {
            HtmlDisplayer.error(req, resp, "No person selected!");
        }

        switch (action) {
            case "edit":
                resp.sendRedirect("views/clients/modif.jsp?personId=" + personId);
                break;
            case "delete":
                DatabaseConnector.executeDelete("DELETE FROM Personne WHERE idPersonne = " + personId);
                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Suppression").information("Personne " + personId).build());
                resp.sendRedirect("views/clients/gestion.jsp");
                break;
            case "update":
                DatabaseConnector.executeUpdate("UPDATE Personne SET " +
                        "nom = '" + nom + "', " +
                        "prenom = '" + prnm + "', " +
                        "dateNaissance = '" + ddn + "', " +
                        "adresse = '" + addr + "', " +
                        "ville = '" + vll + "', " +
                        "codePostal = '" + cp + "', " +
                        "numeroTel = '" + tel + "', " +
                        "email = '" + mel + "' " +
                        "WHERE idPersonne = " + personId);
                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Update").information("Personne " + personId).build());
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
                        nom + "', '" +
                        prnm + "','" +
                        tel + "','" +
                        mel + "','" +
                        addr + "','" +
                        vll + "','" +
                        cp + "','" +
                        ddn + "')");

                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Ajout").information("Client " + (DatabaseConnector.getLastId("Personne", "idPersonne"))).build());

                DatabaseConnector.executeUpdate("INSERT INTO Client (" +
                        "idClient, " +
                        "idPersonne)" +
                        "VALUES (" +
                        (DatabaseConnector.getLastId("Client", "idClient") + 1) + ", " +
                        (DatabaseConnector.getLastId("Personne", "idPersonne")) + ")");

                DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Ajout").information("Client " + (DatabaseConnector.getLastId("Client", "idClient"))).build());


                if (req.getParameter("estProp") != null) {
                    DatabaseConnector.executeUpdate("INSERT INTO Proprietaire (" +
                            "idProprietaire, " +
                            "idPersonne)" +
                            "VALUES (" +
                            (DatabaseConnector.getLastId("Proprietaire", "idProprietaire") + 1) + ", " +
                            (DatabaseConnector.getLastId("Personne", "idPersonne")) + ")");
                    DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Ajout").information("Proprietaire " + (DatabaseConnector.getLastId("Proprietaire", "idProprietaire"))).build());

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
                            mdp + "','" +
                            ((req.getParameter("estAdmin") != null) ? "oui" : "non") + "','" +
                            slr + "','" +
                            demb + "')");
                    DatabaseConnector.log(Log.builder().idAgent((Long) req.getSession().getAttribute("idAgent")).action("Ajout").information("Agent " + (DatabaseConnector.getLastId("AgentImmobilier", "idAgentImmobilier"))).build());
                }

                resp.sendRedirect("views/clients/gestion.jsp");
                break;
        }
    }
}
