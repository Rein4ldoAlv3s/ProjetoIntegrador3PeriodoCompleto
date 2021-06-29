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
import model.motorista;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_motorista {
    
    private Connection conexao;
    
    public dao_motorista(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addMotorista(motorista motorista){
        
        String sql2 = "SELECT id_motorista FROM motorista ORDER BY id_motorista DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_motorista");
                aux++;
                motorista.setId_motorista(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into motorista(id_motorista,telefone,cpf,email,nome,registro_geral,data_nascimento,registro_cnh,categoria_cnh,data_validade,foto_url,endereco_id) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, motorista.getId_motorista());
            preparedStatement.setString(2, motorista.getTelefone());
            preparedStatement.setString(3, motorista.getCpf());
            preparedStatement.setString(4, motorista.getEmail());
            preparedStatement.setString(5, motorista.getNome());
            preparedStatement.setString(6, motorista.getRegistro_geral());
            preparedStatement.setDate(7, new java.sql.Date(motorista.getData_nascimento().getTime()));
            preparedStatement.setString(8, motorista.getRegistro_cnh());
            preparedStatement.setString(9, motorista.getCategoria_cnh());
            preparedStatement.setDate(10, new java.sql.Date(motorista.getData_validade_cnh().getTime()));
            preparedStatement.setString(11, motorista.getFoto_url());
            preparedStatement.setInt(12, motorista.getEndereco().getEndereco_id());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteMotorista(int id_motorista){
        
        String sql = "delete from motorista where id_motorista="+id_motorista;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateMotorista(motorista motorista){
        
        String sql = "update motorista set telefone=?,cpf=?,email=?,nome=?,registro_geral=?,data_nascimento=?,registro_cnh=?,categoria_cnh=?,data_validade=?,foto_url=?,endereco_id=? where id_motorista=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            
            preparedStatement.setString(1, motorista.getTelefone());
            preparedStatement.setString(2, motorista.getCpf());
            preparedStatement.setString(3, motorista.getEmail());
            preparedStatement.setString(4, motorista.getNome());
            preparedStatement.setString(5, motorista.getRegistro_geral());
            preparedStatement.setDate(6, new java.sql.Date(motorista.getData_nascimento().getTime()));
            preparedStatement.setString(7, motorista.getRegistro_cnh());
            preparedStatement.setString(8, motorista.getCategoria_cnh());
            preparedStatement.setDate(9, new java.sql.Date(motorista.getData_validade_cnh().getTime()));
            preparedStatement.setString(10, motorista.getFoto_url());
            preparedStatement.setInt(11, motorista.getEndereco().getEndereco_id());
            preparedStatement.setInt(12, motorista.getId_motorista());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public motorista getMotorista(int id_motorista){
        
        dao_endereco daoe = new dao_endereco();
        
        motorista motorista = new motorista();
        try {
            String sql = "select * from motorista where id_motorista="+id_motorista;
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                motorista.setId_motorista(rs.getInt("id_motorista"));
                motorista.setTelefone(rs.getString("telefone"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setEmail(rs.getString("email"));
                motorista.setNome(rs.getString("nome"));
                motorista.setRegistro_geral(rs.getString("registro_geral"));
                motorista.setData_nascimento(rs.getDate("data_nascimento"));
                motorista.setRegistro_cnh(rs.getString("registro_cnh"));
                motorista.setCategoria_cnh(rs.getString("categoria_cnh"));
                motorista.setData_validade_cnh(rs.getDate("data_validade"));
                motorista.setFoto_url(rs.getString("foto_url"));
                //Puxar o endereço
                endereco endereco = new endereco();
                endereco = daoe.getEndereco(rs.getInt("endereco_id"));
                motorista.setEndereco(endereco);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return motorista;
    }
    
    public motorista getMotoristaCPF(int id_motorista){
        
        dao_endereco daoe = new dao_endereco();
        
        motorista motorista = new motorista();
        try {
            String sql = "select * from motorista where cpf=?"+id_motorista;
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id_motorista);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                motorista.setId_motorista(rs.getInt("id_motorista"));
                motorista.setTelefone(rs.getString("telefone"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setEmail(rs.getString("email"));
                motorista.setNome(rs.getString("nome"));
                motorista.setRegistro_geral(rs.getString("registro_geral"));
                motorista.setData_nascimento(rs.getDate("data_nascimento"));
                motorista.setRegistro_cnh(rs.getString("registro_cnh"));
                motorista.setCategoria_cnh(rs.getString("categoria_cnh"));
                motorista.setData_validade_cnh(rs.getDate("data_validade"));
                motorista.setFoto_url(rs.getString("foto_url"));
                //Puxar o endereço
                endereco endereco = new endereco();
                endereco = daoe.getEndereco(rs.getInt("endereco_id"));
                motorista.setEndereco(endereco);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return motorista;
    }
    
    public List<motorista> getAllMotorista(){
        
        dao_endereco daoe = new dao_endereco();
        List<motorista> motoristas = new ArrayList<motorista>();
        
        String sql = "select * from motorista";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                motorista motorista = new motorista();
                motorista.setId_motorista(rs.getInt("id_motorista"));
                motorista.setTelefone(rs.getString("telefone"));
                motorista.setCpf(rs.getString("cpf"));
                motorista.setEmail(rs.getString("email"));
                motorista.setNome(rs.getString("nome"));
                motorista.setRegistro_geral(rs.getString("registro_geral"));
                motorista.setData_nascimento(rs.getDate("data_nascimento"));
                motorista.setRegistro_cnh(rs.getString("registro_cnh"));
                motorista.setCategoria_cnh(rs.getString("categoria_cnh"));
                motorista.setData_validade_cnh(rs.getDate("data_validade"));
                motorista.setFoto_url(rs.getString("foto_url"));
                //Puxar o endereço
                endereco endereco = new endereco();
                endereco = daoe.getEndereco(rs.getInt("endereco_id"));
                motorista.setEndereco(endereco);
                
                motoristas.add(motorista);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return motoristas;
        
    }
    
}
