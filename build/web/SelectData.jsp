<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

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
                margin-top: 122px;

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
            <a class="nav-link" href="decrypted_files.jsp">Rules</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="logout.jsp">Logout</a>
        </li>

    </ul>
    <%
        Connections cc = new Connections();
        if (cc.con == null) {
            response.sendRedirect("Blank.jsp");
        }
    %>
</head>
<body>
    <div class="innerdiv">
        <div class="firstdiv">
            <div class="tablediv">
                <div class="tablediv"><h2>Select DataSet</h2>
                    <form name="frm" action="ColumnSelect.jsp" method="Post" >

                        <TABLE id="dataTable">
                            <TR>
                                <TD></TD>

                            </TR>

                        </TABLE>

                        <button type="submit" id="colum" name ="column"  class="btn btn-outline-primary">Show Column</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>

        function ShowData() {
            var cc = <%= Connections.con%>
             if (cc === null)
            {
        <%
            Connections c = new Connections();

            Utility_Methods method = new Utility_Methods();
            ArrayList tname = new ArrayList();
            Connection con1 = c.con;
            String dname = c.dname;
            tname = method.getTablename(con1, dname);

        %>
            var tablename = [];
        <%for (int i = 0; i < tname.size(); i++) {%>
            tablename.push("<%= tname.get(i)%>");
        <%}%>

            //      let name ="aas";
            //        
            for (var ii = 0; ii < tablename.length; ii++) {
                let name = tablename[ii];
                addRow('dataTable', name);
            }
            else{
                
            }
            }
            var i = 0;
            function addRow(tableID, name) {


                var chk = document.createElement('input');  // CREATE CHECK BOX.
                chk.setAttribute('type', 'radio');       // SPECIFY THE TYPE OF ELEMENT.
                chk.setAttribute('id', 'prodName' + i);
                chk.setAttribute('class', 'radiobtn'); // SET UNIQUE ID.
                chk.setAttribute('value', name);
                chk.setAttribute('name', 'rb')

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
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

</body>
</html>