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
import model.vistoria;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_vistoria{
    
    private Connection conexao;
    
    public dao_vistoria(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addVistoria(vistoria vistoria){
        
        String sql2 = "SELECT id_vistoria FROM vistoria ORDER BY id_vistoria DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_vistoria");
                aux++;
                vistoria.setId_vistoria(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into vistoria(id_vistoria,data_vistoria,qnte_combustivel,quilometragem_inicial,quilometragem_final,url_video,observacoes) values(? , ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,vistoria.getId_vistoria());
            preparedStatement.setDate(2, new java.sql.Date(vistoria.getData_vistoria().getTime()));
            preparedStatement.setInt(3, vistoria.getQnte_combustivel());
            preparedStatement.setInt(4, vistoria.getQuilometragem_inicial());
            preparedStatement.setInt(5, vistoria.getQuilometragem_final());
            preparedStatement.setString(6, vistoria.getUrl_video());
            preparedStatement.setString(7, vistoria.getObservacoes());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteVistoria(int id_vistoria){
        
        String sql = "delete from vistoria where id_vistoria=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, id_vistoria);
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateVistoria(vistoria vistoria){
        
        String sql ="update vistoria set data_vistoria=?,qnte_combustivel=?,quilometragem_inicial=?,quilometragem_final=?,url_video=?,observacoes=? where id_vistoria=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setDate(1, new java.sql.Date(vistoria.getData_vistoria().getTime()));
            preparedStatement.setInt(2, vistoria.getQnte_combustivel());
            preparedStatement.setInt(3, vistoria.getQuilometragem_inicial());
            preparedStatement.setInt(4, vistoria.getQuilometragem_final());
            preparedStatement.setString(5, vistoria.getUrl_video());
            preparedStatement.setString(6, vistoria.getObservacoes());
            preparedStatement.setInt(7,vistoria.getId_vistoria());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public vistoria getVistoria(int id_vistoria){
        
        vistoria vistoria = new vistoria();
        
        try {
            String sql ="select * from vistoria where id_vistoria="+id_vistoria;
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                vistoria.setId_vistoria(rs.getInt("id_vistoria"));
                vistoria.setData_vistoria(rs.getDate("data_vistoria"));
                vistoria.setQnte_combustivel(rs.getInt("qnte_combustivel"));
                vistoria.setQuilometragem_inicial(rs.getInt("quilometragem_inicial"));
                vistoria.setQuilometragem_final(rs.getInt("quilometragem_final"));
                vistoria.setUrl_video(rs.getString("url_video"));
                vistoria.setObservacoes(rs.getString("observacoes"));
                
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vistoria;
        
    }
    
    public List<vistoria> getAllVistorias(){
        
        List<vistoria> vistorias = new ArrayList<vistoria>();
        
        String sql = "select * from vistoria";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                vistoria vistoria = new vistoria();
                
                vistoria.setId_vistoria(rs.getInt("id_vistoria"));
                vistoria.setData_vistoria(rs.getDate("data_vistoria"));
                vistoria.setQnte_combustivel(rs.getInt("qnte_combustivel"));
                vistoria.setQuilometragem_inicial(rs.getInt("quilometragem_inicial"));
                vistoria.setQuilometragem_final(rs.getInt("quilometragem_final"));
                vistoria.setUrl_video(rs.getString("url_video"));
                vistoria.setObservacoes(rs.getString("observacoes"));
                
                vistorias.add(vistoria);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return vistorias;
    }
    
}
