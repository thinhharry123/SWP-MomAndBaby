/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Voucher {

    private int ID;

    private String name;

    private String code;

    private float value;

    private Date start;

    private Date end;

    private int status;

    private float limit;

    private int used;

    public Voucher() {
    }

    public Voucher(int ID, String name, String code, float value, Date start, Date end, int status, float limit) {
        this.ID = ID;
        this.name = name;
        this.code = code;
        this.value = value;
        this.start = start;
        this.end = end;
        this.status = status;
        this.limit = limit;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    // Getters and Setters
    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Float getLimit() {
        return limit;
    }

    public void setLimit(Float limit) {
        this.limit = limit;
    }
}
