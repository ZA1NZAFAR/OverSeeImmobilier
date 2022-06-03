<%@ page import="tools.Helper" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Gestion des transsactions</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<header>
    <jsp:include page="../header.jsp"/>
</header>
<h1> Modification d'une transaction</h1>
<div id="cadre">
    <div id="formulaire">

        <% IDsDTO v = Helper.visitStringToDTO(request.getParameter("transactionString"));%>
        <% Transaction tr = DatabaseConnector.getTransactionUsing(v);%>
        <% Propriete pr = DatabaseConnector.getProprieteById((int) tr.getNumeroReference());%>

        <form action="/OverSeeImmobilier/ManageTransactionServlet" method="post">

            <label id="lbl_dispo">Date de la vente</label>
            <input type="date" id="date" name="dateVente" value="<%=tr.getDatevente()%>">

            <label id="lbl_ref">Référencce du bien</label>
            <select name="list_ref" id="list_ref">
                <option value="<%=v.getNumeroReferenceBien()%>"><%=v.getNumeroReferenceBien() + " - " + DatabaseConnector.getProprieteById((int) v.getNumeroReferenceBien()).getAdressComplet()%>
                </option>
                <% for (Propriete propriete : DatabaseConnector.getAllProprietes()) { %>
                <option value="<%=propriete.getNumeroReference()%>"><%=propriete.getNumeroReference() + " - " + propriete.getAdressComplet()%>
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

            <label id="lbl_client">Client</label>
            <select name="list_client" id="list_client">
                <option value="<%=v.getIdClient()%>"><%=v.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getClientById((int) v.getIdClient()).getIdPersonne()).getNomComplet()%>
                        <% for (Client client : DatabaseConnector.getAllClients()) { %>
                <option value="<%=client.getIdClient()%>"><%=client.getIdClient() + " - " + DatabaseConnector.getPersonneById((int) client.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>

            <label id="lbl_agent">Agent</label>
            <select name="list_agent" id="list_agent">
                <option value="<%=v.getIdAgentImmobilier()%>"><%=v.getIdAgentImmobilier() + " - " + DatabaseConnector.getPersonneById((int) DatabaseConnector.getAgentById((int) v.getIdAgentImmobilier()).getIdPersonne()).getNomComplet()%>
                        <% for (AgentImmobilier agentImmobilier : DatabaseConnector.getAllAgentImmobiliers()) { %>
                <option value="<%=agentImmobilier.getIdAgentImmobilier()%>"><%=agentImmobilier.getIdAgentImmobilier() + " - " + DatabaseConnector.getPersonneById((int) agentImmobilier.getIdPersonne()).getNomComplet()%>
                </option>
                <% } %>
            </select>
            <br/>

            <label id="lbl_prixVente">Prix de vente</label>
            <input type="text" id="tf_prixVente" name="tf_prixVente" value=<%=pr.getPrixInitial()%>>>

            <label id="lbl_commission">Commision [3% - 5%]</label>
            <input type="text" id="tf_commision" name="tf_commision" value=<%=tr.getCommission()%>>

            <input type="hidden" name="action" value="update">
            <input type="hidden" name="transactionString" value="<%=v.toString()%>">

            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Modifier une transaction">
            </div>
        </form>
    </div>
</div>

</body>
</html>