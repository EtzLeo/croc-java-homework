package ru.croc.javaschool.homework6.model.person;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

/**
 * Человек.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "name", "films" })
public class Person {
    /**
     * Имя.
     */
    @XmlElement
    private String name;

    /**
     * Список из списка фильмов и ролей (режиссер или сценарист) в фильме.
     */
    @XmlElement(name = "film")
    @XmlElementWrapper
    private List<RolesInFilm> films;

    public Person() {
    }

    public Person(String name, List<RolesInFilm> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RolesInFilm> getFilms() {
        return films;
    }

    public void setFilms(List<RolesInFilm> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", films=" + films +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(films, person.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }
}
