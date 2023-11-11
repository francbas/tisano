package org.francescobasile.tisano.prove.composite;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//@Stateful
//@Entity
@Entity
public abstract class AbstractComponent implements IComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public abstract void setParent(AbstractComponent parent);

    public abstract void attachChild(AbstractComponent child);

    public abstract void detachChild(AbstractComponent child);

}
