package com.tax.dao;

import com.tax.dao.daoImpl.InspectorDAOImp;
import com.tax.dao.daoImpl.ReportDAOImp;
import com.tax.dao.daoImpl.TaxPayerDAOImp;
import com.tax.dao.daoImpl.UserDAOImp;

public interface FactoryDAO {
    UserDAOImp getUserDAOImpl();
    TaxPayerDAOImp getTaxPayerDAOImpl();
    ReportDAOImp getReportDAOImpl();
    InspectorDAOImp getInspectorDAOImpl();
}
