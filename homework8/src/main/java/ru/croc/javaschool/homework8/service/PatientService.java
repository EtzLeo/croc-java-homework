package ru.croc.javaschool.homework8.service;

import ru.croc.javaschool.homework8.model.Patient;
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
        if (getByDocuments(patient.getPassportNumber(), patient.getMedicalPolicy()).equals(new Patient())) {
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
        return repository.search(id);
    }

    /**
     * Возвращает запись о человеке по его данным паспорта и полиса.
     *
     * @param passportNumber номер паспорта
     * @param medicalPolicy номер полиса
     * @return человек
     */
    public Patient getByDocuments(String passportNumber, String medicalPolicy) {
        return repository.search(passportNumber, medicalPolicy);
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
