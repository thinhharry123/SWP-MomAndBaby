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
 public List<Product> getProductByPriority(int status) {
        List<Product> products = new ArrayList<>();
        String sql = "select top 7 pro.* from product as pro join category as c on c.id = pro.categoryID"
                + " join producer as p on p.id = pro.producerID join Brand as br on br.ID = pro.brandID"
                + " where pro.status = 1 and "
                + "pro.priority=? and p.status=1 and c.status=1 and br.status =1 order by id desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Get priority product: " + e);
        }
        return products;
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
         public Product statusIsActive(int id) {
        String sql = "select p.* from Product as p join Producer "
                + "as pr on p.producerID = pr.ID join "
                + "Category as Ca on p.categoryID =Ca.ID "
                + "join brand as br on br.ID = p.brandID "
                + "where p.id = ? "
                + "and p.status =1 and pr.status=1 and Ca.status =1";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                Product product = this.getProduct(result);
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Product item: " + e);
        }
        return null;
    }
         
         //user
         
         public int updateQuantitySold(Product p) {
        int result = 0;
        String sql = "UPDATE Product SET quantity = ?, sold = ? WHERE id = ?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, p.getQuantity());
            st.setInt(i++, p.getSold());
            st.setInt(i++, p.getID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update quantity product: " + e);
        }
        return result;
    }
           public List<Product> getProductsByPage(int page, int pageSize, String type, int... id) {
        List<Product> products = new ArrayList<>();
        String sql = "select pro.* from product as pro join category as c on c.id = pro.categoryID "
                + "join producer as p on p.id = pro.producerID where pro.status = 1 and ";
        if (type.equals("category")) {
            sql += "pro.categoryID=? and ";
        } else if (type.equals("brand")) {
            sql += "pro.brandID=? and ";
        }
        sql += "p.status=1 and c.status=1 order by pro.ID desc "
                + "OFFSET ? ROWS "
                + "FETCH FIRST ? ROWS ONLY";
        int offset = (page - 1) * pageSize;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            if (type.equals("category")) {
                st.setInt(i++, id[0]);
            } else if (type.equals("brand")) {
                st.setInt(i++, id[0]);
            }
            st.setInt(i++, offset);
            st.setInt(i++, pageSize);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("get product page: " + e);
        }
        return products;
    }
           
            public List<Product> getAllProductActive(String type, int... id) {
        List<Product> products = new ArrayList<>();
        String sql = "select pro.* from product as pro join category as c on c.id = pro.categoryID"
                + " join producer as p on p.id = pro.producerID join Brand as Br on"
                + " br.ID = pro.brandId where pro.status = 1 and br.status=1 And "
                + "p.status=1 and c.status=1";
        if (type.equals("category")) {
            sql += " and c.ID =?";
        } else if (type.equals("brand")) {
            sql += " and br.ID=?";
        }
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            if (type.equals("category")) {
                st.setInt(i++, id[0]);
            } else if (type.equals("brand")) {
                st.setInt(i++, id[0]);
            }
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Get all product: " + e);
        }
        return products;
    }
 public List<Product> getAllProductActiveRelative(Product p) {
        List<Product> products = new ArrayList<>();
        String sql = "select top 4 pro.* from product as pro join category as c on c.id = pro.categoryID"
                + " join producer as p on p.id = pro.producerID join Brand as Br on"
                + " br.ID = pro.brandId where pro.status = 1 and br.status=1 And "
                + "p.status=1 and c.status=1 and (pro.categoryID =? or pro.brandID = ? or pro.producerID=?) order by pro.ID desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, p.getCategoryID());
            st.setInt(i++, p.getBrandID());
            st.setInt(i++, p.getProducerID());
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("get all product relative: " + e);
        }
        return products;
    }
 
  public List<Product> filterProduct(int[] idCategory, int[] idBrand, float from, float to, int time) {
        List<Product> products = new ArrayList<>();
        String sql = "select pro.* from product as pro join category as c on c.id = pro.categoryID "
                + "join producer as p on p.id = pro.producerID where pro.status = 1 and "
                + "p.status=1 and c.status=1 ";
        int i = 0;
        for (int id : idCategory) {
            if (idCategory.length - 1 == 0) {
                sql += " and pro.categoryId = ? ";
                break;
            } else if (i == 0) {
                sql += "and (pro.categoryId = ? ";
            } else if (i == idCategory.length - 1) {
                sql += "or pro.categoryId = ? ) ";
            } else {
                sql += "or pro.categoryId = ? ";
            }
            i++;
        }
        for (int id : idBrand) {
            if (idBrand.length - 1 == 0) {
                sql += " and pro.brandID = ? ";
                break;
            } else if (i == 0) {
                sql += "and (pro.brandID = ? ";
            } else if (i == idBrand.length - 1) {
                sql += "or pro.brandID = ? ) ";
            } else {
                sql += "or pro.brandID = ? ";
            }
            i++;
        }
        sql += "and ((pro.newPrice >= ?  and pro.newPrice <= ?) or (pro.oldPrice >= ?  and pro.oldPrice <= ?)) ";
        if (time == 0) {
            sql += "order by id asc";
        } else {
            sql += "order by id desc";
        }
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int index = 1;
            for (int id : idCategory) {
                st.setInt(index++, id);
            }
            for (int id : idBrand) {
                st.setInt(index++, id);
            }
            st.setFloat(index++, from);
            st.setFloat(index++, to);
            st.setFloat(index++, from);
            st.setFloat(index++, to);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Get priority product: " + e);
        }
        return products;
    }
  
   public List<Product> seachProduct(String keyword, int page, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "select pro.* from product as pro join category as c on c.id = pro.categoryID "
                + "join producer as p on p.id = pro.producerID where pro.status = 1 and "
                + "p.status=1 and c.status=1 and pro.name like ? order by id desc "
                + "OFFSET ? ROWS "
                + "FETCH FIRST ? ROWS ONLY";
        int offset = (page - 1) * pageSize;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            st.setInt(2, offset);
            st.setInt(3, pageSize);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Get search product: " + e);
        }
        return products;
    }
   
    public List<Product> seachProduct(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "select pro.* from product as pro join category as c on c.id = pro.categoryID "
                + "join producer as p on p.id = pro.producerID where pro.status = 1 and "
                + "p.status=1 and c.status=1 and pro.name like ? order by id desc ";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "%" + keyword + "%");
            ResultSet result = st.executeQuery();
            while (result.next()) {
                products.add(this.getProduct(result));
            }
        } catch (SQLException e) {
            System.out.println("Get priority product: " + e);
        }
        return products;
    }


}
