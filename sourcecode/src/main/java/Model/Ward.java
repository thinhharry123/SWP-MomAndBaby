/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Ward {
    private int wardsID;
    private int districtID;
    private String name;

    public Ward() {
    }

    public Ward(int wardsID, int districtID, String name) {
        this.wardsID = wardsID;
        this.districtID = districtID;
        this.name = name;
    }

    public int getWardsID() {
        return wardsID;
    }

    public void setWardsID(int wardsID) {
        this.wardsID = wardsID;
    }

    public int getDistrictID() {
        return districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
