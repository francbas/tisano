package org.francescobasile.tisano.prove;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LambdaTest {

    public static void main(String[] args) {
        Persona p1 = new Persona(Persona.GENDER.MALE, 10, "1@1");
        Persona p2 = new Persona(Persona.GENDER.MALE, 20, "2@2");
        Persona p3 = new Persona(Persona.GENDER.MALE, 30, "3@3");
        Persona p4 = new Persona(Persona.GENDER.MALE, 40, "4@4");

        List<Persona> lp = process(Arrays.asList(p1, p2, p3, p4));

    }

    private static List<Persona> process(List<Persona> lista) {
        lista.stream()
                .filter(p ->
                        p.getGender() == Persona.GENDER.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25)
                .map(Persona::getEmail)
                .forEach(System.out::println);

        return lista;
    }

}

class Predicate<T extends Comparable<T>> implements Comparable<T> {
    @Override
    public int compareTo(T o) {
        return 0;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

class Persona {
    private GENDER gender;
    private int age;
    private String email;

    public Persona(GENDER gender, int age, String email) {
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public Enum<GENDER> getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    enum GENDER {
        MALE, FEMALE
    }

    @Override
    public String toString() {
        return "Persona{" + "email='" + email + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        Persona persona = (Persona) o;
        return Objects.equals(getEmail(), persona.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
