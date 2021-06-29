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
        <form method="POST" action='MotoristaController' name="frmAddMotorista">
        
           
        ID do Motorista : <input
            type="text" name="IdMotorista" readonly="readonly"
            value="<c:out value="${motorista.id_motorista}" />" /> <br />     
            
        Nome: <input
            type="text" name="Nome"
            value="<c:out value="${motorista.nome}" />" /> <br /> 
        
        Cpf: <input
            type="text" name="Cpf"  
            value="<c:out value="${motorista.cpf}" />" /> <br /> 
        
        Registro Geral: <input
            type="text" name="RegistroGeral"
            value="<c:out value="${motorista.registro_geral}" />" /> <br /> 
        
        Data Nascimento: <input
            type="text" name="DataNascimento"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${motorista.data_nascimento}" />" /> <br /> 
        
        Telefone: <input
            type="text" name="Telefone"
            value="<c:out value="${motorista.telefone}" />" /> <br />  
        
        Email: <input
            type="text" name="Email"
            value="<c:out value="${motorista.email}" />" /> <br />  
        
        Registro Cnh: <input
            type="text" name="RegistroCnh"
            value="<c:out value="${motorista.registro_cnh}" />" /> <br />  
        
        Categoria Cnh: <input
            type="text" name="CategoriaCnh"
            value="<c:out value="${motorista.categoria_cnh}" />" /> <br />  
        
        Data Validade Cnh: <input
            type="text" name="DataValidade"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${motorista.data_validade_cnh}" />" /> <br />  
        
        Foto Url: <input
            type="text" name="FotoUrl"
            value="<c:out value="${motorista.foto_url}" />" /> <br />  
        
        Endereco Id: <input
            type="text" name="EnderecoId"
            value="<c:out value="${motorista.endereco.endereco_id}" />" /> <br />  
        
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarMotorista.jsp"><button>Voltar</button></a>
        
    </body>
</html>
