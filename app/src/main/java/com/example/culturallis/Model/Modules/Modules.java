package com.example.culturallis.Model.Modules;

import java.util.Date;

public class Modules {

    private long pk_id;

    private int pk_cul_cursos_id;

    private String nome;

    private String url_material;

    private Date data_criacao;

    private Date data_mudanca;

    private Date data_desativacao;

    @Override
    public String toString() {
        return "Modules{" +
                "pk_id=" + pk_id +
                ", pk_cul_cursos_id=" + pk_cul_cursos_id +
                ", nome='" + nome + '\'' +
                ", url_material='" + url_material + '\'' +
                ", data_criacao=" + data_criacao +
                ", data_mudanca=" + data_mudanca +
                ", data_desativacao=" + data_desativacao +
                '}';
    }

    public long getPk_id() {
        return pk_id;
    }

    public void setPk_id(long pk_id) {
        this.pk_id = pk_id;
    }

    public int getPk_cul_cursos_id() {
        return pk_cul_cursos_id;
    }

    public void setPk_cul_cursos_id(int pk_cul_cursos_id) {
        this.pk_cul_cursos_id = pk_cul_cursos_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl_material() {
        return url_material;
    }

    public void setUrl_material(String url_material) {
        this.url_material = url_material;
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
