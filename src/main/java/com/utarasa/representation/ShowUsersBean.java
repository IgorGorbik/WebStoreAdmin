/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utarasa.representation;

import com.utarasa.domain.User;
import com.utarasa.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Игорь
 */
@ManagedBean(eager = true)
@SessionScoped
public class ShowUsersBean {

    @ManagedProperty("#{usersService}")
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getActiveUsers() {
        List<User> list = userService.getAllUsers();
        List<User> returnList = new ArrayList();
        for (User user : list) {
            if (user.getIsDeleted() == 0) {
                returnList.add(user);
            }
        }
        return returnList;
    }
    
    public List<User> getBlockedUsers() {
        List<User> list = userService.getAllUsers();
        List<User> returnList = new ArrayList();
        for (User user : list) {
            if (user.getIsDeleted() == 1) {
                returnList.add(user);
            }
        }
        return returnList;
    }

    public void block(int id) {
        userService.blockUser(id);
    }

    public void unblock(int id) {
        userService.unblockUser(id);
    }

}
