package DAO;

import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.BillDetail;

public class BillDetailDAO {

    private Connection conn;

    public BillDetailDAO() {
        try {
            conn = DBConnection.connect();
        } catch (Exception e) {
//            handle error here
            conn = null;
        }
    }

    public int addBillDetail(BillDetail b) {
        int result = 0;
        String sql = "insert into billDetail (billID, imgProduct, numberOfProduct, priceProduct, nameProduct, productID)"
                + "values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            int i = 1;
            st.setInt(i++, b.getBillID());
            st.setString(i++, b.getImgProduct());
            st.setInt(i++, b.getNumberOfProduct());
            st.setFloat(i++, b.getPriceProduct());
            st.setString(i++, b.getNameProduct());
            st.setInt(i++, b.getProductID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Add bill detail" + e);
        }
        return result;
    }

    public List<BillDetail> getBillDetailById(int idBill) {
        List<BillDetail> billDetails = new ArrayList<>();
        String sql = "select * from billDetail where billID=?";
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idBill);
            ResultSet result = st.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String imgProduct = result.getString("imgProduct");
                int numberOfProduct = result.getInt("numberOfProduct");
                float priceProduct = result.getFloat("priceProduct");
                String nameProduct = result.getString("nameProduct");
                int productID = result.getInt("productID");
                BillDetail billDetail = new BillDetail(id, idBill, imgProduct, numberOfProduct, priceProduct, nameProduct, productID);
                billDetails.add(billDetail);
            }
        } catch (SQLException e) {
            System.out.println("Get bill details: " + e);
        }
        return billDetails;
    }
}
