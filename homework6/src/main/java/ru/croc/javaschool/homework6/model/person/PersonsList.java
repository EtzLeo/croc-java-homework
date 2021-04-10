package ru.croc.javaschool.homework6.model.person;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Список людей.
 * Служит для корректной сериализации данных.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "people")
public class PersonsList {
    /**
     * Список людей.
     */
    @XmlElement(name="person")
    private List<Person> persons = new ArrayList<>();

    public PersonsList(List<Person> persons) {
        this.persons = persons;
    }

    public PersonsList() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "PersonsList{" +
                "persons=" + persons +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonsList that = (PersonsList) o;
        return Objects.equals(persons, that.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons);
    }
}
