package ru.croc.javaschool.homework8.model.xmlperson.newcase;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Wrapper для пациентов.
 *
 * @param <T> тип пауиента.
 */
public class PatientWrapper<T> {
    /**
     * Пациенты.
     */
    @XmlElement(name = "patient")
    private List<T> patients;

    public PatientWrapper() {
    }

    public PatientWrapper(List<T> patients) {
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
