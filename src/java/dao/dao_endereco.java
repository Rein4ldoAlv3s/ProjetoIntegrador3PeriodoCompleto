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
import model.endereco;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_endereco{
    
    private Connection conexao;
    
    public dao_endereco(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addEndereco(endereco endereco){
        
        String sql2 = "SELECT endereco_id FROM endereco ORDER BY endereco_id DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("endereco_id");
                aux++;
                endereco.setEndereco_id(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into endereco(endereco_id,numero,logradouro,pais,bairro,cep,complemento,cidade) values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, endereco.getEndereco_id());
            preparedStatement.setInt(2, endereco.getNumero());
            preparedStatement.setString(3, endereco.getLogadouro());
            preparedStatement.setString(4, endereco.getPais());
            preparedStatement.setString(5, endereco.getBairro());
            preparedStatement.setString(6, endereco.getCep());
            preparedStatement.setString(7, endereco.getComplemento());
            preparedStatement.setString(8, endereco.getCidade());
            
            
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteEndereco(int endereco_id){
        
        String sql ="delete from endereco where endereco_id="+endereco_id;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateEndereco(endereco endereco){
        
        String sql ="update endereco set numero=?,logradouro=?,pais=?,bairro=?,cep=?,complemento=?,cidade=? where endereco_id=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setInt(1, endereco.getNumero());
            preparedStatement.setString(2, endereco.getLogadouro());
            preparedStatement.setString(3, endereco.getPais());
            preparedStatement.setString(4, endereco.getBairro());
            preparedStatement.setString(5, endereco.getCep());
            preparedStatement.setString(6, endereco.getComplemento());
            preparedStatement.setString(7, endereco.getCidade());
            preparedStatement.setInt(8, endereco.getEndereco_id());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public endereco getEndereco(int endereco_id){
        
        endereco endereco = new endereco();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from endereco where endereco_id=?");
            preparedStatement.setInt(1,endereco_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                endereco.setEndereco_id(rs.getInt("endereco_id"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setLogadouro(rs.getString("logradouro"));
                endereco.setPais(rs.getString("pais"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCidade(rs.getString("cidade"));
                  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return endereco;
        
    }
    
    public List<endereco> getAllEndereco(){
        
        List<endereco> enderecos = new ArrayList<endereco>();
        
        String sql = "select * from endereco";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                endereco endereco = new endereco();
                
                endereco.setEndereco_id(rs.getInt("endereco_id"));
                endereco.setNumero(rs.getInt("numero"));
                endereco.setLogadouro(rs.getString("logradouro"));
                endereco.setPais(rs.getString("pais"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCidade(rs.getString("cidade"));
                
                enderecos.add(endereco);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
               
        return enderecos;
        
    }
    
}
