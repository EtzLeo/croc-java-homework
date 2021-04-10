package ru.croc.javaschool.homework6;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.homework6.converter.ObjectsConverter;
import ru.croc.javaschool.homework6.model.film.Director;
import ru.croc.javaschool.homework6.model.film.Film;
import ru.croc.javaschool.homework6.model.film.FilmsList;
import ru.croc.javaschool.homework6.model.film.Screenwriter;
import ru.croc.javaschool.homework6.model.person.Functions;
import ru.croc.javaschool.homework6.model.person.Person;
import ru.croc.javaschool.homework6.model.person.PersonsList;
import ru.croc.javaschool.homework6.model.person.RolesInFilm;

import java.util.Arrays;

public class ObjectsConverterTest {

    @Test
    @Description("Тест конвертации списка фильмов в список людей")
    public void testConverter() throws Exception {
        FilmsList films = new FilmsList(
                Arrays.asList(
                        new Film(
                                "Фильм1",
                                "Описание1",
                                Arrays.asList(new Screenwriter("Человек1"), new Screenwriter("Человек2")),
                                Arrays.asList(new Director("Человек1"))),
                        new Film(
                                "Фильм2",
                                "Описание2",
                                Arrays.asList(new Screenwriter("Человек2")),
                                Arrays.asList(new Director("Человек1"), new Director("Человек2")))
                )
        );
        PersonsList persons = new PersonsList(
                Arrays.asList(
                        new Person(
                                "Человек1",
                                Arrays.asList(
                                        new RolesInFilm("Фильм1", Arrays.asList(
                                                new Functions("Режиссер"), new Functions("Сценарист"))),
                                        new RolesInFilm("Фильм2",  Arrays.asList(
                                                new Functions("Режиссер"))))),
                        new Person(
                                "Человек2",
                                Arrays.asList(
                                        new RolesInFilm("Фильм1", Arrays.asList(
                                                new Functions("Сценарист"))),
                                        new RolesInFilm("Фильм2",  Arrays.asList(
                                                new Functions("Режиссер"), new Functions("Сценарист"))))))
                );
        ObjectsConverter converter = new ObjectsConverter(films);
        PersonsList persons1 = converter.convertFilmsToPersons();
        Assertions.assertEquals(persons, persons1);
    }

}
