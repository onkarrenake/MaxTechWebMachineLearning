<%-- 
    Document   : logout
    Created on : Aug 23, 2017, 7:53:41 PM
    Author     : M-5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
             session.invalidate();
             response.sendRedirect("index.jsp");
        %>
    </body>
</html>
