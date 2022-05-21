package servlets;

import lombok.SneakyThrows;
import tools.DatabaseConnector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorServlet extends HttpServlet {

    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("tf_id");
        String password = req.getParameter("tf_pwd");

        DatabaseConnector databaseConnector = new DatabaseConnector();
        if (databaseConnector.login(username, password) != null) {
            resp.sendRedirect("/views/index.html");
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }
}
