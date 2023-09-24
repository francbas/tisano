package org.francescobasile.tisano.prove.composite;

public class Medico {
    private String nome;

    public Medico(String nome) {
        this.nome = nome;
    }

    public Medico() {
        this(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "nome='" + nome + '\'' +
                '}';
    }

}

class MedicoCompositeContent implements ICompositeContent<Medico> {

    private Medico medico;

    public MedicoCompositeContent(Medico medico) {
        this.medico = medico;
    }

    public MedicoCompositeContent(String nome) {
        this.medico = new Medico(nome);
    }

    @Override
    public Medico getContent() {
        return this.medico;
    }

    @Override
    public void processContent() {
        System.out.println(this.medico.getNome());
    }

}
