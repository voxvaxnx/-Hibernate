package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            stat.execute("CREATE TABLE `testdb`.`users` (`id` BIGINT NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) NOT NULL, `lastName` VARCHAR(45) NOT NULL, `age` INT(200) NOT NULL, PRIMARY KEY (`id`));");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            stat.execute("DROP TABLE users");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            PreparedStatement Pstat = connection.prepareStatement("insert into users (name, lastName, age) values ((?) ,(?) ,(?)");
            Pstat.setString(1, name);
            Pstat.setString(2, lastName);
            Pstat.setString(3, String.valueOf(age));
            Pstat.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            PreparedStatement Pstat = connection.prepareStatement("DELETE FROM users WHERE id = (?)");
            Pstat.setString(1, String.valueOf(id));
            Pstat.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                userList.add(user);
            }

            connection.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;

    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement stat = connection.createStatement()) {
            stat.execute("DELETE FROM users");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
