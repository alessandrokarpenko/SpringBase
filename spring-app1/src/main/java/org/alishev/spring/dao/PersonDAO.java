package org.alishev.spring.dao;

import org.alishev.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setAge(resultSet.getInt("age"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }


    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement statement =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setAge(resultSet.getInt("age"));
            person.setName(resultSet.getString("name"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE  id=?");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.setInt(4, person.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE  FROM person WHERE  id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
