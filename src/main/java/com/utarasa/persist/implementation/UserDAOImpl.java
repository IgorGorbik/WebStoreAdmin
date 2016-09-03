package com.utarasa.persist.implementation;

import com.utarasa.persist.interfaces.UserDAO;
import com.utarasa.domain.User;
import com.utarasa.utils.HibernateSessionFactory;
import org.hibernate.*;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public Long addUser(String name, String email, String password, String phone) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long userId = null;
        try {
            tx = session.beginTransaction();

            User user = new User(name, email, password, phone);
            userId = (Long) session.save(user);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return userId;
    }

    @Override
    public User getUser(int userId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User where userId = :userId");
            query.setParameter("userId", userId);
            List list = query.list();
            user = (User) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return user;
    }

    @Override
    public User getUserWithId(Long userId) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();

            user = (User) session.get(User.class, userId);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<User> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from User");
            list = query.list();

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list;
    }

    @Override
    public void updateUserName(Long userId, String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set name = :name where userId = :userId");
            query.setParameter("name", name);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void updateUserMail(Long userId, String email) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set email = :email where userId = :userId");
            query.setParameter("email", email);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void updateUserPass(Long userId, String password) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set password = :password where userId = :userId");
            query.setParameter("password", password);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void updateUserPhone(Long userId, String phone) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set phone = :phone where userId = :userId");
            query.setParameter("phone", phone);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void updateUserStatus(int userId, int status) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update User set is_deleted = :del where userId = :userId");
            query.setParameter("del", status);
            query.setParameter("userId", userId);
            int result = query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete User where userId = :userId");
            query.setParameter("userId", userId);
            int result = query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    @Override
    public void deleteAllUsers() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete User");
            int result = query.executeUpdate();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }
}
