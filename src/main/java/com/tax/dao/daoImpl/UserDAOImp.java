package com.tax.dao.daoImpl;

import com.tax.dao.AbstractJDBC;
import com.tax.dao.GenericDAO;
import com.tax.dao.connection.WrapperConnector;
import com.tax.entity.User;
import com.tax.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDAOImp extends AbstractJDBC<User, Integer> implements GenericDAO {

    private WrapperConnector wrapperConnector = WrapperConnector.getInstance();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db/sql");

    @Override
    protected List<User> parseResultSet(ResultSet rs) {
        List<User> result = new LinkedList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserRoleId(rs.getInt("user_role_id"));
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getFirstName());
            statement.setString(3, object.getSecondName());
            statement.setString(4, object.getEmail());
            statement.setString(5, object.getPassword());
            statement.setInt(6, object.getUserRoleId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, User object) {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, User object) {
        try {
            preparedStatement.setString(1, object.getFirstName());
            preparedStatement.setString(2, object.getSecondName());
            preparedStatement.setString(3, object.getEmail());
            preparedStatement.setString(4, object.getPassword());
            preparedStatement.setInt(5, object.getUserRoleId());
            preparedStatement.setInt(6, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User create(Object object) throws PersistException {
        return persist(object);
    }

    @Override
    public String getSelectQuery() {
        return resourceBundle.getString("getAllUsers");
    }

    @Override
    public String getCreateQuery() {
        return resourceBundle.getString("addUser");
    }

    @Override
    public String getUpdateQuery() {
        return resourceBundle.getString("updateUser");
    }

    @Override
    public String getDeleteQuery() {
        return resourceBundle.getString("deleteUser");
    }

    public User getUserByEmail(String email) throws PersistException {
        List<User> users = null;
        String sql = resourceBundle.getString("getUserByEmail");
        try (PreparedStatement preparedStatement = wrapperConnector.getStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            users = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (users == null || users.size() == 0) {
            return null;
        }
        if (users.size() > 1) {
            throw new PersistException("received more than one record");
        }
        return users.iterator().next();
    }

    public User getUserByPassword(String password) throws PersistException {
        List<User> users = null;
        String sql = resourceBundle.getString("getUserByPassword");
        try (PreparedStatement preparedStatement = wrapperConnector.getStatement(sql)) {
            preparedStatement.setString(1, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            users = parseResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users == null || users.size() == 0) {
            return null;
        }
        if (users.size() > 1) {
            throw new PersistException("received more than one record");
        }
        return users.iterator().next();
    }

}
