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
public class motorista {
    
    private int id_motorista;
    private String telefone;
    private String cpf;
    private String email;
    private String nome;
    private String registro_geral;
    private Date data_nascimento;
    private String registro_cnh;
    private String categoria_cnh;
    private Date data_validade_cnh;
    private String foto_url;
    private endereco endereco;

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro_geral() {
        return registro_geral;
    }

    public void setRegistro_geral(String registro_geral) {
        this.registro_geral = registro_geral;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getRegistro_cnh() {
        return registro_cnh;
    }

    public void setRegistro_cnh(String registro_cnh) {
        this.registro_cnh = registro_cnh;
    }

    public String getCategoria_cnh() {
        return categoria_cnh;
    }

    public void setCategoria_cnh(String categoria_cnh) {
        this.categoria_cnh = categoria_cnh;
    }

    public Date getData_validade_cnh() {
        return data_validade_cnh;
    }

    public void setData_validade_cnh(Date data_validade_cnh) {
        this.data_validade_cnh = data_validade_cnh;
    }

    public String getFoto_url() {
        return foto_url;
    }

    public void setFoto_url(String foto_url) {
        this.foto_url = foto_url;
    }

    public endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(endereco endereco) {
        this.endereco = endereco;
    }
    
    
    
}
