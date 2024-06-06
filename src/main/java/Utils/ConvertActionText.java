/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

public class ConvertActionText {

    public String convertActionText(String act, int status) {
        String messange = "";
        switch (act) {
            case "add-new":
                if (status == 1) {
                    messange = "Add new successfully";
                } else if (status == 2) {
                    messange = "This name is exist in system";
                } else {
                    messange = "Add new fail. Try again";
                }
                break;
            case "add-new-product":
                if (status == 1) {
                    messange = "Add new successfully";
                } else if (status == 2) {
                    messange = "Price sale must be less than price";
                } else {
                    messange = "Add new fail. Try again";
                }
                break;
            case "update-product":
                if (status == 1) {
                    messange = "Update successfully";
                } else if (status == 2) {
                    messange = "Price sale must be less than price";
                } else {
                    messange = "Update new fail. Try again";
                }
                break;
            case "update-account":
                if (status == 1) {
                    messange = "Update successfully";
                } else if (status == 2) {
                    messange = "Email is used. Try another email";
                } else {
                    messange = "Update fail. Try again";
                }
                break;
            case "add-voucher":
                if (status == 1) {
                    messange = "Add voucher successfully";
                } else if (status == 0) {
                    messange = "Add voucher fail. Try again";
                } else {
                    messange = "Voucher is exist in system";
                }
                break;
            case "update-voucher":
                if (status == 1) {
                    messange = "Update successfully";
                } else if (status == 2) {
                    messange = "Voucher is exist in system";
                } else {
                    messange = "Update fail. Try again";
                }
                break;
            case "delete":
                if (status == 1) {
                    messange = "Delete successfully";
                } else if (status == 2) {
                    messange = "Please choose account to delete";
                } else {
                    messange = "Delete fail. Try again";
                }
                break;
            case "add-cart":
                if (status == 1) {
                    messange = "Add cart successfully";
                } else if (status == 2) {
                    messange = "Quantities exceeding the quantity are limited ";
                } else {
                    messange = "Add cart fail";
                }
                break;
            case "update-cart":
                if (status == 1) {
                    messange = "Update cart successfully";
                } else if (status == 2) {
                    messange = "Quantities exceeding the quantity are limited ";
                } else {
                    messange = "Update cart fail";
                }
                break;
            case "remove-cart":
                if (status == 1) {
                    messange = "Remove item successfully";
                } else {
                    messange = "Remove item fail. Try again";
                }
                break;
            case "update-password":
                if (status == 1) {
                    messange = "Update password succesfully.";
                } else if (status == 2) {
                    messange = "Current password incorrect.";
                } else {
                    messange = "Update password fail. Try again.";
                }
                break;
            case "feedback":
                if (status == 1) {
                    messange = "Feedback succesfully.";
                } else {
                    messange = "Feedback fail. Try again.";
                }
                break;
            case "edit-feedback":
                if (status == 1) {
                    messange = "Edit feedback succesfully.";
                } else {
                    messange = "Edit feedback fail. Try again.";
                }
                break;
            case "add-blog":
                if (status == 1) {
                    messange = "Add new blog succesfully.";
                } else if (status == 2) {
                    messange = "Please choose a image.";
                } else {
                    messange = "Add new blog fail. Try again.";
                }
                break;
            case "update-blog":
                if (status == 1) {
                    messange = "Update blog succesfully.";
                } else {
                    messange = "Update blog fail. Try again.";
                }
                break;
            case "update-feedback":
                if (status == 1) {
                    messange = "Update feedback succesfully.";
                } else {
                    messange = "Update bill fail. Try again.";
                }
                break;
            case "update-bill":
                if (status == 1) {
                    messange = "Update bill succesfully.";
                } else {
                    messange = "Update feedback fail. Try again.";
                }
                break;
            case "update":
                if (status == 1) {
                    messange = "Update succesfully.";
                } else if (status == 2) {
                    messange = "This name is exist in system.";
                } else {
                    messange = "Update fail. Try again.";
                }
                break;
            case "send-contact":
                if (status == 1) {
                    messange = "Send contact succesfully. We will reply for you as soon as posible.";
                } else {
                    messange = "Send contact fail. Try again";
                }
                break;
            case "send-mail":
                if (status == 1) {
                    messange = "Send message successfully.";
                } else {
                    messange = "Send message fail. Try again.";
                }
                break;
            case "limit-cart":
                if (status == 0) {
                    messange = "There is 1 product in the cart that has just been sold or the quantity in the cart has been updated.";
                }
                break;
            case "remove-voucher":
                if (status == 1) {
                    messange = "Remove voucher successfully.";
                }
                break;
            case "get-point":
                if (status == 1) {
                    messange = "Get point successfully.";
                } else if (status == 2) {
                    messange = "Uncompleted orders cannot receive points";
                } else {
                    messange = "Get point fail.Try again.";
                }
                break;
            case "add-preorder":
                if (status == 1) {
                    messange = "Your pre order successfully.";
                } else if (status == 2) {
                    messange = "Your pre order fail.";
                } else {
                    messange = "Your pre order fail.Try again.";
                }
                break;
            case "action-preorder":
                if (status == 1) {
                    messange = "Action successfully.";
                } else if (status == 2) {
                    messange = "Please choose account to action.";
                } else if (status == 3) {
                    messange = "Some product have quantity is zero. Can not send mail for this.";
                } else {
                    messange = "Action fail.Try again.";
                }
                break;
            case "delete-preOrder":
                if (status == 1) {
                    messange = "Delete pre order successfully.";
                } else if (status == 2) {
                    messange = "Delete pre order fail.";
                } else {
                    messange = "Delete pre order fail.Try again.";
                }
                break;
        }
        return messange;
    }
}
