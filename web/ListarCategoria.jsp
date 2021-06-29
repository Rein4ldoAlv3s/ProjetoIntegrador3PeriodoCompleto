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
        
        <form method="GET" action='CategoriaController' name="frmMostrarUmaCateg">
            <label>ID da Categoria:</label>
            <input type="text" name="idCategoriaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="CategoriaController?action=listarTodasCategorias"><button>Listar Todas as Categorias</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Categoria</th>
                <th>Descrição da Categoria</th>
                <th>Valor Diária</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
           
                <tr>
                    <td><c:out value="${categoria.id_categoria}" /></td>
                    <td><c:out value="${categoria.descricao}" /></td>
                    <td><c:out value="${categoria.valor_diaria}" /></td>
                    <td><a href="CategoriaController?action=edit&id_categoria=<c:out value="${categoria.id_categoria}"/>">Editar</a></td>
                    <td><a href="CategoriaController?action=delete&id_categoria=<c:out value="${categoria.id_categoria}"/>">Deletar</a></td>
                </tr>
            
        </tbody>
    </table> 
        
        <a href="AdicionarCategoria.jsp"><button>Add Categoria</button></a>
        <a href="CadastrarCategoria.jsp"><button>Voltar</button></a>
        
    </body>
</html>
