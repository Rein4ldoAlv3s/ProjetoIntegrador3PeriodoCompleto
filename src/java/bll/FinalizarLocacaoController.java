/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_locacao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.locacao;

/**
 *
 * @author naldo
 */
@WebServlet(name = "FinalizarLocacaoController", urlPatterns = {"/FinalizarLocacaoController"})
public class FinalizarLocacaoController extends HttpServlet {
    private static String TELAFINALIZAR = "/telafinal.jsp";
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idfinalizarcad = Integer.parseInt(request.getParameter("IdlocacaoFisicaJuridica"));
        
        locacao locacao = new locacao();
        dao_locacao dao_locacao = new dao_locacao();
        
        locacao = dao_locacao.getLocacao(Integer.parseInt(request.getParameter("IdlocacaoFisicaJuridica")));
        
        if (locacao.getId_locacao() + "" != null) {
            RequestDispatcher view = request.getRequestDispatcher(TELAFINALIZAR);
        request.setAttribute("idfinalizarcad", idfinalizarcad);
        view.forward(request, response);
        }
        
        
    }

    
}
