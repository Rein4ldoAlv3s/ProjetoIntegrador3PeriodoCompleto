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
import model.tipo_acessorio;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_tipoAcessorio {
    
    private Connection conexao;
    
    public dao_tipoAcessorio(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addTipoAcessorio(tipo_acessorio tipoAcessorio){
       
        String sql2 = "SELECT id_tipo FROM tipo_acessorio ORDER BY id_tipo DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_tipo");
                aux++;
                tipoAcessorio.setId_tipoacessorio(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into tipo_acessorio (id_tipo,descricao) values(?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,tipoAcessorio.getId_tipoacessorio());
            preparedStatement.setString(2, tipoAcessorio.getDescricao());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteTipoAcessorio(int id){
        
        String sql = "delete from tipo_acessorio where id_tipo=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteTipoAcessorioDescricao(String descricao){
        
        String sql = "delete from tipo_acessorio where descricao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, descricao);
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateTipoAcessorio(tipo_acessorio tipo_acessorio){
        
        String sql = "update tipo_acessorio set descricao=? where id_tipo=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            
            preparedStatement.setString(1, tipo_acessorio.getDescricao());
            preparedStatement.setInt(2, tipo_acessorio.getId_tipoacessorio());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public tipo_acessorio getAcessorioDescricao(String descricao){
        
        tipo_acessorio tipo = new tipo_acessorio();
        
        try {
            String sql = "select * from tipo_acessorio where descricao=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, descricao);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                tipo.setId_tipoacessorio(rs.getInt("id_tipo"));
                tipo.setDescricao(rs.getString("descricao"));
                
            }
          

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return tipo;
        
    }
    
    public tipo_acessorio getTipoAcessorio(int id){
        
        tipo_acessorio tipo = new tipo_acessorio();
        
        try {
            String sql = "select * from tipo_acessorio where id_tipo=?";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                tipo.setId_tipoacessorio(rs.getInt("id_tipo"));
                tipo.setDescricao(rs.getString("descricao"));
                
            }
          

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return tipo;
        
    }
    
    public List<tipo_acessorio> getAllTipoAcessorio(){
        
        List<tipo_acessorio> tipos = new ArrayList<tipo_acessorio>();
        String sql = "select * from tipo_acessorio";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                tipo_acessorio tipo = new tipo_acessorio();
                
                tipo.setId_tipoacessorio(rs.getInt("id_tipo"));
                tipo.setDescricao(rs.getString("descricao"));
                
                tipos.add(tipo);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return tipos;
    }
    
}
