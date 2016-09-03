package com.utarasa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Администратор on 20.05.2016.
 */
@Entity
@Table(name = "category", catalog = "utarasa_shop", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")})
public class Category implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private int categoryId;

    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted = 0;

    @Override
    public String toString() {
        return "Category{"
                + "categoryId=" + categoryId
                + ", name=" + name
                + ", description=" + description
                + ", products=" + products.size()
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.categoryId;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final Category other = (Category) obj;
        if (this.categoryId != other.categoryId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Column(name = "name", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products = new HashSet<>();

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
