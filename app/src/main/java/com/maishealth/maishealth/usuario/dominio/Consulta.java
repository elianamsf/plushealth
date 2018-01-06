package com.maishealth.maishealth.usuario.dominio;


public class Consulta {
    private String data;
    private String descricao;
    private long idPaciente;
    private long idMedico;
    private String status;
    private long idConsulta;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getIdConsulta() { return idConsulta;}

    public void setIdConsulta(long idConsulta) { this.idConsulta = idConsulta; }
}
