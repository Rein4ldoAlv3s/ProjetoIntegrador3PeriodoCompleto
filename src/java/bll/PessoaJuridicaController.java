/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_pessoa_juridica;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.endereco;
import model.pessoa_juridica;

/**
 *
 * @author naldo
 */
@WebServlet(name = "PessoaJuridicaController", urlPatterns = {"/PessoaJuridicaController"})
public class PessoaJuridicaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarPessoaJuridica.jsp";
    private static String LIST_PESSOAS = "/ListarPessoasJuridicas.jsp";
    private static String LIST_PESSOA = "/ListarPessoaJuridica.jsp";
    private dao_pessoa_juridica dao;
    
    public PessoaJuridicaController(){
        super();
        dao = new dao_pessoa_juridica();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String cnpjForm;
        
        if (action.equalsIgnoreCase("delete")){
            String cnpj = request.getParameter("cnpjcliente");
            dao.deletePessoaJuridicaCNPJ(cnpj);
            forward = LIST_PESSOAS;
            request.setAttribute("allpessoas", dao.getAllPessoaJuridica()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String cnpj = request.getParameter("cnpjcliente");
            pessoa_juridica pessoa = dao.getPessoaJuridicaCNPJ(cnpj);
            request.setAttribute("pessoa", pessoa);
        } else if (action.equalsIgnoreCase("listarTodasPessoas")){
            forward = LIST_PESSOAS;
            request.setAttribute("allpessoas", dao.getAllPessoaJuridica());  
        }  else {
            forward = LIST_PESSOA;
            cnpjForm = request.getParameter("cnpjForm");
            request.setAttribute("pessoa", dao.getPessoaJuridicaCNPJ(cnpjForm));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        endereco endereco = new endereco();
        pessoa_juridica pessoa = new pessoa_juridica();
        pessoa.setNome(request.getParameter("Nome"));
        pessoa.setTelefone(request.getParameter("Telefone"));
        pessoa.setEmail(request.getParameter("Email"));
        endereco.setEndereco_id(Integer.parseInt(request.getParameter("IDEndereco")));
        pessoa.setEndereco(endereco);
        pessoa.setCpf_cnpj(request.getParameter("Cnpj"));
        pessoa.setRazao_social(request.getParameter("RazaoSocial"));
        
        String idCliente = request.getParameter("IdCliente");
        
        if (idCliente == null || idCliente.isEmpty()) {
            dao.addPessoaJuridica(pessoa);
        } else {
            pessoa.setId_cliente(Integer.parseInt(idCliente));
            dao.editarPessoaJuridicaCNPJ(pessoa, pessoa.getCpf_cnpj());
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_PESSOAS);
        request.setAttribute("allpessoas", dao.getAllPessoaJuridica());
        view.forward(request, response);
        
        
    }

}
