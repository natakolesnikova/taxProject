package com.tax.dao.daoImpl;

import com.tax.dao.AbstractJDBC;
import com.tax.dao.GenericDAO;
import com.tax.dao.connection.WrapperConnector;
import com.tax.entity.Report;
import com.tax.exception.PersistException;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ReportDAOImp extends AbstractJDBC<Report, Integer> implements GenericDAO {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle("db/sql");
    private WrapperConnector wrapperConnector = WrapperConnector.getInstance();

    @Override
    public String getSelectQuery() {
        return resourceBundle.getString("getAllReports");
    }

    @Override
    protected List<Report> parseResultSet(ResultSet rs) {
        List<Report> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setQuarterPeriod(rs.getInt("quarter_period"));
                report.setIncome(rs.getBigDecimal("income"));
                report.setTaxFromIncome(rs.getBigDecimal("tax_from_income"));
                Report.StatusReport value = Report.StatusReport.valueOf(rs.getString("status").toUpperCase().trim());
                report.setStatusReport(value);
                report.setRejectedReason(rs.getString("rejected_reason"));
                report.setInspectorId(rs.getInt("inspector_id"));
                report.setTaxpayerId(rs.getInt("taxpayer_id"));
                result.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getCreateQuery() {
        return resourceBundle.getString("addReport");
    }

    @Override
    public String getUpdateQuery() {
        return resourceBundle.getString("updateReport");
    }

    @Override
    public String getDeleteQuery() {
        return resourceBundle.getString("removeReport");
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Report object) {
        try {
            statement.setInt(1, object.getId());
            statement.setInt(2, object.getQuarterPeriod());
            statement.setBigDecimal(3, object.getIncome());
            statement.setBigDecimal(4, object.getTaxFromIncome());
            statement.setString(5, object.getStatusReport().toString());
            statement.setString(6, object.getRejectedReason());
            statement.setInt(7, object.getInspectorId());
            statement.setInt(8, object.getTaxpayerId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForDelete(PreparedStatement statement, Report object) {
        try {
            statement.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, Report object) {
        try {
            preparedStatement.setInt(1, object.getQuarterPeriod());
            preparedStatement.setBigDecimal(2, object.getIncome());
            preparedStatement.setBigDecimal(3, object.getTaxFromIncome());
            preparedStatement.setString(4, object.getStatusReport().toString());
            preparedStatement.setString(5, object.getRejectedReason());
            preparedStatement.setInt(6, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Report create(Object object) throws PersistException {
        return persist(object);
    }

    public Report getReportByRejectedReason(String rejectedReason) {
        Report report = null;
        String sql = resourceBundle.getString("getReportByRejectedReason");
        try (PreparedStatement statement = wrapperConnector.getStatement(sql)) {
            statement.setString(1, rejectedReason);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int quarterPeriod = resultSet.getInt("quarter_period");
                BigDecimal income = resultSet.getBigDecimal("income");
                BigDecimal tax = resultSet.getBigDecimal("tax_from_income");
                String status = resultSet.getString("status").toUpperCase().trim();
                String reject = rejectedReason;
                int inspectorId = resultSet.getInt("inspector_id");
                int taxpayerId = resultSet.getInt("taxpayer_id");

                report = new Report.Builder()
                        .setId(id)
                        .setQuarterPeriod(quarterPeriod)
                        .setIncome(income)
                        .setTaxFromIncome(tax)
                        .setStatusReport(Report.StatusReport.valueOf(status))
                        .setRejectedReason(reject)
                        .setInspectorId(inspectorId)
                        .setTaxpayerId(taxpayerId)
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return report;
    }
}
