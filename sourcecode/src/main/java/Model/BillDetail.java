/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class BillDetail {

    private int ID;
    private int billID;
    private String imgProduct;
    private int numberOfProduct;
    private float priceProduct;
    private String nameProduct;
    private int productID;

    public BillDetail() {
    }

    public BillDetail(int ID, int billID, String imgProduct, int numberOfProduct, float priceProduct, String nameProduct, int productID) {
        this.ID = ID;
        this.billID = billID;
        this.imgProduct = imgProduct;
        this.numberOfProduct = numberOfProduct;
        this.priceProduct = priceProduct;
        this.nameProduct = nameProduct;
        this.productID = productID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public float getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(float priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
