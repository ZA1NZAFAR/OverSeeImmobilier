<!-- <%@ page contentType="text/html;charset=UTF-8" language="java" %> -->

<!doctype html>
<html>
    <head>
      <meta charset="utf-8">
      <title>Gestion des agents</title>
      <link rel="stylesheet" href="../css/style.css">
    </head>
    <body>
    <!--
<header>
   <jsp:include page="../header.jsp"/>
</header>
-->
		<div style="text-align: center;">
            <h1> Liste des agents</h1>
        </div>
		<a href="ajout.html" target="_self"><button>Ajouter un agent</button></a>
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
						<th>Identifiant</th>
						<th>Nom </th>
						<th>Prénom </th>
						<th>Adresse </th>
						<th>Code postal </th>
						<th>Ville </th>
                        <th>Date d'embauche </th>	
                        <th>Salaire </th>
						<th>Administrateur </th>
				   </tr>
				 </thead>
			<tbody>
				   <tr>
					   <td> <input type="checkbox" name="selectionner" ></td>
					   <td> </td>
					 
               </tr>
			</tbody>
               
            </table>
     

    </body>
</html>