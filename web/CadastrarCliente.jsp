<%-- 
    Document   : index
    Created on : 15 de jun de 2021, 15:02:27
    Author     : naldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MULA CAR</title>
    </head>
    <body>
        
        <h1>MULA CAR</h1>
        
        <p>É necessário o cadastro do endereço para adicionar pessoa física ou pessoa jurídica.</p>
        
        <a href="AdicionarEndereco.jsp"><button>Adicionar Endereço</button></a>
        <a href="EnderecoController?action=listarTodosEnderecos"><button>Listar Endereços</button></a>  <br><br>
        
        
        <a href="AdicionarPessoaFisica.jsp"><button>Adicionar Pessoa Física</button></a>
        <a href="PessoaFisicaController?action=listarTodasPessoas"><button>Listar Pessoas Físicas</button></a>  <br><br>
        
        
        <a href="AdicionarPessoaJuridica.jsp"><button>Adicionar Pessoa Jurídica</button></a>
        <a href="PessoaJuridicaController?action=listarTodasPessoas"><button>Listar Pessoas Jurídicas</button></a> <br><br>
        
        
        <a href="index.jsp"><button>Voltar</button></a>
        
    </body>
</html>