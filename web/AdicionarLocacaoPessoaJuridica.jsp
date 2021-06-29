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
        <form method="POST" action='LocacaoControllerPessoaFisica' name="frmAddModelo">
           
        ID da locação : <input
            type="text" name="IdLocacao"
            value="<c:out value="${locacao.id_locacao}" />" /> <br />     
            
        
        Data da Retirada: : <input
            type="text" name="DataRetirada"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${locacao.data_retirada}" />" /> <br />   
           
        Data da Devolução : <input
            type="text" name="DataDevolucao"
            value="<fmt:formatDate pattern="dd/MM/yyyy" value="${locacao.data_devolucao}" />" /> <br />   
           
        
        Placa do veículo : <input
            type="text" name="placa"
            value="<c:out value="${locacao.motorista.placa}" />" /> <br />   
        
        ID do(a) motorista : <input
            type="text" name="idmotorista"
            value="<c:out value="${locacao.motorista.id_motorista}" />" /> <br /> 
        
        ID vistoria : <input
            type="text" name="idvistoria"
            value="<c:out value="${locacao.vistoria.id_vistoria}" />" /> <br /> 
        
        ID apólice : <input
            type="text" name="idapolice"
            value="<c:out value="${locacao.apolice.id_apolice}" />" /> <br /> 
        
        
        ID do(a) cliente juridico <input
            type="text" name="idclientefis"
            value="<c:out value="${locacao.fisica.id_cliente}" />" /> <br />     
        
            
         
        
        <input type="submit" value="Cadastrar" /> 
        
        </form> 
            
        <a href="CadastrarCliente.jsp"><button>Voltar</button></a>
        
    </body>
</html>
