<%@ page import="models.Propriete" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="java.lang.reflect.InvocationTargetException" %>
<%@ page import="java.lang.reflect.Method" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des biens</title>
    <link rel="stylesheet" href="../css/style.css">

    <script>
        function updateCheckBoxes() {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            for (var checkbox of checkboxes) {
                var toEdit = document.getElementById("propertyToEdit");
                var toDelete = document.getElementById("propertyToDelete");
                toEdit.setAttribute("value", checkbox.getAttribute("name"));
                toDelete.setAttribute("value", checkbox.getAttribute("name"));
            }

            var checkboxes = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
            for (var checkbox of checkboxes) {
                checkbox.disabled = true;
            }

            if (document.querySelectorAll('input[type="checkbox"]:disabled').length === document.querySelectorAll('input[type="checkbox"]').length) {
                var checkboxes = document.querySelectorAll('input[type="checkbox"]:disabled');
                for (var checkbox of checkboxes) {
                    checkbox.disabled = false;
                }
            }
        }
    </script>

</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>

<center>
    <h1> Liste des biens immobilier</h1>
</center>
<div class="btn">
    <a href="ajout.html" target="_self">
        <button>Ajouter un bien</button>
    </a>
    <div id="droite">
        <form action="/OverSeeImmobilier/EditDeletePropertyServlet" method="post">
            <input type="hidden" id="propertyToEdit" name="propertyId" value="">
            <input type="hidden" name="action" value="edit">
            <input type="submit" name="btn_update" id="btn_update" value="Modifier">
        </form>
        <form action="/OverSeeImmobilier/EditDeletePropertyServlet" method="post">
            <input type="hidden" id="propertyToDelete" name="propertyId" value="">
            <input type="hidden" name="action" value="delete">
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
    </tbody>
</table>

</body>
</html>