package com.app.anthony.pi4.br.edu.cairu.modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;


    public Cliente() {
    }

    public Cliente(String id, String nome, String cpf, String telefone, String email,String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return nome;
    }
}
