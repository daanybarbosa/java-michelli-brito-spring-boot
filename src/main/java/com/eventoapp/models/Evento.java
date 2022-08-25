package com.eventoapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * Entidade Evento
 * Será uma tabela no banco de dados
 */

@Entity
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L; //para implementar o id automaticamente

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //ira gerar o id automaticamente
    private Long codigo;

    @Column
    @NotEmpty
    private String nome;

    @Column
    @NotEmpty
    private String local;

    @Column
    @NotEmpty
    private String data;

    @Column
    @NotEmpty
    private String horario;

    @OneToMany //um evento para muitos convidados
    private List<Convidado> convidados;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
