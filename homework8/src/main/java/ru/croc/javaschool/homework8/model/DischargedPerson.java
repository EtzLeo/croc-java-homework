package ru.croc.javaschool.homework8.model;

import ru.croc.javaschool.homework8.model.Person;

import javax.xml.bind.annotation.XmlElement;

/**
 * Выписанный пациент.
 */
public class DischargedPerson extends Person<String> {
    /**
     * Дата заболевания в виде строки.
     */
    @XmlElement(name = "lastdisasedate")
    private String diseaseDate;

    /**
     * Дата выздоровления в виде строки.
     */
    @XmlElement(name = "lastcuredate")
    private String cureDate;

    public DischargedPerson() {
    }

    public DischargedPerson(String name, String surname, String middleName, String birthDate,
                            String passportNumber, String medicalPolicy, String diseaseDate,
                            String cureDate) {

        super(name, surname, middleName, passportNumber, medicalPolicy, birthDate);
        this.diseaseDate = diseaseDate;
        this.cureDate = cureDate;
    }

    public String getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(String diseaseDate) {
        this.diseaseDate = diseaseDate;
    }

    public String getCureDate() {
        return cureDate;
    }

    public void setCureDate(String cureDate) {
        this.cureDate = cureDate;
    }

    @Override
    public String toString() {
        return "DischargedPerson{" +
                "diseaseDate='" + diseaseDate + '\'' +
                ", cureDate='" + cureDate + '\'' +
                "} " + super.toString();
    }
}
