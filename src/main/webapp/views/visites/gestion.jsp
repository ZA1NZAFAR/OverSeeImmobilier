<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Visite" %>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des visites</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
   <jsp:include page="../header.jsp" />
</header>

<div style="text-align: center;">
    <h1> Liste des visites</h1>
</div>
<a href="ajout.jsp" target="_self">
    <button>Ajouter une visite</button>
</a>
<div id="droite">
    <form action="modif.html" method="post">
        <input type="submit" name="btn_update" id="btn_update" value="Modifier">
    </form>
    <form action="" method="post">
        <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer">
    </form>
</div>


<table>

    <thead>
    <tr>
        <th></th>
        <th>Date de la visite</th>
        <th>Référence du bien</th>
        <th>Agent en charge de la visite</th>
        <th>Client</th>
        <th>Proprietaire</th>

    </tr>
    </thead>
    <tbody>
    <%
        for (Visite pr : DatabaseConnector.getAllVisites()) {
            out.print(pr.toHTML());
        }
    %>
    </tbody>

</table>


</body>
</html>