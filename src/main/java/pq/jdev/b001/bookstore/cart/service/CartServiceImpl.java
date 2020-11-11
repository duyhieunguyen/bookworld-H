package pq.jdev.b001.bookstore.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderInfo;
import pq.jdev.b001.bookstore.cart.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    // @Autowired
    // private SessionFactory sessionFactory;
    
    // @Autowired
    // private CartService cartService;

    @Override
    public void saveOrder(CartInfo cartInfo) {
        // TODO Auto-generated method stub

    }

    // @Override
    // public OrderInfo getOrderInfo(String orderId) {
    //     Order order = cartRepository.findOrder(orderId);
    //     if (order == null) {
    //         return null;
    //     }
    //     return new OrderInfo(order.getId(), order.getOrderDate(), //
    //             order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
    //             order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    // }

    // @Override
    // public Order findOrder(String orderId) {
    //     Session session = this.sessionFactory.getCurrentSession();
    //     return session.find(Order.class, orderId);
    // }
    

}
