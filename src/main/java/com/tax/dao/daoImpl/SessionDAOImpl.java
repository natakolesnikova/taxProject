package com.tax.dao.daoImpl;

import com.tax.dao.SessionDAO;
import com.tax.entity.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SessionDAOImpl implements SessionDAO {
    // now mock implement ib db

    public static final SessionDAO SESSION_DAO = new SessionDAOImpl();
    private final Map<String, Session> sessions = new HashMap<>();

    private SessionDAOImpl() {
    }


    @Override
    public Session getSessionByToken(String token) {
        return sessions.get(token);
    }

    @Override
    public void save(Session session) {
        sessions.put(session.getToken(), session);
    }

    @Override
    public boolean isSessionExist(String token) {
        return Objects.nonNull(getSessionByToken(token));
    }

    @Override
    public void remove(String token) {
        if (isSessionExist(token)) {
            sessions.remove(token);
        }
    }
}
