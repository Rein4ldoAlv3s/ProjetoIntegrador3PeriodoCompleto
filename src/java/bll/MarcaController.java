/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_marca;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.marca;

/**
 *
 * @author naldo
 */
@WebServlet(name = "MarcaController", urlPatterns = {"/MarcaController"})
public class MarcaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarMarca.jsp";
    private static String LIST_MARCAS = "/ListarMarcas.jsp";
    private static String LIST_MARCA = "/ListarMarca.jsp";
    private dao_marca dao;
    
    public MarcaController(){
        super();
        dao = new dao_marca();
    }
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idMarcaForm;

        if (action.equalsIgnoreCase("delete")){
            int id_marca = Integer.parseInt(request.getParameter("idmarca"));
            dao.deleteMarca(id_marca);
            forward = LIST_MARCAS;
            request.setAttribute("allmarcas", dao.getAllMarca()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_marca = Integer.parseInt(request.getParameter("idmarca"));
            marca marca = dao.getMarca(id_marca);
            request.setAttribute("marca", marca);
        } else if (action.equalsIgnoreCase("listarTodasMarcas")){
            forward = LIST_MARCAS;
            request.setAttribute("allmarcas", dao.getAllMarca());  
        } else {
            forward = LIST_MARCA;
            idMarcaForm = Integer.parseInt(request.getParameter("idMarcaForm"));
            request.setAttribute("marca", dao.getMarca(idMarcaForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        marca marca = new marca();
        marca.setDescricao(request.getParameter("DescricaoMarca"));
        marca.setLogomarca(request.getParameter("UrlMarca"));
        
        
        String idmarca = request.getParameter("IdMarca");
        
        if (idmarca == null || idmarca.isEmpty()) {
            dao.addMarca(marca);
        } else {
            marca.setId_marca(Integer.parseInt(idmarca));
            dao.updateMarca(marca);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_MARCAS);
        request.setAttribute("allmarcas", dao.getAllMarca());
        view.forward(request, response);
        
    }

}
