/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.service;

import com.utarasa.domain.Admin;
import com.utarasa.domain.Category;
import com.utarasa.utils.HibernateSessionFactory;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Игорь
 */
@ManagedBean(name = "loginService")
@SessionScoped
public class AdminService {

    public boolean isAdmin(Admin admin) {
        Session session = HibernateSessionFactory.getSession();
        List<Admin> list = null;
        try {
            Query qry = session.createQuery("from com.utarasa.domain.Admin");
            list = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list.contains(admin);
    }

}
