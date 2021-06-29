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
        
        <form method="GET" action='MultaController' name="frmMostrarUmaMulta">
            <label>ID da Multa:</label>
            <input type="text" name="idMultaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="MultaController?action=listarTodasMultas"><button>Listar Todas as Multas</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Multa</th>
                <th>Descrição</th>
                <th>Data Autuacao</th>
                <th>Gravidade</th>
                <th>Valor</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${allmultas}" var="multa">
                    <tr>
                        <td><c:out value="${multa.id_multa}" /></td>
                        <td><c:out value="${multa.descricao}" /></td>
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${multa.data_autuacao}" /></td>
                        <td><c:out value="${multa.gravidade}" /></td>
                        <td><c:out value="${multa.valor}" /></td>
                        <td><a href="MultaController?action=edit&idmulta=<c:out value="${multa.id_multa}"/>">Editar</a></td>
                        <td><a href="MultaController?action=delete&idmulta=<c:out value="${multa.id_multa}"/>">Deletar</a></td>
                    </tr>
                </c:forEach>
            
        </tbody>
    </table> 
        
        <a href="AdicionarMulta.jsp"><button>Add Multa</button></a>
        <a href="CadastrarMulta.jsp"><button>Voltar</button></a>
        
    </body>
</html>
