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
                margin-top: 15px;

            }
            .divScroll {
                overflow-y:auto;
                height:310px;;
                width: fit-content;
                margin: auto;
                margin-top: 22px;
            }
            #colum{
                /*width: 116px;*/
                height: 39px;
                margin-left: 593px;
                margin-top: 7px;
            }
            .sticky {
                position: -webkit-sticky;
                position: sticky;
            }
            .container2{
                display: flex;
                width: 60%;
                justify-content: center;

                margin-top: 28px;
            }
            h2{
                display: flex;
                width: 39%;
                margin: auto;
                justify-content: center;
                margin-top: 51px;

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

</head>
<body>
    <div class="innerdiv">
        <div class="firstdiv">
            <h2 class="sticky">Select Columns</h2>


            <div id="dvTable" class="divScroll" >

                <form  action="ShowData.jsp"  method="Post">

                    <TABLE id="columnTable">

                    </TABLE>
            </div>

            <div class="container2">
                <button type="submit" id="colum" name ="columdata"  class="btn btn-outline-primary">Showdata</button>
            </div>
        </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    <script>
        <%
            Connections c = new Connections();
            Utility_Methods method = new Utility_Methods();
            ArrayList cname = new ArrayList();
            Connection con1 = c.con;
            String radioButton = request.getParameter("rb");
            cname = method.getcolumnname(radioButton, con1);

        %>


        var tablename = [];
        <%for (int i = 0; i < cname.size(); i++) {%>
        tablename.push("<%= cname.get(i)%>");

        <%}%>

//      let name ="aas";
//        
        for (var ii = tablename.length; ii > 0; ii--) {
            let name = tablename[ii - 1];
            addRow('columnTable', name);
        }





        var i = 0;
        function addRow(tableID, name) {


            var chk = document.createElement('input');  // CREATE CHECK BOX.
            chk.setAttribute('type', 'checkbox');       // SPECIFY THE TYPE OF ELEMENT.
            chk.setAttribute('id', 'prod' + i);
            chk.setAttribute('class', 'checkboxcls'); // SET UNIQUE ID.
            chk.setAttribute('value', name);
            chk.setAttribute('name', 'checkboxname');

            var table = document.getElementById(tableID);


            //var rowCount = table.rows.length;
            var row = table.insertRow(i);

            var cell1 = row.insertCell(0);
            var lbl = document.createElement('label');  // CREATE LABEL.
            lbl.setAttribute('for', 'labelid' + i);
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