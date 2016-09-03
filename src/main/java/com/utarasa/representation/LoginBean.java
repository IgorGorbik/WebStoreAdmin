/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import com.utarasa.domain.Admin;
import com.utarasa.service.AdminService;
import com.utarasa.service.ProductService;
import com.utarasa.utils.SessionUtils;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Игорь
 */
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @ManagedProperty(value = "#{loginService}")
    private AdminService adminService;

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    private Admin admin = new Admin();

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String validateUsernamePassword() {
        boolean valid = false;
        valid = adminService.isAdmin(admin);
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", admin.getName());
            return "main";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "index";
        }
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "index";
    }

}
