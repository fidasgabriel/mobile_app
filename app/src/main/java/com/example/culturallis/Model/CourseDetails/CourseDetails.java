package com.example.culturallis.Model.CourseDetails;

import android.content.pm.ModuleInfo;

import com.example.culturallis.Model.Modules.Modules;

import java.util.Date;
import java.util.List;

public class CourseDetails {

    private long pk_id;

    private String nome;

    private int fk_cul_usuarios_id;

    private int fk_cul_categorias_id;

    private String url_midia;

    private String courseOwner;

    private String courseOwnerFoto;

    private String categoria;

    private String descricao;

    private double preco;

    private Date data_criacao;

    private Date data_mudanca;

    private Date data_desativacao;

    private List<Modules> modulos;

    public CourseDetails(long pk_id, String nome, int fk_cul_usuarios_id, int fk_cul_categorias_id, String url_midia, String courseOwner, String courseOwnerFoto, String categoria, String descricao, double preco, Date data_criacao, Date data_mudanca, Date data_desativacao, List<Modules> modulos) {
        this.pk_id = pk_id;
        this.nome = nome;
        this.fk_cul_usuarios_id = fk_cul_usuarios_id;
        this.fk_cul_categorias_id = fk_cul_categorias_id;
        this.url_midia = url_midia;
        this.courseOwner = courseOwner;
        this.courseOwnerFoto = courseOwnerFoto;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
        this.data_criacao = data_criacao;
        this.data_mudanca = data_mudanca;
        this.data_desativacao = data_desativacao;
        this.modulos = modulos;
    }

    public  CourseDetails(){}

    public long getPk_id() {
        return pk_id;
    }

    public void setPk_id(long pk_id) {
        this.pk_id = pk_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFk_cul_usuarios_id() {
        return fk_cul_usuarios_id;
    }

    public void setFk_cul_usuarios_id(int fk_cul_usuarios_id) {
        this.fk_cul_usuarios_id = fk_cul_usuarios_id;
    }

    public int getFk_cul_categorias_id() {
        return fk_cul_categorias_id;
    }

    public void setFk_cul_categorias_id(int fk_cul_categorias_id) {
        this.fk_cul_categorias_id = fk_cul_categorias_id;
    }

    public String getCourseOwner() {
        return courseOwner;
    }

    public void setCourseOwner(String courseOwner) {
        this.courseOwner = courseOwner;
    }

    public String getCourseOwnerFoto() {
        return courseOwnerFoto;
    }

    public void setCourseOwnerFoto(String courseOwnerFoto) {
        this.courseOwnerFoto = courseOwnerFoto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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

    public String getUrl_midia() {
        return url_midia;
    }

    public void setUrl_midia(String url_midia) {
        this.url_midia = url_midia;
    }

    public List<Modules> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modules> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "CourseDetails{" +
                "pk_id=" + pk_id +
                ", nome='" + nome + '\'' +
                ", fk_cul_usuarios_id=" + fk_cul_usuarios_id +
                ", fk_cul_categorias_id=" + fk_cul_categorias_id +
                ", url_midia=" + url_midia +
                ", courseOwner='" + courseOwner + '\'' +
                ", courseOwnerFoto='" + courseOwnerFoto + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", data_criacao=" + data_criacao +
                ", data_mudanca=" + data_mudanca +
                ", data_desativacao=" + data_desativacao +
                ", modulos=" + modulos +
                '}';
    }
}
