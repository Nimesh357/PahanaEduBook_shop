package com.pahanaedu.dao;

import com.pahanaedu.model.Item;
import com.pahanaedu.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public void save(Item i) {
        String sql = "INSERT INTO items(id, name, description, unit_price) VALUES(?,?,?,?) "
                   + "ON DUPLICATE KEY UPDATE name=VALUES(name), description=VALUES(description), unit_price=VALUES(unit_price)";
        try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, i.getId());
            ps.setString(2, i.getName());
            ps.setString(3, i.getDescription());
            ps.setDouble(4, i.getUnitPrice());
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public Item find(long id) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT id, name, description, unit_price FROM items WHERE id=?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            }
            return null;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void delete(long id) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM items WHERE id=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public List<Item> findAll() {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT id, name, description, unit_price FROM items ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            List<Item> list = new ArrayList<>();
            while (rs.next()) list.add(new Item(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            return list;
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
