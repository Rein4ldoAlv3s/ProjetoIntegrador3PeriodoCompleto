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
public class vistoria {
    
    private int id_vistoria;
    private Date data_vistoria;
    private int qnte_combustivel;
    private int quilometragem_inicial;
    private int quilometragem_final;
    private String url_video;
    private String observacoes;

    public int getId_vistoria() {
        return id_vistoria;
    }

    public void setId_vistoria(int id_vistoria) {
        this.id_vistoria = id_vistoria;
    }

    public Date getData_vistoria() {
        return data_vistoria;
    }

    public void setData_vistoria(Date data_vistoria) {
        this.data_vistoria = data_vistoria;
    }

    public int getQnte_combustivel() {
        return qnte_combustivel;
    }

    public void setQnte_combustivel(int qnte_combustivel) {
        this.qnte_combustivel = qnte_combustivel;
    }

    public int getQuilometragem_inicial() {
        return quilometragem_inicial;
    }

    public void setQuilometragem_inicial(int quilometragem_inicial) {
        this.quilometragem_inicial = quilometragem_inicial;
    }

    public int getQuilometragem_final() {
        return quilometragem_final;
    }

    public void setQuilometragem_final(int quilometragem_final) {
        this.quilometragem_final = quilometragem_final;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    
    
}
