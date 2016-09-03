package com.utarasa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders", catalog = "utarasa_shop")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id", unique = true, nullable = false)
    private int orderId;

    @Column(name = "is_archive", nullable = false)
    private Integer isArchive = 0;

    @Column(name = "order_status", nullable = false)
    private Integer orderStatus = 0;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_time", nullable = false)
    private Date dateTime;

    @Column(name = "total_price", nullable = false, precision = 10)
    private BigDecimal totalPrice;

    @Column(name = "delivery_address", nullable = false, length = 30)
    private String deliveryAddress;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itemId.order", cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>(0);

    public Order() {
    }

    public Order(Date dateTime, BigDecimal totalPrice, String deliveryAddress, User user) {
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getIsArchive() {
        return isArchive;
    }

    public void setIsArchive(Integer isArchive) {
        this.isArchive = isArchive;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.orderId);
        hash = 37 * hash + Objects.hashCode(this.dateTime);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        if (!Objects.equals(this.orderId, other.orderId)) {
            return false;
        }
        return true;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    @Override
    public String toString() {
        return "Order{"
                + "orderId=" + orderId
                + ", isArchive=" + isArchive
                + ", orderStatus=" + orderStatus
                + ", dateTime=" + dateTime
                + ", totalPrice=" + totalPrice
                + ", deliveryAddress=" + deliveryAddress
                + ", user=" + user
                + ", items=" + items
                + '}';
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
