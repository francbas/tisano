package org.francescobasile.tisano.prove.hibernate;

import jakarta.persistence.*;
import org.francescobasile.tisano.prove.LocalEntityRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TreeStructureTest {
    public static void main(String[] args) {
        Componente nodo1 = new TreeNode<>("N1", new TreeContent<>(new TreeContent<>(LocalDateTime.now())));
        Componente nodo2 = new TreeNode<>("N2", new TreeContent<>(new TreeContent<>(LocalDateTime.now().plusDays(5))));
        Componente leaf1 = new TreeLeaf("L1");
        Componente leaf2 = new TreeLeaf("L2");
        Componente leaf3 = new TreeLeaf("L3");
        Componente leaf4 = new TreeLeaf("L4");
        Componente leaf5 = new TreeLeaf("L5");
        nodo1.addElemento(leaf1);
        nodo1.addElemento(leaf2);
        nodo1.addElemento(leaf3);
        nodo2.addElemento(leaf4);
        nodo2.addElemento(leaf5);
        nodo1.addElemento(nodo2);

        LocalEntityRepository.save(nodo1);
//        LocalEntityRepository.save(nodo2);
    }
}

@Entity
abstract class Componente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    protected Componente parent;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Set<Componente> elementi;
    @Basic(optional = false)
    protected String denominazione;

    public Componente() {
        this.elementi = new HashSet<>();
    }

    public Componente(String denominazione) {
        this();
        this.denominazione = denominazione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Componente getParent() {
        return parent;
    }

    public void setParent(Componente parent) {
        this.parent = parent;
    }

    public void addElemento(Componente elemento) {
        this.elementi.add(elemento);
        elemento.setParent(this);
    }

    public void removeElemento(Componente elemento) {
        this.elementi.remove(elemento);
        elemento.setParent(null);
    }

    public String getDenominazione() {
        return denominazione;
    }

    abstract public void setDenominazione(String denominazione);

    abstract void process();

}

@Entity
class TreeNode<T> extends Componente {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @Transient
    private TreeContent<T> content;

    public TreeNode() {
        super();
    }

    public TreeNode(String denominazione, TreeContent<T> content) {
        super(denominazione);
        this.content = content;
    }
    //    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }

    @Override
    public void process() {
        System.out.println("Nodo: " + getDenominazione());
    }

    @Override
    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione + "_node";
    }

    public TreeContent<T> getContent() {
        return this.content;
    }
}

@Entity
class TreeLeaf extends Componente {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    public TreeLeaf() {
        super();
    }

    public TreeLeaf(String denominazione) {
        super(denominazione);
    }

//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id = id;
//    }

    @Override
    public void process() {
        System.out.println("Leaf: " + getDenominazione());
    }

    @Override
    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione + "_leaf";
    }
}

class TreeContent<T> {
    private T content;

    public TreeContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}