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
        
        <form method="GET" action='MarcaController' name="frmMostrarUmaMarca">
            <label>ID da Marca:</label>
            <input type="text" name="idMarcaForm">
            <input type="submit" name="action" value="Procurar cadastro" />
        </form> 
        
        <a href="MarcaController?action=listarTodasMarcas"><button>Listar Todas as Marcas</button></a><br>
        
        <table border=1>
        <thead>
            <tr>
                <th>Id da Locação</th>
                <th>Data Retirada</th>
                <th>Data Devolução</th>
                <th>Valor Seguro</th>
                <th>Valor Calção</th>
                <th>Valor Final</th>
                <th>Status</th>
                <th>Placa Veículo</th>
                <th>ID Motorista</th>
                <th>ID Vistoria</th>
                <th>ID Apolice</th>
                <th>ID Multa</th>
                <th>ID Fisico</th>
                <th>ID Juridico</th>
                <th>ID Acessório</th>
                
               
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${alllocacoes}" var="locacao">
                <tr>
                    <td><c:out value="${locacao.id_locacao}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${locacao.data_retirada}" /></td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${locacao.data_devolucao}" /></td>
                    <td><c:out value="${locacao.valor_seguro}" /></td>
                    <td><c:out value="${locacao.valor_calcao}" /></td>
                    <td><c:out value="${locacao.valor_final}" /></td>
                    <td><c:out value="${locacao.status}" /></td>
                    <td><c:out value="${locacao.veiculo.placa}" /></td>
                    <td><c:out value="${locacao.motorista.id_motorista}" /></td>
                    <td><c:out value="${locacao.vistoria.id_vistoria}" /></td>
                    <td><c:out value="${locacao.apolice.id_apolice}" /></td>
                    <td><c:out value="${locacao.multa.id_multa}" /></td>
                    <td><c:out value="${locacao.fisica.id_cliente}" /></td>
                    <td><c:out value="${locacao.juridica.id_cliente}" /></td>
                    <td><c:out value="${locacao.acessorio.id_acessorio}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
        
        <a href="AdicionarMarca.jsp"><button>Add Marca</button></a>
        <a href="CadastrarMarca.jsp"><button>Voltar</button></a>
        
    </body>
</html>
