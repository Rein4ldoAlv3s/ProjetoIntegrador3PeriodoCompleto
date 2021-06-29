<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action='MarcaController' name="frmAddMarca">
        
           
        ID da Marca : <input
            type="text" name="IdMarca" readonly="readonly"
            value="<c:out value="${marca.id_marca}" />" /> <br />     
            
        Descrição : <input
            type="text" name="DescricaoMarca"
            value="<c:out value="${marca.descricao}" />" /> <br />      
          
        URL: : <input
            type="text" name="UrlMarca"
            value="<c:out value="${marca.logomarca}" />" /> <br />     
       
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarMarca.jsp"><button>Voltar</button></a>
        
    </body>
</html>
