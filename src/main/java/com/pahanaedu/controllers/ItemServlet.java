package com.pahanaedu.controllers;

import com.pahanaedu.dao.ItemDAO;
import com.pahanaedu.model.Item;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ItemServlet extends HttpServlet {
    private final ItemDAO dao = new ItemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.equals("list")) {
            List<Item> items = dao.findAll();
            req.setAttribute("items", items);
            req.getRequestDispatcher("/views/items.jsp").forward(req, resp);
        } else if (action.equals("edit")) {
            long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("item", dao.find(id));
            req.getRequestDispatcher("/views/itemForm.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            long id = Long.parseLong(req.getParameter("id"));
            dao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/app/items?action=list");
        } else if (action.equals("new")) {
            req.getRequestDispatcher("/views/itemForm.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));
            dao.save(new Item(id, name, description, unitPrice));
            resp.sendRedirect(req.getContextPath() + "/app/items?action=list");
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/app/items?action=new&error=Invalid+numbers");
        }
    }
}
