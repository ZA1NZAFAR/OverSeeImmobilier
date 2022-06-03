<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Visite" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des visites</title>
    <link rel="stylesheet" href="../css/style.css">

    <script>
        function updateCheckBoxes() {
            var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
            for (var checkbox of checkboxes) {
                var toEdit = document.getElementById("propertyToEdit");
                var toDelete = document.getElementById("propertyToDelete");
                toEdit.setAttribute("value", checkbox.getAttribute("name"));
                toDelete.setAttribute("value", checkbox.getAttribute("name"));
                document.getElementById("btn_update").disabled = false;
                document.getElementById("btn_delete").disabled = false;
            }

            var checkboxes = document.querySelectorAll('input[type="checkbox"]:not(:checked)');
            for (var checkbox of checkboxes) {
                checkbox.disabled = true;
                document.getElementById("btn_update").disabled = false;
                document.getElementById("btn_delete").disabled = false;
            }

            if (document.querySelectorAll('input[type="checkbox"]:disabled').length === document.querySelectorAll('input[type="checkbox"]').length) {
                var checkboxes = document.querySelectorAll('input[type="checkbox"]:disabled');
                for (var checkbox of checkboxes) {
                    checkbox.disabled = false;
                    document.getElementById("btn_update").disabled = true;
                    document.getElementById("btn_delete").disabled = true;
                }
            }
        }
    </script>

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
    <form action="/OverSeeImmobilier/ManageVisitesServlet" method="post">
        <input type="hidden" id="propertyToEdit" name="visitString" value="">
        <input type="hidden" name="action" value="edit">
        <input type="submit" name="btn_update" id="btn_update" value="Modifier" disabled="true">
    </form>
    <form action="/OverSeeImmobilier/ManageVisitesServlet" method="post">
        <input type="hidden" id="propertyToDelete" name="visitString" value="">
        <input type="hidden" name="action" value="delete">
        <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer" disabled="true">
    </form>
</div>


<table>

    <thead>
    <tr>
        <th></th>
        <th>Date de la visite</th>
        <th>Heure de la visite</th>
        <th>Adresse du bien</th>
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