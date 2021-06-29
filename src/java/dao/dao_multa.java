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
import model.multa;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_multa {
    
    private Connection conexao;
    
    public dao_multa(){
        
        conexao = Conexao.getConexao();

    }
    
    public void AddMulta(multa multa){
        
        String sql = "insert into multa(descricao, data_autuacao, gravidade, valor) values(?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, multa.getDescricao());
            preparedStatement.setDate(2, new java.sql.Date(multa.getData_autuacao().getTime()));
            preparedStatement.setString(3, multa.getGravidade());
            preparedStatement.setDouble(4, multa.getValor());
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {  
            e.printStackTrace();
        }
        
    }
    
    public void deleteMulta(int id_multa) {
        try {
                PreparedStatement preparedStatement = conexao
                                .prepareStatement("delete from multa where id_multa=?");
                // Parameters start with 1
                preparedStatement.setInt(1, id_multa);
                preparedStatement.executeUpdate();

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void updateMulta(multa multa) {
        try {
                PreparedStatement preparedStatement = conexao.prepareStatement("update multa set descricao=?, data_autuacao=?, gravidade=?, valor=? where id_multa=?");
                // Parameters start with 1
                preparedStatement.setString(1, multa.getDescricao());
                preparedStatement.setDate(2, new java.sql.Date(multa.getData_autuacao().getTime()));
                preparedStatement.setString(3, multa.getGravidade());
                preparedStatement.setDouble(4, multa.getValor());
                preparedStatement.setInt(5, multa.getId_multa());

                preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public multa getMulta(int id_multa) {
        multa multa = new multa();
        try {
                PreparedStatement preparedStatement = conexao.prepareStatement("select * from multa where id_multa=?");
                preparedStatement.setInt(1, id_multa);
                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    multa.setId_multa(rs.getInt("id_multa"));
                    multa.setDescricao(rs.getString("descricao"));
                    multa.setData_autuacao(rs.getDate("data_autuacao"));
                    multa.setGravidade(rs.getString("gravidade"));
                    multa.setValor(rs.getFloat("valor"));
                }
        } catch (SQLException e) {
                e.printStackTrace();
        }

        return multa;
    }
    
    public List<multa> getAllMultas() {
            List<multa> multas = new ArrayList<multa>();
            try {
                    Statement statement = conexao.createStatement();
                    ResultSet rs = statement.executeQuery("select * from multa");
                    while (rs.next()) {
                            multa multa = new multa();
                            multa.setId_multa(rs.getInt("id_multa"));
                            multa.setDescricao(rs.getString("descricao"));
                            multa.setData_autuacao(rs.getDate("data_autuacao"));
                            multa.setGravidade(rs.getString("gravidade"));
                            multa.setValor(rs.getFloat("valor"));
                            multas.add(multa);
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            
            return multas;
    }
    
}
