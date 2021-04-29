package ru.croc.javaschool.homework8.model;

import ru.croc.javaschool.homework8.model.Person;

import javax.xml.bind.annotation.XmlElement;

/**
 * Больной пациент.
 */
public class DiseasePerson extends Person<String> {
    /**
     * Дата заболевания в виде строки.
     */
    @XmlElement(name = "disasedate")
    private String diseaseDate;

    public DiseasePerson() {
    }

    public DiseasePerson(String name, String surname, String middleName, String birthDate,
                         String passportNumber, String medicalPolicy, String diseaseDate) {
        super(name, surname, middleName, passportNumber, medicalPolicy, birthDate);
        this.diseaseDate = diseaseDate;
    }

    public String getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(String diseaseDate) {
        this.diseaseDate = diseaseDate;
    }

    @Override
    public String toString() {
        return "DiseasePerson{" +
                "diseaseDate='" + diseaseDate + '\'' +
                "} " + super.toString();
    }
}
