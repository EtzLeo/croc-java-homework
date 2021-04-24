package ru.croc.javaschool.homework8.dbprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DataSourceProvider {

    /**
     * Data source.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Параметры конфигурации.
     */
    private Map<String, String> properties = new HashMap<>();

    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.username");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty()
                    && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }

        return dataSource;
    }

    /**
     * Организованное отключение БД.
     *
     * <p>
     *     Дело в том, что по соображениям производительности,
     *     derby внутренне распределяет секвенсоры со 100 значениями
     *     и не может получить неиспользуемые значения.
     * </p>
     * <p>
     *     Поэтому если происходит неожиданный выход из БД,
     *     генератор последовательности пропустит неиспользуемые предварительно выделенные значения,
     *     когда мы снова к ней подключимся.
     * </p>
     * <p>
     *     Чтобы не допустить такой ситуации, необходимо отключить БД по завершении работы с ней.
     * </p>
     */
    public void shutdown() {
        dataSource.setShutdownDatabase("shutdown");
        try {
            dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Отключение БД: " + e.getMessage());
        }
    }
}
