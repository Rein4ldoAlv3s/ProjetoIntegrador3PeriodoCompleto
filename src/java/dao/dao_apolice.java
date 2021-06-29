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
import java.util.ArrayList;
import java.util.List;
import model.apolice;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_apolice {
    
    private Connection conexao;
    
    public dao_apolice(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addApolice(apolice apolice){
        
        String sql2 = "SELECT id_apolice FROM apolice ORDER BY id_apolice DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_apolice");
                aux++;
                apolice.setId_apolice(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into apolice(id_apolice,data_inicio,data_fim,valor) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, apolice.getId_apolice());
            preparedStatement.setDate(2, new java.sql.Date(apolice.getData_inicio().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(apolice.getData_fim().getTime()));
            preparedStatement.setDouble(4, apolice.getValor());
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteApolice(int id_apolice){
        
        String sql = "delete from apolice where id_apolice="+id_apolice;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateApolice(apolice apolice){
        
        String sql = "update apolice set data_inicio=?,data_fim=?,valor=? where id_apolice=?";
        try {
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(apolice.getData_inicio().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(apolice.getData_fim().getTime()));
            preparedStatement.setDouble(3, apolice.getValor());
            preparedStatement.setInt(4, apolice.getId_apolice());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public apolice getApolice(int id_apolice){
        
        apolice apolice = new apolice();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from apolice where id_apolice="+id_apolice);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                apolice.setId_apolice(rs.getInt("id_apolice"));
                apolice.setData_fim(rs.getDate("data_fim"));
                apolice.setData_inicio(rs.getDate("data_inicio"));
                apolice.setValor(rs.getDouble("valor"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return apolice;
    }
    
    public List<apolice> getAllApolice(){
        
        List<apolice> apolices = new ArrayList<apolice>();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from apolice");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                apolice apolice = new apolice();
                
                apolice.setId_apolice(rs.getInt("id_apolice"));
                apolice.setData_fim(rs.getDate("data_fim"));
                apolice.setData_inicio(rs.getDate("data_inicio"));
                apolice.setValor(rs.getDouble("valor"));
                
                apolices.add(apolice);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return apolices;
        
    }
    
}
