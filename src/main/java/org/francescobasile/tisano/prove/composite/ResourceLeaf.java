package org.francescobasile.tisano.prove.composite;

public class ResourceLeaf<T> extends Leaf {

    ICompositeContent<T> compositeContent;

    public ResourceLeaf(ICompositeContent<T> compositeContent) {
        this.compositeContent = compositeContent;
    }

    public ICompositeContent<T> getCompositeContent() {
        return compositeContent;
    }

    public void setCompositeContent(ICompositeContent<T> compositeContent) {
        this.compositeContent = compositeContent;
    }

    @Override
    public void traverse() {
        this.compositeContent.processContent();
    }
}
