package ru.croc.javaschool.homework8;

import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework8.converter.JaxbConverter;
import ru.croc.javaschool.homework8.converter.ObjectConverter;
import ru.croc.javaschool.homework8.dbprovider.DataSourceProvider;
import ru.croc.javaschool.homework8.model.dbperson.Patient;
import ru.croc.javaschool.homework8.model.xmlperson.patient.DischargedPatientsWrapper;
import ru.croc.javaschool.homework8.model.xmlperson.newcase.NewCasesWrapper;
import ru.croc.javaschool.homework8.repository.PatientRepository;
import ru.croc.javaschool.homework8.service.PatientService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Demo {
    @Test
    public void demo() throws IOException {
        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        PatientRepository personRepository = new PatientRepository(
                dataSourceProvider.getDataSource(),
                "patient"
        );
        PatientService service = new PatientService(personRepository);

        JaxbConverter converter = new JaxbConverter();

        String patientsXml = "";
        String casesXml = "";

        try(BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/patients.xml"));){
            String line;
            while ((line = reader.readLine()) != null) {
                patientsXml += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        try(BufferedReader reader = new BufferedReader(
                new FileReader("./src/main/resources/cases.xml"));){
            String line;
            while ((line = reader.readLine()) != null) {
                casesXml += line + "\n";
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        DischargedPatientsWrapper patients = converter.fromXml(patientsXml, DischargedPatientsWrapper.class);
        NewCasesWrapper cases = converter.fromXml(casesXml, NewCasesWrapper.class);

        ObjectConverter objectConverter = new ObjectConverter();
        List<Patient> actualPatients = objectConverter.convert(patients, cases);

        actualPatients.forEach(service::insert);
        System.out.println("Записи базы данных:\n");
        service.getAll().forEach(System.out::println);

        dataSourceProvider.shutdown();
    }
}
