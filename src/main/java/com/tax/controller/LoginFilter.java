package com.tax.controller;


import com.tax.dao.SessionDAO;
import com.tax.dao.daoImpl.SessionDAOImpl;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.tax.controller.LoginServlet.USER_TOKEN_KEY;

//@WebServlet(urlPatterns = "/*")
public class LoginFilter implements Filter {

   // private final SessionDAO sessionDAO = SessionDAOImpl.SESSION_DAO;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
/*        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        boolean logged = false;

        if (Objects.nonNull(cookies)) {
            for (Cookie cookie: cookies) {
                if (USER_TOKEN_KEY.equals(cookie.getName()) && sessionDAO.isSessionExist(cookie.getValue())) {
                    logged = true;
                    break;
                }
            }
        }

        if (logged || request.getRequestURL().equals("/jsp/login.jsp")) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
        }*/
    }

    @Override
    public void destroy() {

    }
}
