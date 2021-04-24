package ru.croc.javaschool.homework8.model.dbperson;

import ru.croc.javaschool.homework8.model.Person;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Пациент.
 */
public class Patient extends Person {
    /**
     * Идентификатор.
     */
    private int id;

    /**
     * Дата заболевания.
     */
    private LocalDate diseaseDate;

    /**
     * Дата выздоровления.
     */
    private LocalDate cureDate;

    /**
     * Болен ли пациент.
     */
    private boolean isSick;

    /**
     * Дата рождения.
     */
    private LocalDate birthDate;

    public Patient(int id, String name, String surname, String middleName, LocalDate birthDate,
                   String passportNumber, String medicalPolicy, LocalDate diseaseDate,
                   LocalDate cureDate, boolean isSick) {

        super(name, surname, middleName, passportNumber, medicalPolicy);
        this.id = id;
        this.diseaseDate = diseaseDate;
        this.cureDate = cureDate;
        this.isSick = isSick;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDiseaseDate() {
        return diseaseDate;
    }

    public void setDiseaseDate(LocalDate diseaseDate) {
        this.diseaseDate = diseaseDate;
    }

    public LocalDate getCureDate() {
        return cureDate;
    }

    public void setCureDate(LocalDate cureDate) {
        this.cureDate = cureDate;
    }

    public boolean isSick() {
        return isSick;
    }

    public void setSick(boolean sick) {
        isSick = sick;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", diseaseDate=" + diseaseDate +
                ", cureDate=" + cureDate +
                ", isSick=" + isSick +
                ", birthDate=" + birthDate +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return id == patient.id && isSick == patient.isSick && Objects.equals(diseaseDate, patient.diseaseDate) &&
                Objects.equals(cureDate, patient.cureDate) && Objects.equals(birthDate, patient.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, diseaseDate, cureDate, isSick, birthDate);
    }
}
