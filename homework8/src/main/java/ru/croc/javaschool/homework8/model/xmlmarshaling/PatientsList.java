package ru.croc.javaschool.homework8.model.xmlmarshaling;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Список пациентов.
 * Служит для корректной сериализации данных.
 *
 * @param <T> тип пациента.
 */
public class PatientsList<T> {
    /**
     * Пациенты.
     */
    @XmlElement(name = "patient")
    private List<T> patients;

    public PatientsList() {
    }

    public PatientsList(List<T> patients) {
        this.patients = patients;
    }

    public List<T> getPatients() {
        return patients;
    }

    public void setPatients(List<T> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "PatientWrapper{" +
                "patients=" + patients +
                '}';
    }
}
