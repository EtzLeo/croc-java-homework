package ru.croc.javaschool.homework6.converter;

import ru.croc.javaschool.homework6.model.film.Director;
import ru.croc.javaschool.homework6.model.film.Film;
import ru.croc.javaschool.homework6.model.film.FilmsList;
import ru.croc.javaschool.homework6.model.film.Screenwriter;
import ru.croc.javaschool.homework6.model.person.RolesInFilm;
import ru.croc.javaschool.homework6.model.person.Functions;
import ru.croc.javaschool.homework6.model.person.Person;
import ru.croc.javaschool.homework6.model.person.PersonsList;

import java.util.*;

/**
 * Конвертер объекта {@link FilmsList} в объект {@link PersonsList}.
 * Преобразование происходит через map, в которой хранятся имена людей в качетве ключа.
 * В качестве значения - еще одна map, в которой ключ - название фильма, значение - множество ролей в фильме.
 */
public class ObjectsConverter {
    /**
     * Список фильмов.
     */
    private FilmsList films;

    public ObjectsConverter(FilmsList films) {
        this.films = films;
    }

    /**
     * Преобразование map в список людей.
     *
     * @return список людей
     */
    public PersonsList convertFilmsToPersons() {
        Map<String, Map<String, Set<String>>> map = convertFilmToMap();

        List<Person> personList = new ArrayList<>();
        map.forEach((name, film) -> {
            List<RolesInFilm> films = new ArrayList<>();

            film.forEach((title, roles) -> {
                List<Functions> functions = new ArrayList<>();

                roles.forEach(role -> {
                    functions.add(new Functions(role));
                });
                films.add(new RolesInFilm(title, functions));

            });
            personList.add(new Person(name, films));
        });

        PersonsList persons = new PersonsList(personList);
        return persons;
    }

    /**
     * Добавление человека в map.
     *
     * @param title название фильма
     * @param screenwriter сценарист
     * @param map словарь
     */
    private void addPersonToMap(String title, Screenwriter screenwriter, Map<String, Map<String, Set<String>>> map) {
        Map<String, Set<String>> filmsMap = new TreeMap<>();
        Set<String> rolesSet = new HashSet<>();

        rolesSet.add("Сценарист");
        if (map.containsKey(screenwriter.getScreenwriter())) {
            filmsMap.putAll(map.get(screenwriter.getScreenwriter()));
            try {
                rolesSet.addAll(map.get(screenwriter.getScreenwriter()).get(title));
            } catch (NullPointerException ignored) {
            }
        }
        filmsMap.put(title, rolesSet);
        map.put(screenwriter.getScreenwriter(), filmsMap);
    }

    /**
     * Добавление человека в map.
     *
     * @param title название фильма
     * @param director режиссер
     * @param map словарь
     */
    private void addPersonToMap(String title, Director director, Map<String, Map<String, Set<String>>> map) {
        Map<String, Set<String>> filmsMap = new TreeMap<>();
        Set<String> rolesSet = new HashSet<>();

        rolesSet.add("Режиссер");
        if (map.containsKey(director.getDirector())) {
            filmsMap.putAll(map.get(director.getDirector()));
            try {
                rolesSet.addAll(map.get(director.getDirector()).get(title));
            } catch (NullPointerException ignored) {
            }
        }
        filmsMap.put(title, rolesSet);
        map.put(director.getDirector(), filmsMap);
    }

    /**
     * Преобразование фильма в map.
     *
     * @return map
     */
    private Map<String, Map<String, Set<String>>> convertFilmToMap() {
        Map<String, Map<String, Set<String>>> map = new TreeMap<>();

        for (Film film: films.getFilmsList()) {

            if (!film.getScreenwriters().isEmpty()) {
                film.getScreenwriters().forEach( screenwriter -> {
                    addPersonToMap(film.getTitle(), screenwriter, map);
                });
            }
            if (!film.getDirectors().isEmpty()) {
                film.getDirectors().forEach( director -> {
                    addPersonToMap(film.getTitle(), director, map);
                });
            }
        }
        return map;
    }

    public FilmsList getFilms() {
        return films;
    }

    public void setFilms(FilmsList films) {
        this.films = films;
    }
}
