/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import com.utarasa.domain.Category;
import com.utarasa.domain.Product;
import com.utarasa.service.CategoryService;
import com.utarasa.service.ProductService;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Игорь
 */
@ManagedBean(eager = true)
@SessionScoped
public class CategoryTableBean implements Serializable {

    @ManagedProperty("#{categoryService}")
    private CategoryService categoryService;

    @ManagedProperty("#{productService}")
    private ProductService productService;

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private List<Category> list;

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private Category currentCategory;

    public Category getCategory() {
        return currentCategory;
    }

    public void setCategory(Category category) {
        this.currentCategory = category;
    }

    @PostConstruct
    public void init() {
        currentCategory = new Category();
        try {
            installCurrentCategory(categoryService.getAllCategories().get(0).getCategoryId());
        } catch (Exception e) {
        }
    }

    public List getActive() {
        int i = currentCategory.getCategoryId();
        return productService.getAllActiveProductsByCategoryId(i);
    }

    public List getArchive() {
        int i = currentCategory.getCategoryId();
        return productService.getAllArchiveProductsByCategoryId(i);
    }

    public void installCurrentCategory(int id) {
        // Calling Business Service
        currentCategory = categoryService.getCategoryWithId(id);
        list = new LinkedList();
        list.add(currentCategory);
    }

    public void deleteCurrentCategory() {
        categoryService.deleteCategory(currentCategory.getCategoryId());
        try {
            currentCategory = categoryService.getAllCategories().get(0);
            list = new LinkedList();
            list.add(currentCategory);
            installCurrentCategory(currentCategory.getCategoryId());
        } catch (Exception e) {
            currentCategory = null;
            list.clear();
        }
    }

    public String deleteProductById(int id) {
        Set<Product> productSet = currentCategory.getProducts();
        for (Iterator<Product> iterator = productSet.iterator(); iterator.hasNext();) {
            Product next = iterator.next();
            if (next.getProductId() == id) {
                iterator.remove();
            }
        }
        productService.deleteProduct(id);
        return "";
    }

}
