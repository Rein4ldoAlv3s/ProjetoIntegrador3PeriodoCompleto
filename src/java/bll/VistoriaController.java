/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_vistoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.vistoria;

/**
 *
 * @author naldo
 */
@WebServlet(name = "VistoriaController", urlPatterns = {"/VistoriaController"})
public class VistoriaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarVistoria.jsp";
    private static String LIST_VISTORIAS = "/ListarVistorias.jsp";
    private static String LIST_VISTORIA = "/ListarVistoria.jsp";
    private dao_vistoria dao;
    
    public VistoriaController(){
        super();
        dao = new dao_vistoria();
    }
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idVistoriaForm;

        if (action.equalsIgnoreCase("delete")){
            int id_vistoria = Integer.parseInt(request.getParameter("idvistoria"));
            dao.deleteVistoria(id_vistoria);
            forward = LIST_VISTORIAS;
            request.setAttribute("allvistorias", dao.getAllVistorias()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_vistoria = Integer.parseInt(request.getParameter("idvistoria"));
            vistoria vistoria = dao.getVistoria(id_vistoria);
            request.setAttribute("vistoria", vistoria);
        } else if (action.equalsIgnoreCase("listarTodasVistorias")){
            forward = LIST_VISTORIAS;
            request.setAttribute("allvistorias", dao.getAllVistorias());  
        } else {
            forward = LIST_VISTORIA;
            idVistoriaForm = Integer.parseInt(request.getParameter("idVistoriaForm"));
            request.setAttribute("vistoria", dao.getVistoria(idVistoriaForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        vistoria vistoria = new vistoria();
        
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataVistoria"));
            vistoria.setData_vistoria(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        vistoria.setQnte_combustivel(Integer.parseInt(request.getParameter("QnteCombustivel")));
        vistoria.setQuilometragem_inicial(Integer.parseInt(request.getParameter("QuilometragemInicial")));
        vistoria.setQuilometragem_final(Integer.parseInt(request.getParameter("QuilometragemFinal")));
        vistoria.setUrl_video(request.getParameter("UrlVideo"));
        vistoria.setObservacoes(request.getParameter("Observacoes"));
        
        String idvistoria = request.getParameter("IdVistoria");
        
        if (idvistoria == null || idvistoria.isEmpty()) {
            dao.addVistoria(vistoria);
        } else {
            vistoria.setId_vistoria(Integer.parseInt(request.getParameter("IdVistoria")));
            dao.updateVistoria(vistoria);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_VISTORIAS);
        request.setAttribute("allvistorias", dao.getAllVistorias());
        view.forward(request, response);
        
    }

}
