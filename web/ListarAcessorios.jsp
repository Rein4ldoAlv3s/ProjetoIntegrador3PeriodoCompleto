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
        
        <form method="GET" action='AcessorioController' name="frmMostrarUmTipoAcess">
            <label>ID Acessório</label>
            <input type="text" name="idAcessorioForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="AcessorioController?action=listarTodosAcessorios"><button>Listar Todos os Acessórios</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>ID acessório</th>
                <th>Valor</th>              
                <th>Status</th>              
                <th>ID tipo Acessório</th>              
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${allacessorios}" var="acessorio">
                <tr>
                    <td><c:out value="${acessorio.id_acessorio}" /></td>
                    <td><c:out value="${acessorio.valor}" /></td>
                    
                    <c:choose>
                    
                    <c:when test = "${acessorio.status == true}">
                        <td>Disponivel</td>
                    </c:when>
                       
                    <c:when test = "${acessorio.status == false}">
                       <td>Indisponivel</td> 
                    </c:when>    
                    
                    <c:otherwise>
                        
                    </c:otherwise>
                    </c:choose> 
                    
                    <td><c:out value="${acessorio.tipo.id_tipoacessorio}" /></td>
                    
                    <td><a href="AcessorioController?action=edit&idacessorio=<c:out value="${acessorio.id_acessorio}"/>">Editar</a></td>
                    <td><a href="AcessorioController?action=delete&idacessorio=<c:out value="${acessorio.id_acessorio}"/>">Deletar</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
        
        <a href="AdicionarAcessorio.jsp"><button>Add Tipo Acessório</button></a>
        <a href="CadastrarAcessorio.jsp"><button>Voltar</button></a>
        
    </body>
</html>
