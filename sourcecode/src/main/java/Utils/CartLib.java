/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

import DAO.CartDAO;
import Model.Cart;
import java.util.List;


public class CartLib {
    public int getNumberOfCart(int id) {
        CartDAO cartDao = new CartDAO();
        List<Cart> carts = cartDao.getAllCart(id);
        int numberOfCart = 0;
        for (Cart cart : carts) {
            numberOfCart += cart.getQuantity();
        }
        return numberOfCart;
    }//get the cart linked to a user's total quantity of all mini cart
    
    public List<Cart> getAllCart(int id) {
        CartDAO cartDao = new CartDAO();
        List<Cart> carts = cartDao.getAllCart(id);
        return carts;
    }//straightup return all mini cart linked to a user
}
