package ru.croc.javaschool.homework7.service;

import ru.croc.javaschool.homework7.model.Person;
import ru.croc.javaschool.homework7.repository.PersonRepository;

import java.util.List;

/**
 * Сервис для взаимодействия с репозиторием.
 */
public class PersonService {

    /**
     * Репозиторий для доступа к таблиуе с данными.
     */
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    /**
     * Вставляет новую строку данных о человеке в таблицу.
     *
     * @param person человек
     */
    public void insert(Person person) {
        repository.createNew(person);
    }

    /**
     * Возвращает все записи о людях из таблицы.
     *
     * @return список людей
     */
    public List<Person> getAll() {
        return repository.findAll();
    }

    /**
     * Возвращает запись о человеке по его ID.
     *
     * @param id идентификатор
     * @return человек
     */
    public Person getById(int id) {
        for(Person person: repository.findAll()) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    /**
     * Изменяет существующую запись о человеке.
     *
     * @param person человек
     */
    public void update(Person person) {
        repository.update(person);
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
     * @param person человек
     */
    public void delete(Person person) {
        repository.delete(person.getId());
    }
}
