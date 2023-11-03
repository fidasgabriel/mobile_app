package com.example.culturallis.Model.PostsHome;

import java.util.Date;

public class PostsHome {
    private long pk_id;

    private long fk_cul_usuarios_id;

    private String descricao;

    private String url_midia;

    private Date data_criacao;

    private Date data_mudanca;

    private Date data_desativacao;

    private String postsOwnerFoto;

    private String postsOwnerName;

    private Boolean curtido;

    private Boolean salvo;

    public PostsHome() {
    }

    public PostsHome(long pk_id, long fk_cul_usuarios_id, String descricao, String url_midia, Date data_criacao, Date data_mudanca, Date data_desativacao, String postsOwnerFoto, String postsOwnerName, Boolean curtido, Boolean salvo) {
        this.pk_id = pk_id;
        this.fk_cul_usuarios_id = fk_cul_usuarios_id;
        this.descricao = descricao;
        this.url_midia = url_midia;
        this.data_criacao = data_criacao;
        this.data_mudanca = data_mudanca;
        this.data_desativacao = data_desativacao;
        this.postsOwnerFoto = postsOwnerFoto;
        this.postsOwnerName = postsOwnerName;
        this.curtido = curtido;
        this.salvo = salvo;
    }

    public long getPk_id() {
        return pk_id;
    }

    public void setPk_id(long pk_id) {
        this.pk_id = pk_id;
    }

    public long getFk_cul_usuarios_id() {
        return fk_cul_usuarios_id;
    }

    public void setFk_cul_usuarios_id(long fk_cul_usuarios_id) {
        this.fk_cul_usuarios_id = fk_cul_usuarios_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_midia() {
        return url_midia;
    }

    public void setUrl_midia(String url_midia) {
        this.url_midia = url_midia;
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

    public String getPostsOwnerFoto() {
        return postsOwnerFoto;
    }

    public void setPostsOwnerFoto(String postsOwnerFoto) {
        this.postsOwnerFoto = postsOwnerFoto;
    }

    public String getPostsOwnerName() {
        return postsOwnerName;
    }

    public void setPostsOwnerName(String postsOwnerName) {
        this.postsOwnerName = postsOwnerName;
    }

    public Boolean getCurtido() {
        return curtido;
    }

    public void setCurtido(Boolean curtido) {
        this.curtido = curtido;
    }

    public Boolean getSalvo() {
        return salvo;
    }

    public void setSalvo(Boolean salvo) {
        this.salvo = salvo;
    }

    @Override
    public String toString() {
        return "PostsHome{" +
                "pk_id=" + pk_id +
                ", fk_cul_usuarios_id=" + fk_cul_usuarios_id +
                ", descricao='" + descricao + '\'' +
                ", url_midia='" + url_midia + '\'' +
                ", data_criacao=" + data_criacao +
                ", data_mudanca=" + data_mudanca +
                ", data_desativacao=" + data_desativacao +
                ", postsOwnerFoto='" + postsOwnerFoto + '\'' +
                ", postsOwnerName='" + postsOwnerName + '\'' +
                ", curtido=" + curtido +
                ", salvo=" + salvo +
                '}';
    }
}
