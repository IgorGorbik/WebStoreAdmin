/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Игорь
 */
@ManagedBean
public class CreateCategoryView {

    public void open() {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("contentWidth", 560);
        RequestContext.getCurrentInstance().openDialog("/pages/createcategory", options, null);
    }

    public void onClosingDialog() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Категория добавлена", "Id:");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
