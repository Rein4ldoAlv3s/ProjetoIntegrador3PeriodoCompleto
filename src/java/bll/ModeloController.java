/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_modelo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.marca;
import model.modelo;


@WebServlet(name = "ModeloController", urlPatterns = {"/ModeloController"})
public class ModeloController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarModelo.jsp";
    private static String LIST_MODELOS = "/ListarModelos.jsp";
    private static String LIST_MODELO = "/ListarModelo.jsp";
    private dao_modelo dao;
    
    public ModeloController(){
        super();
        dao = new dao_modelo();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idModeloForm;

        if (action.equalsIgnoreCase("delete")){
            int id_modelo = Integer.parseInt(request.getParameter("idmodelo"));
            dao.DeleteModelo(id_modelo);
            forward = LIST_MODELOS;
            request.setAttribute("allmodelos", dao.getAllModelo()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_modelo = Integer.parseInt(request.getParameter("idmodelo"));
            modelo modelo = dao.getModelo(id_modelo);
            request.setAttribute("modelo", modelo);
        } else if (action.equalsIgnoreCase("listarTodosModelos")){
            forward = LIST_MODELOS;
            request.setAttribute("allmodelos", dao.getAllModelo());  
        } else {
            forward = LIST_MODELO;
            idModeloForm = Integer.parseInt(request.getParameter("idModelo"));
            request.setAttribute("modelo", dao.getModelo(idModeloForm));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        modelo modelo = new modelo();
        marca marca = new marca();
        modelo.setDescricao(request.getParameter("DescricaoModelo"));
        modelo.setUrl_foto(request.getParameter("UrlFoto"));
        marca.setId_marca(Integer.parseInt(request.getParameter("IdMarca")));
        modelo.setMarca(marca);
        
        String idmodelo = request.getParameter("IdModelo");
        
        if (idmodelo == null || idmodelo.isEmpty()) {
            dao.addModelo(modelo);
        } else {
            modelo.setId_modelo(Integer.parseInt(idmodelo));
            dao.UpdateModelo(modelo);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_MODELOS);
        request.setAttribute("allmodelos", dao.getAllModelo());
        view.forward(request, response);
        
    }

   
}
