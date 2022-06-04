<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body>
<%
    if (request.getSession() == null)
        response.sendRedirect("index.jsp");
    if (request.getSession().getAttribute("idAgent") == null)
        response.sendRedirect("index.jsp");
%>
<div>
    <nav>
        <ul>
            <li class="img_gauche"><img src="../images/logo.png"/>
                <p><br/></p></li>
            <li><a href="../accueil.jsp"><img src="../images/home.png"/> </a>
                <p> Accueil </p></li>
            <li><a href="../biens/gestion.jsp"><img src="../images/immeubles.png"/> </a>
                <p> Biens </p></li>
            <li><a href="../transactions/gestion.jsp"><img src="../images/transaction.png"/> </a>
                <p> Transactions </p></li>
            <li><a href="../visites/gestion.jsp"><img src="../images/visites.png"/> </a>
                <p> Visites </p></li>
            <li><a href="../proprietaires/gestion.jsp"><img src="../images/proprietaires.png"/> </a>
                <p> Proprietaires </p></li>
            <li><a href="../clients/gestion.jsp"><img src="../images/clients.png"/> </a>
                <p> Clients </p></li>
            <li><a href="../agents/gestion.jsp"><img src="../images/agents.png"/> </a>
                <p> Agents </p></li>
            <li class="img_droite"><a href="../../index.jsp" id="logOut"><img src="../images/logOut.png"/> </a>
                <p><br/></p></li>
        </ul>
    </nav>


</div>


</body>
</html>