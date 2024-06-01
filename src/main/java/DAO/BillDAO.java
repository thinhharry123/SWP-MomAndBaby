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
    public int addBill(Bill b){
        int result = 0;
        String sql = "INSERT INTO BILL (accountID, email, customerName, "
                + "phone, address, detailAddress, total, status, "
                + "payment, dateOrder, transactionCode, isUsedPoint ";
        //12 attribute
        if(b.getVoucherID() >=1 ){
            sql += ", voucherID";
//if the user use voucher it'll add that in insert in bill table
        }
        
        sql += ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
        //12 ? for 12 value
        if(b.getVoucherID() >=1 ){
            sql += ", ?";
        }
        sql += ")";
        return 0;
    }
}
