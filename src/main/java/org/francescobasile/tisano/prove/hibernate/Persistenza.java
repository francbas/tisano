package org.francescobasile.tisano.prove.hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.francescobasile.tisano.prove.LocalEntityRepository;
import org.hibernate.annotations.DiscriminatorFormula;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.Objects;

public class Persistenza {

    public static void main(String[] args) {


        {
            Impiegato impiegato = new Impiegato();
            impiegato.setNome("nome01");
            impiegato.setCognome("cognome01");
            impiegato.setStipendio(15000);
            impiegato.setGiornoLibero(DayOfWeek.LUNEDI);

            LocalEntityRepository.save(impiegato);

            impiegato.setStipendio(30000);

            LocalEntityRepository.save(impiegato);

            impiegato = new Impiegato();
            impiegato.setCognome("cognome02");
            impiegato.setNome("nome02");
            LocalEntityRepository.save(impiegato);

            impiegato.setStipendio(80000);
            impiegato.setGiornoLibero(DayOfWeek.SABATO);
            LocalEntityRepository.save(impiegato);

            Persona persona = new Persona();
            persona.setCognome("Papiello01");
            persona.setNome("Ciccio01");

            LocalEntityRepository.save(persona);

            Persona impiegato1 = LocalEntityRepository.find(Persona.class, 3);
//        System.out.printf("Persona: [id %d] %sc(stipendio: %d)%n", impiegato1.getId(), impiegato1.getCognome(), impiegato1.getStipendio());
            System.out.printf("Persona: [id %d] %s (stipendio: %d)%nImpiegato: %s%n", impiegato1.getId(), impiegato1.getCognome(), 0, impiegato1 instanceof Impiegato);
        }

        Animale cleo = new Gatto(new Tassonomia("felidae","felis","felis silvestris" ), "cleo");
        Animale miki = new Gatto(new Tassonomia("felidae","felis","felis silvestris" ), "miki");
        LocalEntityRepository.save(cleo);
        LocalEntityRepository.save(miki);

        LocalEntityRepository.close();
    }
}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("0")
@DiscriminatorFormula("case when stipendio is not null then 1 else 0 end")
class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;
    @Basic(optional = false)
    private String cognome;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String att1P) {
        this.nome = att1P;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String att2P) {
        this.cognome = att2P;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(getId(), persona.getId()) && Objects.equals(getNome(), persona.getNome()) && Objects.equals(getCognome(), persona.getCognome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCognome());
    }
}

@Entity
@DiscriminatorValue("1")
class Impiegato extends Persona {

    @NotNull int stipendio;

    @Enumerated(EnumType.STRING)
    DayOfWeek giornoLibero;

    public int getStipendio() {
        return stipendio;
    }

    public void setStipendio(int att3C) {
        this.stipendio = att3C;
    }

    public DayOfWeek getGiornoLibero() {
        return giornoLibero;
    }

    public void setGiornoLibero(DayOfWeek giornoSettimana) {
        this.giornoLibero = giornoSettimana;
    }
}

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@MappedSuperclass
abstract class Animale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Tassonomia tassonomia;

    @Version
    LocalDateTime lastUpdated;

    public Animale() {
    }

    public Animale(Tassonomia tassonomia) {
        this.tassonomia = tassonomia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Tassonomia getTassonomia() {
        return tassonomia;
    }

    public void setTassonomia(Tassonomia tassonomia) {
        this.tassonomia = tassonomia;
    }
}

@Embeddable
class Tassonomia {
    private String famiglia;
    private String genere;
    private String specie;

    public Tassonomia() {
    }

    public Tassonomia(String famiglia, String genere, String specie) {
        this.famiglia = famiglia;
        this.genere = genere;
        this.specie = specie;
    }

    public String getFamiglia() {
        return famiglia;
    }

    public String getGenere() {
        return genere;
    }

    public String getSpecie() {
        return specie;
    }
}

@Entity
//@PrimaryKeyJoinColumn(name = "animaleId")
class Gatto extends Animale {
    @NaturalId
    private String nome;

    public Gatto() {
    }

    public Gatto(String nome) {
        this.nome = nome;
    }

    public Gatto(Tassonomia specie, String nome) {
        super(specie);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

enum DayOfWeek {
    LUNEDI, MARTEDI, MERCOLEDI, GIOVEDI, VENERDI, SABATO, DOMENICA
}