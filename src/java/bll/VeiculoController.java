/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import dao.dao_veiculo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.categoria;
import model.modelo;
import model.veiculo;

/**
 *
 * @author naldo
 */
@WebServlet(name = "VeiculoController", urlPatterns = {"/VeiculoController"})
public class VeiculoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/AdicionarVeiculo.jsp";
    private static String LIST_VEICULOS = "/ListarVeiculos.jsp";
    private static String LIST_VEICULOSLOC = "/AdicionarLocacao.jsp";
    private static String LIST_VEICULO = "/ListarVeiculo.jsp";
    private dao_veiculo dao;
    
    public VeiculoController(){
        super();
        dao = new dao_veiculo();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        String PlacaVeiculoForm;

        if (action.equalsIgnoreCase("delete")){
            String placa = request.getParameter("placa");
            dao.deleteVeiculo(placa);
            forward = LIST_VEICULOS;
            request.setAttribute("allveiculos", dao.getAllVeiculos()); 
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            String placa = request.getParameter("placa");
            veiculo veiculo = dao.getVeiculo(placa);
            request.setAttribute("veiculo", veiculo);
        } else if (action.equalsIgnoreCase("listarTodosVeiculos")){
            forward = LIST_VEICULOS;
            request.setAttribute("allveiculos", dao.getAllVeiculos());  
            
        }
        else {
            forward = LIST_VEICULO;
            PlacaVeiculoForm = request.getParameter("PlacaVeiculo");
            request.setAttribute("veiculo", dao.getVeiculo(PlacaVeiculoForm));
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
        
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        veiculo veiculo = new veiculo();
        categoria categoria = new categoria();
        modelo modelo = new modelo();
        
        veiculo.setTipo(request.getParameter("Tipo"));
        veiculo.setRenavam(request.getParameter("Renavam"));
        
        int status = Integer.parseInt(request.getParameter("Status"));
        
        if (status == 1) {
            veiculo.setStatus(true);
        } else {
            veiculo.setStatus(false);
        }
        
        veiculo.setPreco_compra(Double.parseDouble(request.getParameter("PrecoCompra")));
        veiculo.setPreco_venda(Double.parseDouble(request.getParameter("PrecoVenda")));
        veiculo.setCor(request.getParameter("Cor"));
        veiculo.setQnt_passageiros(Integer.parseInt(request.getParameter("QtdePassageiros")));
        veiculo.setCapacidade_tanque(Integer.parseInt(request.getParameter("CapacTanque")));
        veiculo.setAno_fabricacao(Integer.parseInt(request.getParameter("AnoFabric")));
        veiculo.setAno_modelo(Integer.parseInt(request.getParameter("AnoModelo")));
        veiculo.setTipo_combustivel(request.getParameter("TipoComb"));
        veiculo.setQuilometragem(Integer.parseInt(request.getParameter("quilom")));
        
        int idCategoria = Integer.parseInt(request.getParameter("IdCateg"));
        categoria.setId_categoria(idCategoria);
        veiculo.setCategoria(categoria);
        
        int idModelo = Integer.parseInt(request.getParameter("IdModelo"));
        modelo.setId_modelo(idModelo);
        veiculo.setModelo(modelo);
        
        veiculo.setPlaca(request.getParameter("Placa"));
        String placa = request.getParameter("Placa");
        
        if (placa != null) {
            dao.updateVeiculo(veiculo.getPlaca(), veiculo);
        } else {
            dao.addVeiculo(veiculo);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_VEICULOS);
        request.setAttribute("allveiculos", dao.getAllVeiculos());
        view.forward(request, response);
        
        
    }
    
}
