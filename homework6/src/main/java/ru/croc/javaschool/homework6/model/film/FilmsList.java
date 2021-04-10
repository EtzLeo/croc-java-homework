package ru.croc.javaschool.homework6.model.film;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список фильмов.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "films")
public class FilmsList {
    /**
     * Список фильмов.
     */
    @XmlElement(name="film")
    private List<Film> filmsList;

    public FilmsList() {
    }

    public FilmsList(List<Film> filmsList) {
        this.filmsList = filmsList;
    }

    public List<Film> getFilmsList() {
        return filmsList;
    }

    public void setFilmsList(List<Film> filmsList) {
        this.filmsList = filmsList;
    }

    @Override
    public String toString() {
        return "FilmsList{" +
                "filmsList=" + filmsList +
                '}';
    }
}
