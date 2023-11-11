package org.francescobasile.tisano.prove.hibernate;

import jakarta.persistence.*;
import org.francescobasile.tisano.prove.LocalEntityRepository;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Association {
    public static void main(String[] args) {

        Person persona1 = new Person(new Name("nome01", "cognome01"));
        Person persona2 = new Person(new Name("nome02", "cognome02"));
        Person persona3 = new Person(new Name("nome03", "cognome03"));
        Author autore1 = new Author();
        Author autore2 = new Author();
        Author autore3 = new Author();

        autore1.setPerson(persona1);
        autore2.setPerson(persona2);
        autore3.setPerson(persona3);

        Book libro1 = new Book("12345", "Titolo01");
        Book libro2 = new Book("34567", "Titolo02");
        Book libro3 = new Book("67890", "Titolo03");
        Book libro4 = new Book("09876", "Titolo04");

        autore1.addBook(libro1);
        autore1.addBook(libro2);
        autore1.addBook(libro4);
        autore2.addBook(libro3);
        autore3.addBook(libro2);
        autore3.addBook(libro4);

        LocalEntityRepository.save(autore1);
        LocalEntityRepository.save(autore2);
        LocalEntityRepository.save(autore3);
//
//        LocalEntityRepository.save(autore1);
//        LocalEntityRepository.save(autore2);
//        LocalEntityRepository.save(autore3);
//        LocalEntityRepository.save(libro1);
//        LocalEntityRepository.save(libro2);
//        LocalEntityRepository.save(libro3);
//        LocalEntityRepository.save(libro4);

        Person persona4 = LocalEntityRepository.find(Person.class, 1);
        System.out.println(persona4.getName().getCognome());
        persona4.getAuthor().getBooks().forEach(b -> System.out.println(b.getIsbn() + ", " + b.getTitolo()));

        LocalEntityRepository.close();
    }
}

@Entity
class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @OneToOne(mappedBy = Author_.PERSON)
    private Author author;

    public Person() {
    }

    public Person(Name name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person o2n)) return false;
        return Objects.equals(getId(), o2n.getId()) && Objects.equals(getName(), o2n.getName()) && Objects.equals(getAuthor(), o2n.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName().getCognome(), getName().getNome());
    }
}

@Entity
class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Person person;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Book> books;

    public Author() {
        this.books = new HashSet<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        person.setAuthor(this);
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.addAutore(this);
    }
}

@Embeddable
class Name {
    private String nome;
    private String cognome;

    public Name() {
    }

    public Name(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}

@Entity
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String titolo;

    @ManyToMany(mappedBy = Author_.BOOKS)
    private Set<Author> autori;

    public Book() {
        this(null, null);
    }

    public Book(String isbn, String titolo) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autori = new HashSet<>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Set<Author> getAutori() {
        return autori;
    }

    public void addAutore(Author autore) {
        this.autori.add(autore);
    }
}
