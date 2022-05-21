<!doctype html>
<html>
    <head>
      <meta charset="utf-8">
      <title>OverSee</title>
      <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
         <form action="" method="post">
             <input type="submit" name="logOut" id="logOut" value=" ">
            </form>
        <center>
           <div style="padding:20vh;">
                <img src="images/logo.png" />
            </div>
            <p> Bonjour Agent [insert nom], que voulez-vous faire ? </p>
            <a href="biens/gestion.jsp" target="_self"><button>Gérer les biens</button></a>
            <a href="transactions/gestion.html" target="_self"><button>Gérer les transactions</button></a>
            <a href="visites/gestion.html" target="_self"><button>Gérer les visites</button></a>
            <a href="proprietaires/gestion.html" target="_self"><button>Gérer les propietaires</button></a>
            <a href="clients/gestion.html" target="_self"><button>Gérer les clients</button></a>
            <%if(session.getAttribute("role") == "admin"){%>
            <a href="agents/gestion.html" target="_self"><button>Gérer les agents</button></a>
            <%}%>
        </center>

    </body>
</html>