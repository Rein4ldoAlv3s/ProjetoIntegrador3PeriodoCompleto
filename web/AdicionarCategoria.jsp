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
        <form method="POST" action='CategoriaController' name="frmAddCategoria">
        
           
        ID da Categoria : <input
            type="text" name="IdCateg" readonly="readonly"
            value="<c:out value="${categoria.id_categoria}" />" /> <br />     
            
        Descrição da Categoria: <input
            type="text" name="DescricaoCateg"
            value="<c:out value="${categoria.descricao}" />" /> <br />       
        
        Valor da Diária: <input
            type="text" name="ValorDiaria"
            value="<c:out value="${categoria.valor_diaria}" />" /> <br />  
        
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarCategoria.jsp"><button>Voltar</button></a>
        
    </body>
</html>
