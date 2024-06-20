/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PreOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PreOrderDAO {

    private Connection conn;

    public PreOrderDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

    public List<PreOrder> getAll() {
        String sql = "select * from preOrder";
        List<PreOrder> preOrder = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                preOrder.add(this.getPreOrder(rs));
            }
        } catch (Exception e) {
            System.out.println("Get all preOrder: " + e);
        }
        return preOrder;
    }

    public List<PreOrder> getAllByAccountID(int accountID) {
        String sql = "select * from preOrder where accountId = ?";
        List<PreOrder> preOrder = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, accountID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                preOrder.add(this.getPreOrder(rs));
            }
        } catch (Exception e) {
            System.out.println("Get all preOrder  by accountID: " + e);
        }
        return preOrder;
    }

    public PreOrder getByID(int id) {
        String sql = "select * from preOrder where id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return this.getPreOrder(rs);
            }
        } catch (Exception e) {
            System.out.println("Get preOrder by ID: " + e);
        }
        return null;
    }

    public int addPreOrder(PreOrder p) {
        String sql = "insert into preOrder (productID, accountID, status) values (?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, p.getProductID());
            st.setInt(i++, p.getAccountID());
            st.setInt(i++, p.getStatus());
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add pre order: " + e);
        }
        return 0;
    }

    public int updateStatus(int status, int ID) {
        String sql = "update preOrder set status =? where id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, ID);
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update status preOrder: " + e);
        }
        return 0;
    }

    public int delete(int id) {
        String sql = "delete from preOrder where id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete preOrder: " + e);
        }
        return 0;
    }

    public PreOrder getPreOrder(ResultSet rs) {
        try {
            int ID = rs.getInt("ID");
            int productID = rs.getInt("productID");
            int accountID = rs.getInt("accountID");
            int status = rs.getInt("status");
            PreOrder p = new PreOrder(ID, productID, accountID, status);
            return p;
        } catch (Exception e) {
            System.out.println("Get preOrder: " + e);
        }
        return null;
    }
}
