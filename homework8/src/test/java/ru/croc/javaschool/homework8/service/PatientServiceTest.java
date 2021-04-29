package ru.croc.javaschool.homework8.service;

import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework8.dbprovider.DataSourceProvider;
import ru.croc.javaschool.homework8.model.Patient;
import ru.croc.javaschool.homework8.repository.PatientRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientServiceTest {
    DataSourceProvider dataSourceProvider;
    PatientRepository patientRepository;
    PatientService service;
    Patient sasha;
    Patient pasha;

    @BeforeEach
    void init() throws IOException {
        dataSourceProvider = new DataSourceProvider();

        try(Connection connection = dataSourceProvider.getDataSource().getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE test_patient");
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

        patientRepository = new PatientRepository(dataSourceProvider.getDataSource(), "test_patient");
        service = new PatientService(patientRepository);

        sasha = new Patient( 1,"Sasha", "Ivanov", "Ivanovich",
                LocalDate.now(), "0103", "111",
                LocalDate.now(), null, true);
        service.insert(sasha);

        pasha = new Patient( 2, "Pasha", "Ivanov", "Pavlovich",
                LocalDate.now(), "0203", "112", LocalDate.now(),
                LocalDate.now(), false);
        service.insert(pasha);
    }

    @Test
    @Description("Тест возвращения всех записей БД.")
    public void testGetAll() throws IOException {
        List<Patient> people = new ArrayList<>(Arrays.asList(sasha, pasha));
        Assertions.assertEquals(people, service.getAll());

        Patient masha = new Patient(
                3,
                "Masha",
                "Ivanova",
                "Ivanovna",
                LocalDate.now(),
                "0303",
                "113",
                LocalDate.now(),
                null,
                true);

        service.insert(masha);
        people.add(2,masha);
        Assertions.assertEquals(people, service.getAll());

        service.delete(1);
        people.remove(0);
        Assertions.assertEquals(people, service.getAll());
    }

    @Test
    @Description("Тест возвращения записи БД по ID.")
    public void testGetById() throws IOException {
        Assertions.assertEquals(sasha, service.getById(1));
        Assertions.assertEquals(new Patient(), service.getById(-1));
        Assertions.assertEquals(new Patient(), service.getById(4));
    }
    @Test
    @Description("Тест возвращения записи БД по данным паспорта и полиса.")
    public void testGetByDocuments() throws IOException {

        Assertions.assertEquals(sasha, service.getByDocuments("0103", "111"));
        Assertions.assertEquals(new Patient(), service.getByDocuments("1010", "1010"));
    }

    @Test
    @Description("Тест удаления записи БД.")
    public void testDelete() throws IOException {
        Assertions.assertEquals(service.getById(1), sasha);
        service.delete(1);
        Assertions.assertEquals(new Patient(), service.getById(1));

        Assertions.assertEquals(service.getById(2),pasha);
        service.delete(pasha);
        Assertions.assertEquals(new Patient(), service.getById(2));
    }

    @Test
    @Description("Тест обновления записи БД.")
    public void testUpdate() throws IOException {
        Assertions.assertEquals(service.getById(2), pasha);

        Patient oleg = new Patient(
                2,
                "Oleg",
                "Ivanov",
                "Olegovich",
                LocalDate.now(),
                "0203",
                "112",
                LocalDate.now(),
                LocalDate.now(),
                false);

        service.update(oleg);
        Assertions.assertEquals(service.getById(2), oleg);
    }

    @Test
    @Description("Тест создания записи БД.")
    public void testInsert() {
        Patient oleg = new Patient(
                3,
                "Oleg",
                "Ivanov",
                "Olegovich",
                LocalDate.now(),
                "0303",
                "113",
                LocalDate.now(),
                LocalDate.now(),
                false);

        service.insert(oleg);
        Assertions.assertEquals(service.getById(3), oleg);

        service.insert(new Patient(
                5,
                "Oleg",
                "Ivanov",
                "Olegovich",
                LocalDate.now(),
                "0303",
                "113",
                LocalDate.now(),
                LocalDate.now(),
                false)
        );

        Assertions.assertEquals(new Patient(), service.getById(5));
    }

    @AfterEach
    public void closeConnection() {
        dataSourceProvider.shutdown();
    }
}
