package com.tax.service;

import com.tax.dao.daoImpl.FactoryDAOImpl;
import com.tax.dao.daoImpl.InspectorDAOImp;
import com.tax.entity.Inspector;
import com.tax.entity.User;
import com.tax.exception.PersistException;

public class InspectorService {
    private final InspectorDAOImp inspectorDAOImp = FactoryDAOImpl.getFactory().getInspectorDAOImpl();

    public Inspector getInspectorByUser(User user) {
        Inspector inspector = null;
        try {
            inspector = inspectorDAOImp.getInspectorByUser(user.getUserRoleId());
        } catch (PersistException e) {
            System.out.println("problems with getting inspector");
            e.printStackTrace();
        }
        return inspector;
    }
}
