package tools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlDisplayer {
    public static void error(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>OverSee</title>");
            out.println("<link rel='stylesheet' href='css/style.css' type='text/css' />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 style=\"text-align: center;\" ><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"images/logo.png\" alt=\"\" /></h2>");
            out.println("<h2 style=\"text-align: center;font-size: 3vw;\">Il y a eu un problème :( </h2>");
            out.println("<h3 style=\"text-align: center;font-size: 2vw;\">Message d'erreur : " + message + "</h3>");
            out.println("<h3 style=\"text-align: center;font-size: 1vw;\">Cliquez ici pour vous reconnecter : <a style=\"color: black;\" href=\"/OverSeeImmobilier\">Login</a> </h3>");
            out.println("<p style=\"text-align: center;font-size: 1vw;\"><span style=\"color: #ff0000;\"><strong><em>Si l'erreur persiste, veuillez contacter un administrateur!</em></strong></span></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public static void warning(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>OverSee</title>");
            out.println("<link rel='stylesheet' href='css/style.css' type='text/css' />");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 style=\"text-align: center;\" ><img style=\"display: block; margin-left: auto; margin-right: auto;\" src=\"images/logo.png\" alt=\"\" /></h2>");
            out.println("<h2 style=\"text-align: center;font-size: 3vw;\">Oops, Une ou plusieurs entrées sont incorrectes ! </h2>");
            out.println("<h3 style=\"text-align: center;font-size: 2vw;\">Message : " + message + "</h3>");
            out.println("<h3 style=\"text-align: center;font-size: 1vw;\">Retournez à la page précédente et réessayez.</h3>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
