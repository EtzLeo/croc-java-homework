package ru.croc.javaschool.homework6.model.film;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Сценарист.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Screenwriter {
    /**
     * Сценарист.
     */
    @XmlAttribute(name = "name")
    private String screenwriter;

    public Screenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public Screenwriter() {
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    @Override
    public String toString() {
        return "Screenwriter{" +
                "screenwriter='" + screenwriter + '\'' +
                '}';
    }
}
