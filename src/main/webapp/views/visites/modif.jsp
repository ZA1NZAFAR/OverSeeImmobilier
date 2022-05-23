<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.*" %>
<%@ page import="tools.Helper" %>
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
    <h1> Modification d'une visite</h1>
</div>

<% IDsDTO v = Helper.visitStringToDTO(request.getParameter("visitString"));%>
<% Visite vi = DatabaseConnector.getVisitUsingDTO(v);%>


<div id="cadre">
    <div id="formulaire">

        <form action="/OverSeeImmobilier/ManageVisitesServlet" method="post">

            <label id="lbl_dispo">Date de la visite</label>
            <input type="date" id="date" name="dateVisite" value="<%=vi.getDateVisite()%>">

            <label id="lbl_heure">Heure de la visite</label>
            <input type="time" id="heureVisite" name="heureVisite" value="<%=vi.getHeureVisite()%>">

            <label id="lbl_ref">Réference du bien</label>
            <select name="list_ref" id="list_ref">
                <option value="<%=v.getNumeroReferenceBien()%>"><%=v.getNumeroReferenceBien() + " - " + DatabaseConnector.getProprieteById((int) v.getNumeroReferenceBien()).getAdressComplet()%>
                </option>
                <% for (Propriete propriete : DatabaseConnector.getAllProprietes()) { %>
                <option value="<%=propriete.getNumeroReference()%>"><%=propriete.getNumeroReference() + " - " + propriete.getAdressComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_agent">Agent immobilier</label>
            <select name="list_agent" id="list_agent">
                <option value="<%=v.getIdAgentImmobilier()%>"><%=v.getIdAgentImmobilier() + " - " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) v.getIdAgentImmobilier()).getIdPersonne()).getNomComplet()%>
                        <% for (Client client : DatabaseConnector.getAllClients()) { %>
                <option value="<%=client.getIdClient()%>"><%=client.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) client.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_client">Client</label>
            <select name="list_client" id="list_client">
                <option value="<%=v.getIdClient()%>"><%=v.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) v.getIdClient()).getIdPersonne()).getNomComplet()%>
                        <% for (Proprietaire proprietaire : DatabaseConnector.getAllProprietaires()) { %>
                <option value="<%=proprietaire.getIdProprietaire()%>"><%=proprietaire.getIdProprietaire() + " - " + DatabaseConnector.getPersonneById((int) proprietaire.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>


            <label id="lbl_prop">Proprietaire</label>
            <select name="list_prop" id="list_prop">
                <option value="<%=v.getIdProprietaire()%>"><%=v.getIdProprietaire() + " - " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) v.getIdProprietaire()).getIdPersonne()).getNomComplet()%>
                        <% for (Proprietaire proprietaire : DatabaseConnector.getAllProprietaires()) { %>
                <option value="<%=proprietaire.getIdProprietaire()%>"><%=proprietaire.getIdProprietaire() + " - " + DatabaseConnector.getPersonneById((int) proprietaire.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>

            <input type="hidden" name="action" value="update">
            <input type="hidden" name="visitString" value="<%=v.toString()%>">


            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Modifier la visite">
            </div>
        </form>
    </div>
</div>

</body>
</html>