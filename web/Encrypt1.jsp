<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

<%@page import="Servlet.ServerRequest"%>
<%@page import="Servlet.Utility_MethodsForServer"%>
<%@page import="Servlet.ServerConnect"%>
<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="Servlet.StoreData"%>
<%@page import="Servlet.Encryptdata"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.Utility_Methods"%>
<%@page import="Servlet.Connections"%>
<%@page import="Servlet.Connections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/datatable1.css" type="text/css" rel="stylesheet" media="all">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
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
            #dvTable {
                border-collapse: collapse;
                border-spacing: 0;
                width: fit-content;
                /* border: 1px solid #ddd; */
                /* margin: auto; */
                margin-left: 100px;
                margin-top: 30px;

            }


            /*            #colum{
                            width: 111px;
                            height: 39px;
            
                            margin-left: 322px;
                            margin-top: 7px;
                        }*/
            .divScroll {
                height:470px;
                width: 400px;
                overflow-y:auto;
                overflow-x: auto;


                margin: auto;
            }

            th, td {
                text-align: left;
                padding: 8px;

            }
            .container2{
                display: flex;

                justify-content: center;
            }
            .SecondContainer{
                display: inline;
            }
            .hadding{
                font: bold;
                font-size: 20px;
                font-variant: petite-caps;
            }
            option{
                display: none;
            }
            div.dataTables_wrapper {
                max-width: 1000px;
                margin: 0 auto;
            }

            .innerdiv
            {
                height: 843px;
            }
            #colum
            {
                width: 28%;
                display: block;
                margin: auto;
                margin-top: 25px;
            }
            .dataTables_scrollHead{
                display: flex;
                justify-content: center;

            }
        </style>
        <title>Hello, world!</title>
    <ul class="nav nav-pills nav-fill">
        <li class="nav-item">
            <a class="nav-link active" href="#">Home</a>
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
            <a class="nav-link" href="decrypted_files.jsp">Rules</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
        </li>

    </ul>

</head>
<body>
    <div class="innerdiv container1">

        <form action="Mappeddata.jsp" method="Post">
            <div id="dvTable " >


                <!--<input type="button" value="Generate Table" onclick="GenerateTable()" />-->
                <!--<button type="button"  class="btn btn-outline-primary databtn" onclick="GenerateTable()">Generate Table</button>-->
                <table id="example1" class="display divScroll"></table>

                <!--<input type="button" value="Generate Table" onclick="GenerateTable()" />-->
                <!--<button type="button"  class="btn btn-outline-primary databtn" onclick="GenerateTable()">Generate Table</button>-->
            </div>

            <button type="submit" id="colum" name ="columdata"  class="btn btn-outline-primary">Showdata</button>
        </form>

    </div>
    <script>

        <%
            Encryptdata data = new Encryptdata();
            String lable = request.getParameter("radiobutton");
            Utility_Methods m = new Utility_Methods();
            String tablename = m.info.get("tablename").toString();
            DefaultTableModel dtm = data.encryptdata(lable, tablename);%>

        var columname = [];

        <% for (int c = 0; c < dtm.getColumnCount(); c++) {
                String cname = dtm.getColumnName(c).toString();%>

        columname.push({title: "<%=dtm.getColumnName(c).toString()%>"});
        <%}%>
        var columdata = [];
        <%
            for (int i = 0; i < dtm.getRowCount(); i++) {%>
        var columdata1 = [];

        <%for (int j = 0; j < dtm.getColumnCount(); j++) {

                String tdata = dtm.getValueAt(i, j).toString();%>
        columdata1.push("<%=dtm.getValueAt(i, j).toString()%>");

        <% }%>
        columdata.push(columdata1);

        <%
            }
        %>
        $(document).ready(function () {
            $('#example1').DataTable({
                "scrollX": true,
                data: columdata,
                columns: columname
            });
        });
    </script>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->


</body>
</html>