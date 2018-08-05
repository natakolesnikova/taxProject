package com.tax.dao.daoImpl;

import com.tax.dao.AbstractJDBC;
import com.tax.dao.GenericDAO;
import com.tax.dao.connection.WrapperConnector;
import com.tax.entity.TaxPayer;
import com.tax.exception.PersistException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TaxPayerDAOImp extends AbstractJDBC<TaxPayer, Integer> implements GenericDAO {

    WrapperConnector wrapperConnector = WrapperConnector.getInstance();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("db/sql");

    @Override
    public String getSelectQuery() {
        return resourceBundle.getString("getAllTaxPayer");
    }

    @Override
    protected List<TaxPayer> parseResultSet(ResultSet rs) {
        List<TaxPayer> result = new LinkedList<>();
        try {
            while (rs.next()) {
                TaxPayer taxPayer = new TaxPayer();
                taxPayer.setId(rs.getInt("id"));
                taxPayer.setPasswordSerialNumber(rs.getString("passport_serial_number"));
                taxPayer.setPassportNumber(rs.getInt("passport_number"));
                taxPayer.setIdentificationCode(rs.getInt("identification_code"));
                taxPayer.setUserId(rs.getInt("user_id"));
                taxPayer.setInspectorId(rs.getInt("inspector_id"));
                result.add(taxPayer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getCreateQuery() {
        return resourceBundle.getString("addTaxpayer");
    }

    @Override
    public String getUpdateQuery() {
        return resourceBundle.getString("updateTaxPayer");
    }

    @Override
    public String getDeleteQuery() {
        return resourceBundle.getString("deleteTaxPayer");
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TaxPayer object) {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getPasswordSerialNumber());
            statement.setInt(3, object.getPassportNumber());
            statement.setInt(4, object.getIdentificationCode());
            statement.setInt(5, object.getUserId());
            statement.setInt(6, object.getInspectorId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, TaxPayer object) {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, TaxPayer object) {
        try {
            preparedStatement.setString(1, object.getPasswordSerialNumber());
            preparedStatement.setInt(2, object.getPassportNumber());
            preparedStatement.setInt(3, object.getIdentificationCode());
            preparedStatement.setInt(4, object.getUserId());
            preparedStatement.setInt(5, object.getInspectorId());
            preparedStatement.setInt(6, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TaxPayer create(Object object) throws PersistException {
        return persist(object);
    }
}
