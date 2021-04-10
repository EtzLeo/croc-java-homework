package ru.croc.javaschool.homework6.model.film;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Фильм.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "title", "description", "screenwriters", "directors" })
public class Film {
    /**
     * Название.
     */
    @XmlElement
    private String title;

    /**
     * Описание.
     */
    @XmlElement
    private String description;

    /**
     * Список сценаристов.
     */
    @XmlElement(name = "screenwriter")
    @XmlElementWrapper
    private List<Screenwriter> screenwriters;

    /**
     * Список режжисеров.
     */
    @XmlElement(name = "director")
    @XmlElementWrapper
    private List<Director> directors;

    public Film(String title, String description, List<Screenwriter> screenwriters, List<Director> directors) {
        this.title = title;
        this.description = description;
        this.screenwriters = screenwriters;
        this.directors = directors;
    }
    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Screenwriter> getScreenwriters() {
        return screenwriters;
    }

    public void setScreenwriters(List<Screenwriter> screenwriters) {
        this.screenwriters = screenwriters;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", screenwriters=" + screenwriters +
                ", directors=" + directors +
                '}';
    }
}
