/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Brand;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CategoryDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
            conn = null;
        }
    }


    public int insert(Category c) {
        int result = 0;
        String sql = "INSERT INTO Category (name, datePost, status) VALUES(?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setTimestamp(2, c.getDatePost());
            ps.setInt(3, c.getStatus());
            result = ps.executeUpdate();
        } catch (SQLException e) {

        }
        return result;
    }

    public int update(Category c) {
        int result = 0;
        String sql = "update Category set name=?, dateUpdate=?, status=? where ID=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setTimestamp(2, c.getDateUpdate());
            ps.setInt(3, c.getStatus());
            ps.setInt(4, c.getID());
            result = ps.executeUpdate();
        } catch (SQLException e) {

        }
        return result;
    }

    public int delete(int id) {
        int result = 0;
        String sql = "delete from Category where ID=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (SQLException er) {

        }
        return result;
    }

    public Category getCategory(ResultSet result) {
        try {
            int ID = result.getInt("ID");
            String name = result.getString("name");
            Timestamp datePost = result.getTimestamp("datePost");
            Timestamp dateUpdate = result.getTimestamp("dateUpdate");
            int status = result.getInt("status");
            Category c = new Category(ID, name, datePost, dateUpdate, status);
            return c;
        } catch (SQLException e) {
            System.out.println("Get category: " + e);
        }
        return null;
    }

    public Category getCategoryByID(int id) {
        String sql = "select * from Category where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return this.getCategory(rs);
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Category> getCategoryByStatus(int status) {
        String sql = "select * from Category  where status=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            rs = ps.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                categories.add(this.getCategory(rs));
            }
            return categories;
        } catch (SQLException er) {
        }
        return null;
    }
//
    public int getNumberProductByCategory(int id) {
        String sql = "select count(id) as numberProduct from Product where categoryID =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("numberProduct");
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("Get number product by category: " + e);
        }
        return 0;
    }
//
    public List<Category> allCategory() {
        String sql = "select * from Category order by id desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (result.next()) {
                categories.add(this.getCategory(result));
            }
            return categories;
        } catch (SQLException er) {

        }
        return null;
    }
//
    public List<Category> getCategoryActive() {
        String sql = "select c.* from Category as c where c.status = 1";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                categories.add(this.getCategory(result));
            }
        } catch (SQLException er) {

        }
        return categories;
    }
//
    public Category getCategoryActiveByID(int id) {
        String sql = "select c.* from Category as c where c.status = 1 and id=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getCategory(result);
            }
        } catch (SQLException er) {
            System.out.println("Get category active by id: " + er);
        }
        return null;
    }
//
    public List<Category> getCategoryInHome() {
        String sql = "select top 5 c.* from Category as c where c.status = 1";
        List<Category> categories = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(this.getCategory(rs));
            }
        } catch (SQLException e) {
        }
        return categories;
    }
      public Category getCategoryByName(String name) {
        String sql = "select * from Category where name = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getCategory(result);
            }
        } catch (SQLException er) {
            System.out.println("Get category by name: " + er);
        }
        return null;
    }
    
    public int getNumberBlogByCategory(int id) {
        String sql = "select count(id) as numberBlog from Blog where categoryID =? and status=1";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return result.getInt("numberBlog");
            }
            return 0;
        } catch (SQLException e) {
             System.out.println("Get number blog by category: "  + e);
        }
        return 0;
    }
}