package org.francescobasile.tisano.prove.composite;

public class Specializzazione {
    private String nome;

    public Specializzazione() {
        this(null);
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

    @Override
    public String toString() {
        return "Specializzazione{" + "nome='" + nome + '\'' + '}';
    }
}

class SpecializzazioneCompositeContent implements ICompositeContent<Specializzazione> {

    private final Specializzazione specializzazione;

    public SpecializzazioneCompositeContent(Specializzazione specializzazione) {
        this.specializzazione = specializzazione;
    }

    public SpecializzazioneCompositeContent(String nome) {
        this.specializzazione = new Specializzazione(nome);
    }

    @Override
    public Specializzazione getContent() {
        return this.specializzazione;
    }

    @Override
    public void processContent() {
        System.out.println(this.specializzazione.getNome());
    }
}
