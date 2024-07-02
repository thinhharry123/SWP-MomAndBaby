/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

public class ConvertStatusOrder {

    public String statusText(int status) {
        String text = "";
        switch (status) {
            case 1:
                text = "new";
                break;
            case 2:
                text = "accept";
                break;
            case 3:
                text = "prepare";
                break;
            case 4:
                text = "Delivery";
                break;
            case 5:
                text = "Finish";
                break;
            case 6:
                text = "Cancel";
                break;
        }
        return text;
    }

    public String statusTag(int status) {
        String text = "";
        switch (status) {
            case 1:
                text = "label label-primary";
                break;
            case 2:
                text = "label label-info";
                break;
            case 3:
                text = "label label-danger";
                break;
            case 4:
                text = "label label-warning";
                break;
            case 5:
                text = "label label-success";
                break;
            case 6:
                text = "label label-default";
                break;
        }
        return text;
    }
}
