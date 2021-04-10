package ru.croc.javaschool.homework6.model.film;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Режиссер.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Director {
    /**
     * Режиссер.
     */
    @XmlAttribute(name = "name")
    private String director;

    public Director(String director) {
        this.director = director;
    }

    public Director() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public String toString() {
        return "Director{" +
                "director='" + director + '\'' +
                '}';
    }
}
