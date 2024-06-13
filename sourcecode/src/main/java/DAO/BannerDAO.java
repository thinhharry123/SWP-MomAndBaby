/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Banner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class BannerDAO {
    
      private Connection conn;

    public BannerDAO() {
        try {
            conn = DBConnection.DBConnection.connect();
        } catch (Exception e) {
            System.out.println("Connection fail: " + e);
        }
    }
    
    
    
    
     private Banner getBanner(ResultSet result) {
        try {
            int ID = result.getInt("ID");
            String img = result.getString("img");
            String name = result.getString("name");
            int status = result.getInt("status");
            Timestamp datePost = result.getTimestamp("datePost");
            Timestamp dateUpdate = result.getTimestamp("dateUpdate");
            Banner b = new Banner(ID, img, name, datePost, dateUpdate, status);
            return b;
        } catch (SQLException e) {
            System.out.println("Get banner: " + e);
        }
        return null;
    }
     
      public List<Banner> getTop5Banner() {
        String sql = "select top 5 * from Banner where status=1 order by id desc";
        List<Banner> banners = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                banners.add(this.getBanner(result));
            }
        } catch (SQLException e) {
            System.out.println("Get top 5 banner: " + e);
        }
        return banners;
    }
}
