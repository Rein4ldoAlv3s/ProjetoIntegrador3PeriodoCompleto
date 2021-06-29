/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author joaov
 */
public class veiculo {
    
    private String placa;
    private String tipo;
    private String renavam;
    private boolean status;
    private double preco_compra;
    private double preco_venda;
    private String cor;
    private int qnt_passageiros;
    private int capacidade_tanque;
    private int ano_fabricacao;
    private int ano_modelo;
    private String tipo_combustivel;
    private int quilometragem;
    private categoria categoria;
    private modelo modelo;

    //GETTERS E SETTERS
    public categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public modelo getModelo() {
        return modelo;
    }

    public void setModelo(modelo modelo) {
        this.modelo = modelo;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRenavam() {
        return renavam;
    }

    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQnt_passageiros() {
        return qnt_passageiros;
    }

    public void setQnt_passageiros(int qnt_passageiros) {
        this.qnt_passageiros = qnt_passageiros;
    }

    public int getCapacidade_tanque() {
        return capacidade_tanque;
    }

    public void setCapacidade_tanque(int capacidade_tanque) {
        this.capacidade_tanque = capacidade_tanque;
    }

    public int getAno_fabricacao() {
        return ano_fabricacao;
    }

    public void setAno_fabricacao(int ano_fabricacao) {
        this.ano_fabricacao = ano_fabricacao;
    }

    public int getAno_modelo() {
        return ano_modelo;
    }

    public void setAno_modelo(int ano_modelo) {
        this.ano_modelo = ano_modelo;
    }

    public String getTipo_combustivel() {
        return tipo_combustivel;
    }

    public void setTipo_combustivel(String tipo_combustivel) {
        this.tipo_combustivel = tipo_combustivel;
    }

    public int getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }
    
}
