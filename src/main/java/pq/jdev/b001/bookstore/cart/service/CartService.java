package pq.jdev.b001.bookstore.cart.service;

import java.util.List;

import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderDetailInfo;
import pq.jdev.b001.bookstore.cart.model.OrderInfo;
import pq.jdev.b001.bookstore.cart.pagination.PaginationResult;

public interface CartService {
    
    void saveOrder(CartInfo cartInfo);

    // public OrderInfo getOrderInfo(String orderId);

    int getMaxOrderNum();

    Order findOrder(String orderId);

    List<OrderDetailInfo> listOrderDetailInfos(String orderId);


}
