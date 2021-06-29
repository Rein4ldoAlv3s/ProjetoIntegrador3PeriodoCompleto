/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author joaov
 */
public class locacao {
    
    //Valores da locação em si
    private int id_locacao;
    private Date data_retirada;
    private Date data_devolucao;
    private double valor_seguro;
    private double valor_calcao;
    private double valor_final;
    private boolean status;

    
    //Tabelas que se relaciona com os IDs dentro dos objetos
    private veiculo veiculo;
    private motorista motorista;
    private vistoria vistoria;
    private apolice apolice;
    private multa multa;
    private pessoa_fisica fisica;
    private pessoa_juridica juridica;
    private acessorio acessorio;

    public int getId_locacao() {
        return id_locacao;
    }

    public void setId_locacao(int id_locacao) {
        this.id_locacao = id_locacao;
    }

    public Date getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(Date data_retirada) {
        this.data_retirada = data_retirada;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public double getValor_seguro() {
        return valor_seguro;
    }

    public void setValor_seguro(double valor_seguro) {
        this.valor_seguro = valor_seguro;
    }

    public double getValor_calcao() {
        return valor_calcao;
    }

    public void setValor_calcao(double valor_calcao) {
        this.valor_calcao = valor_calcao;
    }

    public double getValor_final() {
        return valor_final;
    }

    public void setValor_final(double valor_final) {
        this.valor_final = valor_final;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(motorista motorista) {
        this.motorista = motorista;
    }

    public vistoria getVistoria() {
        return vistoria;
    }

    public void setVistoria(vistoria vistoria) {
        this.vistoria = vistoria;
    }

    public apolice getApolice() {
        return apolice;
    }

    public void setApolice(apolice apolice) {
        this.apolice = apolice;
    }

    public multa getMulta() {
        return multa;
    }

    public void setMulta(multa multa) {
        this.multa = multa;
    }

    public pessoa_fisica getFisica() {
        return fisica;
    }

    public void setFisica(pessoa_fisica fisica) {
        this.fisica = fisica;
    }

    public pessoa_juridica getJuridica() {
        return juridica;
    }

    public void setJuridica(pessoa_juridica juridica) {
        this.juridica = juridica;
    }

    public acessorio getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(acessorio acessorio) {
        this.acessorio = acessorio;
    }
    
    
}