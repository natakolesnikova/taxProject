package com.tax.dao;

import com.tax.dao.daoImpl.InspectorDAOImp;
import com.tax.dao.daoImpl.ReportDAOImp;
import com.tax.dao.daoImpl.UserDAOImp;
import com.tax.entity.Inspector;
import com.tax.entity.Report;
import com.tax.entity.User;
import com.tax.exception.PersistException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class AbstractJDBCTest {

    private AbstractJDBC abstractJDBCUser = new UserDAOImp();
    private AbstractJDBC abstractJDBCInspector = new InspectorDAOImp();
    private AbstractJDBC abstractJDBCReport = new ReportDAOImp();


    @Test
    public void getAllUsers() {
        List<User> list = null;
        try {
             list = abstractJDBCUser.getAll();
        } catch (PersistException e) {
            System.out.println("bad");
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getByPKUser() throws PersistException {
        User user = ((UserDAOImp) abstractJDBCUser).getByPK(1);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getId() == 1);
    }

    @Test
    public void createUser() throws PersistException {
        User user = new User();
        user.setFirstName("TestUser2");
        user.setSecondName("SecondTestName");
        user.setEmail("test@gmail.com");
        user.setPassword("qwerty123");
        user.setUserRoleId(1);
        abstractJDBCUser.create(user);
        User actualUser = ((UserDAOImp) abstractJDBCUser).getUserByPassword("qwerty123");
        Assert.assertEquals(user.getFirstName(), actualUser.getFirstName());
    }

    @Test
    public void update() throws PersistException {
        User user = ((UserDAOImp) abstractJDBCUser).getByPK(3);
        user.setFirstName("TestName");
        user.setSecondName("TestSecondName");
        user.setPassword("testPassword");
        user.setEmail("chancgeEmail@gmail.com");
        user.setUserRoleId(1);
        abstractJDBCUser.update(user);
        Assert.assertTrue(user.getFirstName().equals("TestName"));
    }

    @Test
    public void deleteUser() throws PersistException {
        User user = ((UserDAOImp) abstractJDBCUser).getUserByPassword("qwerty123");
        Assert.assertNotNull(user);
        abstractJDBCUser.delete(user);
    }

    @Test
    public void getAllInspectors() {
        List<User> list = null;
        try {
            list = abstractJDBCInspector.getAll();
        } catch (PersistException e) {
            System.out.println("bad");
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getByPKInspector() throws PersistException {
        Inspector user = (Inspector) abstractJDBCInspector.getByPK(1);
        Assert.assertNotNull(user);
        Assert.assertTrue(user.getId() == 1);
    }

  //  @Ignore
    @Test
    public void createInspector() throws PersistException {
        Inspector inspector = new Inspector.Builder()
                .setWorkNumber(56)
                .setWorkStatus(1)
                .setUserId(1)
                .build();
        abstractJDBCInspector.create(inspector);
        Inspector actualInspector = ((InspectorDAOImp) abstractJDBCInspector).getInspectorByWorkNumber(56);
        Assert.assertEquals(inspector.getWorkNumber(), actualInspector.getWorkNumber());
    }

    @Test
    public void updateInspector() throws PersistException {
        Inspector inspector = ((InspectorDAOImp) abstractJDBCInspector).getByPK(10);
        inspector.setWorkStatus(2);
        inspector.setWorkNumber(5678);
        abstractJDBCInspector.update(inspector);
        Assert.assertTrue(inspector.getWorkStatus() == 2);
        Assert.assertTrue(inspector.getWorkNumber() == 5678);
    }

    @Test
    public void getAllReports() {
        List<Report> list = null;
        try {
            list = abstractJDBCReport.getAll();
        } catch (PersistException e) {
            System.out.println("bad");
        }
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void getByPKReport() throws PersistException {
        Report report = ((ReportDAOImp) abstractJDBCReport).getByPK(1);
        Assert.assertNotNull(report);
        Assert.assertTrue(report.getId() == 1);
    }

    @Test
    public void updateReport() throws PersistException {
        Report report = ((ReportDAOImp) abstractJDBCReport).getByPK(1);
        report.setQuarterPeriod(3);
        report.setIncome(BigDecimal.valueOf(45000.00));
        report.setTaxFromIncome(BigDecimal.valueOf(2250.00));
        report.setStatusReport(Report.StatusReport.APPROVED);
        report.setRejectedReason("incorrect data");
        abstractJDBCReport.update(report);
        Assert.assertTrue(report.getRejectedReason().equals("incorrect data"));
    }

    @Test
    public void createReport() throws PersistException {
        Report report = new Report();
        report.setQuarterPeriod(2);
        report.setIncome(BigDecimal.valueOf(50000.00));
        report.setTaxFromIncome(BigDecimal.valueOf(10000.00));
        report.setStatusReport(Report.StatusReport.PENDING);
        report.setRejectedReason("report created");
        report.setInspectorId(1);
        report.setTaxpayerId(1);
        abstractJDBCReport.create(report);
        Report actualReport = ((ReportDAOImp)abstractJDBCReport).getReportByRejectedReason("report created");
        Assert.assertEquals(report.getRejectedReason(), actualReport.getRejectedReason());
    }

    @Test
    public void deleteReport() throws PersistException {
        Report report = ((ReportDAOImp)abstractJDBCReport).getReportByRejectedReason("report created");
        Assert.assertNotNull(report);
        abstractJDBCReport.delete(report);
    }

}