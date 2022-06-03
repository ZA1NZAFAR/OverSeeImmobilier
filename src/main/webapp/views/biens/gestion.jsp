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
<jsp:include page="../CheckSession.jsp"/>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<article>
    <div style="text-align: center;">
        <h1> Liste des biens immobilier</h1>
    </div>
    <div class="btn">
        <a href="ajout.jsp" target="_self">
            <button>Ajouter un bien</button>
        </a>
        <div id="droite">
            <form action="/OverSeeImmobilier/ManagePropertyServlet" method="post">
                <input type="hidden" id="propertyToEdit" name="propertyId" value="">
                <input type="hidden" name="action" value="edit">
            <input type="submit" name="btn_update" id="btn_update" value="Modifier" disabled="true">
        </form>
        <form action="/OverSeeImmobilier/ManagePropertyServlet" method="post">
            <input type="hidden" id="propertyToDelete" name="propertyId" value="">
            <input type="hidden" name="action" value="delete">
            <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer" disabled="true">
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
        <th>Achat/Vente</th>
        <th>Disponibilité</th>
    </tr>
    </thead>
    <tbody>
    <% for (Propriete p : DatabaseConnector.getAllProprietes()) {
        out.print(p.toHTML());
    }%>
    </tbody>
</table>
</article>
</body>
</html>