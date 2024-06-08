/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

public class NumberCustom {
    public int getInt(String input) {
        int result = 0;
        try {
            result = Integer.parseInt(input);
        }catch(Exception e) {
            System.out.println("Convert int: " + e);
        }
        return result;
    }
    
    public float getFloat(String input) {
        float result = 0;
        try {
            result = Float.parseFloat(input);
        }catch(Exception e) {
            System.out.println("Convert float: " + e);
        }
        return result;
    }
}
