package com.example.culturallis.Model.Usuario;

import java.util.Date;

public class Usuario {

    private long pk_id;

    private String cpf;

    private long fk_cul_generos;

    private String nome_usuario;

    private String nome_completo;

    private Date dataNasc;

    private String bio;

    private String url_foto;

    private String email;

    private String telefone;

    private String senha;

    private Date dataCriacao;

    private Date dataMudanca;

    private Date dataDesativacao;

    public Usuario(){}

    public Usuario(String nome_usuario, String email, String senha) {
        this.nome_usuario = nome_usuario;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String url_foto, String nome_usuario, Date dataNasc, String bio){
        this.url_foto = url_foto;
        this.nome_usuario = nome_usuario;
        this.dataNasc = dataNasc;
        this.bio = bio;
    }

    public Usuario(long pk_id, String cpf, long fk_cul_generos, String nome_usuario, String nome_completo, Date dataNasc, String bio, String url_foto, String email, String telefone, String senha, Date dataCriacao, Date dataMudanca, Date dataDesativacao) {
        this.pk_id = pk_id;
        this.cpf = cpf;
        this.fk_cul_generos = fk_cul_generos;
        this.nome_usuario = nome_usuario;
        this.nome_completo = nome_completo;
        this.dataNasc = dataNasc;
        this.bio = bio;
        this.url_foto = url_foto;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.dataMudanca = dataMudanca;
        this.dataDesativacao = dataDesativacao;
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
        return dataNasc;
    }

    public void setData_nascimento(Date dataNasc) {
        this.dataNasc = dataNasc;
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
        return dataCriacao;
    }

    public void setData_criacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getData_mudanca() {
        return dataMudanca;
    }

    public void setData_mudanca(Date dataMudanca) {
        this.dataMudanca = dataMudanca;
    }

    public Date getData_desativacao() {
        return dataDesativacao;
    }

    public void setData_desativacao(Date dataDesativacao) {
        this.dataDesativacao = dataDesativacao;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "pk_id=" + pk_id +
                ", cpf='" + cpf + '\'' +
                ", fk_cul_generos=" + fk_cul_generos +
                ", nome_usuario='" + nome_usuario + '\'' +
                ", nome_completo='" + nome_completo + '\'' +
                ", dataNasc=" + dataNasc +
                ", bio='" + bio + '\'' +
                ", url_foto='" + url_foto + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataMudanca=" + dataMudanca +
                ", dataDesativacao=" + dataDesativacao +
                '}';
    }
}
