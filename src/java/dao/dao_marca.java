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
import model.marca;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_marca {
    
    private Connection conexao;
    
    public dao_marca(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addMarca(marca marca){
        
        String sql2 = "SELECT id_marca FROM marca ORDER BY id_marca DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_marca");
                aux++;
                marca.setId_marca(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into marca(id_marca, descricao,logomarca_url) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            
            preparedStatement.setInt(1,marca.getId_marca());
            preparedStatement.setString(2,marca.getDescricao());
            preparedStatement.setString(3,marca.getLogomarca());

                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteMarca(int id){
        
        String sql = "delete from marca where id_marca=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,id);
                               
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateMarca(marca marca){
        
        String sql="update marca set descricao=?,logomarca_url=? where id_marca=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1,marca.getDescricao());
            preparedStatement.setString(2, marca.getLogomarca());
            preparedStatement.setInt(3,marca.getId_marca());
                               
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public marca getMarca(int id){
        
        marca marca = new marca();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from marca where id_marca=?");
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                marca.setId_marca(rs.getInt("id_marca"));
                marca.setDescricao(rs.getString("descricao"));
                marca.setLogomarca(rs.getString("logomarca_url"));
                
                           
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return marca;
        
    }
    
    public List<marca> getAllMarca(){
        
        List<marca> marcas = new ArrayList<marca>();
        
        String sql="select * from marca";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                marca marca = new marca();
                
                marca.setId_marca(rs.getInt("id_marca"));
                marca.setDescricao(rs.getString("descricao"));
                marca.setLogomarca(rs.getString("logomarca_url"));
                
                marcas.add(marca);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return marcas;
        
    }
    
}
