package ru.croc.javaschool.homework8.model.xmlmarshaling;

import ru.croc.javaschool.homework8.model.CurePerson;
import ru.croc.javaschool.homework8.model.DiseasePerson;

import javax.xml.bind.annotation.*;

/**
 * Список новых случаев заболевания и выздоровления.
 * Служит для корректной сериализации данных.
 */
@XmlRootElement(name = "patients")
public class NewCasesList {
    /**
     * Больные пациенты.
     */
    private PatientsList<DiseasePerson> sick;

    /**
     * Здоровые пациенты.
     */
    private PatientsList<CurePerson> healthy;

    public NewCasesList() {
    }

    public NewCasesList(PatientsList<DiseasePerson> sick, PatientsList<CurePerson> healthy) {
        this.sick = sick;
        this.healthy = healthy;
    }

    public PatientsList<DiseasePerson> getSick() {
        return sick;
    }

    public void setSick(PatientsList<DiseasePerson> sick) {
        this.sick = sick;
    }

    public PatientsList<CurePerson> getHealthy() {
        return healthy;
    }

    public void setHealthy(PatientsList<CurePerson> healthy) {
        this.healthy = healthy;
    }

    @Override
    public String toString() {
        return "NewCasesWrapper{" +
                "sick=" + sick +
                ", healthy=" + healthy +
                '}';
    }
}

