package com.tax.controller;


import com.tax.dao.SessionDAO;
import com.tax.dao.daoImpl.SessionDAOImpl;
import com.tax.entity.Inspector;
import com.tax.entity.Session;
import com.tax.entity.TaxPayer;
import com.tax.entity.User;
import com.tax.exception.ServiceExceprion;
import com.tax.service.AuthService;
import com.tax.service.InspectorService;
import com.tax.service.ServiceFactory;
import com.tax.service.ServiceFactoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class LoginServlet implements Command {

    private final ServiceFactory serviceFactory = ServiceFactoryImpl.getFactory();
/*    private final SessionDAO sessionDao = SessionDAOImpl.SESSION_DAO;
    public static final String USER_TOKEN_KEY = "user-token";*/

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = serviceFactory.getAuthService().getAuthUser(login, password);
        } catch (ServiceExceprion serviceExceprion) {
            //logg
            System.out.println("bad with auth");
            serviceExceprion.printStackTrace();
        }

        if (Objects.nonNull(user)) {
            //logg
            redirectUser(request, response, user);
        } else {
            //logg
            session.setAttribute("authenticationFailed", true);
            try {
                response.sendRedirect(request.getContextPath() + "/jsp" + page);
            } catch (IOException e) {
                System.out.println("Failed to redirect");
            }
        }
    }

    private void redirectUser(HttpServletRequest request, HttpServletResponse response, User user) {
        HttpSession session = request.getSession();
      //  session.setAttribute("user", user);

        if (user.getUserRoleId() == 1) {
            Inspector inspector = serviceFactory.getInspectorService().getInspectorByUser(user);
            session.setAttribute("inspector", inspector);
/*            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(USER_TOKEN_KEY, token);
            response.addCookie(cookie);
            sessionDao.save(new Session(token, inspector));*/
            try {
                response.sendRedirect(request.getContextPath() + "/controller?command=inspector&page=inspector.jsp");
            } catch (IOException e) {
                System.out.println("troubles with redirect to inspector cabinet");
                e.printStackTrace();
            }
        }
        if (user.getUserRoleId() == 2) {
            TaxPayer taxPayer = serviceFactory.getTaxPayerService().getTaxpayerByUser(user);
            session.setAttribute("taxPayer", taxPayer);
/*            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie(USER_TOKEN_KEY, token);
            response.addCookie(cookie);
            sessionDao.save(new Session(token, taxPayer));*/
            try {
                response.sendRedirect(request.getContextPath() + "/controller?command=taxpayer&page=taxPayer.jsp");
            } catch (IOException e) {
                System.out.println("troubles with redirect to taxpayer cabinet");
                e.printStackTrace();
            }
        }
    }
}
