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
        <form method="POST" action='AcessorioController' name="frmAddAcessorio">
           
        ID acessório : <input 
            type="text" name="IdAcessorio" readonly="readonly"
            value="<c:out value="${acessorio.id_acessorio}" />" /> <br />     
            
        Valor : <input 
            type="text" name="valor" 
            value="<c:out value="${acessorio.valor}" />" /> <br />  
        
        <c:choose>
                    
            <c:when test = "${acessorio.status == true}">
                Status (1- Disponível 0-Indisponível) : <input 
                type="text" name="status" 
                value="<c:out value="1" />" /> <br /> 
            </c:when>

            <c:when test = "${acessorio.status == false}">
               Status (1- Disponível 0-Indisponível) : <input 
               type="text" name="status" 
               value="<c:out value="0" />" /> <br /> 
            </c:when>    

            <c:otherwise>
                Status (1- Disponível 0-Indisponível) : <input 
                type="text" name="status" 
                value="<c:out value="" />" /> <br /> 
            </c:otherwise>
        </c:choose> 
        
        
        ID tipo Acessório : <input 
            type="text" name="IdTipoAcessorio" 
            value="<c:out value="${acessorio.tipo.id_tipoacessorio}" />" /> <br /> 
        
        <input type="submit" value="Cadastrar" /> 
        
        </form> 
            
        <a href="CadastrarAcessorio.jsp"><button>Voltar</button></a>
        
    </body>
</html>
