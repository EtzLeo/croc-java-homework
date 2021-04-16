package ru.croc.javaschool.homework7;

import ru.croc.javaschool.homework7.dbprovider.DataSourceProvider;
import ru.croc.javaschool.homework7.model.Person;
import ru.croc.javaschool.homework7.repository.PersonRepository;
import ru.croc.javaschool.homework7.service.PersonService;

import java.io.IOException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws IOException {

        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        PersonRepository personRepository = new PersonRepository(dataSourceProvider.getDataSource(), "person");
        PersonService service = new PersonService(personRepository);
        Person sasha = new Person(1, "Sasha", "Ivanov",
                LocalDate.now(), true, 234567);
        Person pasha = new Person(2,  "Pasha", "Ivanov",
                LocalDate.now(), false, 456789);
        Person oleg = new Person(3, "Oleg", "Ivanov",
                LocalDate.now(), false, 194567);

        service.insert(sasha);
        service.insert(pasha);
        service.insert(oleg);

        oleg.setHaveChildren(true);
        service.update(oleg);

        service.delete(pasha);
        service.insert(pasha);
        pasha = service.getById(4);

        service.delete(4);

        service.getAll().forEach(System.out::println);

        dataSourceProvider.shutdown();
    }
}
