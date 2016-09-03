package com.utarasa.domain;

//import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item", catalog = "utarasa_shop")
@AssociationOverrides({
    @AssociationOverride(name = "itemId.product",
            joinColumns = @JoinColumn(name = "product_id")),
    @AssociationOverride(name = "itemId.order",
            joinColumns = @JoinColumn(name = "order_id"))})
public class Item implements Serializable {

    private ItemId itemId = new ItemId();

    private Integer totalQuantity;

    public Item() {
    }

    @Transient
    public Order getOrder() {
        return getItemId().getOrder();
    }

    public void setOrder(Order order) {
        this.getItemId().setOrder(order);
    }

    @Transient
    public Product getProduct() {
        return getItemId().getProduct();
    }

    public void setProduct(Product product) {
        this.getItemId().setProduct(product);
    }

    @Column(name = "total_quantity", nullable = false)
    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @EmbeddedId
    public ItemId getItemId() {
        return itemId;
    }

    public void setItemId(ItemId itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Item{"
                + "itemId=" + itemId
                + ", totalQuantity=" + totalQuantity
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item that = (Item) o;

        if (getItemId() != null ? !getItemId().equals(that.getItemId())
                : that.getItemId() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return (getItemId() != null ? getItemId().hashCode() : 0);
    }
}
