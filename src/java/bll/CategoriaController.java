/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_categoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria;

/**
 *
 * @author naldo
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarCategoria.jsp";
    private static String LIST_CATEGORIAS = "/ListarCategorias.jsp";
    private static String LIST_CATEGORIA = "/ListarCategoria.jsp";
    private dao_categoria dao;

    public CategoriaController(){
        super();
        dao = new dao_categoria();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idCategForm;

        if (action.equalsIgnoreCase("delete")){
            int id_caegoria = Integer.parseInt(request.getParameter("id_categoria"));
            dao.DeleteCategoria(id_caegoria);
            forward = LIST_CATEGORIAS;
            request.setAttribute("allcategorias", dao.getAllCategorias()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
            categoria categoria = dao.getCategoria(id_categoria);
            request.setAttribute("categoria", categoria);
        } else if (action.equalsIgnoreCase("listarTodasCategorias")){
            forward = LIST_CATEGORIAS;
            request.setAttribute("allcategorias", dao.getAllCategorias());  
        } else {
            forward = LIST_CATEGORIA;
            idCategForm = Integer.parseInt(request.getParameter("idCategoriaForm"));
            request.setAttribute("categoria", dao.getCategoria(idCategForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        categoria categoria = new categoria();
        categoria.setDescricao(request.getParameter("DescricaoCateg"));
        categoria.setValor_diaria(Double.parseDouble(request.getParameter("ValorDiaria")));
        
        String idcategoria = request.getParameter("IdCateg");
        
        if (idcategoria == null || idcategoria.isEmpty()) {
            dao.AddCategoria(categoria);
        } else {
            categoria.setId_categoria(Integer.parseInt(idcategoria));
            dao.UpdateCategoria(categoria);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_CATEGORIAS);
        request.setAttribute("allcategorias", dao.getAllCategorias());
        view.forward(request, response);
        
        
    }
    

}
