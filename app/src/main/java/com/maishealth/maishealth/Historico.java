package com.maishealth.maishealth;

//FALTA METODOS AINDA*
public class Historico {

    private String nome;
    private String especialidade;
    private String data;

    public Historico(String data, String especialidade ,String nome){
        this.data=data;
        this.especialidade=especialidade;
        this.nome=nome;
    }

    public String getNome() {return nome;}

    public void setNome(String nome){
        this.nome=nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade=especialidade;
    }

    public String getData() {return data;}

    public void setData(String data){
        this.data=data;
    }

}
