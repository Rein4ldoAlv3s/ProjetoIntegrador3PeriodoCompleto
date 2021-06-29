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
        
        <form method="GET" action='VistoriaController' name="frmMostrarUmaVistoria">
            <label>ID da Vistoria:</label>
            <input type="text" name="idVistoriaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="VistoriaController?action=listarTodasVistorias"><button>Listar Todas as Vistorias</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Vistoria</th>
                <th>Data da Vistoria</th>
                <th>Qnte de Combustivel</th>
                <th>Quilometragem Inicial</th>
                <th>Quilometragem Final</th>
                <th>Url do Video</th>
                <th>Observacoes</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allvistorias}" var="vistoria">
                <tr>
                    <td><c:out value="${vistoria.id_vistoria}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${vistoria.data_vistoria}" /></td>
                    <td><c:out value="${vistoria.qnte_combustivel}" /></td>
                    <td><c:out value="${vistoria.quilometragem_inicial}" /></td>
                    <td><c:out value="${vistoria.quilometragem_final}" /></td>
                    <td><c:out value="${vistoria.url_video}" /></td>
                    <td><c:out value="${vistoria.observacoes}" /></td>
                    <td><a href="VistoriaController?action=edit&idvistoria=<c:out value="${vistoria.id_vistoria}"/>">Editar</a></td>
                    <td><a href="VistoriaController?action=delete&idvistoria=<c:out value="${vistoria.id_vistoria}"/>">Deletar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
        
        <a href="AdicionarVistoria.jsp"><button>Add Vistoria</button></a>
        <a href="CadastrarVistoria.jsp"><button>Voltar</button></a>
        
    </body>
</html>
