package deyse.souza.appvacina.model;

import android.widget.Spinner;

public  class Usuario {

    private int id;
    private String Nome;
    private String email;
    private String senha;

    private String tipoperfil;

    private String estado;

    private String municipio;

    private String cnes;

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getTipoperfil() {
        return tipoperfil;
    }

    public void setTipoperfil(String tipoperfil) {
        this.tipoperfil = tipoperfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
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



}
