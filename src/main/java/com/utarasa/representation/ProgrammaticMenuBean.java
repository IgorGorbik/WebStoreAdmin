/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import com.utarasa.domain.Category;
import com.utarasa.service.CategoryService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author Игорь
 */
@ManagedBean
@RequestScoped
public class ProgrammaticMenuBean {

    private MenuModel model;
    private List<Category> list;

    @ManagedProperty("#{categoryService}")
    private CategoryService categoryService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        //create menumodel
        model = new DefaultMenuModel();//model.generateUniqueIds();
        //First submenu
        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Список категорий");
        //get the list of all categories
        list = categoryService.getAllCategories();
        //get the count of categories
        int count = list.size();
        //fill submenu
        for (int j = 0; j < count; j++) {
            //create each menuitem by categories
            DefaultMenuItem item = new DefaultMenuItem(list.get(j).getName());
            item.setCommand("#{categoryTableBean.installCurrentCategory("
                    + list.get(j).getCategoryId() + ")}");
            item.setUpdate(":messages3 :messages4 :messages5");
            firstSubmenu.addElement(item);
        }
        //fill menumodel
        model.addElement(firstSubmenu);
    }

    /**
     *
     * @return current menumodel instance
     */
    public MenuModel getModel() {
        return model;
    }

}
