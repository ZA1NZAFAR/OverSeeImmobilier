<%@ page import="models.Propriete" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="java.lang.reflect.InvocationTargetException" %>
<%@ page import="java.lang.reflect.Method" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des biens</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<form action="" method="post">
    <input type="submit" name="logOut" id="logOut" value=" ">
</form>

<a href="../accueil.jsp"><img src="../images/logo.png" alt="OverSeeImmobilier"/> </a>
<center>
    <h1> Liste des biens immobilier</h1>
</center>
<div class="btn">
    <a href="ajout.html" target="_self">
        <button>Ajouter un bien</button>
    </a>
    <div id="droite">
        <form action="modif.html" method="post">
            <input type="submit" name="btn_update" id="btn_update" value="Modifier">
        </form>
        <form action="" method="post">
            <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer">
        </form>
    </div>
</div>

<table>
    <thead>
    <tr>
        <th></th>
        <th>Référence du bien</th>
        <th>Type</th>
        <th>Nombre de pièce</th>
        <th>Superficie</th>
        <th>État d'habitation</th>
        <th>Garage</th>
        <th>Prix</th>
        <th>Proprietaire</th>
        <th>Adresse</th>
        <th>Code postal</th>
        <th>Ville</th>
        <th>Disponibilité</th>
    </tr>
    </thead>
    <tbody>
    <% for (Propriete p : DatabaseConnector.getAllProprietes()) {
        out.print(p.toHTML());
    }%>
    <%--    <tr>--%>
    <%--        <td><input type="checkbox" name="selectionner"></td>--%>
    <%--        <td>1</td>--%>
    <%--        <td>Maison</td>--%>
    <%--        <td>4</td>--%>
    <%--        <td>72</td>--%>
    <%--        <td>Neuf</td>--%>
    <%--        <td>1</td>--%>
    <%--        <td>100.000</td>--%>
    <%--        <td>Zain Zafar</td>--%>
    <%--        <td>2 rue des Anciens combattant</td>--%>
    <%--        <td>06230</td>--%>
    <%--        <td>Nice</td>--%>
    <%--        <td>01/06/2022</td>--%>
    <%--    </tr>--%>
    </tbody>
</table>


</body>
</html>