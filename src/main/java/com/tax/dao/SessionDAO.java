package com.tax.dao;


import com.tax.entity.Session;

public interface SessionDAO {
    Session getSessionByToken(String token);
    void save(Session session);
    boolean isSessionExist(String token);
    void remove(String token);
}
