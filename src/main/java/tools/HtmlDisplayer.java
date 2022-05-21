package tools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlDisplayer {
    public static void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>\n" +
                    "    <title>Message</title>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <link href=\"design.css\" rel=\"stylesheet\"/>\n" +
                    "</head>");
            out.println("<body class=\"TheMessage\">");
            out.println("<h1>" + request.getAttribute("message") + "</h1>");
            out.println("<br><a href=\"index.jsp\">Homepage</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
