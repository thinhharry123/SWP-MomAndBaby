package Utils;

import DAO.ProductDAO;
import DAO.VoucherDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Product;
import Model.Voucher;
import java.util.List;


public class TemplateEmail {

    public String resetPassword(Account a, String newPassword) {
        String formatEmail = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&amp;display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "    <title>Reset password</title>\n"
                + "    <style>\n"
                + "        * {\n"
                + "            padding: 0;\n"
                + "            margin: 0;\n"
                + "            box-sizing: border-box;\n"
                + "        }\n"
                + "        html {\n"
                + "            font-family: 'Lato', 'Courier New', Courier, monospace;\n"
                + "        }\n"
                + "        .container-mail {\n"
                + "            margin: 10px auto;\n"
                + "            min-width: 320px;\n"
                + "            max-width: 550px;\n"
                + "            word-wrap: break-word;\n"
                + "            word-break: break-word;\n"
                + "            background-color: transparent;\n"
                + "        }\n"
                + "        .type-message {\n"
                + "            text-align: center;\n"
                + "            background-color: #ED495C;\n"
                + "            padding: 20px 0;\n"
                + "            color: #fff;\n"
                + "            font-size: 20px;\n"
                + "        }\n"
                + "        .main-content {\n"
                + "            background-color: #fff;\n"
                + "        }\n"
                + "        .type-message img {\n"
                + "            max-width: 60px;\n"
                + "        }\n"
                + "        .content-message {\n"
                + "            padding: 20px;\n"
                + "        }\n"
                + "        .content-message p {\n"
                + "            padding: 5px 0px;\n"
                + "            line-height: 20px;\n"
                + "            font-size: 17px;\n"
                + "        }\n"
                + "        .password-reset {\n"
                + "            display: inline-block;\n"
                + "            margin-top: 10px;\n"
                + "            padding: 10px 30px;\n"
                + "            background-color: #fcbe00;\n"
                + "            color: #000;\n"
                + "            font-size: 17px;\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "        .link-to-login {\n"
                + "            font-size: 17px;\n"
                + "            display: block;\n"
                + "            margin-top: 10px;\n"
                + "            font-style: italic;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            padding: 20px 10px;\n"
                + "            background-color: #ed495c;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .information {\n"
                + "            color: #fff;\n"
                + "            width: 100%;\n"
                + "            padding: 5px;\n"
                + "        }\n"
                + "\n"
                + "        .information .group-info {\n"
                + "            margin-bottom: 10px;\n"
                + "            font-size: 14px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container-mail\">\n"
                + "        <div class=\"type-message\">\n"
                + "            <img src=\"https://cdn.templates.unlayer.com/assets/1593141680866-reset.png\" alt=\"\">\n"
                + "            <h2>Reset your password</h2>\n"
                + "        </div>\n"
                + "        <div class=\"main-content\">\n"
                + "            <div class=\"content-message\">\n"
                + "                <p>Hello " + a.getUsername() + ", </p>\n"
                + "                <p>We have sent you this email in response to your request to reset your password for your account.</p>\n"
                + "                <p>Your new password is below: </p>\n"
                + "                <span class=\"password-reset\">" + newPassword + "</span>\n"
                + "                <a href=\"\" class=\"link-to-login\">Please click here to login</a>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <div class=\"information\">\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Address:</span>\n"
                + "                    <span>FPT UNIVERSITY</span>\n"
                + "                </div>\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Phone:</span>\n"
                + "                    <span>0949204801</span>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        return formatEmail;
    }

    public String Voucher(String title, String content, String code) {
        String htmlReturn = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&amp;display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "    <title>Gift voucher</title>\n"
                + "    <style>\n"
                + "        html  {\n"
                + "            font-family: 'Lato', 'Courier New', Courier, monospace;\n"
                + "        }\n"
                + "        .container-voucher {\n"
                + "            min-width: 320px;\n"
                + "            max-width: 550px;\n"
                + "            margin: 10px auto;\n"
                + "        }\n"
                + "        .header {\n"
                + "            padding: 20px 0px;\n"
                + "            text-align: center;\n"
                + "            background-color: #ED495C;\n"
                + "            font-size: 20px;\n"
                + "            color: #fff;\n"
                + "        }\n"
                + "        .main-content {\n"
                + "            padding: 10px 0px;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .title-voucher {\n"
                + "            font-size: 30px;\n"
                + "            margin: 0;\n"
                + "            padding: 10px 0;\n"
                + "text-transform: uppercase;"
                + "        }\n"
                + "        .main-content  p {\n"
                + "            font-size: 15px;\n"
                + "            font-style: italic;\n"
                + "            margin: 0;\n"
                + "            padding: 5px 0;\n"
                + "        }\n"
                + "        .code-coupon {\n"
                + "            margin: 5px 0;\n"
                + "            padding: 5px;\n"
                + "            display: inline-flex;\n"
                + "            justify-content: center;\n"
                + "            align-items: center;\n"
                + "            background-color: #d8d9dd;\n"
                + "            border-radius: 4px;\n"
                + "        }\n"
                + "        .code-coupon .code {\n"
                + "            padding:  5px 20px;\n"
                + "            font-weight: bold;\n"
                + "            text-transform: uppercase;\n"
                + "        }\n"
                + "        .message-voucher {\n"
                + "            display: block;\n"
                + "        }\n"
                + "        .link-to-page {\n"
                + "            display: inline-block;\n"
                + "            padding: 10px 30px;\n"
                + "            background-color: #333;\n"
                + "            margin: 10px 0;\n"
                + "            color: #fff;\n"
                + "            text-decoration: none;\n"
                + "            font-weight: bold;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            padding: 20px 10px;\n"
                + "            background-color: #ED495C;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .information {\n"
                + "            color: #fff;\n"
                + "            width: 100%;\n"
                + "            padding: 5px;\n"
                + "        }\n"
                + "\n"
                + "        .information .group-info {\n"
                + "            margin-bottom: 10px;\n"
                + "            font-size: 14px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container-voucher\">\n"
                + "        <div class=\"header\">\n"
                + "            <h2>Thank for begin our customer</h2>\n"
                + "        </div>\n"
                + "        <div class=\"main-content\">\n"
                + "            <h3 class=\"title-voucher\">" + title + "</h3>\n"
                + "            <p>Here your coupon code</p>\n"
                + "            <div class=\"code-coupon\">\n"
                + "                <span class=\"code\">" + code + "</span>\n"
                + "            </div>\n"
                + "            <div class=\"message-voucher\">\n"
                + "              <p>" + content + "</p>  \n"
                + "            </div>\n"
                + "            <a href=\"\" class=\"link-to-page\">Visit to  our website</a>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <div class=\"information\">\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Address:</span>\n"
                + "                    <span> FPT UNIVERSITY</span>\n"
                + "                </div>\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Phone:</span>\n"
                + "                    <span>0949204801</span>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "</body>\n"
                + "</html>";
        return htmlReturn;
    }

    public String sendContactMessage(String fullname, String email, String phone, String message) {
        String contactHtml = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <link href=\"https://fonts.googleapis.com/css?family=Lato:400,700&amp;display=swap\" rel=\"stylesheet\" type=\"text/css\">\n"
                + "    <title>Contact user</title>\n"
                + "    <style>\n"
                + "        html  {\n"
                + "            font-family: 'Lato', 'Courier New', Courier, monospace;\n"
                + "        }\n"
                + "        .container-voucher {\n"
                + "            width: 100%;\n"
                + "            margin: 10px auto;\n"
                + "        }\n"
                + "        .header {\n"
                + "            padding: 20px 0px;\n"
                + "            text-align: center;\n"
                + "            background-color: #ED495C;\n"
                + "            font-size: 20px;\n"
                + "            color: #fff;\n"
                + "        }\n"
                + "        .main-content {\n"
                + "            padding: 10px 0px;\n"
                + "            text-align: left;\n"
                + "        }\n"
                + "        .list-contact {\n"
                + "            list-style: none;\n"
                + "            margin: 0;\n"
                + "            padding: 5px;\n"
                + "        }\n"
                + "        .contact-item {\n"
                + "            padding: 5px 0px;\n"
                + "        }\n"
                + "        .contact-item-title {\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "        .link-to-page {\n"
                + "            display: inline-block;\n"
                + "            padding: 10px 30px;\n"
                + "            background-color: #333;\n"
                + "            margin: 10px 0;\n"
                + "            color: #fff;\n"
                + "            text-decoration: none;\n"
                + "            font-weight: bold;\n"
                + "            border-radius: 5px;\n"
                + "        }\n"
                + "        .footer {\n"
                + "            padding: 20px 10px;\n"
                + "            background-color: #ED495C;\n"
                + "            text-align: center;\n"
                + "        }\n"
                + "        .information {\n"
                + "            color: #fff;\n"
                + "            width: 100%;\n"
                + "            padding: 5px;\n"
                + "        }\n"
                + "\n"
                + "        .information .group-info {\n"
                + "            margin-bottom: 10px;\n"
                + "            font-size: 14px;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container-voucher\">\n"
                + "        <div class=\"header\">\n"
                + "            <h2>Information contact from user</h2>\n"
                + "        </div>\n"
                + "        <div class=\"main-content\">\n"
                + "            <ul class=\"list-contact\">\n"
                + "                <li class=\"contact-item\">\n"
                + "                    <span class=\"contact-item-title\">Full name: </span>\n"
                + "                    <span class=\"contact-item-title\">" + fullname + "</span>\n"
                + "                </li>\n"
                + "                <li class=\"contact-item\">\n"
                + "                    <span class=\"contact-item-title\">Email: </span>\n"
                + "                    <span class=\"contact-item-title\">" + email + "</span>\n"
                + "                </li>\n"
                + "                <li class=\"contact-item\">\n"
                + "                    <span class=\"contact-item-title\">Phone: </span>\n"
                + "                    <span class=\"contact-item-title\">" + phone + "</span>\n"
                + "                </li>\n"
                + "                <li class=\"contact-item\">\n"
                + "                    <span class=\"contact-item-title\">Messasge: </span></br>\n"
                + "                    <span class=\"contact-item-title\">" + message + "</span>\n"
                + "                </li>\n"
                + "            </ul>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            <div class=\"information\">\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Address:</span>\n"
                + "                    <span> FPT UNIVERSITY</span>\n"
                + "                </div>\n"
                + "                <div class=\"group-info\">\n"
                + "                    <span>Phone:</span>\n"
                + "                    <span>0949204801</span>\n"
                + "                </div>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "</body>\n"
                + "</html>";
        return contactHtml;
    }
    
    public String emailConfirmOrder(Bill bill, List<BillDetail> carts, String title) {
        String methodPay = bill.getPayment() == 1 ? "Banking" : "Cash";
        String emailConfirm = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Confirm order</title>\n"
                + "    <style>\n"
                + "        .header {\n"
                + "            margin: 10px 0px;\n"
                + "            font-size: 20px;\n"
                + "            text-align: center;\n"
                + "            text-transform: uppercase;\n"
                + "            color: #ED495C;\n"
                + "        }\n"
                + "        .list-info {\n"
                + "            list-style: none;\n"
                + "            padding: 0 5px;\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .item-info {\n"
                + "            padding: 5px 0px;\n"
                + "        }\n"
                + "        .title-info {\n"
                + "            font-weight: 600;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "        .content-info {\n"
                + "            font-style: italic;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "        .order-product {\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "        .order-product table  {\n"
                + "            width: 100%;\n"
                + "            text-align: center;\n"
                + "            border-collapse: collapse;\n"
                + "            border: 1px solid #333;\n"
                + "        }\n"
                + "        .order-product table th {\n"
                + "            font-weight: bold;\n"
                + "            color: #fff;\n"
                + "            background-color: #ED495C;\n"
                + "        }\n"
                + "        .order-product table th,\n"
                + "        .order-product table td {\n"
                + "            border: 1px solid #333;\n"
                + "            padding: 7px 0px;\n"
                + "        }\n"
                + "        .order-product table .total {\n"
                + "            background-color: #e4e4e4;\n"
                + "            font-weight: bold;\n"
                + "            font-size: 16px;\n"
                + "            text-transform: uppercase;\n"
                + "        }\n"
                + "        .thank p {\n"
                + "            font-size: 16px;\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h2>" + title + "</h2>\n"
                + "        </div>"
                + "<div class=\"main-order\">\n"
                + "            <div class=\"info-order\">\n"
                + "                <ul class=\"list-info\">\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Full name: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getCustomerName() + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Email: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getEmail() + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Phone: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getPhone() + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Address: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getAddress() + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Detail address: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getDetailAddress() + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Method pay: </span>\n"
                + "                        <span class=\"content-info\">" + methodPay + "</span>\n"
                + "                    </li>\n"
                + "                    <li class=\"item-info\">\n"
                + "                        <span class=\"title-info\">Used point: </span>\n"
                + "                        <span class=\"content-info\">" + bill.getIsUsedPoint() + "</span>\n"
                + "                    </li>\n"
                + "                </ul>\n"
                + "            </div>\n"
                + "            <div class=\"order-product\">\n"
                + "                <h2>Products</h2>\n"
                + "                <table>\n"
                + "                    <thead>\n"
                + "                        <tr>\n"
                + "                            <th>.No</th>\n"
                + "                            <th>Name product</th>\n"
                + "                            <th>Number of product</th>\n"
                + "                            <th>Price</th>\n"
                + "                            <th>Total</th>\n"
                + "                        </tr>\n"
                + "                    </thead>\n"
                + "                    <tbody>";
        ProductDAO productDao = new ProductDAO();
        CurrencyConverter currency = new CurrencyConverter();
        int index = 0;
        float total = 0f;
        VoucherDAO voucherDao = new VoucherDAO();
        float discount = 0;
        if (!(bill.getVoucherID() + "").equals("0")) {
            Voucher v = voucherDao.currentVoucher(bill.getVoucherID());
            discount = v.getValue();
        }
        for (BillDetail cart : carts) {
            float price = cart.getPriceProduct();
            float totalItem = price * cart.getNumberOfProduct();
            total += totalItem;
            index++;
            emailConfirm += "<tr>\n"
                    + "                            <td>" + index + "</td>\n"
                    + "                            <td>" + cart.getNameProduct() + "</td>\n"
                    + "                            <td>" + cart.getNumberOfProduct() + "</td>\n"
                    + "                            <td>" + currency.currencyFormat(price, "VND") + "</td>\n"
                    + "                            <td>" + currency.currencyFormat(totalItem, "VND") + "</td>\n"
                    + "                        </tr>";
        }
        emailConfirm += "<tr class=\"total\">\n"
                + "                            <td colspan=\"4\">Discount: </td>\n"
                + "                            <td colspan=\"4\">" + currency.currencyFormat(discount, "VND") + "</td>\n"
                + "                        </tr>"
                + "<tr class=\"total\">\n"
                + "                            <td colspan=\"4\">Total order after voucher: </td>\n"
                + "                            <td colspan=\"4\">" + currency.currencyFormat(bill.getTotal(), "VND") + "</td>\n"
                + "                        </tr>"
                + "                        </tr>"
                + "<tr class=\"total\">\n"
                + "                            <td colspan=\"4\">Total must pay: </td>\n"
                + "                            <td colspan=\"4\">" + currency.currencyFormat(bill.getTotal() - bill.getIsUsedPoint(), "VND") + "</td>\n"
                + "                        </tr>"
                + "</tbody>\n"
                + "                </table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"thank\">\n"
                + "            <p>Thank you for buy product in our website. Have a nice day</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            \n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        return emailConfirm;
    }

    public String emailSendPreOrder(Product p, Account a, String title) {
        String emailConfirm = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Send contact pre order</title>\n"
                + "    <style>\n"
                + "        .header {\n"
                + "            margin: 10px 0px;\n"
                + "            font-size: 20px;\n"
                + "            text-align: center;\n"
                + "            text-transform: uppercase;\n"
                + "            color: #ED495C;\n"
                + "        }\n"
                + "        .list-info {\n"
                + "            list-style: none;\n"
                + "            padding: 0 5px;\n"
                + "            margin: 0;\n"
                + "        }\n"
                + "        .item-info {\n"
                + "            padding: 5px 0px;\n"
                + "        }\n"
                + "        .title-info {\n"
                + "            font-weight: 600;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "        .content-info {\n"
                + "            font-style: italic;\n"
                + "            font-size: 16px;\n"
                + "        }\n"
                + "        .order-product {\n"
                + "            width: 100%;\n"
                + "        }\n"
                + "        .order-product table  {\n"
                + "            width: 100%;\n"
                + "            text-align: center;\n"
                + "            border-collapse: collapse;\n"
                + "            border: 1px solid #333;\n"
                + "        }\n"
                + "        .order-product table th {\n"
                + "            font-weight: bold;\n"
                + "            color: #fff;\n"
                + "            background-color: #ED495C;\n"
                + "        }\n"
                + "        .order-product table th,\n"
                + "        .order-product table td {\n"
                + "            border: 1px solid #333;\n"
                + "            padding: 7px 0px;\n"
                + "        }\n"
                + "        .order-product table .total {\n"
                + "            background-color: #e4e4e4;\n"
                + "            font-weight: bold;\n"
                + "            font-size: 16px;\n"
                + "            text-transform: uppercase;\n"
                + "        }\n"
                + "        .thank p {\n"
                + "            font-size: 16px;\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <div class=\"header\">\n"
                + "            <h2>" + title + "</h2>\n"
                + "        </div>"
                + "<div class=\"main-order\">\n"
                + "            <div class=\"info-order\">\n"
                + " <h3>Hello "+a.getUsername()+"</h3>"
                + " <div>The product you purchased is already available in our store. Click <a href=\"http://localhost:8080/MomAndBaby/product/detail/"+p.getID()+"\">Here</a> to buy this product now"+"</div>"
                + "            </div>\n"
                + "            <div class=\"order-product\">\n"
                + "                <h2>Products</h2>\n"
                + "                <table>\n"
                + "                    <thead>\n"
                + "                        <tr>\n"
                + "                            <th>Name product</th>\n"
                + "                            <th>Price</th>\n"
                + "                        </tr>\n"
                + "                    </thead>\n"
                + "                    <tbody>";
        CurrencyConverter currency = new CurrencyConverter();
        emailConfirm += "<tr>\n"
                    + "                            <td>" + p.getName() + "</td>\n";
        if(p.getNewPrice() > 0) {
            emailConfirm += "                            <td>" + currency.currencyFormat(p.getNewPrice(), "VND") + "</td>\n";
        } else {
            emailConfirm += "                            <td>" + currency.currencyFormat(p.getOldPrice(), "VND") + "</td>\n";
        }   
                    emailConfirm+= "                        </tr>";
        emailConfirm += "</tbody>\n"
                + "                </table>\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"thank\">\n"
                + "            <p>Thank you for buy product in our website. Have a nice day</p>\n"
                + "        </div>\n"
                + "        <div class=\"footer\">\n"
                + "            \n"
                + "        </div>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        return emailConfirm;
    }

}
