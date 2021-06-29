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
        
        <form method="GET" action='TipoAcessorioController' name="frmMostrarUmTipoAcess">
            <label>ID do Tipo Acessório</label>
            <input type="text" name="idTipoAcessForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="TipoAcessorioController?action=listarTodosTiposAcessorios"><button>Listar Todos os Tipos Acessórios</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>ID tipo acessório</th>
                <th>Descrição</th>              
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td><c:out value="${tipo_acessorio.id_tipoacessorio}" /></td>
                    <td><c:out value="${tipo_acessorio.descricao}" /></td>
                    
                    <td><a href="TipoAcessorioController?action=edit&idtipoacess=<c:out value="${tipo_acessorio.id_tipoacessorio}"/>">Editar</a></td>
                    <td><a href="TipoAcessorioController?action=delete&idtipoacess=<c:out value="${tipo_acessorio.id_tipoacessorio}"/>">Deletar</a></td>
                </tr>
            
        </tbody>
    </table> 
        
        <a href="AdicionarTipoAcessorio.jsp"><button>Add Tipo Acessório</button></a>
        <a href="CadastrarAcessorio.jsp"><button>Voltar</button></a>
        
    </body>
</html>
