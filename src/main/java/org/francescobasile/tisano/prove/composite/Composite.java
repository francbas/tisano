package org.francescobasile.tisano.prove.composite;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

//@Stateful
@Entity
public class Composite extends AbstractComponent {

    //    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AbstractComponent parent;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Collection<AbstractComponent> children;

    @ManyToOne(cascade = CascadeType.ALL)
    private AbstractContainer container;

    private String compositeName;

    public Composite() {
        this.children = new ArrayList<>();
    }

    public Composite(AbstractContainer container, String compositeName) {
        this();
        this.container = container;
        this.compositeName = compositeName;
    }

    public String getCompositeName() {
        return compositeName;
    }

    public Collection<AbstractComponent> getChildren() {
        return children;
    }

    public AbstractContainer getContainer() {
        return container;
    }

    @Override
    public AbstractComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(AbstractComponent parent) {
        this.parent = parent;
    }

    @Override
    public void attachChild(AbstractComponent child) {
        children.add(child);
        child.setParent(this);
    }

    @Override
    public void detachChild(AbstractComponent child) {
        children.remove(child);
        child.setParent(null);
    }

    @Override
    public void traverse() {
        container.process();
        children.forEach(IComponent::traverse);
    }

    @Override
    public boolean isLeaf() {
        return this.children.isEmpty();
    }
}
