/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBConnection.DBConnection;
import Model.Feedback;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    private Connection conn;

    public FeedbackDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
//            handle error here
            conn = null;
        }
    }

    public List<Feedback> allFeedbackByProduct(int idProduct) {
        String sql = "select F.* from feedback as F "
                + "join Bill as B on B.ID = F.billID "
                + "join BillDetail as BD on BD.billID = B.ID "
                + "where BD.productID = ? and F.status <= 1 order by F.ID desc";
        List<Feedback> feebacks = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idProduct);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                feebacks.add(this.getFeedback(result));
            }
        } catch (SQLException er) {
            System.out.println("Get all feedback: " + er);
        }
        return feebacks;
    }

    public int getIdProductByFeedback(int idFeedback) {
        String sql = "select BD.* from feedback as F "
                + "join Bill as B on B.ID = F.billID "
                + "join BillDetail as BD on BD.billID = B.ID "
                + "where F.ID = ? order by F.ID desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idFeedback);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return result.getInt("productID");
            }
        } catch (SQLException er) {
            System.out.println("Get product id feedback: " + er);
        }
        return 0;
    }

    public List<Feedback> allFeedbackByNew() {
        String sql = "SELECT F.* "
                + "FROM feedback AS F "
                + "JOIN Bill AS B ON B.ID = F.billID "
                + "JOIN BillDetail AS BD ON BD.billID = B.ID "
                + "WHERE F.status = 0";
        List<Feedback> feebacks = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                feebacks.add(this.getFeedback(result));
            }
        } catch (SQLException er) {
            System.out.println("Get all feedback new: " + er);
        }
        return feebacks;
    }

    public List<Feedback> allFeedbackByProductAdmin(int idProduct) {
        String sql = "select F.* from feedback as F "
                + "join Bill as B on B.ID = F.billID "
                + "join BillDetail as BD on BD.billID = B.ID "
                + "where BD.productID = ? order by F.ID desc";
        List<Feedback> feebacks = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idProduct);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                feebacks.add(this.getFeedback(result));
            }
        } catch (SQLException er) {
            System.out.println("Get all feedback admin: " + er);
        }
        return feebacks;
    }

    public Feedback getFeedBackByBillID(int billID) {
        String sql = "select * from feedback where billID=? order by id desc";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, billID);
            ResultSet result = st.executeQuery();
            if (result.next()) {
                return this.getFeedback(result);
            }
        } catch (SQLException er) {
            System.out.println("Get feedback by order id: " + er);
        }
        return null;
    }

    public Feedback getFeedback(ResultSet result) {
        try {
            int id = result.getInt("id");
            int userId = result.getInt("userId");
            int productId = result.getInt("productId");
            String comment = result.getString("feedback");
            int status = result.getInt("status");
            int star = result.getInt("star");
            Timestamp datePost = result.getTimestamp("datePost");
            Timestamp dateUpdate = result.getTimestamp("dateUpdate");
            int billId = result.getInt("billID");
            Feedback f = new Feedback(id, userId, productId, comment, star, status, datePost, dateUpdate);
            f.setBillID(billId);
            return f;
        } catch (Exception e) {
            System.out.println("Get feedback: " + e);
        }
        return null;
    }

    public int add(Feedback f) {
        String sql = "insert into feedback (userID, star, feedback, status, datePost, billID) values "
                + "(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, f.getUserID());
            st.setInt(i++, f.getStar());
            st.setString(i++, f.getFeedback());
            st.setInt(i++, f.getStatus());
            st.setTimestamp(i++, f.getDatePost());
            st.setInt(i++, f.getBillID());
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add feedback fail: " + e);
        }
        return 0;
    }

    public int update(Feedback f) {
        String sql = "update feedback set star =?, feedback=?, dateUpdate=? where billID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, f.getStar());
            st.setString(i++, f.getFeedback());
            st.setTimestamp(i++, f.getDateUpdate());
            st.setInt(i++, f.getBillID());
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Add feedback fail: " + e);
        }
        return 0;
    }

    public int updateStatusFeedback(int status, int id) {
        String sql = "update feedback set status=? where ID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, status);
            st.setInt(i++, id);
            return st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update status fail: " + e);
        }
        return 0;
    }

    public int delete(int id) {
        String sql = "delete from feedback where id=?";
        int result = 0;
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            result = st.executeUpdate();
        } catch (Exception e) {
            System.err.println("Delete feedback error: " + e);
        }
        return result;
    }
}
