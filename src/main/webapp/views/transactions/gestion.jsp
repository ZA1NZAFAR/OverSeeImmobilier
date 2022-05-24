<%@ page import="models.AgentImmobilier" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Transaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des transactions</title>
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
<article>
<div style="text-align: center;">
    <h1> Liste des transactions</h1>
</div>
<a href="ajout.jsp" target="_self">
    <button>Ajouter une transition</button>
</a>
<div id="droite">
    <form action="/OverSeeImmobilier/ManageTransactionServlet" method="post">
        <input type="hidden" id="propertyToEdit" name="transactionString" value="">
        <input type="hidden" name="action" value="edit">
        <input type="submit" name="btn_update" id="btn_update" value="Modifier">
    </form>
    <form action="/OverSeeImmobilier/ManageTransactionServlet" method="post">
        <input type="hidden" id="propertyToDelete" name="transactionString" value="">
        <input type="hidden" name="action" value="delete">
        <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer">
    </form>
</div>


<table>

    <thead>
    <tr>
        <th></th>
        <th>Date de la vente</th>
        <th>Référence du bien</th>
        <th>Proprietaire</th>
        <th>Client</th>
        <th>Type transaction</th>
        <th>Agent immobilier</th>
        <th>Prix du bien</th>
        <th>Prix de la vente</th>
        <th>Commision</th>
        <th>Totale</th>

    </tr>
    </thead>
    <tbody>
    <%
        for (Transaction pr : DatabaseConnector.getAllTransactions()) {
            out.print(pr.toHTML());
        }
    %>
    </tbody>

</table>

</article>
</body>
</html>