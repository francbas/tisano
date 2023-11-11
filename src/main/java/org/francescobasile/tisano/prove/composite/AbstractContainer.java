package org.francescobasile.tisano.prove.composite;

import jakarta.persistence.*;
import org.francescobasile.tisano.entity.AbstractEntity;
import org.francescobasile.tisano.entity.Medico;
import org.francescobasile.tisano.entity.Specializzazione;
import org.francescobasile.tisano.entity.TimeSlot;

//@Stateful
@Entity
public abstract class AbstractContainer implements IProcessable, IContainable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private AbstractEntity content;

    public AbstractContainer() {
    }

    public AbstractContainer(AbstractEntity content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public AbstractEntity getContent() {
        return this.content;
    }
}

class SpecializzazioneContainer extends AbstractContainer {

    public SpecializzazioneContainer(Specializzazione content) {
        super(content);
    }

    @Override
    public void process() {
        System.out.println(((Specializzazione) this.getContent()).getNome());
    }
}

class MedicoContainer extends AbstractContainer {
    public MedicoContainer(Medico content) {
        super(content);
    }

    @Override
    public void process() {
        System.out.println(((Medico) this.getContent()).getNome());
    }
}

class TimeSlotContainer extends AbstractContainer {
    public TimeSlotContainer(TimeSlot content) {
        super(content);
    }

    @Override
    public void process() {
        System.out.println(((TimeSlot) this.getContent()).getEnd());
    }
}

