package servlets;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EditPropertyServlet", urlPatterns = "/EditPropertyServlet")
public class EditPropertyServlet extends HttpServlet {
    @SneakyThrows
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        String propertyId = req.getParameter("propertyId");
        req.setAttribute("propertyId", propertyId);



        resp.sendRedirect("views/biens/modif.jsp");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/front-end/login.html");
    }

}
