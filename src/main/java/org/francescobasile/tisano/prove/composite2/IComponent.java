package org.francescobasile.tisano.prove.composite2;

import java.util.ArrayList;
import java.util.List;

public interface IComponent {
    IComponent getParent();

    void setParent(IComponent parent);

    void attachChild(IComponent child);

    void detachChild(IComponent child);
}

class Component<T> implements IComponent {
    private List<IComponent> children;
    private IComponent parent;

    public Component() {
        this.children = new ArrayList<>();
    }

    public List<IComponent> getChildren() {
        return children;
    }

    @Override
    public IComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(IComponent parent) {
        this.parent = parent;
    }

    @Override
    public void attachChild(IComponent child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public void detachChild(IComponent child) {
        this.children.remove(child);
        child.setParent(null);
    }
}
