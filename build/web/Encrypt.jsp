<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

<%@page import="Servlet.Encryptdata"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Connection"%>
<%@page import="Servlet.Utility_Methods"%>
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
            .tablediv{
                height: 50%;
                width: 50%;
                margin: auto;
                margin-top: 15px;

            }
            .divScroll {
                overflow-y:auto;
                overflow-x: auto;
                height:470px;

                margin: auto;
            }
            #colum{
                margin-top: 7px;
                display: block;
                width: 12%;
                margin: auto;
            }
            .sticky {
                position: -webkit-sticky;
                position: sticky;
            }
            .container2{
                display: flex;
                width: 68%;
                justify-content: center;
            }
            #dvTable
            {
                margin: 42px 52px;
            }
            .hadding
            {
                
                font-size: 23px;
                font-weight: 600;
            }
            .sticky {
  position: fixed;
  top: 0;
  width: 100%;
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
            <a class="nav-link" href="MappedData.jsp">Encrypted Data</a>
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
    <div class="innerdiv">
        <form action="Mappeddata.jsp" method="Post">
            <div id="dvTable" class="divScroll" >
                <table class=" table-bordered">

                    <%

                        Encryptdata data = new Encryptdata();
                        String lable = request.getParameter("radiobutton");
                        Utility_Methods m = new Utility_Methods();
                        String tablename = m.info.get("tablename").toString();
                        DefaultTableModel dtm = data.encryptdata(lable, tablename);%>
                     
                        
                        <tr class="active hadding">

                        <% for (int c = 0; c < dtm.getColumnCount(); c++) {
                                String cname = dtm.getColumnName(c).toString();%>
                        <td><%=cname%></td>
                        <%
                            }
                        %>
                    </tr>  
                    <%
                        for (int i = 0; i < dtm.getRowCount(); i++) {%>
                    <tr class="active">
                        <%for (int j = 0; j < dtm.getColumnCount(); j++) {

                                String tdata = dtm.getValueAt(i, j).toString();%>
                        <td><%=tdata%></td>
                        <% }%>
                    </tr>
                    <%  }
                    %>
                </table>
            </div>

            <button type="submit" id="colum" name ="columdata"  class="btn btn-outline-primary">Showdata</button>
        </form>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>



</body>
</html>