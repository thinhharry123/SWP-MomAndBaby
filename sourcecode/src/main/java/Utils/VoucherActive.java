/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Model.Voucher;
import java.util.Date;

public class VoucherActive {

    public boolean isActive(Voucher v) {
        Date currentDate = new Date();
        if (v.getStatus() == 0 || v.getStart().after(currentDate) || v.getEnd().before(currentDate)) {
            return false;
        }
        return true;
    }
}
