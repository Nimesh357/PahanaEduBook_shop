package com.pahanaedu.controllers;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername(username);

        if (user != null && user.getPasswordHash().equals(UserDAO.hash(password))) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/views/home.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp?error=Invalid+credentials");
        }
    }
}
