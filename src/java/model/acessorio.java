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
public class acessorio {
    
    private int id_acessorio;
    private double valor;
    private boolean status;
    private tipo_acessorio tipo;

    public int getId_acessorio() {
        return id_acessorio;
    }

    public void setId_acessorio(int id_acessorio) {
        this.id_acessorio = id_acessorio;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public tipo_acessorio getTipo() {
        return tipo;
    }

    public void setTipo(tipo_acessorio tipo) {
        this.tipo = tipo;
    }
    
}
