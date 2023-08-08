package org.example;


import java.sql.*;

public class SQLQuery {

    public static void createTable() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("CREATE TABLE some_table (ID INT PRIMARY KEY, " +
                    "NAME VARCHAR(45) NOT NULL, " +
                    "SURNAME VARCHAR(45) NOT NULL, " +
                    "AGE INT NOT NULL, " +
                    "PHONE VARCHAR(45) NOT NULL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DROP TABLE some_table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("INSERT INTO some_table (ID, NAME, SURNAME, AGE, PHONE) " +
                    "VALUES " +
                    "(1, 'Dmytro', 'ZORO', 25,  '+380964586974')," +
                    "(2, 'Oleg', 'Parker', 20,  '+380964586989')," +
                    "(3, 'Alex', 'Sharko', 15,  '+380964581111')," +
                    "(4, 'Jordan', 'Henderson', 35,  '+380960000000')," +
                    "(5, 'Alexis', 'Sanches', 41,  '+380968927518') ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectAllData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM some_table")) {

            simpleIterator(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectDataParameters() {
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM some_table WHERE AGE < ?")) {

            preparedStatement.setInt(1, 20);
            ResultSet resultSet = preparedStatement.executeQuery();
            simpleIterator(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDataParameters() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsToDelete = statement.executeUpdate("DELETE FROM some_table WHERE AGE > 15");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void truncateData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsToTruncate = statement.executeUpdate("TRUNCATE TABLE some_table");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateData() {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsToUpdate = statement.executeUpdate("UPDATE some_table SET SURNAME = 'Robertson' WHERE NAME = 'Jordan'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void simpleIterator(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String surName = resultSet.getString("SURNAME");
            int age = resultSet.getInt("AGE");
            String phone = resultSet.getString("PHONE");
            System.out.println(id + ", " + name + ", " + surName + ", " + age + ", " + phone);
        }
    }


}
