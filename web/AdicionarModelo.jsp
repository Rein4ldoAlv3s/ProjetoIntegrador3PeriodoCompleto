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
        <form method="POST" action='ModeloController' name="frmAddModelo">
           
        ID do modelo : <input
            type="text" name="IdModelo" readonly="readonly"
            value="<c:out value="${modelo.id_modelo}" />" /> <br />     
            
        Descrição : <input
            type="text" name="DescricaoModelo"
            value="<c:out value="${modelo.descricao}" />" /> <br />      
          
        URL Foto: : <input
            type="text" name="UrlFoto"
            value="<c:out value="${modelo.url_foto}" />" /> <br />   
        
        ID Marca: : <input
            type="text" name="IdMarca"
            value="<c:out value="${modelo.marca.id_marca}" />" /> <br />     
       
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarModelo.jsp"><button>Voltar</button></a>
        
    </body>
</html>
