<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.Propriete" %>
<%@ page import="models.AgentImmobilier" %>
<%@ page import="models.Client" %>
<%@ page import="models.Proprietaire" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des transations</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="../CheckSession.jsp"/>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<div style="text-align: center;">
    <h1> Ajout d'une transaction</h1>
</div>
<div id="cadre">
    <div id="formulaire">



        <form action="/OverSeeImmobilier/ManageTransactionServlet" method="post">

            <label id="lbl_dispo">Date de la vente</label>
            <input type="date" id="date" name="dateVente">


            <label id="lbl_ref">Référencce du bien</label>
            <select name="list_ref" id="list_ref">
                <option></option>
                <% for (Propriete propriete : DatabaseConnector.getAllProprietes()) { %>
                <option value="<%=propriete.getNumeroReference()%>"><%=propriete.getNumeroReference() + " - " + propriete.getAdressComplet()%>
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

            <label id="lbl_client">Client</label>
            <select name="list_client" id="list_client">
                <option></option>
                <% for (Client client : DatabaseConnector.getAllClients()) { %>
                <option value="<%=client.getIdClient()%>"><%=client.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) client.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_agent">Agent Immobilier</label>
            <select name="list_agent" id="list_agent">
                <option></option>
                <% for (AgentImmobilier agent : DatabaseConnector.getAllAgentImmobiliers()) { %>
                <option value="<%=agent.getIdAgentImmobilier()%>"><%=agent.getIdAgentImmobilier() + " - " + DatabaseConnector.getPersonneById((int) agent.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>

            <label id="lbl_prixVente">Prix de vente</label>
            <input type="text" id="tf_prixVente" name="tf_prixVente">
            <br/>

            <label id="lbl_commission">Commision</label>
            <input type="text" id="tf_commision" name="tf_commision">
            <br/>

            <input type="hidden" name="action" value="add">

            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Ajouter une transaction">
            </div>
        </form>
    </div>
</div>

</body>
</html>