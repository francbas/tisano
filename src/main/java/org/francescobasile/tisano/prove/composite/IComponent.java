package org.francescobasile.tisano.prove.composite;

public interface IComponent {
    AbstractComponent getParent();

    void setParent(AbstractComponent parent);

    void attachChild(AbstractComponent child);

    void detachChild(AbstractComponent child);

    void traverse();

    public abstract boolean isLeaf();

}

