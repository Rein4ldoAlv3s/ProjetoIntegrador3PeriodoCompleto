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
        <form method="POST" action='EnderecoController' name="frmAddModelo">
           
        ID do endereço : <input
            type="text" name="IdEndereco" readonly="readonly"
            value="<c:out value="${endereco.endereco_id}" />" /> <br />     
            
        Número Residencial : <input
            type="text" name="NumResidencial"
            value="<c:out value="${endereco.numero}" />" /> <br />      
          
        Logradouro : <input
            type="text" name="logradouro"
            value="<c:out value="${endereco.logadouro}" />" /> <br />   
        
        País : <input
            type="text" name="pais"
            value="<c:out value="${endereco.pais}" />" /> <br />     
        
        Bairro : <input
            type="text" name="bairro"
            value="<c:out value="${endereco.bairro}" />" /> <br />     
        
        CEP : <input
            type="text" name="cep"
            value="<c:out value="${endereco.cep}" />" /> <br />     
        
        Complemento : <input
            type="text" name="complemento"
            value="<c:out value="${endereco.complemento}" />" /> <br />     
        
        Cidade : <input
            type="text" name="cidade"
            value="<c:out value="${endereco.cidade}" />" /> <br />     
        
        
        <input type="submit" value="Cadastrar" /> 
        
        </form> 
            
        <a href="CadastrarCliente.jsp"><button>Voltar</button></a>
        
    </body>
</html>
