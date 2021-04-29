package ru.croc.javaschool.homework8.model;

import ru.croc.javaschool.homework8.model.Person;
import javax.xml.bind.annotation.XmlElement;

/**
 * Здоровый пациент.
 */
public class CurePerson extends Person<String> {
    /**
     * Дата выздоровления в виде строки.
     */
    @XmlElement(name = "curedate")
    private String cureDate;

    public CurePerson() {
    }

    public CurePerson(String name, String surname, String middleName, String birthDate,
                      String passportNumber, String medicalPolicy, String cureDate) {

        super(name, surname, middleName, passportNumber, medicalPolicy, birthDate);
        this.cureDate = cureDate;
    }

    public String getCureDate() {
        return cureDate;
    }

    public void setCureDate(String cureDate) {
        this.cureDate = cureDate;
    }

    @Override
    public String toString() {
        return "CurePerson{" +
                "cureDate='" + cureDate + '\'' +
                "} " + super.toString();
    }
}
