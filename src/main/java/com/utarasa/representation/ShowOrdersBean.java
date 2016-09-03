/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import com.utarasa.domain.Item;
import com.utarasa.domain.Order;
import com.utarasa.domain.User;
import com.utarasa.service.OrderService;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Игорь
 */
@ManagedBean(eager = true, name = "show")
@SessionScoped
public class ShowOrdersBean {

    @ManagedProperty("#{ordersService}")
    private OrderService orderService;

    private User selectedUser;

    private Order selectedOrder;

    private Item editableItem;

    public Item getEditableItem() {
        return editableItem;
    }

    public void setEditableItem(Item editableItem) {
        this.editableItem = editableItem;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Order> getActiveOrders() {
        return orderService.getAllActiveOrders();
    }

    /**
     * Deletes all Item objects from database table "item"
     *
     * @param item
     */
    public void deleteItem(Item item) {
        Set<Item> items = selectedOrder.getItems();
        for (Item item1 : items) {
            if (item1.getOrder().getOrderId() == item.getOrder().getOrderId()) {
                items.remove(item1);
            }
        }
        orderService.deleteItem(item);
    }

}
