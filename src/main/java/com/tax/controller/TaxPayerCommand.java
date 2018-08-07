package com.tax.controller;

import com.tax.entity.TaxPayer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class TaxPayerCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        TaxPayer taxPayer = (TaxPayer) session.getAttribute("taxPayer");

        if (Objects.isNull(taxPayer)) {
            try {
                response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            } catch (IOException e) {
                //logg
                System.out.println("failed to redirect to login page");
            }
        }
    }
}
