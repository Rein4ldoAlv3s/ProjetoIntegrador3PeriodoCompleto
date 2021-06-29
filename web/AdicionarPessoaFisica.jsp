<%@ page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action='PessoaFisicaController' name="frmAddModelo">
           
        ID do cliente : <input
            type="text" name="IdCliente" readonly="readonly"
            value="<c:out value="${pessoa.id_cliente}" />" /> <br />     
            
        Nome Completo : <input
            type="text" name="Nome"
            value="<c:out value="${pessoa.nome}" />" /> <br />      
          
        Telefone : <input
            type="text" name="Telefone"
            value="<c:out value="${pessoa.telefone}" />" /> <br />   
        
        Email : <input
            type="text" name="Email"
            value="<c:out value="${pessoa.email}" />" /> <br />     
        
        ID Endereço : <input
            type="text" name="IDEndereco" 
            value="<c:out value="${pessoa.endereco.endereco_id}" />" /> <br />     
        
        CPF : <input
            type="text" name="Cpf"
            value="<c:out value="${pessoa.cpf_cnpj}" />" /> <br />     
        
        Registro Geral : <input
            type="text" name="regGeral"
            value="<c:out value="${pessoa.registro_geral}" />" /> <br />     
        
        
        <input type="submit" value="Cadastrar" /> 
        
        </form> 
            
        <a href="CadastrarCliente.jsp"><button>Voltar</button></a>
        
    </body>
</html>
