package ru.croc.javaschool.homework7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.homework7.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблиуе с данными о людях.
 */
public class PersonRepository {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME;

    /**
     * Источник данных.
     */
    private EmbeddedDataSource dataSource;

    public PersonRepository(EmbeddedDataSource dataSource, String tableName) {
        this.dataSource = dataSource;
        this.TABLE_NAME = tableName;
        initTable();
    }

    /**
     * Инициализация БД.
     */
    private void initTable() {
        System.out.printf("Инициализация таблицы %s%n", TABLE_NAME);
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Таблица уже существует");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY NOT NULL " +
                                "GENERATED ALWAYS as IDENTITY(START WITH 1 INCREMENT BY 1),"
                                + "firstName VARCHAR(255),"
                                + "lastName VARCHAR(255),"
                                + "birthday DATE,"
                                + "haveChildren BOOLEAN,"
                                + "postalCode INTEGER"
                                + ")");
                System.out.println("Таблица успешно инициализирована");
            }
            resultSet.close();
            databaseMetadata.autoCommitFailureClosesAllResultSets();
        } catch (SQLException e) {
            System.out.println("Ошибка при инициализации таблицы: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    /**
     * Метод поиска всех людей в БД.
     *
     * @return список всех созданных людей
     */
    public List<Person> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Person> people = new ArrayList<>();
            while (resultSet.next()) {
                people.add(
                        new Person(
                                resultSet.getInt("id"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getDate("birthday").toLocalDate(),
                                resultSet.getBoolean("haveChildren"),
                                resultSet.getInt("postalCode")));
            }
            return people;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Создание записи в БД о новом человеке.
     *
     * @param person человек
     */
    public void createNew(Person person) {
        String sqlQuery =
                "INSERT INTO "
                        + TABLE_NAME
                        + "(firstName, lastName, birthday, haveChildren, postalCode)"
                        + " VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setDate(3, Date.valueOf(person.getBirthday()));
            statement.setBoolean(4, person.isHaveChildren());
            statement.setInt(5, person.getPostalCode());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Изменение данных в БД о человеке.
     *
     * <p>
     *     При этом передаваемый экземпляр person должен иметь существующий id, иначе операция не выполнится
     * </p>
     * @param person человек
     */
    public void update(Person person) {
        String sqlQuery =
                "UPDATE " +
                        TABLE_NAME +
                        " SET " +
                        "firstName = ?," +
                        "lastName = ?," +
                        "birthday = ?," +
                        "haveChildren = ?," +
                        "postalCode = ?" +
                        " WHERE " + "id = ?" ;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setDate(3, Date.valueOf(person.getBirthday()));
            statement.setBoolean(4, person.isHaveChildren());
            statement.setInt(5, person.getPostalCode());
            statement.setInt(6, person.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Удаление из БД записи о человеке.
     *
     * @param id идентификатор
     */
    public void delete(int id) {
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM " + TABLE_NAME + " WHERE ID =" + id);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
