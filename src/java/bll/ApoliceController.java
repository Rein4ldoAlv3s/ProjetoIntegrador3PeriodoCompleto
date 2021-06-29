/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_apolice;
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

import model.apolice;


/**
 *
 * @author naldo
 */
@WebServlet(name = "ApoliceController", urlPatterns = {"/ApoliceController"})
public class ApoliceController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarApolice.jsp";
    private static String LIST_APOLICES = "/ListarApolices.jsp";
    private static String LIST_APOLICE = "/ListarApolice.jsp";
    private dao_apolice dao;

    public ApoliceController(){
        super();
        dao = new dao_apolice();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idApoliceForm;

        if (action.equalsIgnoreCase("delete")){
            int id_apolice = Integer.parseInt(request.getParameter("idapolice"));
            dao.deleteApolice(id_apolice);
            forward = LIST_APOLICES;
            request.setAttribute("allapolices", dao.getAllApolice()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_apolice = Integer.parseInt(request.getParameter("idapolice"));
            apolice apolice = dao.getApolice(id_apolice);
            request.setAttribute("apolice", apolice);
        } else if (action.equalsIgnoreCase("listarTodasApolices")){
            forward = LIST_APOLICES;
            request.setAttribute("allapolices", dao.getAllApolice());  
        } else {
            forward = LIST_APOLICE;
            idApoliceForm = Integer.parseInt(request.getParameter("idApoliceForm"));
            request.setAttribute("apolice", dao.getApolice(idApoliceForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        apolice apolice = new apolice();
        
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataInicio"));
            apolice.setData_inicio(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataFim"));
            apolice.setData_fim(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        apolice.setValor(Double.parseDouble(request.getParameter("Valor")));
        
        String idApolice = request.getParameter("IdApolice");
        
        if (idApolice == null || idApolice.isEmpty()) {
            dao.addApolice(apolice);
        } else {
            apolice.setId_apolice(Integer.parseInt(request.getParameter("IdApolice")));
            dao.updateApolice(apolice);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_APOLICES);
        request.setAttribute("allapolices", dao.getAllApolice());
        view.forward(request, response);
        
        
    }
    

}
