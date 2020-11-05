<%-- 
    Document   : UserDatabase_Select
    Created on : Jul 1, 2020, 10:56:01 AM
    Author     : ar
--%>

<%@page import="Servlet.Connect"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.Connections"%>
<%@page import="Servlet.Utility_Methods"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

        <title>Hello, world!</title>
     <link href="css/BodyCss.css" type="text/css" rel="stylesheet" media="all">
        <style>
            .container{
                width: 100%;

                padding: 61px;
                margin: auto;
                margin-bottom: 38px;
                border: 3px solid;
                height: 500px;
                border-radius: 38px;
            }
            .hading{
                display: block;
                margin: auto;
            }
            .radiobtn{
                margin-right: 8px;

            }
            #dataTable{
                display: block;
            }

        </style>
    </head>
    <body>
        <%
            if (session.getAttribute("email") == null && session.getAttribute("password") == null) {
                response.sendRedirect("signin.jsp");
            }
        %>

        <div class="container">
            <div forbutton">

                <h1 class ="hading">Select Database</h1>

                <form  action="Connections"  method="Post">

                    <button type="submit" id="mysql" name ="mysqldatabase"  class="btn btn-outline-primary">Mysql Database</button>
                    <button type="submit" id="oracle" name ="oracledatabase"  class="btn btn-outline-secondary">Oracle Database</button>
                    <button type="submit" id="postgres" name ="postgresdatabase"  class="btn btn-outline-success">Postgres Database</button>
                </form>
            </div>
        </div>

        <div class ="datasets container">

            <h2>please select dataset</h2>

            <form name="frm" action="Column.jsp" method="Post" >


                </TABLE >
                <TABLE id="dataTable" width="350px" border="1">
                    <TR>
                        <TD></TD>

                    </TR>

                </TABLE>

                <button type="submit" id="colum" name ="column"  class="btn btn-outline-primary">Show Column</button>
        </div>


        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>

    <script>


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





        var i = 0;
        function addRow(tableID, name) {


            var chk = document.createElement('input');  // CREATE CHECK BOX.
            chk.setAttribute('type', 'radio');       // SPECIFY THE TYPE OF ELEMENT.
            chk.setAttribute('id', 'prodName' + i);
            chk.setAttribute('class', 'radiobtn'); // SET UNIQUE ID.
            chk.setAttribute('value', name);
            chk.setAttribute('name', 'radiobutton');

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



</html>
