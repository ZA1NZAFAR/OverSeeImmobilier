<%@ page import="models.Log" %>
<%@ page import="tools.DatabaseConnector" %>
<%@ page import="models.LogDisplay" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logs</title>
    <link rel="stylesheet" href="../css/style.css">

</head>
<body>
<jsp:include page="../CheckSession.jsp"/>
<header>
    <jsp:include page="../header.jsp"/>
</header>

<br><br><br><br>


<table>
    <thead>
    <tr>
        <th>idLog</th>
        <th>Action</th>
        <th>Information</th>
        <th>Id Agent</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (LogDisplay l : DatabaseConnector.getAllLogs()) {
            out.print(l.toHTML());
        }
    %>
    </tbody>

</table>
</body>
</html>
