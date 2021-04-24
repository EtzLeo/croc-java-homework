package ru.croc.javaschool.homework8.model.xmlperson.newcase;

import javax.xml.bind.annotation.*;

/**
 * Wrapper для новых случаев заболевания и выздоровления.
 */
@XmlRootElement(name = "patients")
public class NewCasesWrapper {
    /**
     * Больные пациенты.
     */
    private PatientWrapper<DiseasePerson> sick;

    /**
     * Здоровые пациенты.
     */
    private PatientWrapper<CurePerson> healthy;

    public NewCasesWrapper() {
    }

    public NewCasesWrapper(PatientWrapper<DiseasePerson> sick, PatientWrapper<CurePerson> healthy) {
        this.sick = sick;
        this.healthy = healthy;
    }

    public PatientWrapper<DiseasePerson> getSick() {
        return sick;
    }

    public void setSick(PatientWrapper<DiseasePerson> sick) {
        this.sick = sick;
    }

    public PatientWrapper<CurePerson> getHealthy() {
        return healthy;
    }

    public void setHealthy(PatientWrapper<CurePerson> healthy) {
        this.healthy = healthy;
    }

    @Override
    public String toString() {
        return "NewCasesWrapper{" +
                "sick=" + sick +
                ", healthy=" + healthy +
                '}';
    }
//    public NewCasesWrapper(List<DiseasePerson> sick, List<CurePerson> healthy) {
//        this.sick = new PatientWrapper<DiseasePerson>(sick);
//        this.healthy = new PatientWrapper<CurePerson>(healthy);
//    }
//
//    public List<DiseasePerson> getSick() {
//        return sick.getPatients();
//    }
//
//    public List<CurePerson> getHealthy() {
//        return healthy.getPatients();
//    }
//
//    public void setHealthy(List<CurePerson> healthy) {
//        this.healthy.setPatients(healthy);
//    }
//    public void setSick(List<DiseasePerson> sick) {
//        this.sick.setPatients(sick);
//    }
}

