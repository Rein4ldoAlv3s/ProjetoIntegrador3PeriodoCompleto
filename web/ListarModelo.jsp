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
        
        <form method="GET" action='ModeloController' name="frmMostrarUmModelo">
            <label>ID do Modelo:</label>
            <input type="text" name="idModelo">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="ModeloController?action=listarTodosModelos"><button>Listar Todas os Modelos</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id do Modelo</th>
                <th>Descrição</th>
                <th>URL da Foto</th>
                <th>ID da Marca</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td><c:out value="${modelo.id_modelo}" /></td>
                    <td><c:out value="${modelo.descricao}" /></td>
                    <td><c:out value="${modelo.url_foto}" /></td>
                    <td><c:out value="${modelo.marca.id_marca}" /></td>
                    <td><a href="ModeloController?action=edit&idmodelo=<c:out value="${modelo.id_modelo}"/>">Editar</a></td>
                    <td><a href="ModeloController?action=delete&idmodelo=<c:out value="${modelo.id_modelo}"/>">Deletar</a></td>
                </tr>
           
        </tbody>
    </table> 
        
        <a href="AdicionarModelo.jsp"><button>Add Modelo</button></a>
        <a href="CadastrarModelo.jsp"><button>Voltar</button></a>
        
    </body>
</html>
