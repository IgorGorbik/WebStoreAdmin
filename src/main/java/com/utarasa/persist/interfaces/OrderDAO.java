package com.utarasa.persist.interfaces;

import com.utarasa.domain.Order;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface OrderDAO {

    public Long addOrder(Date dateTime, BigDecimal totalPrice, String deliveryAddress, Long userId,
            HashMap<Integer, Integer> map);

    public Long addOrder(Order order);

    public Order getOrder(Long orderId);

    public List<Order> getAllOrders();

    public List<Order> getAllArchiveOrders();

    public List<Order> getAllActiveOrders();

    public List<Order> getArchiveOrdersByUser(Long userId);

    public void updateOrderDateTime(Long orderId, String dateTime);

    public void updateOrderIsArchive(Long orderId, Integer flag);

    public void updateOrderStatus(Long orderId, Integer orderStatus);

    public void updateOrderTotalPrice(Long orderId, BigDecimal totalPrice);

    public void updateOrderAddr(Long orderId, String deliveryAddress);

    public void deleteOrder(Long orderId);

    public void deleteAllOrders();

    public void updateTotalQty(int orderId, int productId, int qty);

    public void deleteItem(int orderId, int productId);
}
