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
        try (Connection connection = Util.getConnection()) {
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement()) {
                statement.execute(
                        "CREATE TABLE IF NOT EXISTS User (" +
                                "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                                "name VARCHAR(50)," +
                                "lastName VARCHAR(50)," +
                                "age TINYINT)");
                connection.commit();
            } catch (SQLException en) {
                connection.rollback();
                System.out.println("Ошибка!" + en.getMessage());
            }
        } catch (SQLException en) {
            System.out.println("Ошибка!" + en.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Connection connection2 = Util.getConnection()) {
            connection2.setAutoCommit(false);

            try (Statement drop = connection2.createStatement()) {
                drop.execute("DROP TABLE IF EXISTS User");
                connection2.commit();
            } catch (SQLException em) {
                connection2.rollback();
                System.out.println("Ошибка!" + em.getMessage());
            }
        } catch (SQLException em) {
            System.out.println("Ошибка!" + em.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection3 = Util.getConnection()) {
            connection3.setAutoCommit(false);
            try (PreparedStatement prep = connection3
                    .prepareStatement("INSERT INTO User(name,lastName,age) VALUES (?,?,?)")) {
                prep.setString(1, name);
                prep.setString(2, lastName);
                prep.setByte(3, age);
                prep.executeUpdate();
                connection3.commit();
            } catch (SQLException ev) {
                connection3.rollback();
                System.out.println("Ошибка" + ev.getMessage());
            }
        } catch (SQLException ev) {
            System.out.println("Ошибка" + ev.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Connection connection4 = Util.getConnection()) {
            connection4.setAutoCommit(false);
            try (PreparedStatement remove = connection4.prepareStatement("DELETE FROM User WHERE id = ?")) {
                remove.setLong(1, id);
                remove.executeUpdate();
                connection4.commit();
            } catch (SQLException ec) {
                connection4.rollback();
                System.out.println("Ошибка" + ec.getMessage());
            }
        } catch (SQLException ec) {
            System.out.println("Ошибка" + ec.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection5 = Util.getConnection()) {
            connection5.setAutoCommit(false);
            try (Statement all = connection5.createStatement();
                 ResultSet resultSet = all.executeQuery("SELECT * FROM User")) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    list.add(user);
                }
                connection5.commit();
            } catch (SQLException ex) {
                connection5.rollback();
                System.out.println("Ошибка" + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка" + ex.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection6 = Util.getConnection()) {
            connection6.setAutoCommit(false);
            try (Statement clean = connection6.createStatement()) {
                clean.execute("TRUNCATE TABLE User");
                connection6.commit();
            } catch (SQLException ez) {
                connection6.rollback();
                System.out.println("Ошибка" + ez.getMessage());
            }
        } catch (SQLException ez) {
            System.out.println("Ошибка" + ez.getMessage());
        }
    }
}

