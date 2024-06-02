/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Bill;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private Connection conn;

    public BillDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fails: " + e);
        }
    }
    public Bill getBill(ResultSet result){
        try{
            int id = result.getInt("id");
            int customerID = result.getInt("accountID");
            String email = result.getString("email");
            String customerName = result.getString("customerName");
            String phone = result.getString("phone");
            String address = result.getString("address");
            String detailAddress = result.getString("detailAddress");
            float total = result.getFloat("total");
            int status = result.getInt("status");
            int payment = result.getInt("payment");
            int isGetPoint = result.getInt("isGetPoint");
            int isUsedPoint = result.getInt("isUsedPoint");
            Timestamp dateOrder = result.getTimestamp("dateOrder");
            Timestamp dateUpdate = result.getTimestamp("dateUpdate");
            String transactionCode = result.getString("transactionCode");
            Bill bill = new Bill(id, customerID, email, customerName, phone, address, detailAddress, total, status, payment, dateOrder, dateUpdate, transactionCode);
            bill.setIsGetPoint(isGetPoint);
            bill.setIsUsedPoint(isUsedPoint);
            return bill;
        }catch(SQLException e){
            System.out.println("Get bill: "+e);
        }return null;
    }
 
    
    public List<Bill> getBillByStatus(int status){
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM BILL "
                + "WHERE STATUS = ? "
                + "ORDER BY id desc";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                bills.add(this.getBill(rs));
            }
        }catch(SQLException e){
            System.out.println("Get bill by status: "+e);
        }
        return bills;
    }
}
