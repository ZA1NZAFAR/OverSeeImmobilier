<%@ page import="models.Propriete" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="java.util.Objects" %>
<%@ page import="models.AgentImmobilier" %>
<%@ page import="models.Client" %>
<%@ page import="models.Proprietaire" %>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des visites</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<div style="text-align: center;">
    <h1> Ajout d'une visite</h1>
</div>
<div id="cadre">
    <div id="formulaire">

        <form action="/OverSeeImmobilier/ManageVisitesServlet" method="post">

            <label id="lbl_date">Date de la visite</label>
            <input type="date" id="dateVisite" name="dateVisite">


            <label id="lbl_heure">Heure de la visite</label>
            <input type="time" id="heureVisite" name="heureVisite">

            <label id="lbl_ref">Réference du bien</label>
            <select name="list_ref" id="list_ref">
                <option></option>
                <% for (Propriete propriete : DatabaseConnector.getAllProprietes()) { %>
                <option value="<%=propriete.getNumeroReference()%>"><%=propriete.getNumeroReference() + " - " + propriete.getAdressComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_agent">Agent immobilier</label>
            <select name="list_agent" id="list_agent">
                <option></option>
                <% for (AgentImmobilier agent : DatabaseConnector.getAllAgentImmobiliers()) { %>
                <option value="<%=agent.getIdAgentImmobilier()%>"><%=agent.getIdAgentImmobilier() + " - " + DatabaseConnector.getPersonneById((int) agent.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_client">Client</label>
            <select name="list_client" id="list_client">
                <option></option>
                <% for (Client client : DatabaseConnector.getAllClients()) { %>
                <option value="<%=client.getIdClient()%>"><%=client.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) client.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_prop">Proprietaire</label>
            <select name="list_prop" id="list_prop">
                <option></option>
                <% for (Proprietaire proprietaire : DatabaseConnector.getAllProprietaires()) { %>
                <option value="<%=proprietaire.getIdProprietaire()%>"><%=proprietaire.getIdProprietaire() + " - " + DatabaseConnector.getPersonneById((int) proprietaire.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>

            <input type="hidden" name="action" value="add">


            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Ajouter une visite">
            </div>
        </form>
    </div>
</div>

</body>
</html>