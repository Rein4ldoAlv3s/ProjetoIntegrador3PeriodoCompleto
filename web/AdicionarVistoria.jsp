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
        <form method="POST" action='VistoriaController' name="frmAddVistoria">
        
           
        ID da Vistoria : <input
            type="text" name="IdVistoria" readonly="readonly"
            value="<c:out value="${vistoria.id_vistoria}" />" /> <br />     
        
        Data da Vistoria : <input
            type="text" name="DataVistoria"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${vistoria.data_vistoria}" />" /> <br />   
        
        Quantidade de Combustivel : <input
            type="text" name="QnteCombustivel"
            value="<c:out value="${vistoria.qnte_combustivel}" />" /> <br />   
         
        Quilometragem Inicial : <input
            type="text" name="QuilometragemInicial"
            value="<c:out value="${vistoria.quilometragem_inicial}" />" /> <br />   
        
        Quilometragem Final : <input
            type="text" name="QuilometragemFinal"
            value="<c:out value="${vistoria.quilometragem_final}" />" /> <br />
          
        URL: : <input
            type="text" name="UrlVideo"
            value="<c:out value="${vistoria.url_video}" />" /> <br />     
        
        Observacoes : <input
            type="text" name="Observacoes"
            value="<c:out value="${vistoria.observacoes}" />" /> <br /> 
       
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarVistoria.jsp"><button>Voltar</button></a>
        
    </body>
</html>
