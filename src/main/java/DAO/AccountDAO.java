/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Model.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    
    private Connection conn;

    public AccountDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }//initialize conn,try to connect to database, return error if fail
    
     private Account getAccount(ResultSet rs) {
        try {
            int id = rs.getInt("ID");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String fullname = rs.getString("fullname");
            Timestamp datePost = rs.getTimestamp("date");
            int status = rs.getInt("status");
            String avatar = rs.getString("avatar");
            int role = rs.getInt("role");
            String roleName = rs.getString("roleName");
            Account a = new Account(id, username, password, email, phone, status, fullname, datePost, role, roleName, avatar);
            a.setRoleName(roleName);
            return a;
        } catch (Exception e) {
            System.out.println("Get account: " + e);
        }
        return null;
    }//return an Account from a result set
     
    public Account Login(String username){
        String sql = "SELECT Account.*,Role.name as roleName "
                + "FROM [Account] as Account join [Role] as Role "
                + "ON Account.role = R.Id "
                + "WHERE username = ? and Account.status = 1 and Role.status=1 ";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return this.getAccount(rs);
            }
            
        }catch(Exception e){
            System.out.println("Login: " + e);
        }
        return null;
    }//Return an Account based on the username
    
}
