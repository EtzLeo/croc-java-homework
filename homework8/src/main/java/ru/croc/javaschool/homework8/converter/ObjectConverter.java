package ru.croc.javaschool.homework8.converter;

import ru.croc.javaschool.homework8.model.dbperson.Patient;
import ru.croc.javaschool.homework8.model.xmlperson.newcase.CurePerson;
import ru.croc.javaschool.homework8.model.xmlperson.newcase.DiseasePerson;
import ru.croc.javaschool.homework8.model.xmlperson.newcase.NewCasesWrapper;
import ru.croc.javaschool.homework8.model.xmlperson.patient.DischargedPatientsWrapper;
import ru.croc.javaschool.homework8.model.xmlperson.patient.DischargedPerson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Преобразователь объектов.
 */
public class ObjectConverter {

    /**
     * Пациенты.
     */
    private List<Patient> patients = new ArrayList<>();

    /**
     * Преобразует объекты, сгенерированные из xml в объект для работы с БД.
     *
     * @param allPatients все выписанные пациенты
     * @param newCases новые случаи заболевания и(или) выздоровления
     * @return список пациентов
     */
    public List<Patient> convert(DischargedPatientsWrapper allPatients, NewCasesWrapper newCases){
        try{
            allPatients.getPatients().forEach(patient -> patients.add(convertObject(patient)));
        } catch (NullPointerException e) {
            System.out.println("Файл пуст: " + e.getMessage());
        }
        try{
            newCases.getSick().getPatients().forEach(patient -> patients.add(convertObject(patient)));
        } catch (NullPointerException e) {
            System.out.println("Файл пуст: " + e.getMessage());
        }
        try{
            newCases.getHealthy().getPatients().forEach(this::convertObject);
        } catch (NullPointerException e) {
            System.out.println("Файл пуст: " + e.getMessage());
        }
        return patients;
    }

    /**
     * Преобразователь объекта DischargedPerson в Patient.
     *
     * @param patient выписанный человек
     * @return пациент
     */
    private Patient convertObject(DischargedPerson patient){
        return new Patient(
                0,
                patient.getName(),
                patient.getSurname(),
                patient.getMiddleName(),
                convertDate(patient.getBirthDate()),
                patient.getPassportNumber(),
                patient.getMedicalPolicy(),
                convertDate(patient.getDiseaseDate()),
                convertDate(patient.getCureDate()),
                false
        );
    }

    /**
     * Изменяет данные объекта Patient, если он существует.
     * <p>
     *     Если запись о patient (поиск по паспорту и полису) есть в списке пациентов, то
     *     меняется дата выздоровления пауиента и он отмечается как выздоровевший.
     *     Иначе - выводится сообщение, что данного пациента нет,
     *     а также пациент записывается как новый выздоровевший, но без даты заболевания.
     * </p>
     * @param patient пациент
     */
    private void convertObject(CurePerson patient){
        Patient currentPatient =
                patients.
                        stream().
                        filter(p -> (
                                p.getMedicalPolicy().equals(patient.getMedicalPolicy()) &&
                                        p.getPassportNumber().equals(patient.getPassportNumber()))).
                        findFirst().
                        orElse(null);
        if (currentPatient == null) {
            System.out.println("Выздоровешего пациента нет в базе: " + patient);
            patients.add(new Patient(
                    0,
                    patient.getName(),
                    patient.getSurname(),
                    patient.getMiddleName(),
                    convertDate(patient.getBirthDate()),
                    patient.getPassportNumber(),
                    patient.getMedicalPolicy(),
                    null,
                    convertDate(patient.getCureDate()),
                    false
            ));
        }
        else {
            currentPatient.setCureDate(convertDate(patient.getCureDate()));
            currentPatient.setSick(false);
        }
    }

    /**
     * Преобразовывает DiseasePerson в Patient.
     *
     * @param patient новый заболевший человек
     * @return пациент
     */
    private Patient convertObject(DiseasePerson patient){
        return new Patient(
                0,
                patient.getName(),
                patient.getSurname(),
                patient.getMiddleName(),
                convertDate(patient.getBirthDate()),
                patient.getPassportNumber(),
                patient.getMedicalPolicy(),
                convertDate(patient.getDiseaseDate()),
                null,
                true
        );
    }

    /**
     * Преобразует строку типа "дд.мм.гггг" в дату.
     *
     * @param date строка даты
     * @return дата
     */
    private LocalDate convertDate(String date) {//TODO parse exception обработать
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, formatter);
    }
}
