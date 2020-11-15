package pq.jdev.b001.bookstore.cart.service;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;



import pq.jdev.b001.bookstore.books.model.Book;
import pq.jdev.b001.bookstore.books.service.BookService;
import pq.jdev.b001.bookstore.cart.model.CartInfo;
import pq.jdev.b001.bookstore.cart.model.CartLineInfo;
import pq.jdev.b001.bookstore.cart.model.CustomerInfo;
import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderDetail;
import pq.jdev.b001.bookstore.cart.model.OrderDetailInfo;
import pq.jdev.b001.bookstore.cart.model.OrderInfo;
import pq.jdev.b001.bookstore.cart.repository.CartRepository;
import pq.jdev.b001.bookstore.cart.pagination.PaginationResult;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


@Transactional
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookService bookService;

   
  

    @Override
    public void saveOrder(CartInfo cartInfo) {
        // TODO Auto-generated method stub

    }



    @Override
    public Order findOrder(String orderId) {
        Order order = cartRepository.getOne(orderId);
		return order;
    }

    public OrderInfo getOrderInfo(String orderId) {
        Order order = this.findOrder(orderId);
        if (order == null) {
            return null;
        }
        return new OrderInfo(order.getId(), order.getOrderDate(), //
                order.getOrderNum(), order.getAmount(), order.getCustomerName(), //
                order.getCustomerAddress(), order.getCustomerEmail(), order.getCustomerPhone());
    }

    @Override
    public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
        return cartRepository.listOrderDetailInfos(orderId);
    }

    @Override
    public List<Order> findAll() {
        return cartRepository.findAll();
    }

    // @Override
    // public OrderInfo queryOrderInfo(OrderInfo orderInfo) {
    //     return cartRepository.queryOrderInfo();
    // }

    // @Override
    // public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
    //     String sql = "Select new " + OrderInfo.class.getName()//
    //                 + "(ord.id, ord.orderDate, ord.orderNum, ord.amount, "
    //                 + " ord.customerName, ord.customerAddress, ord.customerEmail, ord.customerPhone) " + " from "
    //                 + Order.class.getName() + " ord "//
    //                 + " order by ord.orderNum desc";

    //     Session session = this.sessionFactory.getCurrentSession();
    //     Query<OrderInfo> query = session.createQuery(sql, OrderInfo.class);
    //     return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
    // }

    
    // private Query<OrderInfo> queryOrderInfo() {
    //     return cartRepository.queryOrderInfo();
    // }

   



     

    
 
   
    

  
    

}
