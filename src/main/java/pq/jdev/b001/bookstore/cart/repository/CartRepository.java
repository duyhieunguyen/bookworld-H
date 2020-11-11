package pq.jdev.b001.bookstore.cart.repository;

import org.springframework.stereotype.Repository;

import pq.jdev.b001.bookstore.cart.model.Order;
import pq.jdev.b001.bookstore.cart.model.OrderDetailInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface CartRepository extends JpaRepository<Order, String>, CrudRepository<Order, String>  {
    
    // @Query("SELECT o FROM Order o WHERE o.id =:orderId ")
    // public Order findOrder(@Param("orderId") String orderId);
    
    // @Query("SELECT new OrderDetailInfo (d.id, d.book.id, d.book.title, d.quanity, d.price, d.amount) FROM OrderDetail d WHERE d.order.id =: orderId")
	// List<OrderDetailInfo> listOrderDetailInfos(@Param("orderId") String orderId);
}
