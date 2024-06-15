/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Cart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CartDAO {

    private Connection conn;
    //a row in cart table is a mini cart
    //a mini cart contain only 1 product's information
    //many mini cart linked to user by accountID make a big cart
    public CartDAO() {
        try {
            this.conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.err.println("Connection fail: " + e);
            this.conn = null;
        }//get connection
    }

    public List<Cart> getAllCart(int userId) {
        String sql = "select * from cart where accountID=?";
        //get all mini cart for a user using userID
        List<Cart> carts = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int accountID = rs.getInt("accountID");
                float totalPrice = rs.getFloat("totalPrice");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                Cart c = new Cart(id, accountID, totalPrice, quantity, productID);
                carts.add(c);
            }
        } catch (Exception e) {
            System.out.println("Get all cart: " + e);
        }
        return carts;
    }

    public Cart getByIdUser(int productId, int user_id) {
        String sql = "select * from cart where accountID=? and productID=?";
        //get a product from a user's cart
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, productId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                int accountID = rs.getInt("accountID");
                float totalPrice = rs.getFloat("totalPrice");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                Cart c = new Cart(id, accountID, totalPrice, quantity, productID);
                return c;
            }
        } catch (Exception e) {
            System.out.println("Get all cart: " + e);
        }
        return null;
    }

    public int addToCart(Cart c) {
        String sql = "insert into Cart (accountID, totalPrice, productID, quantity) values (?, ?, ?, ?)";
        int result = 0;
        //add a new product to user's cart
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, c.getAccountID());
            st.setFloat(2, c.getTotalPrice());
            st.setInt(3, c.getProductID());
            st.setInt(4, c.getQuantity());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add to cart: " + e);
        }
        return result;
    }

    public int updateToCart(Cart c) {
        String sql = "update Cart set quantity=?, totalPrice=? where accountID=? and productID=?";
        int result = 0;
        //update a product's quantity
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, c.getQuantity());
            st.setDouble(2, c.getTotalPrice());
            st.setInt(3, c.getAccountID());
            st.setInt(4, c.getProductID());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update to cart: " + e);
        }
        return result;
    }

    public int deleteCartItem(int id) {
        String sql = "delete from cart where ID=?";
        //remove from cart using mini cart's id
        int result = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete cart error: " + e);
        }
        return result;
    }

    public int deleteCartItemByUser(int user) {
        //remove all mini cart from that user's ID?
        String sql = "delete from cart where accountID=?";
        int result = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, user);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Delete cart error by user: " + e);
        }
        return result;
    }
}
