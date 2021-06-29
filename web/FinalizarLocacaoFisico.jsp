<%-- 
    Document   : FinalizarLocacaoFisico
    Created on : 25 de jun de 2021, 18:35:12
    Author     : naldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Finalizar Locação</h1>
        
        <form method="POST" action='FinalizarLocacaoController' name="frmAddAcessorio">
            ID locacao : <input 
            type="text" name="IdlocacaoFisicaJuridica" 
             /> <br /> 
            
            <input type="submit" value="Submit" />
            
        </form>
    </body>
</html>
