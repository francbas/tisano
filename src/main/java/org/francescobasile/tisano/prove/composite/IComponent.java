package org.francescobasile.tisano.prove.composite;

import java.util.ArrayList;
import java.util.List;

public interface IComponent {
    void traverse();

    IComponent getParent();

    void setParent(IComponent parent);

}

abstract class Composite implements IComponent {

    private final List<IComponent> children;
    private IComponent parent;

    public Composite() {
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public void attachChild(IComponent child) {
        this.children.add(child);
        child.setParent(this);
    }

    public void detachChild(IComponent child) {
        this.children.remove(child);
        child.setParent(null);
    }

    @Override
    public IComponent getParent() {
        if (parent == null) {
            return null;
        }
        return this.parent;
    }

    public void setParent(IComponent parent) {
        this.parent = parent;
    }

    public List<IComponent> getChildren() {
        return children;
    }

    @Override
    public void traverse() {
        this.children.forEach(IComponent::traverse);
    }
}

abstract class Leaf implements IComponent {

    private IComponent parent;

    public Leaf() {
        this.parent = null;
    }

    @Override
    public IComponent getParent() {
        if (parent == null) {
            return null;
        }
        return this.parent;
    }
    @Override
    public void setParent(IComponent parent) {
        this.parent = parent;
    }
}