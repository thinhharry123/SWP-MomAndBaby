/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    private Connection conn;
    public FeedbackDAO(){
        try{
            conn=DBConnection.DBConnection.connect();
        }catch(Exception e){
            System.out.println("Connection fails at FeedbackDAO: "+e);
            conn=null;
        }
       
    }
     public Feedback getFeedback(ResultSet result){
            try{
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
            }catch(SQLException e){
                System.out.println("Get feedback: "+e);
            }return null;
        }
    public List<Feedback> allFeedbackByNew(){
        String sql = "SELECT F.* "
                + "FROM feedback AS F "
                + "JOIN Bill AS B ON B.ID = F.billID "
                + "JOIN BillDetail AS BD ON BD.billID = B.ID "
                + "WHERE F.status = 0";
        List<Feedback> feedbacks = new ArrayList<>();
        try{
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                feedbacks.add(this.getFeedback(rs));
            }
        }catch(SQLException e){
            System.out.println("Get all feedback by productAdmin: "+e);
        }
        return feedbacks;
    }
}
