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
import model.modelo;
import model.veiculo;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_veiculo {
    
    private Connection conexao;
    
    public dao_veiculo(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addVeiculo(veiculo veiculo){
        
        String sql = "insert into veiculo(placa, tipo, renavam, status, preco_compra, preco_venda, cor, qntd_passageiros, capacidade_tanque, ano_fabricacao, ano_modelo, tipo_combustivel, quilometragem, veiculo_id_categoria, veiculo_id_modelo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1,veiculo.getPlaca());
            preparedStatement.setString(2,veiculo.getTipo());
            preparedStatement.setString(3,veiculo.getRenavam());
            preparedStatement.setBoolean(4,veiculo.isStatus());
            preparedStatement.setDouble(5,veiculo.getPreco_compra());
            preparedStatement.setDouble(6,veiculo.getPreco_venda());
            preparedStatement.setString(7,veiculo.getCor());
            preparedStatement.setInt(8,veiculo.getQnt_passageiros());
            preparedStatement.setInt(9, veiculo.getCapacidade_tanque());
            preparedStatement.setInt(10, veiculo.getAno_fabricacao());
            preparedStatement.setInt(11, veiculo.getAno_modelo());
            preparedStatement.setString(12, veiculo.getTipo_combustivel());
            preparedStatement.setInt(13, veiculo.getQuilometragem());
            preparedStatement.setInt(14, veiculo.getCategoria().getId_categoria());
            preparedStatement.setInt(15, veiculo.getModelo().getId_modelo());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteVeiculo(String placa){
        
        String sql = "delete from veiculo where placa=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setString(1, placa);
                        
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateVeiculo(String placa, veiculo veiculo){
        
        String sql ="update veiculo set tipo=?, renavam=?, status=?, preco_compra=?, preco_venda=?, cor=?, qntd_passageiros=?, capacidade_tanque=?, ano_fabricacao=?, ano_modelo=?, tipo_combustivel=?, quilometragem=?, veiculo_id_categoria=?, veiculo_id_modelo=? where placa = ?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters start with 1
            preparedStatement.setString(1,veiculo.getTipo());
            preparedStatement.setString(2,veiculo.getRenavam());
            preparedStatement.setBoolean(3,veiculo.isStatus());
            preparedStatement.setDouble(4,veiculo.getPreco_compra());
            preparedStatement.setDouble(5,veiculo.getPreco_venda());
            preparedStatement.setString(6,veiculo.getCor());
            preparedStatement.setInt(7,veiculo.getQnt_passageiros());
            preparedStatement.setInt(8, veiculo.getCapacidade_tanque());
            preparedStatement.setInt(9, veiculo.getAno_fabricacao());
            preparedStatement.setInt(10, veiculo.getAno_modelo());
            preparedStatement.setString(11, veiculo.getTipo_combustivel());
            preparedStatement.setInt(12, veiculo.getQuilometragem());
            preparedStatement.setInt(13, veiculo.getCategoria().getId_categoria());
            preparedStatement.setInt(14, veiculo.getModelo().getId_modelo());
            preparedStatement.setString(15, placa);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public veiculo getVeiculo(String placa){
        
        veiculo veiculo = new veiculo();
        dao_categoria dao = new dao_categoria();
        dao_modelo daom = new dao_modelo();
        dao_marca daomarca = new dao_marca();
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement("select * from veiculo where placa=?");
            preparedStatement.setString(1, placa);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setStatus(rs.getBoolean("status"));
                veiculo.setPreco_compra(rs.getDouble("preco_compra"));
                veiculo.setPreco_venda(rs.getDouble("preco_venda"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setQnt_passageiros(rs.getInt("qntd_passageiros"));
                veiculo.setCapacidade_tanque(rs.getInt("capacidade_tanque"));
                veiculo.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setAno_modelo(rs.getInt("ano_modelo"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                //Cria um objeto categoria null
                categoria categoria = new categoria();
                //Usa a Dao da Categoria com base no ID contido no banco do veiculo pra preencher a descrição da categoria do Objeto Veiculo
                categoria = dao.getCategoria(rs.getInt("veiculo_id_categoria"));
                //Adiciona a categoria no veículo com sua descrição e tudo mais
                veiculo.setCategoria(categoria);
                
                //Cria um objeto modelo null
                modelo modelo = new modelo();
                //Usa a Dao do modelo com base no ID contido no banco do veiculo pra preencher a descrição do modelo do Objeto Veiculo
                modelo = daom.getModelo(rs.getInt("veiculo_id_modelo"));
                //Adiciona a categoria no veículo           
                veiculo.setModelo(modelo);          
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return veiculo;
        
    }
    
    public List<veiculo> getAllVeiculos(){
        
        List<veiculo> veiculos = new ArrayList<veiculo>();
        String sql = "select * from veiculo";
        dao_categoria dao = new dao_categoria();
        dao_modelo daom = new dao_modelo();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                veiculo veiculo = new veiculo();
                
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setStatus(rs.getBoolean("status"));
                veiculo.setPreco_compra(rs.getDouble("preco_compra"));
                veiculo.setPreco_venda(rs.getDouble("preco_venda"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setQnt_passageiros(rs.getInt("qntd_passageiros"));
                veiculo.setCapacidade_tanque(rs.getInt("capacidade_tanque"));
                veiculo.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setAno_modelo(rs.getInt("ano_modelo"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                //Cria um objeto categoria null
                categoria categoria = new categoria();
                //Usa a Dao da Categoria com base no ID contido no banco do veiculo pra preencher a descrição da categoria do Objeto Veiculo
                categoria = dao.getCategoria(rs.getInt("veiculo_id_categoria"));
                //Adiciona a categoria no veículo com sua descrição e tudo mais
                veiculo.setCategoria(categoria);
                
                //Cria um objeto modelo null
                modelo modelo = new modelo();
                //Usa a Dao do modelo com base no ID contido no banco do veiculo pra preencher a descrição do modelo do Objeto Veiculo
                modelo = daom.getModelo(rs.getInt("veiculo_id_modelo"));
                //Adiciona a categoria no veículo
                veiculo.setModelo(modelo);
                
                veiculos.add(veiculo);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
    
    public List<veiculo> getAllVeiculosDisponiveis(){
        
        List<veiculo> veiculos = new ArrayList<veiculo>();
        String sql = "select * from veiculo where status='t'";
        dao_categoria dao = new dao_categoria();
        dao_modelo daom = new dao_modelo();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                veiculo veiculo = new veiculo();
                
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setStatus(rs.getBoolean("status"));
                veiculo.setPreco_compra(rs.getDouble("preco_compra"));
                veiculo.setPreco_venda(rs.getDouble("preco_venda"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setQnt_passageiros(rs.getInt("qntd_passageiros"));
                veiculo.setCapacidade_tanque(rs.getInt("capacidade_tanque"));
                veiculo.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setAno_modelo(rs.getInt("ano_modelo"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                //Cria um objeto categoria null
                categoria categoria = new categoria();
                //Usa a Dao da Categoria com base no ID contido no banco do veiculo pra preencher a descrição da categoria do Objeto Veiculo
                categoria = dao.getCategoria(rs.getInt("veiculo_id_categoria"));
                //Adiciona a categoria no veículo com sua descrição e tudo mais
                veiculo.setCategoria(categoria);
                
                //Cria um objeto modelo null
                modelo modelo = new modelo();
                //Usa a Dao do modelo com base no ID contido no banco do veiculo pra preencher a descrição do modelo do Objeto Veiculo
                modelo = daom.getModelo(rs.getInt("veiculo_id_modelo"));
                //Adiciona a categoria no veículo
                veiculo.setModelo(modelo);
                
                veiculos.add(veiculo);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
    
    public List<veiculo> getAllVeiculosPorCategoria(int id_categoria){
        
        List<veiculo> veiculos = new ArrayList<veiculo>();
        String sql = "select * from veiculo where veiculo_id_categoria="+id_categoria;
        dao_categoria dao = new dao_categoria();
        dao_modelo daom = new dao_modelo();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                veiculo veiculo = new veiculo();
                
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setStatus(rs.getBoolean("status"));
                veiculo.setPreco_compra(rs.getDouble("preco_compra"));
                veiculo.setPreco_venda(rs.getDouble("preco_venda"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setQnt_passageiros(rs.getInt("qntd_passageiros"));
                veiculo.setCapacidade_tanque(rs.getInt("capacidade_tanque"));
                veiculo.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setAno_modelo(rs.getInt("ano_modelo"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                //Cria um objeto categoria null
                categoria categoria = new categoria();
                //Usa a Dao da Categoria com base no ID contido no banco do veiculo pra preencher a descrição da categoria do Objeto Veiculo
                categoria = dao.getCategoria(rs.getInt("veiculo_id_categoria"));
                //Adiciona a categoria no veículo com sua descrição e tudo mais
                veiculo.setCategoria(categoria);
                
                //Cria um objeto modelo null
                modelo modelo = new modelo();
                //Usa a Dao do modelo com base no ID contido no banco do veiculo pra preencher a descrição do modelo do Objeto Veiculo
                modelo = daom.getModelo(rs.getInt("veiculo_id_modelo"));
                //Adiciona a categoria no veículo
                veiculo.setModelo(modelo);
                
                veiculos.add(veiculo);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
    
    public List<veiculo> getAllVeiculosModelo(int id_modelo){
        
        List<veiculo> veiculos = new ArrayList<veiculo>();
        String sql = "select * from veiculo where veiculo_id_modelo="+id_modelo;
        dao_categoria dao = new dao_categoria();
        dao_modelo daom = new dao_modelo();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                veiculo veiculo = new veiculo();
                
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setStatus(rs.getBoolean("status"));
                veiculo.setPreco_compra(rs.getDouble("preco_compra"));
                veiculo.setPreco_venda(rs.getDouble("preco_venda"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setQnt_passageiros(rs.getInt("qntd_passageiros"));
                veiculo.setCapacidade_tanque(rs.getInt("capacidade_tanque"));
                veiculo.setAno_fabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setAno_modelo(rs.getInt("ano_modelo"));
                veiculo.setTipo_combustivel(rs.getString("tipo_combustivel"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                //Cria um objeto categoria null
                categoria categoria = new categoria();
                //Usa a Dao da Categoria com base no ID contido no banco do veiculo pra preencher a descrição da categoria do Objeto Veiculo
                categoria = dao.getCategoria(rs.getInt("veiculo_id_categoria"));
                //Adiciona a categoria no veículo com sua descrição e tudo mais
                veiculo.setCategoria(categoria);
                
                //Cria um objeto modelo null
                modelo modelo = new modelo();
                //Usa a Dao do modelo com base no ID contido no banco do veiculo pra preencher a descrição do modelo do Objeto Veiculo
                modelo = daom.getModelo(rs.getInt("veiculo_id_modelo"));
                //Adiciona a categoria no veículo
                veiculo.setModelo(modelo);
                
                veiculos.add(veiculo);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }
    
        public void alugarCarro(String placa){
        
        String sql = "update veiculo set status='f' where placa="+placa;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}
