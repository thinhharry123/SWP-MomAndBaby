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
            Account a = new Account(id, username, password, email, phone, status, fullname, datePost, role, roleName,avatar);           
            return a;
        } catch (Exception e) {
            System.out.println("Get account: " + e);
        }
        return null;
    }//return an Account from a result set
     
     
    public Account Login(String username){
        String sql = "SELECT Account.*,Role.name as roleName "
                + "FROM [Account] as Account join [Role] as Role "
                + "ON Account.role = Role.Id "
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
  
    public Account getAccountByUsername(String username){
        String sql = "SELECT Account.*,Role.name as roleName "
                + "FROM [Account] as Account join [Role] as Role "
                + "ON Account.role = Role.Id "
                + "WHERE username = ? and Account.status = 1 and Role.status=1 ";
        
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return this.getAccount(rs);
            }
        }catch(Exception e){
            System.out.println("getAccountByUsername: "+e);
        }
        return null;
    }//pretty much the same as above
    
    public Account isExistAccount(String username, String email){
        String sql = "SELECT Account.* ,Role.name "
                + "FROM [Account] as Account "
                + "JOIN [Role] as Role "
                + "ON Account.role = Role.ID "
                + "WHERE (username = ? or email = ?)";
    try{
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, username);
        st.setString(2, email);
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            return this.getAccount(rs);
        }
    }catch(SQLException e){
        System.out.println("Admin check exist account by username,email: "+e);
    }
        return null;
    }
    //get account based on username or email
    
    public Account getAccountByID(int id){
        String sql= "SELECT Account.* ,Role.name as roleName "
                + "FROM [Account] as Account "
                + "JOIN [Role] as Role "
                + "ON Account.role = Role.ID "
                + "WHERE Account.ID = ? ";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return this.getAccount(rs);
            }
        }catch(SQLException e){
            System.out.println("Get account by id: "+e);
        }
        return null;
    }//get account based on id
    
    public List<Account>allAccount(){
        String sql = "SELECT Account.*, Role.name as roleName "
                + "FROM [Account] as Account "
                + "JOIN [Role] as Role "
                + "ON Role.ID = Account.role "
                + "ORDER BY Account.role desc, Account.id desc ";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List <Account> accounts = new ArrayList<>();
            while(rs.next()){
                accounts.add(this.getAccount(rs));
            }
        }catch(SQLException e){
            System.out.println("Get all account: "+e);
        }
        return null;
    }//get all account 
    
    public List<Account> allAccountByStaff(){
        String sql = "SELECT Account.*, Role.name as roleName "
                + "FROM [Account] as Account "
                + "JOIN [Role] as Role "
                + "ON Role.ID = Account.role "
                + "WHERE Role.name = 'user' "
                + "ORDER BY Account.role desc, Account.id desc ";
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List <Account> accounts = new ArrayList<>();
            while(rs.next()){
                accounts.add(this.getAccount(rs));
            }
            return accounts;
        }catch(SQLException e){
            System.out.println("Get all account: "+e);
        }
        return null;
    }//get all user account
    
    public void insert(Account a){
        int result = 0;
        String sql = "INSERT INTO "
                + "Account(fullname, email, phone, "
                + "username, password, role, "
                + "date, status, avatar) "
                + "VALUES(?, ?, ?, ?, ?, ?, ? ,?, ?)";
    }
}
