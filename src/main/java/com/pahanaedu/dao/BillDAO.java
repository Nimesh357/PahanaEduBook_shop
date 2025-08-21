package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.BillItem;
import com.pahanaedu.util.DB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public Bill find(long id) {
        try (Connection con = DB.getConnection()) {
            Bill bill = null;
            try (PreparedStatement p1 = con.prepareStatement("SELECT b.id, b.customer_account_number, c.name, b.created_at FROM bills b JOIN customers c ON b.customer_account_number=c.account_number WHERE b.id=?")) {
                p1.setLong(1, id);
                try (ResultSet r1 = p1.executeQuery()) {
                    if (r1.next()) {
                        bill = new Bill(r1.getLong(1), r1.getLong(2), r1.getString(3));
                        bill.setCreatedAt(r1.getTimestamp(4).toLocalDateTime());
                    } else return null;
                }
            }
            try (PreparedStatement p2 = con.prepareStatement("SELECT item_id, item_name, unit_price, quantity FROM bill_items WHERE bill_id=?")) {
                p2.setLong(1, id);
                try (ResultSet r2 = p2.executeQuery()) {
                    List<BillItem> items = new ArrayList<>();
                    while (r2.next()) items.add(new BillItem(r2.getLong(1), r2.getString(2), r2.getInt(4), r2.getDouble(3)));
                    bill.setItems(items);
                }
            }
            return bill;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public List<Bill> findAll() {
        String sql = "SELECT b.id, b.customer_account_number, c.name, b.created_at, "
                   + "COALESCE(SUM(bi.quantity),0) AS total_units, COALESCE(SUM(bi.line_total),0) AS total_amount "
                   + "FROM bills b JOIN customers c ON b.customer_account_number=c.account_number "
                   + "LEFT JOIN bill_items bi ON bi.bill_id=b.id "
                   + "GROUP BY b.id, b.customer_account_number, c.name, b.created_at ORDER BY b.id DESC";
        try (Connection con = DB.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            List<Bill> out = new ArrayList<>();
            while (rs.next()) {
                Bill b = new Bill(rs.getLong(1), rs.getLong(2), rs.getString(3));
                b.setCreatedAt(rs.getTimestamp(4).toLocalDateTime());
                out.add(b);
            }
            return out;
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public Bill save(Bill b) {
        String insBill = "INSERT INTO bills(customer_account_number) VALUES(?)";
        String insItem = "INSERT INTO bill_items(bill_id, item_id, item_name, unit_price, quantity, line_total) VALUES(?,?,?,?,?,?)";
        try (Connection con = DB.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement p1 = con.prepareStatement(insBill, Statement.RETURN_GENERATED_KEYS)) {
                p1.setLong(1, b.getCustomerAccountNumber());
                p1.executeUpdate();
                try (ResultSet keys = p1.getGeneratedKeys()) {
                    if (keys.next()) b.setId(keys.getLong(1));
                }
            }
            try (PreparedStatement p2 = con.prepareStatement(insItem)) {
                for (BillItem it : b.getItems()) {
                    p2.setLong(1, b.getId());
                    p2.setLong(2, it.getItemId());
                    p2.setString(3, it.getItemName());
                    p2.setDouble(4, it.getUnitPrice());
                    p2.setInt(5, it.getQuantity());
                    p2.setDouble(6, it.getLineTotal());
                    p2.addBatch();
                }
                p2.executeBatch();
            }
            con.commit();
            return b;
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}
