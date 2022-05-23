<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Propriete" %>
<%@ page import="models.Client" %>
<%@ page import="models.Personne" %>
<%@ page import="models.Proprietaire" %>
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
    <jsp:include page="../header.html"/>
</header>


<div style="text-align: center;">
    <h1> Modification d'un client</h1>
</div>


<div id="cadre">
    <div id="formulaire">

        <% Client client = DatabaseConnector.getClientById(Integer.parseInt(request.getParameter("clientId")));%>
        <% Personne person = DatabaseConnector.getPersonneById((int) client.getIdPersonne());%>
        <% Proprietaire prop = DatabaseConnector.getProprietaireByPersonneId((int) client.getIdPersonne());%>

        <form action="/OverSeeImmobilier/ManagePersonServlet" method="post">

            <label id="lbl_idPerso">Référence du bien</label>
            <input type="text" id="idPersonne" name="idPersonne" value="<%=person.getIdPersonne()%>" readonly="readonly"/>

            <label id="lbl_idClient">Référence du bien</label>
            <input type="text" id="idClient" name="idClient" value="<%=client.getIdClient()%>" readonly="readonly"/>

            <%if (prop != null) {%>
            <label id="lbl_idProp">Référence du bien</label>
            <input type="text" id="idProp" name="idProp" value="<%=prop.getIdProprietaire()%>" readonly="readonly"/>
            <%}%>

            <label id="lbl_nom">Nom</label>
            <input type="text" id="tf_nom" name="tf_nom" value="<%=person.getNom()%>">

            <label id="lbl_prenom">Prénom</label>
            <input type="text" id="tf_prenom" name="tf_prenom" value="<%=person.getPrenom()%>">

            <label id="lbl_dispo">Date de naissance</label>
            <input type="date" id="dateNaissance" name="dateNaissance" value="<%=person.getDateNaissance()%>">

            <label id="lbl_adr">Adresse</label>
            <input type="text" id="tf_adr" name="tf_adr" value="<%=person.getAdresse()%>">

            <label id="lbl_cp">Code postal</label>
            <input type="text" id="tf_cp" name="tf_cp" value="<%=person.getCodePostal()%>">

            <label id="lbl_ville">Ville</label>
            <input type="text" id="tf_ville" name="tf_ville" value="<%=person.getVille()%>">

            <label id="lbl_tel">Telephone</label>
            <input type="text" id="tf_tel" name="tf_tel" value="<%=person.getNumeroTel()%>">

            <label id="lbl_email">Email</label>
            <input type="text" id="tf_email" name="tf_email" value="<%=person.getEmail()%>">

            <input type="hidden" name="action" value="update">

            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Modifier">
            </div>
        </form>
    </div>
</div>

</body>
</html>