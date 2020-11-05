<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

<%@page import="java.util.List"%>
<%@page import="Servlet.Utility_MethodsForServer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link href="css/databaseCss.css" type="text/css" rel="stylesheet" media="all">
        <style>
            .nav-pills{
                background-color: #bbbec0;
                margin: 0px 100px;
                margin-top: 24px;
                height: 62px;
                font-size: 23px;
            }
            .nav-pills .nav-link.active, .nav-pills .show>.nav-link {
                color: #fff;
                background-color: #007bff;
                height: 62px;
            }
        </style>
<link href="css/BodyCss.css" type="text/css" rel="stylesheet" media="all">
        <title>Hello, world!</title>
    <ul id="myTab"  class="nav nav-pills nav-fill">
        <li class="nav-item">
            <a class="nav-link active" href="AdminHomePage.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="AdminRequest.jsp">SelectDatabase</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="SelectDataServer.jsp">Select Data</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Request.jsp">Request to Server</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Encrypt.jsp">Encrypted Data</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="decrypted_files.jsp">Rules</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
        </li>

    </ul>

</head>
<body>
    <div class="innerdiv  show active">
        <form>
            <table class="table table-bordered">
                <tr class="active">
                    <td><font style="color: red">User ID</td>
                    <td><font style="color: red">User Name</td>
                    <td><font style="color: red">User Email</td>
                    <td><font style="color: red">Company Name</td>
                    <td><font style="color: red">Table Name</td>
                    <td><font style="color: red">Type</td>
                    <td><font style="color: red">Action</td>
                </tr>
                <tr class="active">
                    <%
//                        ArrayList list_data = new ArrayList();
                        Utility_MethodsForServer method = new Utility_MethodsForServer();
                        int status=0;
                        List<String> request_list = method.getrequest(status);
                        int i1 = 0;
                        for (String request_list1 : request_list) {
                            String[] list_data = request_list1.split(",");
//                            if (i1 % 2 == 0) {
                    %>
                <tr class="active">
                    <%
                        for (int i = 0; i <= list_data.length - 1; i++) {
                            String data = list_data[i];
                    %>
                    <td><%=data%></td>
                    <%
                        }

                    %>
                    <td><a href='TableCreate?id=<%=list_data[0]%>&uname=<%=list_data[1]%>&email=<%=list_data[2]%>&cname=<%=list_data[3]%>&tname=<%=list_data[4]%>&type=<%=list_data[5]%>'>Execute</a></td>
                </tr>
                <%
                    }


                %>
        </form>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>