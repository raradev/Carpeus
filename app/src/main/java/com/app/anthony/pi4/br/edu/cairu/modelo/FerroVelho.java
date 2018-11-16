package com.app.anthony.pi4.br.edu.cairu.modelo;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

public class FerroVelho implements Serializable {
    private String id;
    private String nome;
    private String cnpj;
    private String rua;
    private String barrio;
    private String cidade;
    private String complemento;
    public Double latidude;
    public Double logintude;



    public FerroVelho() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getBarrio() { return barrio; }

    public void setBarrio(String barrio) { this.barrio = barrio; }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Double getLatidude() { return latidude; }

    public void setLatidude(Double latidude) { this.latidude = latidude; }

    public Double getLogintude() {
        return logintude;
    }

    public void setLogintude(Double logintude) {
        this.logintude = logintude;
    }

    public String getRua() { return rua; }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
