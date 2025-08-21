package com.pahanaedu.config;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;
import com.pahanaedu.util.DB;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContext;
import java.io.InputStream;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext ctx = sce.getServletContext();
            String propsPath = ctx.getInitParameter("DB_PROPS");
            try (InputStream in = ctx.getResourceAsStream(propsPath)) {
                DB.init(in);
            }

            // Ensure default admin exists
            UserDAO userDAO = new UserDAO();
            if (userDAO.findByUsername("admin") == null) {
                userDAO.save(new User("admin", UserDAO.hash("admin123"), "Administrator"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize application", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
