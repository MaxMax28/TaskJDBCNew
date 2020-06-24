package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.execute("create table if not exists users (id bigint auto_increment, primary key (id), name varchar(256), " +
                    "lastName varchar(256), age int)");
            stmt.close();
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = Util.getConnection().prepareStatement("insert into users (name, lastName, age) value (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeQuery("select * from users");
            ResultSet result = stmt.getResultSet();
            List<User> usersList = new ArrayList<>();
            while (result.next()) {
                usersList.add(new User(result.getString("name"),
                        result.getString("lastName"), result.getByte("age")));
            }
            result.close();
            stmt.close();
            return usersList;
        } catch (SQLException e) {

        }
        return new ArrayList<>();
    }

    public void cleanUsersTable() {
        try {
            Statement stmt = Util.getConnection().createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            stmt.close();
        } catch (SQLException e) {

        }
    }
}