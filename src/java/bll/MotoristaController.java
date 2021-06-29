/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_motorista;
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
import model.endereco;


import model.motorista;


/**
 *
 * @author naldo
 */
@WebServlet(name = "MotoristaController", urlPatterns = {"/MotoristaController"})
public class MotoristaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarMotorista.jsp";
    private static String LIST_MOTORISTAS = "/ListarMotoristas.jsp";
    private static String LIST_MOTORISTA = "/ListarMotorista.jsp";
    private dao_motorista dao;

    public MotoristaController(){
        super();
        dao = new dao_motorista();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idMotoristaForm;

        if (action.equalsIgnoreCase("delete")){
            int id_motorista = Integer.parseInt(request.getParameter("idmotorista"));
            dao.deleteMotorista(id_motorista);
            request.setAttribute("allmotoristas", dao.getAllMotorista()); 
            forward = LIST_MOTORISTAS;
        } else if (action.equalsIgnoreCase("edit")){
            int id_motorista = Integer.parseInt(request.getParameter("idmotorista"));
            motorista motorista = dao.getMotorista(id_motorista);
            request.setAttribute("motorista", motorista);
            forward = INSERT_OR_EDIT;
        } else if (action.equalsIgnoreCase("listarTodosMotoristas")){
            forward = LIST_MOTORISTAS;
            request.setAttribute("allmotoristas", dao.getAllMotorista());  
        } else {
            forward = LIST_MOTORISTA;
            idMotoristaForm = Integer.parseInt(request.getParameter("idMotoristaForm"));
            request.setAttribute("motorista", dao.getMotorista(idMotoristaForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        motorista motorista = new motorista();
        endereco endereco = new endereco();
        
        
        motorista.setTelefone(request.getParameter("Telefone"));
        motorista.setCpf(request.getParameter("Cpf"));
        motorista.setEmail(request.getParameter("Email"));
        motorista.setNome(request.getParameter("Nome"));
        motorista.setRegistro_geral(request.getParameter("RegistroGeral"));
         try {
            Date dob1 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataNascimento"));
            motorista.setData_nascimento(dob1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        motorista.setRegistro_cnh(request.getParameter("RegistroCnh"));
        motorista.setCategoria_cnh(request.getParameter("CategoriaCnh"));
        try {
            Date dob2 = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataValidade"));
            motorista.setData_validade_cnh(dob2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        motorista.setFoto_url(request.getParameter("FotoUrl"));
        
        endereco.setEndereco_id(Integer.parseInt(request.getParameter("EnderecoId")));
        motorista.setEndereco(endereco);
        
        String idMotorista = request.getParameter("IdMotorista");
        
        if (idMotorista == null || idMotorista.isEmpty()) {
            dao.addMotorista(motorista);
        } else {
            motorista.setId_motorista(Integer.parseInt(request.getParameter("IdMotorista")));
            dao.updateMotorista(motorista);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_MOTORISTAS);
        request.setAttribute("allmotoristas", dao.getAllMotorista());
        view.forward(request, response);
        
        
    }
    

}
