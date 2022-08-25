package com.eventoapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;


//Entidade/Tabela no banco de dados
@Entity
public class Convidado {

    @Id //chave primaria da tabela
    @NotEmpty
    //@Column(nullable = false, unique = true)
    private String rg;

    @NotEmpty
    private String nomeConvidado;

    @ManyToOne //muitos convidados para um evento
    private Evento evento;


    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeConvidado() {
        return nomeConvidado;
    }

    public void setNomeConvidado(String nomeConvidado) {
        this.nomeConvidado = nomeConvidado;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
