package com.tax.service;

import com.tax.dao.daoImpl.FactoryDAOImpl;
import com.tax.dao.daoImpl.UserDAOImp;
import com.tax.entity.User;
import com.tax.exception.PersistException;
import com.tax.exception.ServiceException;

public class AuthService {

    private final UserDAOImp userDAOImp = FactoryDAOImpl.getFactory().getUserDAOImpl();

    public User getAuthUser(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDAOImp.getUserByEmail(login);
        } catch (PersistException e) {
            // logg
            throw new ServiceException();
        }
        if (user != null) {
            if (!login.equals(user.getEmail()) && password.equals(user.getPassword())) {
                user = null;
            }
        }
        return user;
    }
}
