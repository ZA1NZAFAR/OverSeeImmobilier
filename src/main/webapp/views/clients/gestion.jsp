<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Client" %>
<%@ page import="models.Personne" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des clients</title>
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
    <jsp:include page="../header.jsp"/>
</header>

<div style="text-align: center;">
    <h1> Liste des clients</h1>
</div>
<a href="ajout.jsp" target="_self">
    <button>Ajouter un client</button>
</a>
<div id="droite">
    <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">
        <input type="hidden" id="propertyToEdit" name="clientId" value="">
        <input type="hidden" name="action" value="edit">
        <input type="submit" name="btn_update" id="btn_update" value="Modifier" disabled="true">
    </form>
    <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">
        <input type="hidden" id="propertyToDelete" name="clientId" value="">
        <input type="hidden" name="action" value="delete">
        <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer" disabled="true">
    </form>
</div>


<table>

    <thead>
    <tr>
        <th></th>
        <th>Identifiant</th>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Date de naissance</th>
        <th>Adresse</th>
        <th>Code postal</th>
        <th>Ville</th>
        <th>Telephone</th>
        <th>Email</th>
        <th>Nombre de transactions</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Client c : DatabaseConnector.getAllClients()) {
            out.print(c.toHTML());
        }
    %>
    </tbody>

</table>


</body>
</html>