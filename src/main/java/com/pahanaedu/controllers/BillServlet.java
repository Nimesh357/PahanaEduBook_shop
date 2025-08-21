package com.pahanaedu.controllers;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillServlet extends HttpServlet {

    private final BillDAO billDAO = new BillDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ItemDAO itemDAO = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            req.setAttribute("bills", billDAO.findAll());
            req.getRequestDispatcher("/views/bills.jsp").forward(req, resp);
        } else if (action.equals("new")) {
            req.setAttribute("items", itemDAO.findAll());
            req.getRequestDispatcher("/views/billCreate.jsp").forward(req, resp);
        } else if (action.equals("print")) {
            long id = Long.parseLong(req.getParameter("id"));
            Bill b = billDAO.find(id);
            req.setAttribute("bill", b);
            req.getRequestDispatcher("/views/billPrint.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long accountNumber = Long.parseLong(req.getParameter("accountNumber"));
            Customer c = customerDAO.find(accountNumber);
            if (c == null) {
                resp.sendRedirect(req.getContextPath() + "/app/bills?action=new&error=Customer+not+found");
                return;
            }
            List<Item> allItems = itemDAO.findAll();
            List<BillItem> billItems = new ArrayList<>();
            for (Item it : allItems) {
                String qtyStr = req.getParameter("qty_" + it.getId());
                if (qtyStr == null || qtyStr.trim().isEmpty()) continue;
                int q = 0;
                try { q = Integer.parseInt(qtyStr); } catch (Exception ignore) {}
                if (q > 0) billItems.add(new BillItem(it.getId(), it.getName(), q, it.getUnitPrice()));
            }
            if (billItems.isEmpty()) {
                resp.sendRedirect(req.getContextPath() + "/app/bills?action=new&error=No+items+selected");
                return;
            }
            Bill bill = new Bill(0, c.getAccountNumber(), c.getName());
            bill.setItems(billItems);
            bill = billDAO.save(bill);
            resp.sendRedirect(req.getContextPath() + "/app/bills?action=print&id=" + bill.getId());
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/app/bills?action=new&error=Invalid+input");
        }
    }
}
