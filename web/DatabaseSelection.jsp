<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

<%@page import="com.mysql.cj.protocol.Message"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="Servlet.Connections"%>
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
        <link href="css/BodyCss.css" type="text/css" rel="stylesheet" media="all">
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
            #disp{
                /*display: none;*/
            }
        </style>
        <title>Hello, world!</title>
    <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
            <a class="nav-link active" href="UserHomePage.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DatabaseSelection.jsp">SelectDatabase</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="SelectData.jsp">Select Data</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Request.jsp">Request to Server</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Encrypt.jsp">Encrypted Data</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Rules.jsp">Rules</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
        </li>

    </ul>

</head>
<body>
    <div class="innerdiv">
        <div class="firstdiv">
            <div class="formdiv">

                <!--<div class="seconddiv">-->
                <form class="" action="Connections"  method="Post">

                    <input type="text" name="port" class="dt" placeholder="Enter Database Port Number">
                    <input type="text" name="databasename" class="dt" placeholder="Enter Database Name">
                    <input type="text" name="databaseid" class="dt" placeholder="Enter Database Connection Id">
                    <input type="text" name="databasepassword" class="dt" placeholder="Enter Database Connection Password">
                    <button type="submit" id="mysql" name ="mysqldatabase"  class="btn btn-outline-primary databtn" onclick="callalert()">Mysql Database</button>
                    <button type="submit" id="oracle" name ="oracledatabase" class="btn btn-outline-secondary databtn" onclick="callalert()">Oracle Database</button>
                    <button type="submit" id="postgres" name ="postgresdatabase" class="btn btn-outline-success databtn"onclick="callalert()" >Postgres Database</button>
                </form>
                <!--</div>-->
            </div>
            <div id ="disp" class="alert alert-warning alert-dismissible fade show" role="alert">
                <strong>Holy guacamole!</strong> You should check in on some of those fields below.
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </div>


    </div>
    <script>

        myFunction();
        function myFunction() {


            var cc = <%= Connections.con%>
            if (cc === null)
            {
                console.log(cc);
                let alert2 = document.getElementById("disp");
                alert2.style.display = 'none';
            } else {
                let alert1 = document.getElementById("disp");
                alert1.style.display = 'block';
            }
        }

        <%

            if (Connections.con != null) {%>
        var val = 1;
        function callalert() {
            if (val == 1) {
                myFunction();
                val++;
            }
        }


        <%
            }%>
      


//            alert("Connection Established");



    </script>


</body>
</html> 