package org.francescobasile.tisano.entity;

import jakarta.persistence.Entity;

@Entity
public class Specializzazione extends AbstractEntity {

    private String nome;

    public Specializzazione() {
    }

    public Specializzazione(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}