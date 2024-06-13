/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import DBConnection.DBConnection;
import Model.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VoucherDAO {
private Connection conn;

    public VoucherDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            conn = null;
        }
    }

    public List<Voucher> allVoucher() {
        String sql = "select * from Voucher";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            List<Voucher> vouchers = new ArrayList<>();
            while (result.next()) {
                vouchers.add(this.getVoucher(result));
            }
            return vouchers;
        } catch (SQLException er) {
            System.out.println("SQL get all voucher: " + er);
        }
        return null;
    }

    public List<Voucher> getVoucherByStatus(int status) {
        String sql = "select * from Voucher where status=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet result = st.executeQuery();
            List<Voucher> vouchers = new ArrayList<>();
            while (result.next()) {
                vouchers.add(this.getVoucher(result));
            }
            return vouchers;
        } catch (SQLException er) {
            System.out.println("SQL get all voucher: " + er);
        }
        return null;
    }

    public Voucher getVoucherByCode(String code) {
        String sql = "select * from Voucher where code=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, code);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getVoucher(result);
            }
        } catch (SQLException er) {
            System.out.println("SQL get voucher by code: " + er);
        }
        return null;
    }

    public Voucher getVoucherByCode(String code, float priceBill) {
        String sql = "select * from Voucher where code=? and limit <= ? and [end] >= CONVERT(DATE, GETDATE()) and [start] <= CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, code);
            st.setFloat(2, priceBill);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getVoucher(result);
            }
        } catch (SQLException er) {
            System.out.println("SQL get voucher by code: " + er);
        }
        return null;
    }

    public int updateUsedVouhcer(int used, int id) {
        String sql = "update voucher set used = ? where id=?";
        int result = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, used);
            st.setInt(2, id);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update used voucher: " + e);
        }
        return result;
    }

    public List<Voucher> allVoucherActive() {
        String sql = "SELECT * FROM Voucher WHERE [end] >= CONVERT(DATE, GETDATE())";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            List<Voucher> vouchers = new ArrayList<>();
            while (result.next()) {
                vouchers.add(this.getVoucher(result));
            }
            return vouchers;
        } catch (SQLException er) {
            System.out.println("SQL get all voucher: " + er);
        }
        return null;
    }

    public Voucher currentVoucher(int id) {
        String sql = "select * from voucher where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getVoucher(result);
            }
        } catch (SQLException er) {
            System.out.println("SQL get current voucher: " + er);
        }
        return null;
    }

    private Voucher getVoucher(ResultSet result) {
        try {
            int ID = result.getInt("ID");
            String name = result.getString("name");
            String code = result.getString("code");
            float value = result.getFloat("value");
            Timestamp start = result.getTimestamp("start");
            Timestamp end = result.getTimestamp("end");
            int status = result.getInt("status");
            float limit = result.getFloat("limit");
            int used = result.getInt("used");
            Voucher v = new Voucher(ID, name, code, value, start, end, status, limit);
            v.setUsed(used);
            return v;
        } catch (SQLException e) {
            System.out.println("Get voucher: " + e);
        }
        return null;
    }

    public int insert(Voucher c) {
        int result = 0;
        String sql = "INSERT INTO voucher (name, code, value, start, [end], status, limit, used) "
                + "VALUES(?,?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, c.getName());
            st.setString(i++, c.getCode());
            st.setFloat(i++, c.getValue());
            st.setTimestamp(i++, c.getStart());
            st.setTimestamp(i++, c.getEnd());
            st.setInt(i++, c.getStatus());
            st.setFloat(i++, c.getLimit());
            st.setInt(i++, 0);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Insert voucher: " + e);
        }
        return result;
    }

    public int update(Voucher c) {
        int result = 0;
        String sql = "update voucher set  name=?, code=?, value=?, start=?, [end]=?, status=?, limit=? where ID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, c.getName());
            st.setString(i++, c.getCode());
            st.setFloat(i++, c.getValue());
            st.setTimestamp(i++, c.getStart());
            st.setTimestamp(i++, c.getEnd());
            st.setInt(i++, c.getStatus());
            st.setFloat(i++, c.getLimit());
            st.setInt(i++, c.getId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL update voucher: " + e);
        }
        return result;
    }
    
    public int updateUsed(Voucher c) {
        int result = 0;
        String sql = "update voucher set used where ID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, c.getUsed());
            st.setInt(i++, c.getId());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL update voucher: " + e);
        }
        return result;
    }

    public int delete(int id) {
        int result = 0;
        String sql = "delete from voucher where id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Delete voucher: " + e);
        }
        return result;
    }
}
