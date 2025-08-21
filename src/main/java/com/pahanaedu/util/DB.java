package com.pahanaedu.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
    private static String url, user, password, driver;
    public static void init(InputStream propsStream) throws Exception {
        Properties p = new Properties();
        p.load(propsStream);
        url = p.getProperty("jdbc.url");
        user = p.getProperty("jdbc.user");
        password = p.getProperty("jdbc.password");
        driver = p.getProperty("jdbc.driver");
        Class.forName(driver);
    }
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }
}
