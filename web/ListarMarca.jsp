<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <form method="GET" action='MarcaController' name="frmMostrarUmaMarca">
            <label>ID da Marca:</label>
            <input type="text" name="idMarcaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="MarcaController?action=listarTodasMarcas"><button>Listar Todas as Marcas</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Marca</th>
                <th>Descrição da marca</th>
                <th>URL da marca</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td><c:out value="${marca.id_marca}" /></td>
                    <td><c:out value="${marca.descricao}" /></td>
                    <td><c:out value="${marca.logomarca}" /></td>
                    <td><a href="MarcaController?action=edit&idmarca=<c:out value="${marca.id_marca}"/>">Editar</a></td>
                    <td><a href="MarcaController?action=delete&idmarca=<c:out value="${marca.id_marca}"/>">Deletar</a></td>
                </tr>
            
        </tbody>
    </table> 
        
        <a href="AdicionarMarca.jsp"><button>Add Marca</button></a>
        <a href="CadastrarMarca.jsp"><button>Voltar</button></a>
        
    </body>
</html>
