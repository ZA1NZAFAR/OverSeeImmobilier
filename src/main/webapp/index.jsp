<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>OverSee</title>
    <link rel="stylesheet" href="views/css/style.css">
</head>
<body>
<div style="text-align: center;">
    <img alt="logo" src="views/images/logo.png" />
    <form action="LoginServlet" method="post">
        <label id="lbl_id">Identifiant</label>
        <input type="text" id="tf_id" name="tf_id">
        <br/>
        <label id="lbl_pwd">Mot de passe</label>
        <input type="password" id="tf_pwd" name="tf_pwd">
        <br/>
        <input type="submit" name="btn_submit" id="btn_submit" value="Se connecter">
    </form>
</div>

</body>
</html>