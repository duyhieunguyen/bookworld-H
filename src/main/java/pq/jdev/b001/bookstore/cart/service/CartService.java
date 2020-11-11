package pq.jdev.b001.bookstore.cart.service;

import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderInfo;

public interface CartService {
    
    void saveOrder(CartInfo cartInfo);

    // public OrderInfo getOrderInfo(String orderId);

    // Order findOrder(String orderId);
}
