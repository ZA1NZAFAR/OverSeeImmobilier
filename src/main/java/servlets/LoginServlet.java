package servlets;

import com.mysql.cj.util.StringUtils;
import lombok.SneakyThrows;
import models.AgentImmobilier;
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
            resp.sendRedirect("views/accueil.jsp?idAgent=" + agentImmobilier.getIdAgentImmobilier());
        } else {
            req.setAttribute("message", "Unable to login with the given credentials");
            HtmlDisplayer.processRequest(req, resp);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }


}
