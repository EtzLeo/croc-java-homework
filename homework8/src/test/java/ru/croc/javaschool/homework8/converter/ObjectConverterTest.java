package ru.croc.javaschool.homework8.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework8.model.Patient;
import ru.croc.javaschool.homework8.model.xmlmarshaling.DischargedPatientsList;
import ru.croc.javaschool.homework8.model.xmlmarshaling.NewCasesList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ObjectConverterTest {
    @Test
    public void testConvert() throws IOException {
        JaxbConverter converter = new JaxbConverter();
        String patientsXml = "";
        String casesXml = "";
        try(BufferedReader reader = new BufferedReader(
                new FileReader("./src/test/resources/patientsTest.xml"));){
            String line;
            while ((line = reader.readLine()) != null) {
                patientsXml += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        try(BufferedReader reader = new BufferedReader(
                new FileReader("./src/test/resources/casesTest.xml"));){
            String line;
            while ((line = reader.readLine()) != null) {
                casesXml += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        DischargedPatientsList patients = converter.fromXml(patientsXml, DischargedPatientsList.class);
        NewCasesList cases = converter.fromXml(casesXml, NewCasesList.class);

        ObjectConverter objectConverter = new ObjectConverter();
        List<Patient> actualPatients = objectConverter.convert(patients, cases);

        List<Patient> expectedPatients = Arrays.asList(
                new Patient(
                        0,
                        "John",
                        "Doe",
                        null,
                        LocalDate.of(1990,4,13),
                        "0310 123456",
                        "123456789012345",
                        LocalDate.of(2021, 4,20),
                        null,
                        true),
                new Patient(
                        0,
                        "Ivanov",
                        "Ivan",
                        "Ivanovich",
                        LocalDate.of(1998,5,12),
                        "0000 123457",
                        "123456789012346",
                        LocalDate.of(2021, 4,20),
                        null,
                        true)
        );
        Assertions.assertEquals(expectedPatients, actualPatients);
    }
}
