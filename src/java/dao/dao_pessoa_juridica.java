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
import model.pessoa_juridica;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_pessoa_juridica {
    
    private Connection conexao;
    
    public dao_pessoa_juridica(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addPessoaJuridica(pessoa_juridica pessoa){
        
        String sql2 = "SELECT id_cliente FROM pessoa_juridica ORDER BY id_cliente DESC LIMIT 1";
        
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
        
        String sql = "insert into pessoa_juridica(id_cliente,nome,telefone,email,id_endereco,cpf_cnpj,razao_social) values(?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,pessoa.getId_cliente());
            preparedStatement.setString(2,pessoa.getNome());
            preparedStatement.setString(3,pessoa.getTelefone());
            preparedStatement.setString(4,pessoa.getEmail());
            preparedStatement.setInt(5,pessoa.getEndereco().getEndereco_id());
            preparedStatement.setString(6,pessoa.getCpf_cnpj());
            preparedStatement.setString(7,pessoa.getRazao_social());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deletePessoaJuridicaCNPJ(String cnpj){
        
        String sql = "delete from pessoa_juridica where cpf_cnpj="+cnpj;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarPessoaJuridicaCNPJ(pessoa_juridica pessoa, String cnpj){
        
        String sql = "update pessoa_juridica set nome=?,telefone=?,email=?,id_endereco=?,razao_social=?,cpf_cnpj=? where cpf_cnpj=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1,pessoa.getNome());
            preparedStatement.setString(2,pessoa.getTelefone());
            preparedStatement.setString(3,pessoa.getEmail());
            preparedStatement.setInt(4,pessoa.getEndereco().getEndereco_id());
            preparedStatement.setString(5,pessoa.getRazao_social());
            preparedStatement.setString(6,pessoa.getCpf_cnpj());
            preparedStatement.setString(7, cnpj);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public pessoa_juridica getPessoaJuridicaCNPJ(String cnpj){
        
        pessoa_juridica pessoa = new pessoa_juridica();
        dao_endereco daoendereco = new dao_endereco();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_juridica where cpf_cnpj=?");
            preparedStatement.setString(1, cnpj);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRazao_social(rs.getString("razao_social"));
                
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
    
    public pessoa_juridica getPessoaJuridicaID(int id){
        
        pessoa_juridica pessoa = new pessoa_juridica();
        dao_endereco daoendereco = new dao_endereco();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_juridica where id_cliente=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRazao_social(rs.getString("razao_social"));
                
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
    
    public pessoa_juridica getPessoaJuridicaRazaoSocial(String razao_social){
        
        pessoa_juridica pessoa = new pessoa_juridica();
        dao_endereco daoendereco = new dao_endereco();
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from pessoa_juridica where razao_social=?");
            preparedStatement.setString(1, razao_social);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRazao_social(rs.getString("razao_social"));
                
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
    
    public List<pessoa_juridica> getAllPessoaJuridica(){
        
        List<pessoa_juridica> pessoas = new ArrayList<pessoa_juridica>();
        
        dao_endereco daoendereco = new dao_endereco();
        String sql = "select * from pessoa_juridica";

        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                pessoa_juridica pessoa = new pessoa_juridica();
                
                pessoa.setId_cliente(rs.getInt("id_cliente"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setEmail(rs.getString("email"));
                pessoa.setCpf_cnpj(rs.getString("cpf_cnpj"));
                pessoa.setRazao_social(rs.getString("razao_social"));
                
                //Cria um objeto endere�o
                endereco endereco = new endereco();
                //Puxa o endereco usando a dao dele
                endereco = daoendereco.getEndereco(rs.getInt("id_endereco"));
                //Joga o endereo�o puxado pra dentro de pessoa
                pessoa.setEndereco(endereco);  
                
                pessoas.add(pessoa);
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pessoas;
    }
    
}
