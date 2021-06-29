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
import model.pessoa_fisica;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_pessoa_fisica {
    
    private Connection conexao;
    
    public dao_pessoa_fisica(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addPessoaFisica(pessoa_fisica pessoa){
        
        String sql2 = "SELECT id_cliente FROM pessoa_fisica ORDER BY id_cliente DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_cliente");
                aux++;
                pessoa.setId_cliente(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql = "insert into pessoa_fisica(id_cliente,nome,telefone,email,id_endereco,cpf_cnpj,registro_geral) values(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,pessoa.getId_cliente());
            preparedStatement.setString(2,pessoa.getNome());
            preparedStatement.setString(3,pessoa.getTelefone());
            preparedStatement.setString(4,pessoa.getEmail());
            preparedStatement.setInt(5,pessoa.getEndereco().getEndereco_id());
            preparedStatement.setString(6,pessoa.getCpf_cnpj());
            preparedStatement.setString(7,pessoa.getRegistro_geral());
            
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deletePessoaFisicaID(int id){
        
        String sql = "delete from pessoa_fisica where id_cliente=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePessoaFisicaNome(String nome){
        
        String sql = "delete from pessoa_fisica where nome=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deletePessoaFisicaCpf(String cpf){
        
        String sql = "delete from pessoa_fisica where cpf_cnpj=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, cpf);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarPessoaFisica(pessoa_fisica pessoa, String cpf){
        
        String sql = "update pessoa_fisica set nome=?,telefone=?,email=?,id_endereco=?,registro_geral=?,cpf_cnpj=? where cpf_cnpj=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1,pessoa.getNome());
            preparedStatement.setString(2,pessoa.getTelefone());
            preparedStatement.setString(3,pessoa.getEmail());
            preparedStatement.setInt(4,pessoa.getEndereco().getEndereco_id());
            preparedStatement.setString(5,pessoa.getRegistro_geral());
            preparedStatement.setString(6,pessoa.getCpf_cnpj());
            preparedStatement.setString(7, cpf);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public pessoa_fisica getPessoaFisicaCPF(String cpf){
        
        pessoa_fisica pessoa = new pessoa_fisica();
        dao_endereco daoendereco = new dao_endereco();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_fisica where cpf_cnpj=?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRegistro_geral(rs.getString("registro_geral"));
                
                //Cria um objeto endere�o
                endereco endereco = new endereco();
                //Puxa o endereco usando a dao dele
                endereco = daoendereco.getEndereco(rs.getInt("id_endereco"));
                //Joga o endereo�o puxado pra dentro de pessoa
                pessoa.setEndereco(endereco);     
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pessoa;
        
    }
    
    public pessoa_fisica getPessoaFisicaID(int id){
        
        pessoa_fisica pessoa = new pessoa_fisica();
        dao_endereco daoendereco = new dao_endereco();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_fisica where id_cliente=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRegistro_geral(rs.getString("registro_geral"));
                
                //Cria um objeto endere�o
                endereco endereco = new endereco();
                //Puxa o endereco usando a dao dele
                endereco = daoendereco.getEndereco(rs.getInt("id_endereco"));
                //Joga o endereo�o puxado pra dentro de pessoa
                pessoa.setEndereco(endereco);     
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pessoa;
        
    }
    
    public pessoa_fisica getPessoaFisicaNome(String nome){
        
        
        pessoa_fisica pessoa = new pessoa_fisica();
        dao_endereco daoendereco = new dao_endereco();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_fisica where nome=?");
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRegistro_geral(rs.getString("registro_geral"));
                
                //Cria um objeto endere�o
                endereco endereco = new endereco();
                //Puxa o endereco usando a dao dele
                endereco = daoendereco.getEndereco(rs.getInt("id_endereco"));
                //Jogao endereo�o puxado pra dentro de pessoa
                pessoa.setEndereco(endereco);     
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pessoa;
        
    }
    
    public List<pessoa_fisica> getAllPessoa(){
        
        List<pessoa_fisica> pessoas = new ArrayList<pessoa_fisica>();
        dao_endereco daoendereco = new dao_endereco();
        String sql = "select * from pessoa_fisica";

        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                pessoa_fisica pessoa = new pessoa_fisica();
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRegistro_geral(rs.getString("registro_geral"));
                
                //Cria um objeto endere�o
                endereco endereco = new endereco();
                //Puxa o endereco usando a dao dele
                endereco = daoendereco.getEndereco(rs.getInt("id_endereco"));
                //Jogao endereo�o puxado pra dentro de pessoa
                pessoa.setEndereco(endereco);     
                
                pessoas.add(pessoa);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pessoas;
    }
    
}
