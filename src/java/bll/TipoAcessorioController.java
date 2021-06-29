/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_acessorio;
import dao.dao_tipoAcessorio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.tipo_acessorio;

/**
 *
 * @author naldo
 */
@WebServlet(name = "TipoAcessorioController", urlPatterns = {"/TipoAcessorioController"})
public class TipoAcessorioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarTipoAcessorio.jsp";
    private static String LIST_TIPOACESSORIOS = "/ListarTipoAcessorios.jsp";
    private static String LIST_TIPOACESSORIO = "/ListarTipoAcessorio.jsp";
    private dao_tipoAcessorio dao;
    
    public TipoAcessorioController(){
        super();
        dao = new dao_tipoAcessorio();
    }
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String forward="";
        String action = request.getParameter("action");
        int idTipoAcessForm;

        if (action.equalsIgnoreCase("delete")){
            int id_tipo = Integer.parseInt(request.getParameter("idtipoacess"));
            dao.deleteTipoAcessorio(id_tipo);
            request.setAttribute("alltipoacessorios", dao.getAllTipoAcessorio()); 
            forward = LIST_TIPOACESSORIOS;
        } else if (action.equalsIgnoreCase("edit")){
            int id_tipo = Integer.parseInt(request.getParameter("idtipoacess"));
            tipo_acessorio tipo_acessorio = dao.getTipoAcessorio(id_tipo);
            request.setAttribute("tipo_acessorio", tipo_acessorio);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listarTodosTiposAcessorios")){
            request.setAttribute("alltipoacessorios", dao.getAllTipoAcessorio());  
            forward = LIST_TIPOACESSORIOS;
        } else {
            idTipoAcessForm = Integer.parseInt(request.getParameter("idTipoAcessForm"));
            request.setAttribute("tipo_acessorio", dao.getTipoAcessorio(idTipoAcessForm));
            forward = LIST_TIPOACESSORIO;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        tipo_acessorio tipo_acessorio = new tipo_acessorio();
        tipo_acessorio.setDescricao(request.getParameter("Descricao"));
        
        String idTipoAcessorio = request.getParameter("IdTipoAcessorio");
        
        if (idTipoAcessorio == null || idTipoAcessorio.isEmpty()) {
            dao.addTipoAcessorio(tipo_acessorio);
        } else {
            tipo_acessorio.setId_tipoacessorio(Integer.parseInt(idTipoAcessorio));
            dao.updateTipoAcessorio(tipo_acessorio);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_TIPOACESSORIOS);
        request.setAttribute("alltipoacessorios", dao.getAllTipoAcessorio());
        view.forward(request, response);
    }



}
