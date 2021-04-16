package ru.croc.javaschool;

import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import ru.croc.javaschool.homework7.dbprovider.DataSourceProvider;
import ru.croc.javaschool.homework7.model.Person;
import ru.croc.javaschool.homework7.repository.PersonRepository;
import ru.croc.javaschool.homework7.service.PersonService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonServiceTest {

    DataSourceProvider dataSourceProvider;
    PersonRepository personRepository;
    PersonService service;
    Person sasha;
    Person pasha;

    @BeforeEach
    void init() throws IOException {
        dataSourceProvider = new DataSourceProvider();

        try(Connection connection = dataSourceProvider.getDataSource().getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE test_person");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        personRepository = new PersonRepository(dataSourceProvider.getDataSource(), "test_person");
        service = new PersonService(personRepository);

        sasha = new Person( 1,"Sasha", "Ivanov",
                LocalDate.now(), true, 123456);
        service.insert(sasha);

        pasha = new Person( 2, "Pasha", "Ivanov",
                LocalDate.now(), true, 345678);
        service.insert(pasha);
    }

    @Test
    @Description("Тест возвращения всех записей БД.")
    public void testGetAll() throws IOException {
        List<Person> people = new ArrayList<>(Arrays.asList(sasha, pasha));
        Assertions.assertEquals(people, service.getAll());

        Person masha = new Person(
                3,
                "Masha",
                "Ivanova",
                LocalDate.now(),
                true,
                123456);

        service.insert(masha);
        people.add(2,masha);
        Assertions.assertEquals(people, service.getAll());

        service.delete(1);
        people.remove(0);
        Assertions.assertEquals(people, service.getAll());
    }

    @Test
    @Description("Тест возвращения всех записей БД.")
    public void testGetById() throws IOException {

        Assertions.assertEquals(service.getById(1), sasha);
        Assertions.assertNull(service.getById(-1));
        Assertions.assertNull(service.getById(4));
    }

    @Test
    @Description("Тест удаления записи БД.")
    public void testDelete() throws IOException {
        Assertions.assertEquals(service.getById(1), sasha);
        service.delete(1);
        Assertions.assertNull(service.getById(1));

        Assertions.assertEquals(service.getById(2),pasha);
        service.delete(pasha);
        Assertions.assertNull(service.getById(2));
    }

    @Test
    @Description("Тест обновления записи БД.")
    public void testUpdate() throws IOException {
        Assertions.assertEquals(service.getById(2), pasha);

        Person oleg = new Person(
                2,
                "Oleg",
                "Ivanov",
                LocalDate.now(),
                false,
                101012);

        service.update(oleg);

        Assertions.assertEquals(service.getById(2), oleg);

    }

    @Test
    @Description("Тест создания записи БД.")
    public void testInsert() {
        Person oleg = new Person(
                3,
                "Oleg",
                "Ivanov",
                LocalDate.now(),
                false,
                101012);

        service.insert(oleg);
        Assertions.assertEquals(service.getById(3), oleg);

        service.insert(new Person("Oleg", "Ivanov",
                LocalDate.now(), false, 456378));
        Assertions.assertEquals(service.getById(4), new Person(4,"Oleg", "Ivanov",
                LocalDate.now(), false, 456378));
    }

    @AfterEach
    public void closeConnection() {
        dataSourceProvider.shutdown();
    }
}
