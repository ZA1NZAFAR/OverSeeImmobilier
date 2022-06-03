<%@ page import="tools.HtmlDisplayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session Checker</title>
</head>
<body>
<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("idAgent") == null) {
        HtmlDisplayer.processRequest(request, response, "session_expired.html");
    }
%>
</body>
</html>
