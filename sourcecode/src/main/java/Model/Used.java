/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.sql.Timestamp;



public class Used {
    private int voucherID;
    private int accountID;
    private Timestamp dateUse;

    public Used() {
    }

    public Used(int voucherID, int accountID, Timestamp dateUse) {
        this.voucherID = voucherID;
        this.accountID = accountID;
        this.dateUse = dateUse;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public void setVoucherID(int voucherID) {
        this.voucherID = voucherID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public Timestamp getDateUse() {
        return dateUse;
    }

    public void setDateUse(Timestamp dateUse) {
        this.dateUse = dateUse;
    }
}
