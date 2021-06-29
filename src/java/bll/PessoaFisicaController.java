/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_pessoa_fisica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.endereco;
import model.pessoa_fisica;

/**
 *
 * @author naldo
 */
@WebServlet(name = "PessoaFisicaController", urlPatterns = {"/PessoaFisicaController"})
public class PessoaFisicaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarPessoaFisica.jsp";
    private static String LIST_PESSOAS = "/ListarPessoasFisicas.jsp";
    private static String LIST_PESSOA = "/ListarPessoaFisica.jsp";
    private dao_pessoa_fisica dao;
    
    public PessoaFisicaController(){
        super();
        dao = new dao_pessoa_fisica();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String cpf;

        if (action.equalsIgnoreCase("delete")){
            int id_cliente = Integer.parseInt(request.getParameter("idcliente"));
            dao.deletePessoaFisicaID(id_cliente);
            forward = LIST_PESSOAS;
            request.setAttribute("allpessoas", dao.getAllPessoa()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_cliente = Integer.parseInt(request.getParameter("idcliente"));
            pessoa_fisica pessoa = dao.getPessoaFisicaID(id_cliente);
            request.setAttribute("pessoa", pessoa);
        } else if (action.equalsIgnoreCase("listarTodasPessoas")){
            forward = LIST_PESSOAS;
            request.setAttribute("allpessoas", dao.getAllPessoa());  
        } else {
            forward = LIST_PESSOA;
            cpf = request.getParameter("cpfConsulta");
            request.setAttribute("pessoa", dao.getPessoaFisicaCPF(cpf));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        endereco endereco = new endereco();
        pessoa_fisica pessoa = new pessoa_fisica();
        pessoa.setNome(request.getParameter("Nome"));
        pessoa.setTelefone(request.getParameter("Telefone"));
        pessoa.setEmail(request.getParameter("Email"));
        endereco.setEndereco_id(Integer.parseInt(request.getParameter("IDEndereco")));
        pessoa.setEndereco(endereco);
        pessoa.setCpf_cnpj(request.getParameter("Cpf"));
        pessoa.setRegistro_geral(request.getParameter("regGeral"));
        
        String idCliente = request.getParameter("IdCliente");
            
        if (idCliente == null || idCliente.isEmpty()) {
            dao.addPessoaFisica(pessoa);
        } else {
            pessoa.setId_cliente(Integer.parseInt(idCliente));
            dao.editarPessoaFisica(pessoa, pessoa.getCpf_cnpj());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_PESSOAS);
        request.setAttribute("allpessoas", dao.getAllPessoa());
        view.forward(request, response);
    }

    
}
