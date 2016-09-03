///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.utarasa.service;
//
//import com.utarasa.domain.Category;
//import com.utarasa.utils.HibernateUtil;
//import java.util.List;
//import javax.faces.bean.ApplicationScoped;
//import javax.faces.bean.ManagedBean;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
///**
// *
// * @author Игорь
// */
//@ManagedBean(name = "categoryService")
//@ApplicationScoped
//public class CategoryService {
//
//    public List<Category> getCategoryList() {
//        Session session = HibernateUtil.currentSession();
//        List<Category> list = null;
//        try {
//            Query qry = session.createQuery("from com.utarasa.domain.Category");
//            list = qry.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.closeSession();
//        }
//        return list;
//    }
//
//    public Category getCategoryById(int id) {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        List<Category> list = null;
//        String query = "from com.utarasa.domain.Category where category_id=" + id;
//        try {
//            Query qry = session.createQuery(query);
//            list = qry.list();
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.closeSession();
//        }
//        return list.get(0);
//    }
//
//    public void registerCategory(Category category) {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            session.save(category);     
//            tx.commit();
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            HibernateUtil.closeSession();
//        }
//    }
//
//    public void deleteCategoryById(int id) {
//        Session session = HibernateUtil.currentSession();
//        Transaction tx = session.beginTransaction();
//        try {
//            Query query = session.createQuery("delete com.utarasa.domain.Category where category_id = :param");
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

import com.utarasa.persist.interfaces.CategoryDAO;
import com.utarasa.persist.implementation.CategoryDAOImpl;
import com.utarasa.domain.Category;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "categoryService")
@ApplicationScoped
public class CategoryService {

    private CategoryDAO cat = new CategoryDAOImpl();

    /**
     * Adds Category object into database table "category"
     *
     * @param name - "name" field in table
     * @param description - "description" field in table
     * @return id of added Category object
     */
    public Long addCategory(String name, String description) {
        return cat.addCategory(name, description);
    }

    /**
     * Adds ready Category object into database table "category"
     *
     * @param category - ready category
     * @return true if Category object adding was being successfull
     */
    public boolean addReadyCategory(Category category) {
        return cat.addReadyCategory(category);
    }

    /**
     * Calculates the number of elements in table "category"
     *
     * @return number of elements
     */
    public Integer countCategoryElements() {
        return cat.countCategoryElements();
    }

    /**
     * Retrieves Category object from database table "category"
     *
     * @param categoryId - "id" field in table
     * @return Category object with (id, name, description) fields
     */
    public Category getCategory(Long categoryId) {
        return cat.getCategory(categoryId);
    }

    /**
     * Retrieves random Category object from database table "category"
     *
     * @return Category object with (id, name, description) fields
     */
    public Category getRandomCategory() {
        return cat.getRandomCategory();
    }

    /**
     * Retrieves first Category object from database table "category"
     *
     * @return Category object with (id, name, description) fields
     */
    public Category getFirstCategory() {
        return cat.getFirstCategory();
    }

    /**
     * Retrieves Category proxy-object from database table "category"
     *
     * @param categoryId - "id" field in table
     * @return Category proxy-object with only field "categoryId"
     */
    public Category getCategoryWithId(int categoryId) {
        return cat.getCategoryWithId(categoryId);
    }

    /**
     * Retrieves all Category objects from database table "category"
     *
     * @return List of Category objects
     */
    public List<Category> getAllCategories() {
        return cat.getAllCategories();
    }

    /**
     * Finds Category "id" field using "name" filed in table "category"
     *
     * @param name - "name" field in table
     * @return "categoryId" field of Category object
     */
    public Long getCategoryIdByName(String name) {
        return cat.getCategoryIdByName(name);
    }

    /**
     * Updates "name" field in database table "category"
     *
     * @param categoryId - "id" field in table
     * @param name - "name" field in table
     */
    public void updateCategoryName(Long categoryId, String name) {
        cat.updateCategoryName(categoryId, name);
    }

    /**
     * Updates "description" field in database table "category"
     *
     * @param categoryId - "id" field in table
     * @param description - "description" field in table
     */
    public void updateCategoryDesc(Long categoryId, String description) {
        cat.updateCategoryDesc(categoryId, description);
    }

    /**
     * Deletes Category object from database table "category"
     *
     * @param categoryId - "id" field in table
     */
    public void deleteCategory(int categoryId) {
        cat.deleteCategory(categoryId);
    }

    /**
     * Deletes all Category objects from database table "category"
     */
    public void deleteAllCategories() {
        cat.deleteAllCategories();
    }

}
