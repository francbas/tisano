package org.francescobasile.tisano.prove.composite;

public class ResourceComposite<T> extends Composite {

    private ICompositeContent<T> compositeContent;

    public ResourceComposite(ICompositeContent<T> compositeContent) {
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
        super.traverse();
    }
}