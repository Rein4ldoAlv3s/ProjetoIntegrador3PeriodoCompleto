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
        <form method="POST" action='TipoAcessorioController' name="frmAddTipoAcessorio">
           
        ID tipo acessório : <input 
            type="text" name="IdTipoAcessorio" readonly="readonly"
            value="<c:out value="${tipo_acessorio.id_tipoacessorio}" />" /> <br />     
            
        Descrição : <input
            type="text" name="Descricao"
            value="<c:out value="${tipo_acessorio.descricao}" />" /> <br />      
          
        
        <input type="submit" value="Cadastrar" /> 
        
        </form> 
            
        <a href="CadastrarAcessorio.jsp"><button>Voltar</button></a>
        
    </body>
</html>
