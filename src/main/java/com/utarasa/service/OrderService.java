package com.utarasa.service;

import com.utarasa.domain.Item;
import com.utarasa.persist.implementation.OrderDAOImpl;
import com.utarasa.domain.Order;
import com.utarasa.representation.ShowOrdersBean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by Администратор on 09.06.2016.
 */
@ManagedBean(name = "ordersService")
@SessionScoped
public class OrderService {

    private OrderDAOImpl order = new OrderDAOImpl();

    /**
     * Adds Order object into database table "orders"
     *
     * @param dateTime - "date_time" field in table
     * @param totalPrice - "total_price " field in table
     * @param deliveryAddress - "delivery_address" field in table
     * @param userId - "user_id" field in table
     * @param map - collection of pairs "productId" and "totalQuantity"
     * @return id of added Order object
     */
    public Long addOrder(Date dateTime, BigDecimal totalPrice, String deliveryAddress, Long userId,
            HashMap<Integer, Integer> map) {
        return order.addOrder(dateTime, totalPrice, deliveryAddress, userId, map);
    }

    /**
     * Adds Order object into database table "orders"
     *
     * @param or - Order object with (dateTime, totalPrice, deliveryAddress,
     * User user, Set<Item> items(total_quantity, Product product))) fields
     * @return id of added Order object
     */
    public Long addOrder(Order or) {
        return order.addOrder(or);

    }

    /**
     * Retrieves Order object from database table "orders"
     *
     * @param orderId - "id" field in table
     * @return Order object with (dateTime, totalPrice, deliveryAddress, User
     * user, Set<Item> items(total_quantity, Product product))) fields
     */
    public Order getOrder(Long orderId) {
        return order.getOrder(orderId);
    }

    /**
     * Retrieves all Order objects from database table "orders"
     *
     * @return List of Order objects
     */
    public List<Order> getAllOrders() {
        return order.getAllOrders();
    }

    /**
     * Retrieves all archive Order objects from database table "orders"
     *
     * @return List of archive Order objects
     */
    public List<Order> getAllArchiveOrders() {
        return order.getAllArchiveOrders();
    }

    /**
     * Retrieves all active Order objects from database table "orders"
     *
     * @return List of active Order objects
     */
    public List<Order> getAllActiveOrders() {
        return order.getAllActiveOrders();
    }

    /**
     * Retrieves all archive Order objects from database table "orders" for User
     *
     * @param userId - "user_id" field in table
     * @return List of archive Order objects
     */
    public List<Order> getArchiveOrdersByUser(Long userId) {
        return order.getArchiveOrdersByUser(userId);
    }

    /**
     * Updates "date_time" field in database table "orders"
     *
     * @param orderId - "order_id" field in table
     * @param dateTime - "date_time" field in table
     */
    public void updateOrderDateTime(Long orderId, String dateTime) {
        order.updateOrderDateTime(orderId, dateTime);
    }

    /**
     * Updates "is_archive" field in database table "orders". Whether this order
     * archive or not.
     *
     * @param orderId - "order_id" field in table
     * @param flag - 0 -"Активный", 1 - "Архивный". Default 0
     */
    public void updateOrderIsArchive(Long orderId, Integer flag) {
        order.updateOrderIsArchive(orderId, flag);
    }

    /**
     * Updates "order_status" field in database table "orders".
     *
     * @param orderId - "order_id" field in table
     * @param order_status - 0 -"Не подтвержден", 1 - "Подтвержден", 2 -
     * "Отменен". Default 0
     */
    public void updateOrderStatus(Long orderId, Integer order_status) {
        order.updateOrderStatus(orderId, order_status);
    }

    /**
     * Updates "total_price" field in database table "orders".
     *
     * @param orderId - "order_id" field in table
     * @param totalPrice - "total_price" field in table
     */
    public void updateOrderTotalPrice(Long orderId, BigDecimal totalPrice) {
        order.updateOrderTotalPrice(orderId, totalPrice);
    }

    /**
     * Updates "delivery_address" field in database table "orders".
     *
     * @param orderId - "order_id" field in table
     * @param deliveryAddress - "delivery_address" field in table
     */
    public void updateOrderAddr(Long orderId, String deliveryAddress) {
        order.updateOrderAddr(orderId, deliveryAddress);
    }

    /**
     * Deletes Order object from database table "orders"
     *
     * @param orderId - "order_id" field in table
     */
    public void deleteOrder(Long orderId) {
        order.deleteOrder(orderId);
    }

    /**
     * Deletes all Order objects from database table "orders"
     */
    public void deleteAllOrders() {
        order.deleteAllOrders();
    }

    /**
     * Deletes all Item objects from database table "item"
     *
     * @param item
     */
    public void deleteItem(Item item) {
        order.deleteItem(item.getOrder().getOrderId(), item.getProduct().getProductId());
    }
}
