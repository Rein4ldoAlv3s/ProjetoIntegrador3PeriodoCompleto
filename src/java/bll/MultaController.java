package bll;

import dao.dao_multa;
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
import model.multa;

/**
 *
 * @author naldo
 */
@WebServlet(name = "MultaController", urlPatterns = {"/MultaController"})
public class MultaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarMulta.jsp";
    private static String LIST_MULTAS = "/ListarMultas.jsp";
    private static String LIST_MULTA = "/ListarMulta.jsp";
    private dao_multa dao;
    
    public MultaController(){
        super();
        dao = new dao_multa();
    }
 
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        int idMultaForm;

        if (action.equalsIgnoreCase("delete")){
            int id_multa = Integer.parseInt(request.getParameter("idmulta"));
            dao.deleteMulta(id_multa);
            forward = LIST_MULTAS;
            request.setAttribute("allmultas", dao.getAllMultas()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int id_multa = Integer.parseInt(request.getParameter("idmulta"));
            multa multa = dao.getMulta(id_multa);
            request.setAttribute("multa", multa);
        } else if (action.equalsIgnoreCase("listarTodasMultas")){
            forward = LIST_MULTAS;
            request.setAttribute("allmultas", dao.getAllMultas());  
        } else {
            forward = LIST_MULTA;
            idMultaForm = Integer.parseInt(request.getParameter("idMultaForm"));
            request.setAttribute("multa", dao.getMulta(idMultaForm));
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        multa multa = new multa();
        multa.setDescricao(request.getParameter("Descricao"));
        
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataAutuacao"));
            multa.setData_autuacao(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        multa.setGravidade(request.getParameter("Gravidade"));
        multa.setValor(Double.parseDouble(request.getParameter("Valor")));
        
        String idmulta = request.getParameter("IdMulta");
        
        if (idmulta == null || idmulta.isEmpty()) {
            dao.AddMulta(multa);
        } else {
            multa.setId_multa(Integer.parseInt(idmulta));
            dao.updateMulta(multa);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_MULTAS);
        request.setAttribute("allmultas", dao.getAllMultas());
        view.forward(request, response);
        
    }

}
