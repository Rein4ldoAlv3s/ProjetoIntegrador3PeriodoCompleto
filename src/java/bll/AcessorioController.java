/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_acessorio;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.acessorio;
import model.tipo_acessorio;

/**
 *
 * @author naldo
 */
@WebServlet(name = "AcessorioController", urlPatterns = {"/AcessorioController"})
public class AcessorioController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarAcessorio.jsp";
    private static String LIST_ACESSORIOS = "/ListarAcessorios.jsp";
    private static String LIST_ACESSORIO = "/ListarAcessorio.jsp";
    private dao_acessorio dao;
    
    public AcessorioController(){
        super();
        dao = new dao_acessorio();
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String forward="";
        String action = request.getParameter("action");
        int idAcessorioForm;

        if (action.equalsIgnoreCase("delete")){
            int idacessorio = Integer.parseInt(request.getParameter("idacessorio"));
            dao.deleteAcessorio(idacessorio);
            request.setAttribute("allacessorios", dao.getAllAcessorio()); 
            forward = LIST_ACESSORIOS;
        } else if (action.equalsIgnoreCase("edit")){
            int idacessorio = Integer.parseInt(request.getParameter("idacessorio"));
            acessorio acessorio = dao.getAcessorio(idacessorio);
            request.setAttribute("acessorio", acessorio);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listarTodosAcessorios")){
            request.setAttribute("allacessorios", dao.getAllAcessorio());  
            forward = LIST_ACESSORIOS;
        } else {
            idAcessorioForm = Integer.parseInt(request.getParameter("idAcessorioForm"));
            request.setAttribute("acessorio", dao.getAcessorio(idAcessorioForm));
            forward = LIST_ACESSORIO;
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
        
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        acessorio acessorio = new acessorio();
        
        acessorio.setValor(Double.parseDouble(request.getParameter("valor")));
        int valorStatus = Integer.parseInt(request.getParameter("status"));
        if (valorStatus == 1) {
            acessorio.setStatus(true);
        } else if (valorStatus == 0) {
            acessorio.setStatus(false);
        }
        
        tipo_acessorio tipo_acessorio = new tipo_acessorio();
        tipo_acessorio.setId_tipoacessorio(Integer.parseInt(request.getParameter("IdTipoAcessorio")));
        acessorio.setTipo(tipo_acessorio);
        
        
        String idAcessorio = request.getParameter("IdAcessorio");
        
        if (idAcessorio.isEmpty() || idAcessorio == null) {
            dao.addAcessorio(acessorio);
        } else {
            acessorio.setId_acessorio(Integer.parseInt(idAcessorio));
            dao.updateAcessorio(acessorio);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_ACESSORIOS);
        request.setAttribute("allacessorios", dao.getAllAcessorio());
        view.forward(request, response);
        
    }

    
}
