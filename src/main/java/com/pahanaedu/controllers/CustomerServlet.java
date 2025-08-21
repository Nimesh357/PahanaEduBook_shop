package com.pahanaedu.controllers;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    private final CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            List<Customer> customers = dao.findAll();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("/views/customers.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            long acc = Long.parseLong(req.getParameter("accountNumber"));
            Customer c = dao.find(acc);
            req.setAttribute("customer", c);
            req.getRequestDispatcher("/views/customerForm.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            long acc = Long.parseLong(req.getParameter("accountNumber"));
            dao.delete(acc);
            resp.sendRedirect(req.getContextPath() + "/app/customers?action=list");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/views/customerForm.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long accountNumber = Long.parseLong(req.getParameter("accountNumber"));
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String telephone = req.getParameter("telephone");
            int units = 0;
            try { units = Integer.parseInt(req.getParameter("unitsConsumed")); } catch (Exception ignore) {}
            dao.save(new Customer(accountNumber, name, address, telephone, units));
            resp.sendRedirect(req.getContextPath() + "/app/customers?action=list");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/app/customers?action=new&error=Invalid+numbers");
        }
    }
}
