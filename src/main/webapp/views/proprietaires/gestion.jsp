<%@ page import="models.Proprietaire" %>
<%@ page import="tools.DatabaseConnector" %>
 <%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>
    <head>
      <meta charset="utf-8">
      <title>Gestion des proprietaires</title>
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
		<div style="text-align: center;">
            <h1> Liste des proprietaires</h1>
        </div>
		<a href="../clients/ajout.jsp" target="_self"><button>Ajouter un proprietaires</button></a>
		<div id="droite">
            <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">
                <input type="hidden" id="propertyToEdit" name="propId" value="">
                <input type="hidden" name="action" value="edit">
                <input type="submit" name="btn_update" id="btn_update" value="Modifier">
            </form>
            <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">
                <input type="hidden" id="propertyToDelete" name="propId" value="">
                <input type="hidden" name="action" value="delete">
                <input type="submit" name="btn_delete" id="btn_delete" value="Supprimer">
            </form>
		</div>


            <table>

                 <thead>
					 <tr>
					    <th></th>
						<th>Identifiant</th>
						<th>Nom </th>
						<th>Prénom </th>
						<th>Date de naissance </th>
						<th>Adresse </th>
						<th>Code postal </th>
						<th>Ville </th>
						<th>Telephone </th>
						<th>Email </th>
                        <th>Nombre de proprieté mis en vente </th>
                        <th>Nombre de proprieté mis en location </th>

				   </tr>
				 </thead>
			<tbody>
            <%for (Proprietaire pr : DatabaseConnector.getAllProprietaires()) {
                out.print(pr.toHTML());
            }%>
			</tbody>

            </table>


    </body>
</html>