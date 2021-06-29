/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_locacao;
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
import model.cliente;
import model.locacao;
import model.motorista;
import model.pessoa_fisica;
import model.veiculo;
import model.vistoria;

/**
 *
 * @author naldo
 */
@WebServlet(name = "LocacaoControllerPessoaFisica", urlPatterns = {"/LocacaoControllerPessoaFisica"})
public class LocacaoControllerPessoaFisica extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarLocacaoPessoaFisica.jsp";
    private static String LIST_LOCACOES = "/ListarLocacaoPessoaFisicass.jsp";
    private static String LIST_LOCACAO = "/ListarLocacaoPessoaFisica.jsp";
    private dao_locacao dao;
    
    public LocacaoControllerPessoaFisica(){
        super();
        dao = new dao_locacao();
    }
    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        locacao locacao = new locacao();
        pessoa_fisica pessoa_fisica = new pessoa_fisica();
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataRetirada"));
            locacao.setData_retirada(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        try {
            Date dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("DataDevolucao"));
            locacao.setData_devolucao(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        veiculo veiculo = new veiculo();
        veiculo.setPlaca(request.getParameter("placa"));
        locacao.setVeiculo(veiculo);
        
        motorista motorista = new motorista();
        motorista.setId_motorista(Integer.parseInt(request.getParameter("idmotorista")));
        locacao.setMotorista(motorista);
        
        vistoria vistoria = new vistoria();
        vistoria.setId_vistoria(Integer.parseInt(request.getParameter("idvistoria")));
        locacao.setVistoria(vistoria);
        
        apolice apolice = new apolice();
        apolice.setId_apolice(Integer.parseInt(request.getParameter("idapolice")));
        locacao.setApolice(apolice);
        
        
        int idcliente = Integer.parseInt(request.getParameter("idclientefis"));
        pessoa_fisica.setId_cliente(idcliente);
        locacao.setFisica(pessoa_fisica);
        
        
        
        
        dao.addLocacaoPessoaFisica(locacao);
        
        
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_LOCACOES);
        request.setAttribute("alllocacoes", dao.getAllLocacao());
        view.forward(request, response);
        
       
        
    }

    
   
}
