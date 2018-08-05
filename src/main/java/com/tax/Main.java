package com.tax;

import com.tax.dao.AbstractJDBC;
import com.tax.dao.daoImpl.InspectorDAOImp;
import com.tax.dao.daoImpl.ReportDAOImp;
import com.tax.dao.daoImpl.TaxPayerDAOImp;
import com.tax.dao.daoImpl.UserDAOImp;
import com.tax.entity.Inspector;
import com.tax.entity.Report;
import com.tax.entity.TaxPayer;
import com.tax.entity.User;
import com.tax.exception.PersistException;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) throws PersistException {


/*        try {
            WrapperConnector wrapperConnector = WrapperConnector.getInstance();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println("no connection");
        }*/

/*
       AbstractJDBC abstractJDBC = new InspectorDAOImp();
       AbstractJDBC abstractJDBC1 = new UserDAOImp();
       User user = ((UserDAOImp) abstractJDBC1).getByPK(4);
        Inspector inspector = new Inspector();
        inspector.setWorkNumber(345);
        inspector.setWorkStatus(1);
        inspector.setUserId(user.getId());
        abstractJDBC.create(inspector);*/

        AbstractJDBC abstractJDBC = new ReportDAOImp();
/*        List<Report> report = abstractJDBC.getAll();
        for (Report report1: report) {
            System.out.println(report1);
        }*/

        Report report = new Report();
        report.setQuarterPeriod(2);
        report.setIncome(BigDecimal.valueOf(50000.00));
        report.setTaxFromIncome(BigDecimal.valueOf(10000.00));
        report.setStatusReport(Report.StatusReport.PENDING);
        report.setRejectedReason("");
        report.setInspectorId(1);
        report.setTaxpayerId(1);
       // abstractJDBC.create(report);

        Report report1 = ((ReportDAOImp) abstractJDBC).getByPK(1);
       // System.out.println(report1);
        report1.setStatusReport(Report.StatusReport.APPROVED);

        abstractJDBC.update(report1);
    }

}
