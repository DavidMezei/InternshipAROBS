<%@ page import = "java.util.*" %>

    <html>
          <center>
            <head><title>Convert Hex number to decimal</title></head>
            <body>
            <form action="./convertHex" method = "POST">
            Hex number <input type = "text" name = "hex" ><br>
            <input type="submit" value="convert" ><br><br>
            </form>
            Decimal: <input type = "text" disabled = "true" value = "${resultDecimal}">
            <p> Execution time: <% out.print(application.getAttribute("executionTime")); %> </p>
            <p> Requests: ${requestCount} </p>
         </body>
     <center>
</html>