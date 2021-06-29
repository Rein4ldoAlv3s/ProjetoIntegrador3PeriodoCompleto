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
        
        <form method="GET" action='MotoristaController' name="frmMostrarUmMotorista">
            <label>ID do Motorista:</label>
            <input type="text" name="idMotoristaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="MotoristaController?action=listarTodosMotoristas"><button>Listar Todos os Motoristas</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id do Motorista</th>
                <th>Nome</th>
                <th>Cpf</th>
                <th>Registro Geral</th>
                <th>Data Nascimento</th>
                <th>Telefone</th>
                <th>Email</th>
                <th>Registro Cnh</th>
                <th>Categoria Cnh</th>
                <th>Data Validade Cnh</th>
                <th>Foto Url</th>
                <th>Endereco Id</th>
              
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
           
                <tr>
                    <td><c:out value="${motorista.id_motorista}" /></td>
                    <td><c:out value="${motorista.nome}" /></td>
                    <td><c:out value="${motorista.cpf}" /></td>
                    <td><c:out value="${motorista.registro_geral}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${motorista.data_nascimento}" /></td>
                    <td><c:out value="${motorista.telefone}" /></td>
                    <td><c:out value="${motorista.email}" /></td>
                    <td><c:out value="${motorista.registro_cnh}" /></td>
                    <td><c:out value="${motorista.categoria_cnh}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${motorista.data_validade_cnh}" /></td>
                    <td><c:out value="${motorista.foto_url}" /></td>
                    <td><c:out value="${motorista.endereco.endereco_id}" /></td>
                    
                    <td><a href="MotoristaController?action=edit&idmotorista=<c:out value="${motorista.id_motorista}"/>">Editar</a></td>
                    <td><a href="MotoristaController?action=delete&idmotorista=<c:out value="${motorista.id_motorista}"/>">Deletar</a></td>
                </tr>
        
        </tbody>
    </table> 
        
        <a href="AdicionarMotorista.jsp"><button>Add Motorista</button></a>
        <a href="CadastrarMotorista.jsp"><button>Voltar</button></a>
        
    </body>
</html>
