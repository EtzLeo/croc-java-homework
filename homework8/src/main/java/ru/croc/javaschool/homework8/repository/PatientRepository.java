package ru.croc.javaschool.homework8.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.homework8.model.dbperson.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для доступа к таблиуе с данными о людях.
 */
public class PatientRepository {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME;

    /**
     * Источник данных.
     */
    private EmbeddedDataSource dataSource;

    public PatientRepository(EmbeddedDataSource dataSource, String tableName) {
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
                                + "name VARCHAR(255),"
                                + "surname VARCHAR(255),"
                                + "middleName VARCHAR(255),"
                                + "birthDate DATE,"
                                + "passportNumber VARCHAR(11),"
                                + "medicalPolicy VARCHAR(15),"
                                + "diseaseDate DATE,"
                                + "cureDate DATE,"
                                + "isSick BOOLEAN"
                                + ")");
                System.out.println("Таблица успешно инициализирована");
            }
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
    public List<Patient> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Patient> patients = new ArrayList<>();
            while (resultSet.next()) {
                LocalDate diseaseDate = null;
                LocalDate cureDate = null;
                if (resultSet.getDate("diseaseDate") != null) {
                    diseaseDate = resultSet.getDate("diseaseDate").toLocalDate();
                }
                if (resultSet.getDate("cureDate") != null) {
                    cureDate = resultSet.getDate("cureDate").toLocalDate();

                }

                patients.add(
                        new Patient(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("surname"),
                                resultSet.getString("middleName"),
                                resultSet.getDate("birthDate").toLocalDate(),
                                resultSet.getString("passportNumber"),
                                resultSet.getString("medicalPolicy"),
                                diseaseDate,
                                cureDate,
                                resultSet.getBoolean("isSick")));
            }
            return patients;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Создание записи в БД о новом человеке.
     *
     * @param patient человек
     */
    public void createNew(Patient patient) {
        String sqlQuery =
                "INSERT INTO "
                        + TABLE_NAME
                        + "(name, surname, middleName, birthDate, passportNumber, medicalPolicy, " +
                        "diseaseDate, cureDate, isSick)"
                        + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setObject(3, patient.getMiddleName(), Types.VARCHAR);
            statement.setDate(4, Date.valueOf(patient.getBirthDate()));
            statement.setString(5, patient.getPassportNumber());
            statement.setString(6, patient.getMedicalPolicy());

            if(patient.getDiseaseDate() == null) {
                statement.setNull(7, Types.DATE);
            }
            else {
                statement.setDate(7, Date.valueOf(patient.getDiseaseDate()));
            }

            if(patient.getCureDate() == null) {
                statement.setNull(8, Types.DATE);
            }
            else {
                statement.setDate(8, Date.valueOf(patient.getCureDate()));
            }

            statement.setBoolean(9, patient.isSick());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Изменение данных в БД о человеке.
     *
     * <p>
     *     При этом передаваемый экземпляр patient должен иметь существующий id, иначе операция не выполнится
     * </p>
     * @param patient человек
     */
    public void update(Patient patient) {
        String sqlQuery =
                "UPDATE " +
                        TABLE_NAME +
                        " SET " +
                        "name = ?," +
                        "surname = ?," +
                        "middleName = ?," +
                        "birthDate = ?," +
                        "passportNumber = ?," +
                        "medicalPolicy = ?," +
                        "diseaseDate = ?," +
                        "cureDate = ?," +
                        "isSick = ?" +
                        " WHERE " + "id = ?" ;

        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, patient.getName());
            statement.setString(2, patient.getSurname());
            statement.setObject(3, patient.getMiddleName(), Types.VARCHAR);
            statement.setDate(4, Date.valueOf(patient.getBirthDate()));
            statement.setString(5, patient.getPassportNumber());
            statement.setString(6, patient.getMedicalPolicy());

            if(patient.getDiseaseDate() == null) {
                statement.setNull(7, Types.DATE);
            }
            else {
                statement.setDate(7, Date.valueOf(patient.getDiseaseDate()));
            }

            if(patient.getCureDate() == null) {
                statement.setNull(8, Types.DATE);
            }
            else {
                statement.setDate(8, Date.valueOf(patient.getCureDate()));
            }

            statement.setBoolean(9, patient.isSick());
            statement.setInt(10, patient.getId());
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
