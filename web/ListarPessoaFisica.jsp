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
        
        <form method="GET" action='PessoaFisicaController' name="frmMostrarUmaPessFisica">
            <label>CPF:</label>
            <input type="text" name="cpfConsulta">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="PessoaFisicaController?action=listarTodasPessoas"><button>Listar Todas as Pessoas</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>ID do cliente</th>
                <th>Nome Completo</th>
                <th>Telefone</th>
                <th>Email</th>
                <th>ID Endereço</th>
                <th>CPF</th>
                <th>Registro Geral</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
                <tr>
                    <td><c:out value="${pessoa.id_cliente}" /></td>
                    <td><c:out value="${pessoa.nome}" /></td>
                    <td><c:out value="${pessoa.telefone}" /></td>
                    <td><c:out value="${pessoa.email}" /></td>
                    <td><c:out value="${pessoa.endereco.endereco_id}" /></td>
                    <td><c:out value="${pessoa.cpf_cnpj}" /></td>
                    <td><c:out value="${pessoa.registro_geral}" /></td>
                    <td><a href="PessoaFisicaController?action=edit&idcliente=<c:out value="${pessoa.id_cliente}"/>">Editar</a></td>
                    <td><a href="PessoaFisicaController?action=delete&idcliente=<c:out value="${pessoa.id_cliente}"/>">Deletar</a></td>
                </tr>
        </tbody>
    </table> 
        
        <a href="AdicionarPessoaFisica.jsp"><button>Add Modelo</button></a>
        <a href="CadastrarCliente.jsp"><button>Voltar</button></a>
        
    </body>
</html>
