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
public class multa {
    
    private Integer id_multa;
    private String descricao;
    private Date data_autuacao;
    private String gravidade;
    private double valor;

    public Integer getId_multa() {
        return id_multa;
    }

    public void setId_multa(Integer id_multa) {
        this.id_multa = id_multa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_autuacao() {
        return data_autuacao;
    }

    public void setData_autuacao(Date data_autuacao) {
        this.data_autuacao = data_autuacao;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
