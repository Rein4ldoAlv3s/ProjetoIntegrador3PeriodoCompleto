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
        
        <form method="GET" action='EnderecoController' name="frmMostrarEndereco">
            <label>ID da Endereço</label>
            <input type="text" name="idEndereco">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="EnderecoController?action=listarTodosEnderecos"><button>Listar Todos Endereços</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id do Endereço</th>
                <th>Número Residencial</th>
                <th>Logradouro</th>
                <th>País</th>
                <th>Bairro</th>
                <th>CEP</th>
                <th>Complemento</th>
                <th>Cidade</th>
                
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
           
                <tr>
                    <td><c:out value="${endereco.endereco_id}" /></td>
                    <td><c:out value="${endereco.numero}" /></td>
                    <td><c:out value="${endereco.logadouro}" /></td>
                    <td><c:out value="${endereco.pais}" /></td>
                    <td><c:out value="${endereco.bairro}" /></td>
                    <td><c:out value="${endereco.cep}" /></td>
                    <td><c:out value="${endereco.complemento}" /></td>
                    <td><c:out value="${endereco.cidade}" /></td>
                    <td><a href="EnderecoController?action=edit&id_endereco=<c:out value="${endereco.endereco_id}"/>">Editar</a></td>
                    <td><a href="EnderecoController?action=delete&id_endereco=<c:out value="${endereco.endereco_id}"/>">Deletar</a></td>
                </tr>
           
        </tbody>
    </table> 
        
        <a href="AdicionarEndereco.jsp"><button>Add Endereço</button></a>
        <a href="CadastrarCliente.jsp"><button>Voltar</button></a>
        
    </body>
</html>
