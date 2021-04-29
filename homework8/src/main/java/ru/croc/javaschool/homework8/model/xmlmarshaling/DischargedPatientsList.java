package ru.croc.javaschool.homework8.model.xmlmarshaling;

import ru.croc.javaschool.homework8.model.DischargedPerson;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Список выписанных пациентов.
 * Служит для корректной сериализации данных.
 */
@XmlRootElement(name = "patients")
public class DischargedPatientsList {
    /**
     * Пациенты.
     */
    @XmlElement(name = "patient")
    private List<DischargedPerson> patients;

    public DischargedPatientsList() {
    }

    public DischargedPatientsList(List<DischargedPerson> patients) {
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
