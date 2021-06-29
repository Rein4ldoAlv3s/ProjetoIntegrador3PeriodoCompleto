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
        
        <form method="GET" action='VeiculoController' name="frmMostrarUmVeiculo">
            <label>Placa</label>
            <input type="text" name="PlacaVeiculo">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="VeiculoController?action=listarTodosVeiculos"><button>Listar Todas os Veículos</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Placa</th>
                <th>Tipo</th>
                <th>Renavam</th>
                <th>Status</th>
                <th>Preço de Compra</th>
                <th>Preço de Venda</th>
                <th>Cor</th>
                <th>Quantidade de Passageiros</th>
                <th>Capacidade do Tanque</th>
                <th>Ano Fabricação</th>
                <th>Ano modelo</th>
                <th>Tipo Combustivel</th>
                <th>Quilometragem</th>
                <th>ID da Categoria</th>
                <th>ID do Modelo</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            
                <tr>
                    <td><c:out value="${veiculo.placa}" /></td>
                    <td><c:out value="${veiculo.tipo}" /></td>
                    <td><c:out value="${veiculo.renavam}" /></td>
                    
                    <c:choose>
                    
                    <c:when test = "${veiculo.status == true}">
                        <td>Disponivel</td>
                    </c:when>
                       
                    <c:when test = "${veiculo.status == false}">
                       <td>Indisponivel</td> 
                    </c:when>    
                    
                    <c:otherwise>
                        
                    </c:otherwise>
                    </c:choose>
                    
                    <td><c:out value="${veiculo.preco_compra}" /></td>
                    <td><c:out value="${veiculo.preco_venda}" /></td>
                    <td><c:out value="${veiculo.cor}" /></td>
                    <td><c:out value="${veiculo.qnt_passageiros}" /></td>
                    <td><c:out value="${veiculo.capacidade_tanque}" /></td>
                    <td><c:out value="${veiculo.ano_fabricacao}" /></td>
                    <td><c:out value="${veiculo.ano_modelo}" /></td>
                    <td><c:out value="${veiculo.tipo_combustivel}" /></td>
                    <td><c:out value="${veiculo.quilometragem}" /></td>
                    <td><c:out value="${veiculo.categoria.id_categoria}" /></td>
                    <td><c:out value="${veiculo.modelo.id_modelo}" /></td>
                    <td><a href="VeiculoController?action=edit&placa=<c:out value="${veiculo.placa}"/>">Editar</a></td>
                    <td><a href="VeiculoController?action=delete&placa=<c:out value="${veiculo.placa}"/>">Deletar</a></td>
                </tr>
            
        </tbody>
    </table> 
        
        <a href="AdicionarVeiculo.jsp"><button>Add Veículo</button></a>
        <a href="CadastrarVeiculo.jsp"><button>Voltar</button></a>
        
    </body>
</html>
