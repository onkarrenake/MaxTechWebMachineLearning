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
                overflow:auto;
                height:500px;
                width:50%;
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
        <div id="dvTable" >


            <!--<input type="button" value="Generate Table" onclick="GenerateTable()" />-->
            <!--<button type="button"  class="btn btn-outline-primary databtn" onclick="GenerateTable()">Generate Table</button>-->
            <table id="example1" class="display"></table>

            <!--<input type="button" value="Generate Table" onclick="GenerateTable()" />-->
            <!--<button type="button"  class="btn btn-outline-primary databtn" onclick="GenerateTable()">Generate Table</button>-->
        </div>
        <div class="SecondContainer divScroll" id="dvTable">

            <h3 class="sticky">Select Your Table</h3>

            <button type="button" id="fortable" name =""  class="btn btn-outline-secondary databtn" onclick="callTable('columnTable')">Your Tables</button>


            <form class="" action="SubmitData"  method="Post">
                <TABLE id="columnTable">
                </TABLE>


                <button type="submit" id="mysql" name =""  class="btn btn-outline-primary databtn">Submit</button>
            </form>
        </div>


    </div>

    <script>

        <%
            Encryptdata data = new Encryptdata();
            StoreData csv = new StoreData();
            String lable = request.getParameter("lable");
            Utility_Methods m = new Utility_Methods();
            String tablename = m.info.get("tablename").toString();
            DefaultTableModel dtm = data.mappeddata(lable, tablename);

            csv.csvwriter(dtm, tablename);%>

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
    <script>


        <%
            ServerConnect con = new ServerConnect();
            ArrayList tname = new ArrayList();
            Connection con1 = con.getConnection();
            Utility_MethodsForServer tablename1 = new Utility_MethodsForServer();
            ServerRequest s = new ServerRequest();
            String em = s.information.get(0).toString();
            String ps = s.information.get(1).toString();
            tname = tablename1.getUserTables(em, ps);

        %>
        var tablename = [];
        <%for (int i = 0; i < tname.size(); i++) {%>
        tablename.push("<%= tname.get(i)%>");
        <%}%>

        //      let name ="aas";
        //    
        var val = 1;
        function callTable(tableID) {
            if (val == 1) {
                for (var ii = 0; ii < tablename.length; ii++) {
                    let name = tablename[ii];
                    addRow(tableID, name);
                    val++;
                }
            }
        }

        var i = 0;
        function addRow(tableID, name) {


            var chk = document.createElement('input');  // CREATE CHECK BOX.
            chk.setAttribute('type', 'radio');       // SPECIFY THE TYPE OF ELEMENT.
            chk.setAttribute('id', 'prodName' + i);
            chk.setAttribute('class', 'radiobtn'); // SET UNIQUE ID.
            chk.setAttribute('value', name);
            chk.setAttribute('name', 'radiobutton')

            var table = document.getElementById(tableID);


            //var rowCount = table.rows.length;
            var row = table.insertRow(i);

            var cell1 = row.insertCell(0);
            var lbl = document.createElement('label');  // CREATE LABEL.
            lbl.setAttribute('for', 'prodName' + i);
            lbl.appendChild(document.createTextNode(name));
            cell1.appendChild(chk);
            cell1.appendChild(lbl);

            //var cell2 = row.insertCell(1);
            // cell2.innerHTML = rowCount + 1;

            // var cell3 = row.insertCell(2);
            // var element2 = document.createElement("input");
            // element2.type = "text";
            // element2.name = "txtbox[]";
            // cell3.appendChild(element2);
            i++;


        }

    </script>

</body>
</html>