package com.otus.db;

import java.sql.*;

/*
1. Создать таблицу Student
Колонки id, fio, sex, id_group

2. Создать таблицу Group
Колонки id, name, id_curator

3. Создать таблицу Curator
Колонки id, fio

4. Заполнить таблицы данными(15 студентов, 3 группы, 4 куратора)

5. Вывести на экран информацию о всех студентах включая название группы и имя куратора

6. Вывести на экран количество студентов

7. Вывести студенток

8. Обновить данные по группе сменив куратора

9. Вывести список групп с их кураторами

10. Используя вложенные запросы вывести на экран студентов из определенной группы(поиск по имени группы)
 */

public class HomeworkPreparation {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/myDb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    private static final String CREATE_CURATOR_TABLE =
            "CREATE TABLE IF NOT EXISTS Curator(id int auto_increment primary key, fio varchar(50));";

    private static final String CREATE_GROUP_TABLE =
            "CREATE TABLE IF NOT EXISTS Groupp(id int auto_increment primary key, name varchar(50), " +
                    "id_curator int, FOREIGN KEY (id_curator) REFERENCES Curator (id));";

    private static final String CREATE_STUDENT_TABLE =
            "CREATE TABLE IF NOT EXISTS Student(id int auto_increment primary key, fio varchar(50), sex varchar(1), " +
                    "id_group int, FOREIGN KEY (id_group) REFERENCES Groupp (id));";

    private static final String INSERT_INTO_STUDENT_TABLE = "INSERT INTO Student(fio, sex, id_group) VALUES (?, ?, ?);";
    private static final String INSERT_INTO_GROUP_TABLE = "INSERT INTO Groupp(name, id_curator) VALUES (?, ?);";
    private static final String INSERT_INTO_CURATOR_TABLE = "INSERT INTO Curator(fio) VALUES (?);";

    private static final String SELECT_STUDENT_DATA_WITH_GROUP_AND_CURATOR_INFO =
            "SELECT s.fio AS StudentFIO, s.sex, g.name AS GroupName, c.fio AS CuratorFIO " +
                    "FROM myDb.Student s " +
                    "         JOIN myDb.Groupp g ON s.id_group = g.id " +
                    "         JOIN myDb.Curator c ON g.id_curator = c.id; ";

    private static final String SELECT_COUNT_OF_STUDENTS =
            "SELECT COUNT(myDb.Student.id) AS NumberOfStudents " +
                    "FROM myDb.Student";

    private static final String SELECT_COUNT_OF_FEMALE_STUDENTS =
            "SELECT COUNT(myDb.Student.id) AS NumberOfFemaleStudents " +
                    "FROM myDb.Student " +
                    "WHERE sex = 'ж';";

    private static final String UPDATE_CURATOR_IN_GROUP = "UPDATE myDb.Groupp SET id_curator = ? WHERE id = ?;";

    private static final String SELECT_ALL_GROUPS_WITH_CURATORS =
            "SELECT g.id AS GroupId, g.name AS GroupName, c.id AS CuratorId, c.fio AS CuratorFIO " +
                    "FROM myDb.Groupp g " +
                    "JOIN myDb.Curator c ON g.id_curator = c.id;";

    private static final String SELECT_STUDENTS_WITH_GROUP =
            "SELECT s.fio AS StudentFIO " +
                    "FROM myDb.Student s " +
                    "         JOIN myDb.Groupp g ON s.id_group = g.id " +
                    "WHERE g.id IN (SELECT Groupp.id FROM myDb.Groupp where Groupp.name = ?);";


    public static void main(String[] args) throws SQLException {
        HomeworkPreparation homeworkPreparation = new HomeworkPreparation();
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
            //Задание №1,2,3
            homeworkPreparation.createCuratorTable(connection);
            homeworkPreparation.createGroupTable(connection);
            homeworkPreparation.createStudentTable(connection);

            //Задание №4
            homeworkPreparation.insertDataIntoCuratorTable(connection, "Иванов Иван Иванович");
            homeworkPreparation.insertDataIntoCuratorTable(connection, "Петров Петр Петрович");
            homeworkPreparation.insertDataIntoCuratorTable(connection, "Сидоров Сидор Сидорович");
            homeworkPreparation.insertDataIntoCuratorTable(connection, "Максимов Максим Максимович");

            homeworkPreparation.insertDataIntoGroupTable(connection, "Alpha", "1");
            homeworkPreparation.insertDataIntoGroupTable(connection, "Beta", "2");
            homeworkPreparation.insertDataIntoGroupTable(connection, "Delta", "3");

            homeworkPreparation.insertDataIntoStudentTable(connection, "Лилиева Лилия Андреевна", "ж", "1");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Варчаров Варчар Варчарович", "м", "1");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Стрингов Стринг Стрингович", "м", "1");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Интова Инта Интоновна ", "ж", "2");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Лонг Шорт Интеджерович", "м", "2");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Степнов Степень Степенович", "м", "1");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Стул Стула Стулович", "м", "3");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Розова Роза Розовна", "ж", "1");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Небо Неб Небович", "м", "2");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Солнцев Солнце Солнович", "м", "3");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Дерево Дерев Деревьевич", "м", "3");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Веселова Веcела Веселовна", "ж", "3");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Ноутов Ноут Ноутович", "м", "2");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Тестов Тест Тестович", "м", "2");
            homeworkPreparation.insertDataIntoStudentTable(connection, "Кит Кот Котович", "м", "1");

            //Задание №5
            homeworkPreparation.getAllStudentsInfoWithGroupAndCuratorName(connection);
            //Задание №6
            homeworkPreparation.getNumberOfStudents(connection);
            //Задание №7
            homeworkPreparation.getNumberOfFemaleStudents(connection);
            //Задание №8
            homeworkPreparation.updateCuratorInGroup(connection, 2, 3);
            //Задание №9
            homeworkPreparation.getAllGroupsWithCurators(connection);
            //Задание №10
            homeworkPreparation.getStudentsWithGroup(connection, "Alpha");
        }
    }

    //Создание таблицы Curator
    private void createCuratorTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_CURATOR_TABLE);
        }
    }

    //Создание таблицы Group
    private void createGroupTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_GROUP_TABLE);
        }
    }

    //Создание таблицы Student
    private void createStudentTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_STUDENT_TABLE);
        }
    }

    //Внесение данных в таблицу Curator
    private void insertDataIntoCuratorTable(Connection connection, String fio) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CURATOR_TABLE)) {
            statement.setString(1, fio);
            statement.executeUpdate();
        }
    }

    //Внесение данных в таблицу Group
    private void insertDataIntoGroupTable(Connection connection, String name, String curatorId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_GROUP_TABLE)) {
            statement.setString(1, name);
            statement.setString(2, curatorId);
            statement.executeUpdate();
        }
    }

    //Внесение данных в таблицу Student
    private void insertDataIntoStudentTable(Connection connection, String fio, String sex, String groupId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STUDENT_TABLE)) {
            statement.setString(1, fio);
            statement.setString(2, sex);
            statement.setString(3, groupId);
            statement.executeUpdate();
        }
    }

    //Вывод данных о всех студентах, включая название группы и имя куратора
    private void getAllStudentsInfoWithGroupAndCuratorName(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_STUDENT_DATA_WITH_GROUP_AND_CURATOR_INFO)) {
            while (resultSet.next()) {
                String studentFIO = resultSet.getString("StudentFIO");
                String studentSex = resultSet.getString("sex");
                String groupName = resultSet.getString("GroupName");
                String curatorFIO = resultSet.getString("CuratorFIO");

                String row = String.format("StudentFIO: %s, sex: %s, GroupName: %s, CuratorFIO: %s", studentFIO, studentSex, groupName, curatorFIO);
                System.out.println(row);
            }
        }
    }

    //Вывод данных о количестве студентов
    private void getNumberOfStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_COUNT_OF_STUDENTS)) {
            while (resultSet.next()) {
                int numberOfStudents = resultSet.getInt("NumberOfStudents");
                System.out.println(numberOfStudents);
            }
        }
    }

    //Вывод данных о количестве студенток
    private void getNumberOfFemaleStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_COUNT_OF_FEMALE_STUDENTS)) {
            while (resultSet.next()) {
                int numberOfFemaleStudents = resultSet.getInt("NumberOfFemaleStudents");
                System.out.println(numberOfFemaleStudents);
            }
        }
    }

    //Изменение куратора в группе
    private void updateCuratorInGroup(Connection connection, int newCuratorId, int groupId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CURATOR_IN_GROUP)) {
            statement.setInt(1, newCuratorId);
            statement.setInt(2, groupId);
            int updatedRowsNumber = statement.executeUpdate();
            System.out.println("Rows updated: " + updatedRowsNumber);
        }
    }

    //Вывод данных о всех группах с их кураторами
    private void getAllGroupsWithCurators(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_GROUPS_WITH_CURATORS)) {
            while (resultSet.next()) {
                String groupId = resultSet.getString("GroupId");
                String groupName = resultSet.getString("GroupName");
                String curatorId = resultSet.getString("CuratorId");
                String curatorFIO = resultSet.getString("CuratorFIO");

                String row = String.format("GroupId: %s, GroupName: %s, CuratorId: %s, CuratorFIO: %s", groupId, groupName, curatorId, curatorFIO);
                System.out.println(row);
            }
        }
    }

    //Вывод студентов по определенной группе
    private void getStudentsWithGroup(Connection connection, String groupName) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_STUDENTS_WITH_GROUP)) {
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String studentFIO = resultSet.getString("StudentFIO");
                String row = String.format("StudentFIO: %s", studentFIO);
                System.out.println(row);
            }
        }
    }
}
