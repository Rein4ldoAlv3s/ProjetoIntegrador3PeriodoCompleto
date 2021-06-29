/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_endereco;
import dao.dao_pessoa_fisica;
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
import model.pessoa_fisica;

/**
 *
 * @author naldo
 */
@WebServlet(name = "EnderecoController", urlPatterns = {"/EnderecoController"})
public class EnderecoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarEndereco.jsp";
    private static String LIST_ENDERECOS = "/ListarEnderecos.jsp";
    private static String LIST_ENDERECO = "/ListarEndereco.jsp";
    private dao_endereco dao;
    
    public EnderecoController(){
        super();
        dao = new dao_endereco();
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idEnderForm;

        if (action.equalsIgnoreCase("delete")){
            int idEndereco = Integer.parseInt(request.getParameter("id_endereco"));
            dao.deleteEndereco(idEndereco);
            forward = LIST_ENDERECOS;
            request.setAttribute("allenderecos", dao.getAllEndereco()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int idEndereco = Integer.parseInt(request.getParameter("id_endereco"));
            endereco endereco = dao.getEndereco(idEndereco);
            request.setAttribute("endereco", endereco);
        } else if (action.equalsIgnoreCase("listarTodosEnderecos")){
            forward = LIST_ENDERECOS;
            request.setAttribute("allenderecos", dao.getAllEndereco());  
        } else {
            forward = LIST_ENDERECO;
            idEnderForm = Integer.parseInt(request.getParameter("idEndereco"));
            request.setAttribute("endereco", dao.getEndereco(idEnderForm));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
            endereco endereco = new endereco();
            
            endereco.setNumero(Integer.parseInt(request.getParameter("NumResidencial")));
            endereco.setLogadouro(request.getParameter("logradouro"));
            endereco.setPais(request.getParameter("pais"));
            endereco.setBairro(request.getParameter("bairro"));
            endereco.setCep(request.getParameter("cep"));
            endereco.setComplemento(request.getParameter("complemento"));
            endereco.setCidade(request.getParameter("cidade"));
            
            String idEndereco = request.getParameter("IdEndereco");
            
            if (idEndereco == null || idEndereco.isEmpty()) {
                dao.addEndereco(endereco);
            } else {
                endereco.setEndereco_id(Integer.parseInt(idEndereco));
                dao.updateEndereco(endereco);
            }
            
            
        RequestDispatcher view = request.getRequestDispatcher(LIST_ENDERECOS);
        request.setAttribute("allenderecos", dao.getAllEndereco());
        view.forward(request, response);
    }

   
}
