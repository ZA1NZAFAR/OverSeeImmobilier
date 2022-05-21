package servlets;

import tools.ConnectionManager;
import tools.DatabaseConnector;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("tf_id");
        String password = req.getParameter("tf_pwd");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        if (databaseConnector.login(username, password)!=null) {
            resp.sendRedirect("/views/index.html");
        }

        if (username.equals("admin") && password.equals("admin")) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("front-end/accueil.jsp");
        } else {
            resp.sendRedirect("/login.html");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }


}
