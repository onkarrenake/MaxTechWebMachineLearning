<%-- 
    Document   : UserHmePage
    Created on : Jul 13, 2020, 10:00:31 AM
    Author     : ar
--%>

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

                margin-top: 30px;
                max-height: 90%;
            }
            tbody{


                overflow-x:auto;
                overflow-y:auto;
            }


            /*            #colum{
                            width: 111px;
                            height: 39px;
            
                            margin-left: 322px;
                            margin-top: 7px;
                        }*/
            .divScroll {

                height:394px;
                width: 87%;
                overflow-x:auto;
                overflow-y:auto;
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

            /*            label{
                            display: none;
                        }*/
            option{
                display: none;
            }
            .innerdiv
            {
                height: 814px;
            }
            button
            {
                margin: 10px 0px;
            }
            #example{
                /*                max-width: 800px;*/
                width: 30%;
                overflow-x:auto;
            }
            div.dataTables_wrapper {
                /*width: 500px;*/
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
                <table id="example" class="display"></table>
            </div>
            <div class="SecondContainer " id="dvTable">

                <h3 class="sticky">Select Class Lable</h3>

                <button type="button" id="fortable" name =""  class="btn btn-outline-secondary databtn" onclick="callColumn('columnTable')">Class Lable</button>


                <form  action="Encrypt1.jsp"  method="Post">
                    <div class="divScroll">
                        <TABLE id="columnTable">
                        </TABLE>
                    </div>

                    <button type="submit" id="mysql" name =""  class="btn btn-outline-primary databtn">Submit</button>
                </form>
            </div>

      
    </div>
    <script>

        <%
            Connections c = new Connections();
            Utility_Methods method = new Utility_Methods();
            ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
            ArrayList cname = new ArrayList();
            Connection con1 = c.con;

            String[] colum = request.getParameterValues("checkboxname");
            for (int cn = 0; cn < colum.length; cn++) {
                cname.add(colum[cn]);
            }
            //                cname = method.getcolumnname(method.Tablename, con1);
            data = method.getdata(colum, con1);
            Encryptdata dta = new Encryptdata();
            dta.gettabledata(data, colum);
            //            con1.close();

        %>
        var columname = [];
        <%for (int i = 0; i < cname.size(); i++) {%>

        columname.push({title: "<%= cname.get(i)%>"});

        <%}%>
        var customers = new Array();
        //                    customers.push(columname);



        <%for (int j = 0; j < data.get(0).size(); j++) {%>
        var columdata = [];
        <%  int t = 0;
            int k = data.size();
            while (t < k) {%>
        columdata.push("<%= data.get(t).get(j)%>");
        <%t++;%>
        <%}%>
        customers.push(columdata);


        <%}%>


        $(document).ready(function () {
            $('#example').DataTable({
                "scrollX": true,
                data: customers,
                columns: columname
            });
        });

        var columname1 = [];
        <%for (int i = 0; i < cname.size(); i++) {%>
        columname1.push("<%= cname.get(i)%>");
        <%}%>

        //      let name ="aas";
        //    
        var val = 1;
        function callColumn(tableID) {
            if (val == 1) {
                for (var ii = 0; ii < columname1.length; ii++) {
                    let name = columname1[ii];
                    showColumn(tableID, name);

                }
                showColumn(tableID, "none of this");
                val++;
            }
        }

        var i = 0;
        function showColumn(tableID, name) {


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