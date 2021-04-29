package ru.croc.javaschool.homework8.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

/**
 * Человек.
 */
public abstract class Person<T> {
    /**
     * Имя.
     */
    @XmlElement(name = "name")
    private String name;

    /**
     * Фамилия.
     */
    @XmlElement(name = "surname")
    private String surname;

    /**
     * Отчество.
     */
    @XmlElement(name = "middlename")
    private String middleName;

    /**
     * Номер паспорта.
     */
    @XmlElement(name = "passportnumber")
    private String passportNumber;

    /**
     * Номер медицинского полиса.
     */
    @XmlElement(name = "medicalpolice")
    private String medicalPolicy;

    /**
     * Дата рождения.
     */
    @XmlElement(name = "birthdate")
    private T birthDate;

    public Person() {
    }

    public Person(String name, String surname, String middleName, String passportNumber, String medicalPolicy, T birthDate) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.passportNumber = passportNumber;
        this.medicalPolicy = medicalPolicy;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getMedicalPolicy() {
        return medicalPolicy;
    }

    public void setMedicalPolicy(String medicalPolicy) {
        this.medicalPolicy = medicalPolicy;
    }

    public T getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(T birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", medicalPolicy='" + medicalPolicy + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person<?> person = (Person<?>) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(middleName, person.middleName) && Objects.equals(passportNumber, person.passportNumber) && Objects.equals(medicalPolicy, person.medicalPolicy) && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, middleName, passportNumber, medicalPolicy, birthDate);
    }
}
