package ru.croc.javaschool.homework8.model.xmlperson.patient;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Wrapper для выписанных пациентов.
 */
@XmlRootElement(name = "patients")
public class DischargedPatientsWrapper {
    /**
     * Пациенты.
     */
    @XmlElement(name = "patient")
    private List<DischargedPerson> patients;

    public DischargedPatientsWrapper() {
    }

    public DischargedPatientsWrapper(List<DischargedPerson> patients) {
        this.patients = patients;
    }

    public List<DischargedPerson> getPatients() {
        return patients;
    }

    public void setPatients(List<DischargedPerson> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "DischargedPatientsWrapper{" +
                "patients=" + patients +
                '}';
    }
}
