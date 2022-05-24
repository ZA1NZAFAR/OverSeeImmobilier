<%@ page import="tools.DatabaseConnector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OverSee</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<a href="../index.jsp"><img src="images/logOut.png"/> </a>

<div style="text-align: center;">
    <div style="padding:20vh;">
        <img src="images/logo.png"/>
    </div>
    <p> Bonjour
        <%=DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById(((Long) request.getSession().getAttribute("idAgent")).intValue()).getIdPersonne()).getNomComplet()%>
        , que voulez-vous faire ? </p>
    <a href="biens/gestion.jsp" target="_self">
        <button>Gérer les biens</button>
    </a>
    <a href="transactions/gestion.jsp" target="_self">
        <button>Gérer les transactions</button>
    </a>
    <a href="visites/gestion.jsp" target="_self">
        <button>Gérer les visites</button>
    </a>
    <a href="proprietaires/gestion.jsp" target="_self">
        <button>Gérer les propietaires</button>
    </a>
    <a href="clients/gestion.jsp" target="_self">
        <button>Gérer les clients</button>
    </a>
    <%if (DatabaseConnector.isAdmin(((Long) request.getSession().getAttribute("idAgent")).intValue() + "")) {%>
    <a href="agents/gestion.jsp" target="_self">
        <button>Gérer les agents</button>
    </a>
    <%}%>
</div>

</body>
</html>