package com.utarasa.persist.interfaces;

import com.utarasa.domain.User;
import java.util.List;

/**
 * Created by Администратор on 22.05.2016.
 */
public interface UserDAO {

    public Long addUser(String name, String email, String password, String phone);

    public User getUser(int userId);

    public User getUserWithId(Long userId);

    public List<User> getAllUsers();

    public void updateUserName(Long userId, String name);

    public void updateUserMail(Long userId, String email);

    public void updateUserPass(Long userId, String password);

    public void updateUserPhone(Long userId, String phone);
    
    public void updateUserStatus(int userId, int status);

    public void deleteUser(Long userId);

    public void deleteAllUsers();
}
