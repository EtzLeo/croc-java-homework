package ru.croc.javaschool.homework6.model.person;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

/**
 * Роли в фильме.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RolesInFilm {
    /**
     * Название фильма.
     */
    @XmlAttribute(name = "title")
    private String film;

    /**
     * Список ролей.
     */
    @XmlElement(name = "function")
    @XmlElementWrapper
    private List<Functions> functions;

    public RolesInFilm() {
    }

    public RolesInFilm(String film, List<Functions> functions) {
        this.film = film;
        this.functions = functions;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public List<Functions> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Functions> functions) {
        this.functions = functions;
    }

    @Override
    public String toString() {
        return "FilmsForPersonList{" +
                "films='" + film + '\'' +
                ", functions=" + functions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesInFilm that = (RolesInFilm) o;
        return Objects.equals(film, that.film) && Objects.equals(functions, that.functions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, functions);
    }
}
