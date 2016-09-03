///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.utarasa.service;
//
//import com.utarasa.domain.Product;
//import com.utarasa.utils.HibernateUtil;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.SessionScoped;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.Query;
//
///**
// *
// * @author Игорь
// */
//@ManagedBean(name = "productService")
//@SessionScoped
//public class ProductService {
//
////    public List<Category> getCategoryList() {
////        Session session = HibernateUtil.currentSession();
////        List<Category> list = null;
////        try {
////            Query qry = session.createQuery("from com.utarasa.domain.Category");
////            list = qry.list();
////        } catch (Exception e) {
////            e.printStackTrace();
////        } finally {
////            HibernateUtil.closeSession();
////        }
////        return list;
////    }
//
////    public Category getCategoryById(int id) {
////        Session session = HibernateUtil.currentSession();
////        Transaction tx = session.beginTransaction();
////        List<Category> list = null;
////        String query = "from com.utarasa.domain.Category where id=" + id;
////        try {
////            Query qry = session.createQuery(query);
////            list = qry.list();
////            tx.commit();
////        } catch (Exception e) {
////            tx.rollback();
////            e.printStackTrace();
////        } finally {
////            HibernateUtil.closeSession();
////        }
////        return list.get(0);
////    }
//
//    public void registerProduct(Product product) {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            session.save(product);     
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.closeSession();
//        }
//    }
//
//    public void deleteProductById(int id) {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            Query query = session.createQuery("delete com.utarasa.domain.Product where id = :param");
//            query.setParameter("param", id);
//            query.executeUpdate();
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.closeSession();
//        }
//    }
//
//}
package com.utarasa.service;

import com.utarasa.persist.implementation.ProductDAOImpl;
import com.utarasa.domain.Category;
import com.utarasa.domain.Product;

import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "productService")
@SessionScoped
public class ProductService {

    private ProductDAOImpl pr = new ProductDAOImpl();

    /**
     * Adds Product object into database table "product"
     *
     * @param name - "name" field in table
     * @param price - "price" field in table
     * @param quantity - "quantity" field in table
     * @param description - "description" field in table
     * @param categoryId - "category_id" field in table
     * @return id of added Order object
     */
    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId) {
        return pr.addProduct(name, price, quantity, description, categoryId);
    }

    /**
     * Adds ready Product object into database table "product"
     *
     * @param product - ready product
     * @param categoryId - category id for this product
     * @return true if Product object was being successfully added
     */
    public boolean addReadyProduct(Product product, int categoryId) {
        return pr.addReadyProduct(product, categoryId);
    }

    /**
     * Retrieves Order object from database table "product"
     *
     * @param productId - "product_id" field in table
     * @return Product object with (productId, isDeleted, name, price, quantity,
     * description) fields
     */
    public Product getProduct(Long productId) {
        return pr.getProduct(productId);
    }

    /**
     * Retrieves Product proxy-object from database table "product"
     *
     * @param productId - "product_id" field in table
     * @return Category proxy-object with only field "productId"
     */
    public Product getProductWithId(Long productId) {
        return pr.getProductWithId(productId);
    }

    /**
     * Retrieves all Product objects from database table "product" for Category
     *
     * @param categoryId - "category_id" field in table
     * @return List of Product objects
     */
    public List<Product> getProductsByCategory(Long categoryId) {
        return pr.getProductsByCategory(categoryId);
    }

    /**
     * Retrieves all Product objects from database table "product"
     *
     * @return List of Product objects
     */
    public List<Product> getAllProducts() {
        return pr.getAllProducts();
    }

    /**
     * Retrieves all "deleted" Product objects from database table "product"
     *
     * @return List of Product objects
     */
    public List<Product> getAllDeletedProducts() {
        return pr.getAllDeletedProducts();
    }

    /**
     * Retrieves all active Product objects from database table "product"
     *
     * @return List of Product objects
     */
    public List<Product> getAllActiveProducts() {
        return pr.getAllActiveProducts();
    }

    /**
     * Retrieves all active Product objects from database table "product"
     *
     * @param id - category id of this product
     * @return List of Product objects
     */
    public List<Product> getAllActiveProductsByCategoryId(int id) {
        return pr.getAllActiveProductsByCategoryId(id);
    }
    
    /**
     * Retrieves all archive Product objects from database table "product"
     *
     * @param id - category id of this product
     * @return List of Product objects
     */
    public List<Product> getAllArchiveProductsByCategoryId(int id) {
        return pr.getAllArchiveProductsByCategoryId(id);
    }

    /**
     * Updates "name" field in database table "product".
     *
     * @param productId - "product_id" field in table
     * @param name - "name" field in table
     */
    public void updateProductName(Long productId, String name) {
        pr.updateProductName(productId, name);
    }

    /**
     * Updates "is_deleted" field in database table "product".
     *
     * @param productId - "product_id" field in table
     * @param flag: 0 - "Активный", 1 - "Удален". Default 0
     */
    public void updateProductIsDeleted(int productId, Integer flag) {
        pr.updateProductIsDeleted(productId, flag);
    }

    /**
     * Updates "price" field in database table "product".
     *
     * @param productId - "product_id" field in table
     * @param price - "price" field in table
     */
    public void updateProductPrice(Long productId, BigDecimal price) {
        pr.updateProductPrice(productId, price);
    }

    /**
     * Updates "quantity" field in database table "product".
     *
     * @param productId - "product_id" field in table
     * @param quantity - "quantity" field in table
     */
    public void updateProductQty(Long productId, Integer quantity) {
        pr.updateProductQty(productId, quantity);
    }

    /**
     * Updates "description" field in database table "product".
     *
     * @param productId - "product_id" field in table
     * @param description - "description" field in table
     */
    public void updateProductDesc(Long productId, String description) {
        pr.updateProductDesc(productId, description);
    }

    /**
     * Updates "category_id" field in database table "product". Changes product
     * category
     *
     * @param productId - "product_id" field in table
     * @param category_id - "category_id" field in table
     */
    public void updateProductCategory(Long productId, Category category_id) {
        pr.updateProductCategory(productId, category_id);
    }

    /**
     * Deletes Product object from database table "product"
     *
     * @param productId - "product_id" field in table
     */
    public void deleteProduct(int productId) {
        pr.deleteProduct(productId);
    }

    /**
     * Deletes all Product objects from database table "product"
     */
    public void deleteAllProducts() {
        pr.deleteAllProducts();
    }
}
