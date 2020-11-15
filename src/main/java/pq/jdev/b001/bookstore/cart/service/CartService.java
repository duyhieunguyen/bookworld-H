package pq.jdev.b001.bookstore.cart.service;

import java.util.List;

import org.hibernate.query.Query;

import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderDetailInfo;
import pq.jdev.b001.bookstore.cart.model.OrderInfo;
import pq.jdev.b001.bookstore.cart.pagination.PaginationResult;

public interface CartService {
    
    void saveOrder(CartInfo cartInfo);

    // public OrderInfo getOrderInfo(String orderId);

    // int getMaxOrderNum();

    Order findOrder(String orderId);

    List<OrderDetailInfo> listOrderDetailInfos(String orderId);

    List<Order> findAll();

    // Query<OrderInfo> queryOrderInfo();

    // PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);
}
