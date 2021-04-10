package ru.croc.javaschool.homework6;

import ru.croc.javaschool.homework6.converter.JaxbConverter;
import ru.croc.javaschool.homework6.converter.ObjectsConverter;
import ru.croc.javaschool.homework6.model.film.FilmsList;
import ru.croc.javaschool.homework6.model.person.PersonsList;

import java.io.*;

public class App {
    public static void main(String[] args) throws IOException {
        final JaxbConverter converter = new JaxbConverter();
        BufferedReader reader = new BufferedReader(new FileReader("rawData.xml"));
        String xml = "";
        String line;
        while ((line = reader.readLine()) != null) {
            xml += line + "\n";
        }
        reader.close();

        FilmsList films = converter.fromXml(xml, FilmsList.class);

        ObjectsConverter convertToPerson = new ObjectsConverter(films);
        PersonsList personsList = convertToPerson.convertFilmsToPersons();

        String xmlConverter = converter.toXml(personsList);

        BufferedWriter writer = new BufferedWriter(new FileWriter("processedData.xml"));
        writer.write(xmlConverter);
        writer.close();
    }
}
