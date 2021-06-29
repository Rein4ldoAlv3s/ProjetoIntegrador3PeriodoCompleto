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
        <form method="POST" action='MultaController' name="frmAddMulta">
        
           
        ID da Multa : <input
            type="text" name="IdMulta" readonly="readonly"
            value="<c:out value="${multa.id_multa}" />" /> <br />     
        
        Data Autuação : <input
            type="text" name="DataAutuacao"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${multa.data_autuacao}" />" /> <br />   
        
        Gravidade : <input
            type="text" name="Gravidade"
            value="<c:out value="${multa.gravidade}" />" /> <br /> 
        
        Valor : <input
            type="text" name="Valor"
            value="<c:out value="${multa.valor}" />" /> <br /> 
        
        Descrição : <input
            type="text" name="Descricao"
            value="<c:out value="${multa.descricao}" />" /> <br /> 
        
        
       
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarMulta.jsp"><button>Voltar</button></a>
        
    </body>
</html>
