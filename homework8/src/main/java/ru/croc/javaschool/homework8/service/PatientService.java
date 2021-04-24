package ru.croc.javaschool.homework8.service;

import ru.croc.javaschool.homework8.model.dbperson.Patient;
import ru.croc.javaschool.homework8.repository.PatientRepository;

import java.util.List;

/**
 * Сервис для взаимодействия с репозиторием.
 */
public class PatientService {

    /**
     * Репозиторий для доступа к таблице с данными.
     */
    private final PatientRepository repository;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    /**
     * Вставляет новую строку данных о человеке в таблицу, если записи о нем нет в таблице.
     *
     * @param patient человек
     */
    public void insert(Patient patient) {
        if (!search(patient)) {
            repository.createNew(patient);
        }
    }

    /**
     * Возвращает все записи о людях из таблицы.
     *
     * @return список людей
     */
    public List<Patient> getAll() {
        return repository.findAll();
    }

    /**
     * Возвращает запись о человеке по его ID.
     *
     * @param id идентификатор
     * @return человек
     */
    public Patient getById(int id) {
        for(Patient patient: repository.findAll()) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    /**
     * Есть ли человек в бд.
     *
     * @param currentPatient искомый человек.
     * @return true - если запись о человеке есть в бд, false - если ее там нет.
     */
    public boolean search(Patient currentPatient) {
        for(Patient patient: repository.findAll()) {
            if (patient.getMedicalPolicy().equals(currentPatient.getMedicalPolicy()) &&
                    patient.getPassportNumber().equals(currentPatient.getPassportNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Изменяет существующую запись о человеке.
     *
     * @param patient человек
     */
    public void update(Patient patient) {
        repository.update(patient);
    }

    /**
     * Удаляет запись по ID.
     *
     * @param id идентификатор
     */
    public void delete(int id) {
        repository.delete(id);
    }

    /**
     * Удаляет запись по ID.
     *
     * @param patient человек
     */
    public void delete(Patient patient) {
        repository.delete(patient.getId());
    }
}
