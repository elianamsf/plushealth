package com.maishealth.maishealth.usuario.dominio;

public class Medico {
    private long id;
    private String crm; //Talvez um int ou long seja melhor
    private String estado; //Será um ENUM
    private String especialidade; //Será um ENUM
    
    /*
    Partes faltando, pois o medico possui um usuario
    private Usuario usuario;
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
