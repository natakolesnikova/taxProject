package com.tax.service;


import com.tax.dao.daoImpl.FactoryDAOImpl;
import com.tax.dao.daoImpl.UserDAOImp;
import com.tax.entity.User;
import com.tax.exception.PersistException;
import com.tax.exception.ServiceExceprion;

public class AuthService {
    private final UserDAOImp userDAOImp = FactoryDAOImpl.getFactory().getUserDAOImpl();

    public User getAuthUser(String login, String password) throws ServiceExceprion {
        User user;
        try {
            user = userDAOImp.getUserByEmail(login);
        } catch (PersistException e) {
            // imolement logger
            throw new ServiceExceprion();
        }
        if (user != null) {
            if (!login.equals(user.getEmail()) && password.equals(user.getPassword())) {
                user = null;
            }
        }
        return user;
    }
}
