<%@ page import="models.Propriete" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="java.util.Objects" %>
<%@ page import="models.Proprietaire" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<head>
    <meta charset="utf-8">
    <title>Gestion des biens</title>
    <link rel="stylesheet" href="../css/style.css">
</head>

<body>
<jsp:include page="../CheckSession.jsp"/>
<header>
    <jsp:include page="../header.jsp"/>
</header>


<div style="text-align: center;">
    <h1>Modification du bien</h1></div>

<% Propriete p = DatabaseConnector.getProprieteById(Integer.parseInt(request.getParameter("propertyId")));%>

<div id="cadre">
    <div id="formulaire">
        <form action="/OverSeeImmobilier/ManagePropertyServlet" method="post">

            <label id="lbl_refBien">Référence du bien</label>
            <input type="text" id="refBien" name="refBien" value="<%=p.getNumeroReference()%>" readonly="readonly"/>

            <label id="lbl_type">Type du bien</label>
            <select name="listT_biens">
                <option>Appartement</option>
                <option>Maison</option>
            </select>

            <label id="lbl_nbPiece">Nombre de pièce</label>
            <input type="number" id="stepper_nbPiece" name="stepper_nbPiece" min="1" max="100"
                   value="<%=p.getNombre_de_piece()%>"/>

            <label id="lbl_superficie">Superficie</label>
            <input type="number" id="tf_superficie" name="tf_superficie" value="<%=p.getSuperficie()%>">

            <label id="lbl_etatHab">Etat d'habitation</label>
            <select name="list_type" id="list_type">
                <option>Bon Etat</option>
                <option>Neuf</option>
                <option>Tres Bon Etat</option>
            </select>

            <label id="lbl_garage">Garage</label>
            <input type="number" id="stepper_garage" name="stepper_garage" min="0" max="10" value="<%=p.getGarage()%>"/>


            <label id="lbl_prix">Prix</label>
            <input type="number" id="stepper_prix" name="stepper_prix" min="0" value="<%=p.getPrixInitial()%>"/>
            <br/>


            <label id="lbl_prop">Proprietaire</label>
            <select name="list_prop" id="list_prop">

                <option value="<%=p.getIdProprietaire()%>"><%=Objects.requireNonNull(DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) p.getIdProprietaire()).getIdPersonne())).getNomComplet()%>
                </option>
                <% for (Proprietaire personne : DatabaseConnector.getAllProprietaires()) { %>

                <option value="<%=personne.getIdProprietaire()%>"><%=Objects.requireNonNull(DatabaseConnector.getPersonneById((int) DatabaseConnector.getProprietaireById((int) personne.getIdProprietaire()).getIdPersonne())).getNomComplet()%>
                </option>

                <% } %>

            </select>

            <label id="lbl_adr">Adresse</label>
            <input type="text" id="tf_adr" name="tf_adr" value="<%=p.getAdresse()%>">

            <label id="lbl_cp">Code postal</label>
            <input type="text" id="tf_cp" name="tf_cp" value="<%=p.getCodePostal()%>">

            <label id="lbl_ville">Ville</label>
            <input type="text" id="tf_ville" name="tf_ville" value="<%=p.getVille()%>">

            <label id="lbl_locVent">Location Ou Vente</label>
            <input type="text" id="tf_locVent" name="tf_locVent" value="<%=p.getLocationOuVente()%>">

            <label id="lbl_dispo">Disponibilité</label>
            <input type="date" id="date" name="date" value="<%=p.getDateDisponibilite()%>">

            <input type="hidden" name="action" value="update">

            <div style="text-align: center;">
                <input type="submit" name="btn_submit" id="btn_submit" value="Modifier le bien"></div>
        </form>
    </div>
</div>
</body>

</html>