package ru.croc.javaschool.homework8.model.xmlperson.newcase;

import ru.croc.javaschool.homework8.model.Person;

import javax.xml.bind.annotation.XmlElement;

/**
 * Больной пациент.
 */
public class DiseasePerson extends Person {
    /**
     * Дата рождения в виде строки.
     */
    @XmlElement(name = "birthdate")
    private String birthDate;

    /**
     * Дата заболевания в виде строки.
     */
    @XmlElement(name = "disasedate")
    private String diseaseDate;

    public DiseasePerson() {
    }

    public DiseasePerson(String name, String surname, String middleName, String birthDate,
                         String passportNumber, String medicalPolicy, String diseaseDate) {
        super(name, surname, middleName, passportNumber, medicalPolicy);
        this.diseaseDate = diseaseDate;
        this.birthDate = birthDate;
    }

    public String getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(String diseaseDate) {
        this.diseaseDate = diseaseDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "DiseasePerson{" +
                "birthDate='" + birthDate + '\'' +
                ", diseaseDate='" + diseaseDate + '\'' +
                "} " + super.toString();
    }
}
