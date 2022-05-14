package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("tf_id");
        String password = req.getParameter("tf_pwd");

        if (username.equals("admin") && password.equals("admin")) {
            req.getSession().setAttribute("username", username);
            resp.sendRedirect("front-end/accueil.html");
        } else {
            resp.sendRedirect("/login.html");
        }
    }
}
