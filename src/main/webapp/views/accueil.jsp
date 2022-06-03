<%@ page import="tools.DatabaseConnector" %>
<%@ page import="tools.HtmlDisplayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OverSee</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div style="width: 100%; height: 10vw;">
    <a href="../index.jsp"><img src="images/logOut.png"
                                style="width: 3vw; height: 3vw; position: relative; left: 95%;"/> </a>
</div>


<div style="text-align: center; font-size: 3vw;">


    <img src="images/logo.png" style="position: relative; right: 0vw; width: 15%; height:15%"/>

    <%
        String name = null;
        try {
            name = DatabaseConnector.getPersonneById(
                    (int) DatabaseConnector.getAgentById(
                            ((Long) request.getSession().getAttribute("idAgent")).intValue()
                    ).getIdPersonne()).getNomComplet();
        } catch (Exception e) {
            HtmlDisplayer.processRequest(request, response, "Session expired! Please log in again.");
        }
    %>

    <p> Bonjour <%=name%>, que voulez-vous faire ? </p>

    <a href="biens/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les biens</button>
    </a>
    <a href="transactions/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les transactions</button>
    </a>
    <a href="visites/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les visites</button>
    </a>
    <a href="proprietaires/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les propietaires</button>
    </a>
    <a href="clients/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les clients</button>
    </a>
    <%if (DatabaseConnector.isAdmin(((Long) request.getSession().getAttribute("idAgent")).intValue() + "")) {%>
    <a href="agents/gestion.jsp" target="_self">
        <button style="font-size:2vw;">Gérer les agents</button>
    </a>
    <%}%>
</div>


<jsp:include page="footer.jsp"/>

</body>
</html>