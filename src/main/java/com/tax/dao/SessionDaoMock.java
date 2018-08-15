package com.tax.dao;

import com.tax.entity.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SessionDaoMock implements SessionDao {

    public static final SessionDao SESSION_DAO = new SessionDaoMock();
    private final Map<String, Session> sessions = new HashMap<>();

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
