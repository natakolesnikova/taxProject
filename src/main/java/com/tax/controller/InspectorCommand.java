package com.tax.controller;

import com.tax.entity.Inspector;
import com.tax.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class InspectorCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Inspector inspector = (Inspector) session.getAttribute("inspector");

        if (Objects.isNull(inspector)) {
            try {
                response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            } catch (IOException e) {
                //logg
                System.out.println("Failed to redirect to login page");
            }
        }



    }
}
