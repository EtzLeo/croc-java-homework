package ru.croc.javaschool.homework8.model.xmlperson.newcase;

import ru.croc.javaschool.homework8.model.Person;
import javax.xml.bind.annotation.XmlElement;

/**
 * Здоровый пациент.
 */
public class CurePerson extends Person {
    /**
     * Дата рождения в виде строки.
     */
    @XmlElement(name = "birthdate")
    private String birthDate;

    /**
     * Дата выздоровления в виде строки.
     */
    @XmlElement(name = "curedate")
    private String cureDate;

    public CurePerson() {
    }

    public CurePerson(String name, String surname, String middleName, String birthDate,
                      String passportNumber, String medicalPolicy, String cureDate) {

        super(name, surname, middleName, passportNumber, medicalPolicy);
        this.cureDate = cureDate;
        this.birthDate = birthDate;
    }

    public String getCureDate() {
        return cureDate;
    }

    public void setCureDate(String cureDate) {
        this.cureDate = cureDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "CurePerson{" +
                "birthDate='" + birthDate + '\'' +
                ", cureDate='" + cureDate + '\'' +
                "} " + super.toString();
    }
}
