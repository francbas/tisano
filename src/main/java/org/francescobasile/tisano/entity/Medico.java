package org.francescobasile.tisano.entity;

import jakarta.persistence.Entity;

@Entity
public class Medico extends AbstractEntity {

    private String nome;
    private String cognome;

    public Medico() {
    }

    public Medico(String nome) {
        this.nome = nome;
    }

    public Medico(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
