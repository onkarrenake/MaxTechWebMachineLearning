<%-- 
    Document   : Column_Select
    Created on : Jul 3, 2020, 5:10:18 PM
    Author     : ar
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Servlet.Utility_Methods"%>
<%@page import="Servlet.Connections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/BodyCss.css" type="text/css" rel="stylesheet" media="all">
    </head>
    <body>
        <form  action="Showdata.jsp"  method="Post">
            <div class="container">
                <h1>Select Columns</h1>
                <TABLE id="columnTable">
                    <TR>
                        <TD></TD>

                    </TR>

                </TABLE>
            </div>


            <button type="submit" id="colum" name ="columdata"  class="btn btn-outline-primary">Showdata</button>
            <script>
                <%
                    Connections c = new Connections();
                    Utility_Methods method = new Utility_Methods();
                    ArrayList cname = new ArrayList();
                    Connection con1 = c.con;
                    String radioButton = request.getParameter("radiobutton");
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
