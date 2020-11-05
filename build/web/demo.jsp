<%-- 
    Document   : demo
    Created on : Jul 4, 2020, 11:12:36 AM
    Author     : ar
--%>

<%@page import="Servlet.Utility_Methods"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Checkbox Dynamically using JavaScript</title>
    </head>

    <body>
        <!--Enter a Value <input type="text" id="prod" autofocus />-->
        <%

            Utility_Methods list = new Utility_Methods();

            ArrayList ar = new ArrayList();
            ar.add("arun");
            ar.add("jain");
            ar.add("is");
            ar.add("good");
            ar.add("boy");
            ar.add("hai");

        %>
        <p>

            <!--<input type="button" id="bt" value="Create Checkbox" onclick="createChk(ar)" />-->
        </p>

        <p id="container"> </p>
    </body>


    <script>
        var i = 1;      // COUNTER, FOR CHECKBOX ID.

        function createChk(name) {
            if (name !== '') {

                var chk = document.createElement('input');  // CREATE CHECK BOX.
                chk.setAttribute('type', 'checkbox');       // SPECIFY THE TYPE OF ELEMENT.
                chk.setAttribute('id', 'prodName' + i);     // SET UNIQUE ID.
                chk.setAttribute('value', name);
                chk.setAttribute('name', 'products');

                var lbl = document.createElement('label');  // CREATE LABEL.
                lbl.setAttribute('for', 'prodName' + i);

                // CREATE A TEXT NODE AND APPEND IT TO THE LABEL.
                lbl.appendChild(document.createTextNode(name));

                // APPEND THE NEWLY CREATED CHECKBOX AND LABEL TO THE <p> ELEMENT.
                container.appendChild(chk);
                container.appendChild(lbl);

                name = '';
                //            document.getElementById(obj.id).focus();

                i = i + 1;

            }
        }

        var jsArray = [];
        <%for (int i = 0; i < ar.size(); i++) {%>
        jsArray.push("<%= ar.get(i)%>");
        <%}%>

//      let name ="aas";
//        
        for (var ii = 0; ii < 5; ii++) {
            let name = jsArray[ii];
            createChk(name);
        }
    </script>
</html>