package com.example.culturallis.Model.Usuario;

import java.util.Date;

public class Usuario {

    private long pk_id;

    private String cpf;

    private long fk_cul_generos;

    private String nome_usuario;

    private String nome_completo;

    private Date data_nascimento;

    private String bio;

    private String url_foto;

    private String email;

    private String telefone;

    private String senha;

    private Date data_criacao;

    private Date data_mudanca;

    private Date data_desativacao;

    public Usuario(){}

    public Usuario(String nome_usuario, String email, String senha) {
        this.nome_usuario = nome_usuario;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(long pk_id, String cpf, long fk_cul_generos, String nome_usuario, String nome_completo, Date data_nascimento, String bio, String url_foto, String email, String telefone, String senha, Date data_criacao, Date data_mudanca, Date data_desativacao) {
        this.pk_id = pk_id;
        this.cpf = cpf;
        this.fk_cul_generos = fk_cul_generos;
        this.nome_usuario = nome_usuario;
        this.nome_completo = nome_completo;
        this.data_nascimento = data_nascimento;
        this.bio = bio;
        this.url_foto = url_foto;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.data_criacao = data_criacao;
        this.data_mudanca = data_mudanca;
        this.data_desativacao = data_desativacao;
    }

    public long getPk_id() {
        return pk_id;
    }

    public void setPk_id(long pk_id) {
        this.pk_id = pk_id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public long getFk_cul_generos() {
        return fk_cul_generos;
    }

    public void setFk_cul_generos(long fk_cul_generos) {
        this.fk_cul_generos = fk_cul_generos;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getData_mudanca() {
        return data_mudanca;
    }

    public void setData_mudanca(Date data_mudanca) {
        this.data_mudanca = data_mudanca;
    }

    public Date getData_desativacao() {
        return data_desativacao;
    }

    public void setData_desativacao(Date data_desativacao) {
        this.data_desativacao = data_desativacao;
    }
}
