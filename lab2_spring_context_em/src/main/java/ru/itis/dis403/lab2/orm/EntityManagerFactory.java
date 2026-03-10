package ru.itis.dis403.lab2.orm;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.dis403.lab2.orm.annotation.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class EntityManagerFactory {

    private HikariDataSource dataSource;
    private List<Class<?>> entities = new ArrayList<>();

    public EntityManagerFactory(List<Class<?>> entityClasses) throws Exception {
        Class.forName("org.postgresql.Driver");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/lab2");
        config.setUsername("elvina");
        config.setPassword("");
        config.setConnectionTimeout(50000);
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);

        for (Class<?> cls : entityClasses) {
            if (cls.isAnnotationPresent(Entity.class)) {
                entities.add(cls);
                System.out.println("Найдена сущность: " + cls.getSimpleName());
            }
        }

        generateSchema();
        validateSchema();
    }

    private void generateSchema() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            for (Class<?> cls : entities) {
                String tableName = cls.getSimpleName().toLowerCase();
                StringBuilder sql = new StringBuilder();
                sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (\n");

                List<String> columns = new ArrayList<>();
                for (Field field : cls.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Id.class)) {
                        columns.add("    " + field.getName() + " BIGSERIAL PRIMARY KEY");
                    } else if (field.isAnnotationPresent(Column.class)) {
                        columns.add("    " + field.getName() + " VARCHAR(255)");
                    } else if (field.isAnnotationPresent(ManyToOne.class)) {
                        // country → country_id bigint
                        columns.add("    " + field.getName() + "_id BIGINT");
                    }
                }
                // CREATE TABLE IF NOT EXISTS city (id BIGSERIAL PRIMARY KEY, name VARCHAR(255), country_id BIGINT)
                sql.append(String.join(",\n", columns));
                sql.append("\n)");

                System.out.println("DDL: " + sql);
                conn.createStatement().execute(sql.toString());
                System.out.println("Таблица '" + tableName + "' создана (или уже существует)");
            }
        }
    }

    private void validateSchema() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();

            for (Class<?> cls : entities) {
                String tableName = cls.getSimpleName().toLowerCase();

                // есть ли таблица с таким именем
                ResultSet tables = meta.getTables(null, null, tableName, null);
                if (tables.next()) {
                    System.out.println("Таблица '" + tableName + "' существует");
                } else {
                    System.out.println("Таблица '" + tableName + "' НЕ найдена!");
                    continue;
                }

                // вытаскиваю список всех ее колонок из бд
                Set<String> dbColumns = new HashSet<>();
                ResultSet cols = meta.getColumns(null, null, tableName, null);
                while (cols.next()) {
                    dbColumns.add(cols.getString("COLUMN_NAME").toLowerCase());
                }

                // для каждого поля класса проверяю есть ли нужная колонка
                for (Field field : cls.getDeclaredFields()) {
                    String expectedCol = null;
                    if (field.isAnnotationPresent(Id.class)) {
                        expectedCol = field.getName().toLowerCase();
                    } else if (field.isAnnotationPresent(Column.class)) {
                        expectedCol = field.getName().toLowerCase();
                    } else if (field.isAnnotationPresent(ManyToOne.class)) {
                        expectedCol = field.getName().toLowerCase() + "_id";
                    }

                    if (expectedCol != null) {
                        if (dbColumns.contains(expectedCol)) {
                            System.out.println("Колонка '" + expectedCol + "' существует");
                        } else {
                            System.out.println("Колонка '" + expectedCol + "' не найдена");
                        }
                    }
                }
            }
        }
    }

    public EntityManager getEntityManager() {
        try {
            return new EntityManagerImpl(dataSource.getConnection(), entities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        dataSource.close();
    }
}
