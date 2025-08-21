package com.pahanaedu.dao;

import com.pahanaedu.model.Customer;
import com.pahanaedu.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void save(Customer c) {
        String sql = "INSERT INTO customers(account_number, name, address, telephone, units_consumed) VALUES(?,?,?,?,?) "
                   + "ON DUPLICATE KEY UPDATE name=VALUES(name), address=VALUES(address), telephone=VALUES(telephone), units_consumed=VALUES(units_consumed)";
        try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, c.getAccountNumber());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getTelephone());
            ps.setInt(5, c.getUnitsConsumed());
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public Customer find(long accountNumber) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT account_number, name, address, telephone, units_consumed FROM customers WHERE account_number=?")) {
            ps.setLong(1, accountNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                }
            }
            return null;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void delete(long accountNumber) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM customers WHERE account_number=?")) {
            ps.setLong(1, accountNumber);
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public List<Customer> findAll() {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT account_number, name, address, telephone, units_consumed FROM customers ORDER BY account_number");
             ResultSet rs = ps.executeQuery()) {
            List<Customer> list = new ArrayList<>();
            while (rs.next()) list.add(new Customer(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            return list;
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
