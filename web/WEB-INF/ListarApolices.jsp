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
        
        <form method="GET" action='ApoliceController' name="frmMostrarUmaApolice">
            <label>ID da Apolice:</label>
            <input type="text" name="idApoliceForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="ApoliceController?action=listarTodasApolices"><button>Listar Todas as Apolices</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Apolice</th>
                <th>Data Inicio</th>
                <th>Data Fim</th>
                <th>Valor</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td><c:out value="${apolice.id_apolice}" /></td>
                    <td><c:out value="${apolice.data_inicio}" /></td>
                    <td><c:out value="${apolice.data_fim}" /></td>
                    <td><c:out value="${apolice.valor}" /></td>
                    <td><a href="ApoliceController?action=edit&idapolice=<c:out value="${apolice.id_apolice}"/>">Editar</a></td>
                    <td><a href="ApoliceController?action=delete&idapolice=<c:out value="${apolice.id_apolice}"/>">Deletar</a></td>
                </tr>
            
        </tbody>
    </table> 
        
        <a href="AdicionarApolice.jsp"><button>Add Apolice</button></a>
        <a href="CadastrarApolice.jsp"><button>Voltar</button></a>
        
    </body>
</html>
