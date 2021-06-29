/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.acessorio;
import model.tipo_acessorio;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_acessorio {
    
    private Connection conexao;
    
    public dao_acessorio(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addAcessorio(acessorio acessorio){
       
        String sql2 = "SELECT id_acessorio FROM acessorio ORDER BY id_acessorio DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_acessorio");
                aux++;
                acessorio.setId_acessorio(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into acessorio(id_acessorio,valor,status,id_tipo) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,acessorio.getId_acessorio());
            preparedStatement.setDouble(2, acessorio.getValor());
            preparedStatement.setBoolean(3, true);
            preparedStatement.setInt(4, acessorio.getTipo().getId_tipoacessorio());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteAcessorio(int id_acessorio){
        
        String sql = "delete from acessorio where id_acessorio=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, id_acessorio);
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateAcessorio(acessorio acessorio){
        
        String sql = "update acessorio set valor=?,status=?,id_tipo=? where id_acessorio=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setDouble(1, acessorio.getValor());
            preparedStatement.setBoolean(2, acessorio.isStatus());
            preparedStatement.setInt(3, acessorio.getTipo().getId_tipoacessorio());
            preparedStatement.setInt(4, acessorio.getId_acessorio());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public acessorio getAcessorio(int id){
        
        acessorio acessorio = new acessorio();
        dao_tipoAcessorio daoT = new dao_tipoAcessorio();
        try {
            String sql = "select * from acessorio where id_acessorio=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                acessorio.setId_acessorio(rs.getInt("id_acessorio"));
                acessorio.setValor(rs.getDouble("valor"));
                acessorio.setStatus(rs.getBoolean("status"));
                
                tipo_acessorio tipo = new tipo_acessorio();
                tipo = daoT.getTipoAcessorio(rs.getInt("id_tipo"));
                
                acessorio.setTipo(tipo);
                
            }
          

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return acessorio;
    }
    
    public List<acessorio> getAllAcessorio(){
        
        List<acessorio> acessorios = new ArrayList<acessorio>();
        dao_tipoAcessorio daoT = new dao_tipoAcessorio();
        
        String sql = "select * from acessorio";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                acessorio acessorio = new acessorio();
                
                acessorio.setId_acessorio(rs.getInt("id_acessorio"));
                acessorio.setValor(rs.getDouble("valor"));
                acessorio.setStatus(rs.getBoolean("status"));
                
                tipo_acessorio tipo = new tipo_acessorio();
                tipo = daoT.getTipoAcessorio(rs.getInt("id_tipo"));
                
                acessorio.setTipo(tipo);
                
                acessorios.add(acessorio);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return acessorios;
  
    }
    
    public List<acessorio> getAllAcessorioDisponiveis(){
        
        List<acessorio> acessorios = new ArrayList<acessorio>();
        dao_tipoAcessorio daoT = new dao_tipoAcessorio();
        
        String sql = "select * from acessorio where status='t'";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                acessorio acessorio = new acessorio();
                
                acessorio.setId_acessorio(rs.getInt("id_acessorio"));
                acessorio.setValor(rs.getDouble("valor"));
                acessorio.setStatus(rs.getBoolean("status"));
                
                tipo_acessorio tipo = new tipo_acessorio();
                tipo = daoT.getTipoAcessorio(rs.getInt("id_tipo"));
                
                acessorio.setTipo(tipo);
                
                acessorios.add(acessorio);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return acessorios;
  
    }
    
}
