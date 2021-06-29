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
import model.modelo;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_modelo {
    
    private Connection conexao;
    
    public dao_modelo(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addModelo(modelo modelo){
        
        String sql2 = "SELECT id_modelo FROM modelo ORDER BY id_modelo DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_modelo");
                aux++;
                modelo.setId_modelo(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into modelo(id_modelo,descricao,url_foto, id_marca) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, modelo.getId_modelo());
            preparedStatement.setString(2,modelo.getDescricao());
            preparedStatement.setString(3,modelo.getUrl_foto());
            preparedStatement.setInt(4, modelo.getMarca().getId_marca());
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void DeleteModelo(int id_modelo){
        
        String sql = "delete from modelo where id_modelo=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, id_modelo);
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateModelo(modelo modelo){
        
        String sql ="update modelo set descricao=?,url_foto=?,id_marca=? where id_modelo=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);

            preparedStatement.setString(1, modelo.getDescricao());
            preparedStatement.setString(2, modelo.getUrl_foto());
            preparedStatement.setInt(3, modelo.getMarca().getId_marca());
            preparedStatement.setInt(4, modelo.getId_modelo());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public modelo getModelo(int id_modelo){
        
        modelo modelo = new modelo();
        dao_marca daomarca = new dao_marca();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from modelo where id_modelo=?");
            preparedStatement.setInt(1, id_modelo);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                modelo.setId_modelo(rs.getInt("id_modelo"));
                modelo.setDescricao(rs.getString("descricao"));
                modelo.setUrl_foto(rs.getString("url_foto"));
                marca marca = new marca();
                marca = daomarca.getMarca(rs.getInt("id_marca"));
                modelo.setMarca(marca);
                                  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return modelo;
    }
    
    public List<modelo> getAllModelo(){
        
        List<modelo> modelos = new ArrayList<modelo>();
        dao_marca daomarca = new dao_marca();
        String sql = "select * from modelo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                modelo modelo = new modelo();
                
                modelo.setId_modelo(rs.getInt("id_modelo"));
                modelo.setDescricao(rs.getString("descricao"));
                modelo.setUrl_foto(rs.getString("url_foto"));
                marca marca = new marca();
                marca = daomarca.getMarca(rs.getInt("id_marca"));
                modelo.setMarca(marca);
                
                modelos.add(modelo);

                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelos;
        
    }
    
}
