
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body>
<% request.getSession().setAttribute("idAgent", request.getSession().getAttribute("idAgent")); %>
<div>
    <ul>
        <li><a href="../accueil.jsp"><img src="../images/logo.png"/> </a></li>
        <li><a href="../biens/gestion.jsp"><img src="../images/immeubles.png"/> </a></li>
        <li><a href="../transactions/gestion.jsp"><img src="../images/transaction.png"/> </a></li>
        <li><a href="../visites/gestion.jsp"><img src="../images/visites.png"/> </a></li>
        <li><a href="../proprietaires/gestion.jsp"><img src="../images/proprietaires.png"/> </a></li>
        <li><a href="../clients/gestion.jsp"><img src="../images/clients.png"/> </a></li>
        <li><a href="../agents/gestion.jsp"><img src="../images/agents.png"/> </a></li>
        <li><a href="../../index.jsp" id="logOut"><img src="../images/logOut.png"/> </a></li>
    </ul>

</div>


</body>
</html>