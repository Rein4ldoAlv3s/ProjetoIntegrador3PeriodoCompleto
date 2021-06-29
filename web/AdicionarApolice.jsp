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
        <form method="POST" action='ApoliceController' name="frmAddApolice">
        
           
        ID da Apolice : <input
            type="text" name="IdApolice" readonly="readonly"
            value="<c:out value="${apolice.id_apolice}" />" /> <br />     
            
        Data Inicio da Apolice: <input
            type="text" name="DataInicio"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${apolice.data_inicio}" />" /> <br /> 
        
        Data Fim da Apolice: <input
            type="text" name="DataFim"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${apolice.data_fim}" />" /> <br />  
        
        Valor da Apolice: <input
            type="text" name="Valor"
            value="<c:out value="${apolice.valor}" />" /> <br />  
        
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarApolice.jsp"><button>Voltar</button></a>
        
    </body>
</html>
