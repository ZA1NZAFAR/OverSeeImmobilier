<%@ page import="tools.DatabaseConnector" %>
<%@ page import="tools.HtmlDisplayer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OverSee</title>
    <link rel="stylesheet" href="views/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>


</head>
<body>
<%
    request.getSession().invalidate();
    session = null;
    request.getSession().setAttribute("idAgent", null);
%>


<div style="text-align: center;">
    <img alt="logo" src="views/images/logo.png" style="position:relative; top: 5vw;"/>
    <form action="LoginServlet" method="post">
        <div style="text-align: center; position: relative; left:30vw; top:10vw;">
            <div class="mb-3 row">
                <label for="tf_id" class="col-sm-2 col-form-label" id="lbl_id">Identifiant</label>
                <div class="col-sm-2">
                    <input type="text" class="form-control" id="tf_id" name="tf_id">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="tf_pwd" class="col-sm-2 col-form-label" id="lbl_pwd">Mot de passe</label>
                <div class="col-sm-2">
                    <input type="password" class="form-control" id="tf_pwd" name="tf_pwd">
                </div>
            </div>
        </div>
        <div style="position: relative; top:12vw">
            <input type="submit" name="btn_submit" id="btn_submit" value="Se connecter">
        </div>
    </form>
</div>
</body>
</html>