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
        <form method="POST" action='VeiculoController' name="frmAddVeiculo">
           
        Placa : <input
            type="text" name="Placa"
            value="<c:out value="${veiculo.placa}" />" /> <br />     
            
        Tipo : <input
            type="text" name="Tipo"
            value="<c:out value="${veiculo.tipo}" />" /> <br />
        
        Renavam : <input
            type="text" name="Renavam"
            value="<c:out value="${veiculo.renavam}" />" /> <br />
        
       
        
        <c:choose>
                    
                    <c:when test = "${veiculo.status == true}">
                        Status (1-Disponí­vel, 0- Indisponí­vel) : <input
                            type="text" name="Status"
                            value="<c:out value="1" />" /> <br />
                    </c:when>
                       
                    <c:when test = "${veiculo.status == false}">
                        Status (1-Disponí­vel, 0- Indisponí­vel) : <input
                            type="text" name="Status"
                            value="<c:out value="0" />" /> <br />
                    </c:when>    
                    
                    <c:otherwise>
                        Status (1-Disponí­vel, 0- Indisponí­vel) : <input
                            type="text" name="Status"
                             /> <br />
                    </c:otherwise>
        </c:choose>
        
        
        Preço de Compra : <input
            type="text" name="PrecoCompra"
            value="<c:out value="${veiculo.preco_compra}" />" /> <br />
        
        Preço de Venda : <input
            type="text" name="PrecoVenda"
            value="<c:out value="${veiculo.preco_venda}" />" /> <br />
       
        Cor : <input
            type="text" name="Cor"
            value="<c:out value="${veiculo.cor}" />" /> <br />
        
        Quantidade de Passageiros : <input
            type="text" name="QtdePassageiros"
            value="<c:out value="${veiculo.qnt_passageiros}" />" /> <br />
        
        Capacidade do Tanque : <input
            type="text" name="CapacTanque"
            value="<c:out value="${veiculo.capacidade_tanque}" />" /> <br />
        
        Ano Fabricação : <input
            type="text" name="AnoFabric"
            value="<c:out value="${veiculo.ano_fabricacao}" />" /> <br />
        
        Ano Modelo : <input
            type="text" name="AnoModelo"
            value="<c:out value="${veiculo.ano_modelo}" />" /> <br />
        
        Tipo Combustivel : <input
            type="text" name="TipoComb"
            value="<c:out value="${veiculo.tipo_combustivel}" />" /> <br />
        
        Quilometragem : <input
            type="text" name="quilom"
            value="<c:out value="${veiculo.quilometragem}" />" /> <br />
        
        ID da Categoria : <input
            type="text" name="IdCateg"
            value="<c:out value="${veiculo.categoria.id_categoria}" />" /> <br />
        
        ID do Modelo : <input
            type="text" name="IdModelo"
            value="<c:out value="${veiculo.modelo.id_modelo}" />" /> <br />
        
        
        <input type="submit" value="Submit" />
        
        </form>
            
        <a href="CadastrarVeiculo.jsp"><button>Voltar</button></a>
        
    </body>
</html>
