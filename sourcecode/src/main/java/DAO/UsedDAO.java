/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAO;

import Model.Used;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;


public class UsedDAO {
private Connection conn;

    public UsedDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        }catch(Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }
    
    public Used getUsed(int userID, int voucherID) {
        String sql = "select * from [used] where accountId=? and voucherID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, voucherID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Timestamp dateUse = rs.getTimestamp("dateUse");
                Used r = new Used(voucherID, voucherID, dateUse);
                return r;
            }
        }catch(Exception e) {
            System.out.println("Get used: " + e);
        }
        return null;
    }
    
    public int addUsed(Used u) {
        String sql = "insert into [Used] (accountId, voucherId, dateUse) values (? , ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, u.getAccountID());
            st.setInt(2, u.getVoucherID());
            st.setTimestamp(3, u.getDateUse());
            return st.executeUpdate();
        }catch(Exception e) {
            System.out.println("Add used voucher: " + e);
        }
        return 0;
    }
}
