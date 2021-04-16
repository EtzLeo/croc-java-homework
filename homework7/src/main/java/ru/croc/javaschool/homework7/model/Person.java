package ru.croc.javaschool.homework7.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Человек.
 */
public class Person {
    /**
     * Идентификатор в базе.
     */
    private int id;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Дата рождения.
     */
    private LocalDate birthday;

    /**
     * Наличие детей.
     */
    private boolean haveChildren;

    /**
     * Почтовый индекс.
     */
    private int postalCode;

    public Person(String firstName, String lastName, LocalDate birthday, boolean haveChildren, int postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.haveChildren = haveChildren;
        this.postalCode = postalCode;
    }
    public Person(int id, String firstName, String lastName, LocalDate birthday, boolean haveChildren, int postalCode) {
        this(firstName, lastName, birthday, haveChildren, postalCode);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isHaveChildren() {
        return haveChildren;
    }

    public void setHaveChildren(boolean haveChildren) {
        this.haveChildren = haveChildren;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", haveChildren=" + haveChildren +
                ", postalCode=" + postalCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && haveChildren == person.haveChildren && postalCode == person.postalCode && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, haveChildren, postalCode);
    }
}
