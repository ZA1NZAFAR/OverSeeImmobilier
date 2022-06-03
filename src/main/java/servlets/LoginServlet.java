package servlets;

import com.mysql.cj.util.StringUtils;
import lombok.SneakyThrows;
import models.AgentImmobilier;
import models.Log;
import tools.DatabaseConnector;
import tools.HtmlDisplayer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("tf_id");
        String password = req.getParameter("tf_pwd");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        AgentImmobilier agentImmobilier;
        if (StringUtils.isStrictlyNumeric(username) && (agentImmobilier = databaseConnector.login(username, password)) != null) {
            DatabaseConnector.log(Log.builder().idAgent(agentImmobilier.getIdAgentImmobilier()).action("Connexion").information("Connexion agent " + agentImmobilier.getIdAgentImmobilier()).build());
            req.getSession(true).setAttribute("idAgent", agentImmobilier.getIdAgentImmobilier());
            resp.sendRedirect("views/accueil.jsp?idAgent=" + agentImmobilier.getIdAgentImmobilier());
        } else {
            HtmlDisplayer.error(req, resp, "Unable to login with the given credentials");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("index.jsp");
    }


}
