/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {
    
    private Connection conn;
     public ProductDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }

     //admin start
     
      public Product getProduct(ResultSet rs) {
        try {
            int ID = rs.getInt("ID");
            String name = rs.getString("name");
            float oldPrice = rs.getFloat("oldPrice");
            float newPrice = rs.getFloat("newPrice");
            String description = rs.getString("description");
            Timestamp datePost = rs.getTimestamp("datePost");
            Timestamp dateUpdate = rs.getTimestamp("dateUpdate");
            String mainImg = rs.getString("mainImg");
            int status = rs.getInt("status");
            int quantity = rs.getInt("quantity");
            Integer sold = rs.getInt("sold");
            String model = rs.getString("model");
            int priority = rs.getInt("priority");
            int categoryID = rs.getInt("categoryID");
            int producerID = rs.getInt("producerID");
            int brandID = rs.getInt("brandID");
            Product p = new Product(ID, name, oldPrice, newPrice, description, datePost, dateUpdate, mainImg, status, quantity, sold, model, priority, categoryID, producerID, brandID);
            return p;
        } catch (Exception e) {
            System.out.println("Get product: " + e);
        }
        return null;
    }
     
      public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "select Pro.* from Product as Pro join "
                + "Category as C on Pro.categoryID = C.ID "
                + "join Producer as P on P.ID = Pro.producerID "
                + "join Brand as B on B.ID = Pro.brandID "
                + "order by Pro.ID desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                products.add(this.getProduct(rs));
            }
        } catch (Exception e) {
            System.out.println("Get product: " + e);
        }
        return products;
    }

       public Product getProductByID(int id) {
        String sql = "select * from Product where id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                Product p = this.getProduct(result);
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Get product by id: " + e);
        }
        return null;
    }
       
       public int insert(Product p) {
        int result = 0;
        String sql = "insert into Product (name, description, datePost, oldPrice, newPrice, "
                + "mainImg, status, quantity, sold, model, priority, categoryID, producerID, brandID)"
                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            st.setString(i++, p.getName());
            st.setString(i++, p.getDescription());
            st.setTimestamp(i++, p.getDatePost());
            st.setFloat(i++, p.getOldPrice());
            st.setFloat(i++, p.getNewPrice());
            st.setString(i++, p.getMainImg());
            st.setInt(i++, p.getStatus());
            st.setInt(i++, p.getQuantity());
            st.setInt(i++, p.getSold());
            st.setString(i++, p.getModel());
            st.setInt(i++, p.getPriority());
            st.setInt(i++, p.getCategoryID());
            st.setInt(i++, p.getProducerID());
            st.setInt(i++, p.getBrandID());
            result = st.executeUpdate();
            if (result > 0) {
                try ( ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        return id;
                    }
                } catch (SQLException e) {

                }
            }
        } catch (SQLException e) {
            System.out.println("Add product" + e);
        }
        return 0;
    }
       
        public int update(Product p) {
        int result = 0;
        String sql = "UPDATE Product SET name = ?, description = ?, dateUpdate = ?, oldPrice = ?, newPrice = ?, "
                + "mainImg = ?, status = ?, quantity = ?, sold = ?, model = ?, priority = ?, categoryID = ?, "
                + "producerID = ?, brandID = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setString(i++, p.getName());
            st.setString(i++, p.getDescription());
            st.setTimestamp(i++, p.getDateUpdate());
            st.setFloat(i++, p.getOldPrice());
            st.setFloat(i++, p.getNewPrice());
            st.setString(i++, p.getMainImg());
            st.setInt(i++, p.getStatus());
            st.setInt(i++, p.getQuantity());
            st.setInt(i++, p.getSold());
            st.setString(i++, p.getModel());
            st.setInt(i++, p.getPriority());
            st.setInt(i++, p.getCategoryID());
            st.setInt(i++, p.getProducerID());
            st.setInt(i++, p.getBrandID());
            st.setInt(i++, p.getID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update product: " + e);
        }
        return result;
    }

    public int delete(int id) {
        int result = 0;
        String sql = "delete from Product where id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete product: " + e);
        }
        return result;
    }
     public List<Product> getTopFiveProduct() {
        List<Product> products = new ArrayList<>();
        String sql = "select top 5 * from product where sold > 0 order by sold desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
        }
        return products;
    }
}
