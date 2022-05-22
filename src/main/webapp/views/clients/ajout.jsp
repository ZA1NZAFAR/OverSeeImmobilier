<%@ page import="tools.DatabaseConnector" %>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des clients</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<header>
    <jsp:include page="../header.jsp"/>
</header>


<div style="text-align: center;">
    <h1> Ajout d'un client</h1>
</div>
<div id="cadre">
    <div id="formulaire">

        <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">
            <label id="lbl_nom">Nom</label>
            <input type="text" id="tf_nom" name="tf_nom">

            <label id="lbl_prenom">Prénom</label>
            <input type="text" id="tf_prenom" name="tf_prenom">

            <label id="lbl_dispo">Date de naissance</label>
            <input type="date" id="dateNaissance" name="dateNaissance">

            <label id="lbl_adr">Adresse</label>
            <input type="text" id="tf_adr" name="tf_adr">

            <label id="lbl_cp">Code postal</label>
            <input type="text" id="tf_cp" name="tf_cp">

            <label id="lbl_ville">Ville</label>
            <input type="text" id="tf_ville" name="tf_ville">

            <label id="lbl_tel">Telephone</label>
            <input type="text" id="tf_tel" name="tf_tel">

            <label for="estProp"> Est Proprietaire</label><input type="checkbox" id="estProp" name="estProp" value="estProp">

            <%if (DatabaseConnector.isAdmin(((Long) request.getSession().getAttribute("idAgent")).intValue() + "")) {%>
            <label for="estAgent"> Est Agent</label><input type="checkbox" id="estAgent" name="estAgent" value="">
            <%}%>

            <input type="hidden" name="action" value="add">

            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Ajouter un client">

            </div>
        </form>
    </div>
</div>

</body>
</html>