package com.tax.dao.daoImpl;

import com.tax.dao.FactoryDAO;

public class FactoryDAOImpl implements FactoryDAO {
    private static FactoryDAOImpl factory;

    private UserDAOImp userDAOImp;
    private TaxPayerDAOImp taxPayerDAOImp;
    private ReportDAOImp reportDAOImp;
    private InspectorDAOImp inspectorDAOImp;

    private FactoryDAOImpl() {
        userDAOImp = new UserDAOImp();
        taxPayerDAOImp = new TaxPayerDAOImp();
        reportDAOImp = new ReportDAOImp();
        inspectorDAOImp = new InspectorDAOImp();
    }

    private static FactoryDAOImpl getFactory() {
        if (factory == null) {
            factory = new FactoryDAOImpl();
        }
        return factory;
    }

    @Override
    public UserDAOImp getUserDAOImpl() {
        return userDAOImp;
    }

    @Override
    public TaxPayerDAOImp getTaxPayerDAOImpl() {
        return taxPayerDAOImp;
    }

    @Override
    public ReportDAOImp getReportDAOImpl() {
        return reportDAOImp;
    }

    @Override
    public InspectorDAOImp getInspectorDAOImpl() {
        return inspectorDAOImp;
    }
}
