/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Игорь
 */
@ManagedBean
@SessionScoped
public class ArchiveProductView {

    public void open() {
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("contentWidth", 680);
        options.put("closable", false);
        RequestContext.getCurrentInstance().openDialog("/pages/archiveproducts", options, null);
    }

    public void close() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }

}
