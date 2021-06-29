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
import model.categoria;
import util.Conexao;
/**
 *
 * @author joaov
 */
public class dao_categoria {
    
    private Connection conexao;
    
    public dao_categoria(){
        
        conexao = Conexao.getConexao();

    }
    
    public void AddCategoria(categoria categoria){
        
        String sql2 = "SELECT id_categoria FROM categoria ORDER BY id_categoria DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_categoria");
                aux++;
                categoria.setId_categoria(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into categoria(id_categoria,descricao_categoria, valor_diaria) values(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,categoria.getId_categoria());
            preparedStatement.setString(2,categoria.getDescricao());
            preparedStatement.setDouble(3, categoria.getValor_diaria());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void DeleteCategoria(int id_categoria){
        
        String sql = "delete from categoria where id_categoria=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, id_categoria);
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void UpdateCategoria(categoria categoria){
        
        String sql ="update categoria set descricao_categoria=?,valor_diaria=? where id_categoria = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setString(1, categoria.getDescricao());
            preparedStatement.setDouble(2, categoria.getValor_diaria());
            preparedStatement.setInt(3, categoria.getId_categoria());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
    public categoria getCategoria(int id_categoria){
        
        categoria categoria = new categoria();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from categoria where id_categoria=?");
            preparedStatement.setInt(1, id_categoria);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao_categoria"));
                categoria.setValor_diaria(rs.getDouble("valor_diaria"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return categoria;
    }
    
   
    
    
    public List<categoria> getAllCategorias(){
        
        List<categoria> categorias = new ArrayList<categoria>();
        
        String sql = "select * from categoria";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                categoria categoria = new categoria();
                
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setDescricao(rs.getString("descricao_categoria"));
                categoria.setValor_diaria(rs.getDouble("valor_diaria"));
                
                categorias.add(categoria);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }
    
}
