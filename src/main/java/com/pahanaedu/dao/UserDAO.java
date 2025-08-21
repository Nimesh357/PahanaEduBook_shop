package com.pahanaedu.dao;

import com.pahanaedu.model.User;
import com.pahanaedu.util.DB;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User findByUsername(String username) {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT username, password_hash, full_name FROM users WHERE username=?")) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(rs.getString(1), rs.getString(2), rs.getString(3));
                }
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(User u) {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO users(username, password_hash, full_name) VALUES(?,?,?) ON DUPLICATE KEY UPDATE password_hash=VALUES(password_hash), full_name=VALUES(full_name)")) {
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getFullName());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT username, password_hash, full_name FROM users");
             ResultSet rs = ps.executeQuery()) {
            List<User> out = new ArrayList<>();
            while (rs.next()) out.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
            return out;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
