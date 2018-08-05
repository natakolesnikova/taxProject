package com.tax.dao.daoImpl;

import com.tax.dao.AbstractJDBC;
import com.tax.dao.GenericDAO;
import com.tax.dao.connection.WrapperConnector;
import com.tax.entity.Inspector;
import com.tax.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class InspectorDAOImp extends AbstractJDBC<Inspector, Integer> implements GenericDAO {

    private WrapperConnector wrapperConnector = WrapperConnector.getInstance();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db/sql");

    @Override
    public String getSelectQuery() {
        return resourceBundle.getString("getAllInspector");
    }

    @Override
    protected List<Inspector> parseResultSet(ResultSet rs) {
        List<Inspector> result = new LinkedList<>();
        try {
            while (rs.next()) {
            int id = rs.getInt("id");
            int setWorkNumber = rs.getInt("work_number");
            int setWorkStatus = rs.getInt("work_status");
            int setUserId = rs.getInt("user_id");

            Inspector inspector = new Inspector.Builder()
                    .setId(id)
                    .setWorkNumber(setWorkNumber)
                    .setWorkStatus(setWorkStatus)
                    .setUserId(setUserId)
                    .build();
            result.add(inspector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getCreateQuery() {
        return resourceBundle.getString("addInspector");
    }

    @Override
    public String getUpdateQuery() {
        return resourceBundle.getString("updateInspector");
    }

    @Override
    public String getDeleteQuery() {
        return resourceBundle.getString("deleteInspector");
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Inspector object) {
        try {
            statement.setInt(1, object.getId());
            statement.setInt(2, object.getWorkNumber());
            statement.setInt(3, object.getWorkStatus());
            statement.setInt(4, object.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Inspector object) {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Inspector object) {
        try {
            preparedStatement.setInt(1, object.getWorkNumber());
            preparedStatement.setInt(2, object.getWorkStatus());
            preparedStatement.setInt(3, object.getUserId());
            preparedStatement.setInt(4, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Inspector create(Object object) throws PersistException {
        return persist(object);
    }

    public Inspector getInspectorByWorkNumber(int workNumber) throws PersistException {
        List<Inspector> inspector = null;
        String sql = resourceBundle.getString("getInspectorByWorkNumber");
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            statement.setInt(1, workNumber);
            ResultSet resultSet = statement.executeQuery();
            inspector = parseResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (inspector == null || inspector.size() == 0) {
            return null;
        }
        if (inspector.size() > 1) {
            throw new PersistException("received more than one record");
        }
        return inspector.iterator().next();
    }
}
