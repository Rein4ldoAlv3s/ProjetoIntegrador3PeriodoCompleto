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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import model.acessorio;
import model.apolice;
import model.locacao;
import model.motorista;
import model.multa;
import model.pessoa_fisica;
import model.pessoa_juridica;
import model.veiculo;
import model.vistoria;
import util.Conexao;

/**
 *
 * @author joaov
 */
public class dao_locacao {
    
    double valorFinalAtual;
    double valorFinalAtualizado;
    
    private Connection conexao;
    
    public dao_locacao(){
        
        conexao = Conexao.getConexao();

    }
    
    public void addLocacaoPessoaFisica(locacao locacao){
        
        String sql3 = "SELECT id_locacao FROM locacao ORDER BY id_locacao DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql3);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_locacao");
                aux++;
                locacao.setId_locacao(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Definindo o valor do seguro com base no pre�o de compra do ve�culo, 10% do valor
        dao_veiculo dao_veiculo = new dao_veiculo(); //USAR A DAO PRA PUXAR O VEICULO PARA VER O VALOR DE COMPRA
        veiculo veiculo = new veiculo();
        veiculo = dao_veiculo.getVeiculo(locacao.getVeiculo().getPlaca()); //PUXA AQUI COM BASE NA PLACA SALVA NA LOCA��O
        double valorSeguro = veiculo.getPreco_compra() * 0.01;
        locacao.setValor_seguro(valorSeguro);
        
        //Pegando a quantidade de dias que foi alugado para estimar o valor
        Date firstDate = new java.sql.Date(locacao.getData_retirada().getTime());
        Date secondDate = new java.sql.Date(locacao.getData_devolucao().getTime());
        
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diferenca = time.convert(diff, TimeUnit.MILLISECONDS); 
        int diferencaDias = (int) diferenca; //essa vari�vel contem o numero de dias que o carro foi alugado.
        
        //Calculando o valor das diarias multiplicado pela quantidade de dias e somando o valor da apolice
        double valorDiarias = diferencaDias * veiculo.getCategoria().getValor_diaria();
        
        dao_apolice dao_apolice = new dao_apolice(); //USAR A DAO PRA PUXAR O VALOR DA APOLICE
        apolice apolice = new apolice();
        apolice = dao_apolice.getApolice(locacao.getApolice().getId_apolice()); //PUXA AQUI COM BASE NO ID DA APOLICE NA LOCACA��O
        double valorFinal = valorDiarias + apolice.getValor() + locacao.getValor_seguro();
        
        locacao.setValor_final(valorFinal);
        
        //organizando os valores de cal��o, apolice e etc.
        locacao.setValor_calcao(locacao.getValor_final()*2.5);
        
        String sql = "insert into locacao(id_locacao,data_retirada,data_devolucao,valor_seguro,valor_calcao,valor_final,status,id_motorista,id_vistoria,id_fisico,id_apolice,placa_veiculo) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,locacao.getId_locacao());
            preparedStatement.setDate(2,new java.sql.Date(locacao.getData_retirada().getTime()));
            preparedStatement.setDate(3,new java.sql.Date(locacao.getData_devolucao().getTime()));
            preparedStatement.setDouble(4,locacao.getValor_seguro());
            preparedStatement.setDouble(5,locacao.getValor_calcao());
            preparedStatement.setDouble(6,locacao.getValor_final());
            preparedStatement.setBoolean(7,locacao.isStatus());
            preparedStatement.setInt(8,locacao.getMotorista().getId_motorista());
            preparedStatement.setInt(9,locacao.getVistoria().getId_vistoria());
            preparedStatement.setInt(10, locacao.getFisica().getId_cliente());
            preparedStatement.setInt(11,locacao.getApolice().getId_apolice());
            preparedStatement.setString(12,locacao.getVeiculo().getPlaca());

            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql2="update veiculo set status='f' where placa=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            preparedStatement.setString(1, locacao.getVeiculo().getPlaca());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addLocacaoPessoaJuridica(locacao locacao){
        
        String sql3 = "SELECT id_locacao FROM locacao ORDER BY id_locacao DESC LIMIT 1";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql3);
            
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int aux = rs.getInt("id_locacao");
                aux++;
                locacao.setId_locacao(aux);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //Definindo o valor do seguro com base no pre�o de compra do ve�culo, 10% do valor
        dao_veiculo dao_veiculo = new dao_veiculo(); //USAR A DAO PRA PUXAR O VEICULO PARA VER O VALOR DE COMPRA
        veiculo veiculo = new veiculo();
        veiculo = dao_veiculo.getVeiculo(locacao.getVeiculo().getPlaca()); //PUXA AQUI COM BASE NA PLACA SALVA NA LOCA��O
        double valorSeguro = veiculo.getPreco_compra() * 0.01;
        locacao.setValor_seguro(valorSeguro);
        
        //Pegando a quantidade de dias que foi alugado para estimar o valor
        Date firstDate = new java.sql.Date(locacao.getData_retirada().getTime());
        Date secondDate = new java.sql.Date(locacao.getData_devolucao().getTime());
        
        long diff = secondDate.getTime() - firstDate.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long diferenca = time.convert(diff, TimeUnit.MILLISECONDS); 
        int diferencaDias = (int) diferenca; //essa vari�vel contem o numero de dias que o carro foi alugado.
        
        //Calculando o valor das diarias multiplicado pela quantidade de dias e somando o valor da apolice
        double valorDiarias = diferencaDias * veiculo.getCategoria().getValor_diaria();
        
        dao_apolice dao_apolice = new dao_apolice(); //USAR A DAO PRA PUXAR O VALOR DA APOLICE
        apolice apolice = new apolice();
        apolice = dao_apolice.getApolice(locacao.getApolice().getId_apolice()); //PUXA AQUI COM BASE NO ID DA APOLICE NA LOCACA��O
        double valorFinal = valorDiarias + locacao.getApolice().getValor() + locacao.getValor_seguro();
        
        locacao.setValor_final(valorFinal);
        
        //organizando os valores de cal��o, apolice e etc.
        locacao.setValor_calcao(locacao.getValor_final()*2.5);
        
        String sql = "insert into locacao(id_locacao,data_retirada,data_devolucao,valor_seguro,valor_calcao,valor_final,status,id_motorista,id_vistoria,id_juridico,id_apolice,placa_veiculo) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1,locacao.getId_locacao());
            preparedStatement.setDate(2,new java.sql.Date(locacao.getData_retirada().getTime()));
            preparedStatement.setDate(3,new java.sql.Date(locacao.getData_devolucao().getTime()));
            preparedStatement.setDouble(4,locacao.getValor_seguro());
            preparedStatement.setDouble(5,locacao.getValor_calcao());
            preparedStatement.setDouble(6,locacao.getValor_final());
            preparedStatement.setBoolean(7,locacao.isStatus());
            preparedStatement.setInt(8,locacao.getMotorista().getId_motorista());
            preparedStatement.setInt(9,locacao.getVistoria().getId_vistoria());
            preparedStatement.setInt(10, locacao.getJuridica().getId_cliente());
            preparedStatement.setInt(11,locacao.getApolice().getId_apolice());
            preparedStatement.setString(12,locacao.getVeiculo().getPlaca());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql2="update veiculo set status='f' where placa=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            preparedStatement.setString(1, locacao.getVeiculo().getPlaca());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void deleteLocacao(int id_locacao){
        
        String sql = "delete from locacao where id_locacao="+id_locacao;
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateLocacao(locacao locacao){
        
        String sql = "update locacao set data_retirada=?,data_devolucao=?,valor_seguro=?,valor_calcao=?,valor_final=?,status=?,placa_veiculo=?,id_motorista=?,id_vistoria=?,id_apolice=? where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
           
            preparedStatement.setDate(1,new java.sql.Date(locacao.getData_retirada().getTime()));
            preparedStatement.setDate(2,new java.sql.Date(locacao.getData_devolucao().getTime()));
            preparedStatement.setDouble(3,locacao.getValor_seguro());
            preparedStatement.setDouble(4,locacao.getValor_calcao());
            preparedStatement.setDouble(5,locacao.getValor_final());
            preparedStatement.setBoolean(6,locacao.isStatus());
            preparedStatement.setString(7,locacao.getVeiculo().getPlaca());
            preparedStatement.setInt(8,locacao.getMotorista().getId_motorista());
            preparedStatement.setInt(9,locacao.getVistoria().getId_vistoria());
            preparedStatement.setInt(10,locacao.getApolice().getId_apolice());
            preparedStatement.setInt(11,locacao.getId_locacao());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public locacao getLocacao(int id_locacao){
        
        //DECLARAÇÃO DAS DAOS NECESSÁRIAS PARA PREENCHER OS OBJETOS QUE ESTÃO CONTIDOS NA LOCAÇÃO
        dao_veiculo dao_veiculo = new dao_veiculo();
        dao_motorista dao_motorista = new dao_motorista();
        dao_vistoria dao_vistoria = new dao_vistoria();
        dao_pessoa_juridica dao_juridica = new dao_pessoa_juridica();
        dao_pessoa_fisica dao_fisica = new dao_pessoa_fisica();
        dao_apolice dao_apolice = new dao_apolice();
        dao_multa dao_multa = new dao_multa();
        dao_acessorio dao_acessorio = new dao_acessorio();
        locacao locacao = new locacao();
        
        try {
            String sql = "select * from locacao where id_locacao="+id_locacao;

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                
                locacao.setId_locacao(rs.getInt("id_locacao"));
                locacao.setData_retirada(rs.getDate("data_retirada"));
                locacao.setData_devolucao(rs.getDate("data_devolucao"));
                locacao.setValor_seguro(rs.getDouble("valor_seguro"));
                locacao.setValor_calcao(rs.getDouble("valor_calcao"));
                locacao.setValor_final(rs.getDouble("valor_final"));
                locacao.setStatus(rs.getBoolean("status"));
                
                //COMEÇA AQUI A CHAMAR DAOS E MODELS QUE ADMINISTRAM VÁRIAS TABELAS PARA PREENCHER O OBJETO LOCAÇÃO DA FORMA ADEQUEADA.
                //O RELACIONAMENTO É FEITO AQUI
                
                //PEGANDO O ID QUE TÁ NO BANCO NA TABELA LOCAÇÃO E PREENCHENDO NO OBJETO LOCAÇÃO USANDO A DAO DO OBJETO
                
                //PARA VEICULO
                veiculo veiculo = new veiculo();
                veiculo = dao_veiculo.getVeiculo(rs.getString("placa_veiculo"));
                locacao.setVeiculo(veiculo);
                
                //PARA MOTORISTA
                motorista motorista = new motorista();
                motorista = dao_motorista.getMotorista(rs.getInt("id_motorista"));
                locacao.setMotorista(motorista);
                
                //PARA VISTORIA
                vistoria vistoria = new vistoria();
                vistoria = dao_vistoria.getVistoria(rs.getInt("id_vistoria"));
                locacao.setVistoria(vistoria);
                
                
                //PARA APOLICE
                apolice apolice = new apolice();
                apolice = dao_apolice.getApolice(rs.getInt("id_apolice"));
                locacao.setApolice(apolice);
                
                //PARA MULTA
                multa multa = new multa();
                multa = dao_multa.getMulta(rs.getInt("id_multa"));
                locacao.setMulta(multa);
                
                //TENTANDO CAPTURAR PESSOA F�SICA
                pessoa_fisica fisica = new pessoa_fisica();
                fisica = dao_fisica.getPessoaFisicaID(rs.getInt("id_fisico"));
                locacao.setFisica(fisica);
                
                
                //TENTANDO CAPTURAR PESSOA JURIDICA
                pessoa_juridica juridica = new pessoa_juridica();
                juridica = dao_juridica.getPessoaJuridicaID(rs.getInt("id_juridico"));
                locacao.setJuridica(juridica);
                
                
                //TENTANDO PEGAR ACESS�RIO
                acessorio acessorio = new acessorio();
                acessorio = dao_acessorio.getAcessorio(rs.getInt("id_acessorio"));
                
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locacao;
    }
    
    public List<locacao> getAllLocacao(){
        
        List<locacao> locacoes = new ArrayList<locacao>();
        String sql = "select * from locacao";
        //DECLARAÇÃO DAS DAOS NECESSÁRIAS PARA PREENCHER OS OBJETOS QUE ESTÃO CONTIDOS NA LOCAÇÃO
        dao_veiculo dao_veiculo = new dao_veiculo();
        dao_motorista dao_motorista = new dao_motorista();
        dao_vistoria dao_vistoria = new dao_vistoria();
        dao_apolice dao_apolice = new dao_apolice();
        dao_multa dao_multa = new dao_multa();
        dao_pessoa_juridica dao_juridica = new dao_pessoa_juridica();
        dao_pessoa_fisica dao_fisica = new dao_pessoa_fisica();
        dao_acessorio dao_acessorio = new dao_acessorio();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                locacao locacao = new locacao();
                
                locacao.setId_locacao(rs.getInt("id_locacao"));
                locacao.setData_retirada(rs.getDate("data_retirada"));
                locacao.setData_devolucao(rs.getDate("data_devolucao"));
                locacao.setValor_seguro(rs.getDouble("valor_seguro"));
                locacao.setValor_calcao(rs.getDouble("valor_calcao"));
                locacao.setValor_final(rs.getDouble("valor_final"));
                locacao.setStatus(rs.getBoolean("status"));
                
                //COMEÇA AQUI A CHAMAR DAOS E MODELS QUE ADMINISTRAM VÁRIAS TABELAS PARA PREENCHER O OBJETO LOCAÇÃO DA FORMA ADEQUEADA.
                //O RELACIONAMENTO É FEITO AQUI
                
                //PEGANDO O ID QUE TÁ NO BANCO NA TABELA LOCAÇÃO E PREENCHENDO NO OBJETO LOCAÇÃO USANDO A DAO DO OBJETO
                
                //PARA VEICULO
                veiculo veiculo = new veiculo();
                veiculo = dao_veiculo.getVeiculo(rs.getString("placa_veiculo"));
                locacao.setVeiculo(veiculo);
                
                //PARA MOTORISTA
                motorista motorista = new motorista();
                motorista = dao_motorista.getMotorista(rs.getInt("id_motorista"));
                locacao.setMotorista(motorista);
                
                //PARA VISTORIA
                vistoria vistoria = new vistoria();
                vistoria = dao_vistoria.getVistoria(rs.getInt("id_vistoria"));
                locacao.setVistoria(vistoria);
                
                //PARA CLIENTE
                //PULADO PQ FALTA O REINALDO TERMINAR
                
                //PARA APOLICE
                apolice apolice = new apolice();
                apolice = dao_apolice.getApolice(rs.getInt("id_apolice"));
                locacao.setApolice(apolice);
                
                //PARA MULTA
                multa multa = new multa();
                //HÁ ESSE TESTE AQUI PQ MULTA NÃO É OBRIGATÓRIO TER TODA VEZ, ENTÃO PODE SER UM VALOR NULO;
                Integer aux = rs.getInt("id_multa");
                if(aux!=null){
                    multa = dao_multa.getMulta(rs.getInt("id_multa"));
                    
                }else multa=null;
                locacao.setMulta(multa);
                
                //TENTANDO CAPTURAR PESSOA F�SICA
                pessoa_fisica fisica = new pessoa_fisica();
                Integer idf = rs.getInt("id_fisico");
                if(idf!=null){
                    fisica = dao_fisica.getPessoaFisicaID(rs.getInt(idf));
                    locacao.setFisica(fisica);
                }
                
                //TENTANDO CAPTURAR PESSOA JURIDICA
                pessoa_juridica juridica = new pessoa_juridica();
                Integer idj = rs.getInt("id_juridico");
                if(idj!=null){
                    juridica = dao_juridica.getPessoaJuridicaID(idj);
                    locacao.setJuridica(juridica);
                }
                
                //TENTANDO PEGAR ACESS�RIO
                acessorio acessorio = new acessorio();
                Integer ida = rs.getInt("id_acessorio");
                if(ida!=null){
                    acessorio = dao_acessorio.getAcessorio(ida);
                    locacao.setAcessorio(acessorio);
                }
                
                locacoes.add(locacao);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locacoes;
    }
    
    public List<locacao> getAllLocacaoMultadas(){
        
        List<locacao> locacoes = new ArrayList<locacao>();
        String sql = "select * from locacao where id_multa IS NOT NULL";
        //DECLARAÇÃO DAS DAOS NECESSÁRIAS PARA PREENCHER OS OBJETOS QUE ESTÃO CONTIDOS NA LOCAÇÃO
        dao_veiculo dao_veiculo = new dao_veiculo();
        dao_motorista dao_motorista = new dao_motorista();
        dao_vistoria dao_vistoria = new dao_vistoria();
        dao_apolice dao_apolice = new dao_apolice();
        dao_multa dao_multa = new dao_multa();
        dao_pessoa_juridica dao_juridica = new dao_pessoa_juridica();
        dao_pessoa_fisica dao_fisica = new dao_pessoa_fisica();
        dao_acessorio dao_acessorio = new dao_acessorio();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                locacao locacao = new locacao();
                
                locacao.setId_locacao(rs.getInt("id_locacao"));
                locacao.setData_retirada(rs.getDate("data_retirada"));
                locacao.setData_devolucao(rs.getDate("data_devolucao"));
                locacao.setValor_seguro(rs.getDouble("valor_seguro"));
                locacao.setValor_calcao(rs.getDouble("valor_calcao"));
                locacao.setValor_final(rs.getDouble("valor_final"));
                locacao.setStatus(rs.getBoolean("status"));
                
                //COMEÇA AQUI A CHAMAR DAOS E MODELS QUE ADMINISTRAM VÁRIAS TABELAS PARA PREENCHER O OBJETO LOCAÇÃO DA FORMA ADEQUEADA.
                //O RELACIONAMENTO É FEITO AQUI
                
                //PEGANDO O ID QUE TÁ NO BANCO NA TABELA LOCAÇÃO E PREENCHENDO NO OBJETO LOCAÇÃO USANDO A DAO DO OBJETO
                
                //PARA VEICULO
                veiculo veiculo = new veiculo();
                veiculo = dao_veiculo.getVeiculo(rs.getString("placa_veiculo"));
                locacao.setVeiculo(veiculo);
                
                //PARA MOTORISTA
                motorista motorista = new motorista();
                motorista = dao_motorista.getMotorista(rs.getInt("id_motorista"));
                locacao.setMotorista(motorista);
                
                //PARA VISTORIA
                vistoria vistoria = new vistoria();
                vistoria = dao_vistoria.getVistoria(rs.getInt("id_vistoria"));
                locacao.setVistoria(vistoria);
                
                //PARA CLIENTE
                //PULADO PQ FALTA O REINALDO TERMINAR
                
                //PARA APOLICE
                apolice apolice = new apolice();
                apolice = dao_apolice.getApolice(rs.getInt("id_apolice"));
                locacao.setApolice(apolice);
                
                //PARA MULTA
                multa multa = new multa();
                //HÁ ESSE TESTE AQUI PQ MULTA NÃO É OBRIGATÓRIO TER TODA VEZ, ENTÃO PODE SER UM VALOR NULO;
                Integer aux = rs.getInt("id_multa");
                if(aux!=null){
                    multa = dao_multa.getMulta(rs.getInt("id_multa"));
                    
                }else multa=null;
                locacao.setMulta(multa);
                
                //TENTANDO CAPTURAR PESSOA F�SICA
                pessoa_fisica fisica = new pessoa_fisica();
                Integer idf = rs.getInt("id_fisico");
                if(idf!=null){
                    fisica = dao_fisica.getPessoaFisicaID(rs.getInt(idf));
                    locacao.setFisica(fisica);
                }
                
                //TENTANDO CAPTURAR PESSOA JURIDICA
                pessoa_juridica juridica = new pessoa_juridica();
                Integer idj = rs.getInt("id_juridico");
                if(idj!=null){
                    juridica = dao_juridica.getPessoaJuridicaID(idj);
                    locacao.setJuridica(juridica);
                }
                
                //TENTANDO PEGAR ACESS�RIO
                acessorio acessorio = new acessorio();
                Integer ida = rs.getInt("id_acessorio");
                if(ida!=null){
                    acessorio = dao_acessorio.getAcessorio(ida);
                    locacao.setAcessorio(acessorio);
                }
                
                locacoes.add(locacao);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locacoes;
    }
    
    public List<locacao> getAllLocacaoAtivas(){
        
        List<locacao> locacoes = new ArrayList<locacao>();
        String sql = "select * from locacao where status='t'";
        //DECLARAÇÃO DAS DAOS NECESSÁRIAS PARA PREENCHER OS OBJETOS QUE ESTÃO CONTIDOS NA LOCAÇÃO
        dao_veiculo dao_veiculo = new dao_veiculo();
        dao_motorista dao_motorista = new dao_motorista();
        dao_vistoria dao_vistoria = new dao_vistoria();
        dao_apolice dao_apolice = new dao_apolice();
        dao_multa dao_multa = new dao_multa();
        dao_pessoa_juridica dao_juridica = new dao_pessoa_juridica();
        dao_pessoa_fisica dao_fisica = new dao_pessoa_fisica();
        dao_acessorio dao_acessorio = new dao_acessorio();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                locacao locacao = new locacao();
                
                locacao.setId_locacao(rs.getInt("id_locacao"));
                locacao.setData_retirada(rs.getDate("data_retirada"));
                locacao.setData_devolucao(rs.getDate("data_devolucao"));
                locacao.setValor_seguro(rs.getDouble("valor_seguro"));
                locacao.setValor_calcao(rs.getDouble("valor_calcao"));
                locacao.setValor_final(rs.getDouble("valor_final"));
                locacao.setStatus(rs.getBoolean("status"));
                
                //COMEÇA AQUI A CHAMAR DAOS E MODELS QUE ADMINISTRAM VÁRIAS TABELAS PARA PREENCHER O OBJETO LOCAÇÃO DA FORMA ADEQUEADA.
                //O RELACIONAMENTO É FEITO AQUI
                
                //PEGANDO O ID QUE TÁ NO BANCO NA TABELA LOCAÇÃO E PREENCHENDO NO OBJETO LOCAÇÃO USANDO A DAO DO OBJETO
                
                //PARA VEICULO
                veiculo veiculo = new veiculo();
                veiculo = dao_veiculo.getVeiculo(rs.getString("placa_veiculo"));
                locacao.setVeiculo(veiculo);
                
                //PARA MOTORISTA
                motorista motorista = new motorista();
                motorista = dao_motorista.getMotorista(rs.getInt("id_motorista"));
                locacao.setMotorista(motorista);
                
                //PARA VISTORIA
                vistoria vistoria = new vistoria();
                vistoria = dao_vistoria.getVistoria(rs.getInt("id_vistoria"));
                locacao.setVistoria(vistoria);
                
                //PARA CLIENTE
                //PULADO PQ FALTA O REINALDO TERMINAR
                
                //PARA APOLICE
                apolice apolice = new apolice();
                apolice = dao_apolice.getApolice(rs.getInt("id_apolice"));
                locacao.setApolice(apolice);
                
                //PARA MULTA
                multa multa = new multa();
                //HÁ ESSE TESTE AQUI PQ MULTA NÃO É OBRIGATÓRIO TER TODA VEZ, ENTÃO PODE SER UM VALOR NULO;
                Integer aux = rs.getInt("id_multa");
                if(aux!=null){
                    multa = dao_multa.getMulta(rs.getInt("id_multa"));
                    
                }else multa=null;
                locacao.setMulta(multa);
                
                //TENTANDO CAPTURAR PESSOA F�SICA
                pessoa_fisica fisica = new pessoa_fisica();
                Integer idf = rs.getInt("id_fisico");
                if(idf!=null){
                    fisica = dao_fisica.getPessoaFisicaID(rs.getInt(idf));
                    locacao.setFisica(fisica);
                }
                
                //TENTANDO CAPTURAR PESSOA JURIDICA
                pessoa_juridica juridica = new pessoa_juridica();
                Integer idj = rs.getInt("id_juridico");
                if(idj!=null){
                    juridica = dao_juridica.getPessoaJuridicaID(idj);
                    locacao.setJuridica(juridica);
                }
                
                //TENTANDO PEGAR ACESS�RIO
                acessorio acessorio = new acessorio();
                Integer ida = rs.getInt("id_acessorio");
                if(ida!=null){
                    acessorio = dao_acessorio.getAcessorio(ida);
                    locacao.setAcessorio(acessorio);
                }
                
                locacoes.add(locacao);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locacoes;
    }
    
    public List<locacao> getAllLocacaoDesativas(){
        
        List<locacao> locacoes = new ArrayList<locacao>();
        String sql = "select * from locacao where status='f'";
        //DECLARAÇÃO DAS DAOS NECESSÁRIAS PARA PREENCHER OS OBJETOS QUE ESTÃO CONTIDOS NA LOCAÇÃO
        dao_veiculo dao_veiculo = new dao_veiculo();
        dao_motorista dao_motorista = new dao_motorista();
        dao_vistoria dao_vistoria = new dao_vistoria();
        dao_apolice dao_apolice = new dao_apolice();
        dao_multa dao_multa = new dao_multa();
        dao_pessoa_juridica dao_juridica = new dao_pessoa_juridica();
        dao_pessoa_fisica dao_fisica = new dao_pessoa_fisica();
        dao_acessorio dao_acessorio = new dao_acessorio();
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                locacao locacao = new locacao();
                
                locacao.setId_locacao(rs.getInt("id_locacao"));
                locacao.setData_retirada(rs.getDate("data_retirada"));
                locacao.setData_devolucao(rs.getDate("data_devolucao"));
                locacao.setValor_seguro(rs.getDouble("valor_seguro"));
                locacao.setValor_calcao(rs.getDouble("valor_calcao"));
                locacao.setValor_final(rs.getDouble("valor_final"));
                locacao.setStatus(rs.getBoolean("status"));
                
                //COMEÇA AQUI A CHAMAR DAOS E MODELS QUE ADMINISTRAM VÁRIAS TABELAS PARA PREENCHER O OBJETO LOCAÇÃO DA FORMA ADEQUEADA.
                //O RELACIONAMENTO É FEITO AQUI
                
                //PEGANDO O ID QUE TÁ NO BANCO NA TABELA LOCAÇÃO E PREENCHENDO NO OBJETO LOCAÇÃO USANDO A DAO DO OBJETO
                
                //PARA VEICULO
                veiculo veiculo = new veiculo();
                veiculo = dao_veiculo.getVeiculo(rs.getString("placa_veiculo"));
                locacao.setVeiculo(veiculo);
                
                //PARA MOTORISTA
                motorista motorista = new motorista();
                motorista = dao_motorista.getMotorista(rs.getInt("id_motorista"));
                locacao.setMotorista(motorista);
                
                //PARA VISTORIA
                vistoria vistoria = new vistoria();
                vistoria = dao_vistoria.getVistoria(rs.getInt("id_vistoria"));
                locacao.setVistoria(vistoria);
                
                //PARA CLIENTE
                //PULADO PQ FALTA O REINALDO TERMINAR
                
                //PARA APOLICE
                apolice apolice = new apolice();
                apolice = dao_apolice.getApolice(rs.getInt("id_apolice"));
                locacao.setApolice(apolice);
                
                //PARA MULTA
                multa multa = new multa();
                //HÁ ESSE TESTE AQUI PQ MULTA NÃO É OBRIGATÓRIO TER TODA VEZ, ENTÃO PODE SER UM VALOR NULO;
                Integer aux = rs.getInt("id_multa");
                if(aux!=null){
                    multa = dao_multa.getMulta(rs.getInt("id_multa"));
                    
                }else multa=null;
                locacao.setMulta(multa);
                
                //TENTANDO CAPTURAR PESSOA F�SICA
                pessoa_fisica fisica = new pessoa_fisica();
                Integer idf = rs.getInt("id_fisico");
                if(idf!=null){
                    fisica = dao_fisica.getPessoaFisicaID(rs.getInt(idf));
                    locacao.setFisica(fisica);
                }
                
                //TENTANDO CAPTURAR PESSOA JURIDICA
                pessoa_juridica juridica = new pessoa_juridica();
                Integer idj = rs.getInt("id_juridico");
                if(idj!=null){
                    juridica = dao_juridica.getPessoaJuridicaID(idj);
                    locacao.setJuridica(juridica);
                }
                
                //TENTANDO PEGAR ACESS�RIO
                acessorio acessorio = new acessorio();
                Integer ida = rs.getInt("id_acessorio");
                if(ida!=null){
                    acessorio = dao_acessorio.getAcessorio(ida);
                    locacao.setAcessorio(acessorio);
                }
                
                locacoes.add(locacao);
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return locacoes;
    }
    
    public void finalizarLocacao(locacao locacao){
        
        String sql = "update locacao set status='f' where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            
            preparedStatement.setInt(1, locacao.getId_locacao());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql2="update veiculo set status='t' where placa=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            preparedStatement.setString(1, locacao.getVeiculo().getPlaca());
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void adicionarAcessorio(acessorio acessorio, int id_locacao){
        
        String sql2="update locacao set id_acessorio=? where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            preparedStatement.setInt(1,acessorio.getId_acessorio());
            preparedStatement.setInt(2, id_locacao);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql3="select valor_final from locacao where id_locacao=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql3);

            preparedStatement.setInt(1, id_locacao);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()){
                
                valorFinalAtual = rs.getDouble("valor_final");
                valorFinalAtualizado = valorFinalAtual + acessorio.getValor();
            }
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql4 = "update locacao set valor_final=? where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql4);
            
            preparedStatement.setDouble(1,valorFinalAtualizado);
            preparedStatement.setInt(2, id_locacao);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void adicionarMulta(multa multa, int id_locacao){
        
        String sql2="update locacao set id_multa=? where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql2);
            
            preparedStatement.setInt(1,multa.getId_multa());
            preparedStatement.setInt(2, id_locacao);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql3="select valor_final from locacao where id_locacao=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql3);

            preparedStatement.setInt(1, id_locacao);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()){
                
                valorFinalAtual = rs.getDouble("valor_final");
                valorFinalAtualizado = valorFinalAtual + multa.getValor();
            }
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String sql4 = "update locacao set valor_final=? where id_locacao=?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql4);
            
            preparedStatement.setDouble(1,valorFinalAtualizado);
            preparedStatement.setInt(2, id_locacao);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
}