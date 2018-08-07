package com.tax.service;

import com.tax.dao.daoImpl.FactoryDAOImpl;
import com.tax.dao.daoImpl.TaxPayerDAOImp;
import com.tax.entity.TaxPayer;
import com.tax.entity.User;
import com.tax.exception.PersistException;

public class TaxpayerService {
    private final TaxPayerDAOImp taxPayerDAOImp = FactoryDAOImpl.getFactory().getTaxPayerDAOImpl();

    public TaxPayer getTaxpayerByUser(User user) {
        TaxPayer taxPayer = null;
        try {
            taxPayer = taxPayerDAOImp.getTaxPayerByUserId(user.getUserRoleId());
        } catch (PersistException e) {
            System.out.println("smth wrong with getting taxpayer");
            e.printStackTrace();
        }
        return taxPayer;
    }
}
