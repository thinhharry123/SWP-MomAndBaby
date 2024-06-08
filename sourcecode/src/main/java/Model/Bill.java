/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;

public class Bill {

    private int ID;
    private int customerID;
    private String email;
    private String customerName;
    private String phone;
    private String address;
    private String detailAddress;
    private float total;
    private int status;
    private int payment;
    private Timestamp dateOrder;
    private Timestamp dateUpdate;
    private String transactionCode;
    private int isGetPoint;
    private float isUsedPoint;
    private int voucherID;

    public Bill() {
    }

    public Bill(int ID, int customerID, String email, String customerName, String phone, String address, String detailAddress, float total, int status, int payment, Timestamp dateOrder, Timestamp dateUpdate, String transactionCode) {
        this.ID = ID;
        this.customerID = customerID;
        this.email = email;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.detailAddress = detailAddress;
        this.total = total;
        this.status = status;
        this.payment = payment;
        this.dateOrder = dateOrder;
        this.dateUpdate = dateUpdate;
        this.transactionCode = transactionCode;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Timestamp getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Timestamp dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public int getIsGetPoint() {
        return isGetPoint;
    }

    public void setIsGetPoint(int isGetPoint) {
        this.isGetPoint = isGetPoint;
    }

    public float getIsUsedPoint() {
        return isUsedPoint;
    }

    public void setIsUsedPoint(float isUsedPoint) {
        this.isUsedPoint = isUsedPoint;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }
    
    
    
}
