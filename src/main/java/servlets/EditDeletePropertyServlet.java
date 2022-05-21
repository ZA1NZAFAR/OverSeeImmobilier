package servlets;

import lombok.SneakyThrows;
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
        }

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
