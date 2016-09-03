package com.utarasa.persist.implementation;

import com.utarasa.domain.Category;
import com.utarasa.persist.interfaces.CategoryDAO;
import com.utarasa.utils.HibernateSessionFactory;

import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public Long addCategory(String name, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long categoryId = null;
        try {
            tx = session.beginTransaction();

            Category cat = new Category(name, description);
            categoryId = (Long) session.save(cat);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return categoryId;
    }

    @Override
    public boolean addReadyCategory(Category category) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long categoryId = null;
        try {
            tx = session.beginTransaction();
            session.save(category);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                HibernateSessionFactory.closeSession();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public Integer countCategoryElements() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long res = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("select count(*) from Category ");
            res = (Long) query.uniqueResult();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return res.intValue();
    }

    @Override
    public Category getCategory(Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Category where categoryId = :category_id");
            query.setParameter("category_id", categoryId);
            List list = query.list();
            cat = (Category) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;
    }

    @Override
    public Category getRandomCategory() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            SQLQuery query = session.createSQLQuery("SELECT t.*\n"
                    + "FROM category AS t \n"
                    + "JOIN (SELECT ((SELECT MIN(category_id) - 1 FROM category)"
                    + " + RAND() * (SELECT MAX(category_id) - MIN(category_id)\n"
                    + "FROM category)) AS id) AS rand\n"
                    + "WHERE t.category_id >= rand.id\n"
                    + "ORDER BY t.category_id\n"
                    + "LIMIT 1").addEntity(Category.class);

            List list = query.list();
            cat = (Category) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;

    }

    @Override
    public Category getFirstCategory() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Category cat = null;
        try {
            tx = session.beginTransaction();

            SQLQuery query = session
                    .createSQLQuery("SELECT * FROM category LIMIT 1")
                    .addEntity(Category.class);

            List list = query.list();
            cat = (Category) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return cat;
    }

    @Override
    public Category getCategoryWithId(int categoryId) {
//
//        Session session = HibernateSessionFactory.getSession();
//        Transaction tx = null;
//        Category cat = null;
//        try {
//            tx = session.beginTransaction();
//
//            cat = (Category) session.get(Category.class, categoryId);
//
//            tx.commit();
//        } catch (HibernateException e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            HibernateSessionFactory.closeSession();
//        }
//        return cat;
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        List<Category> list = null;
        String query = "from com.utarasa.domain.Category where category_id=" + categoryId;
        try {
            Query qry = session.createQuery(query);
            list = qry.list();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return list.get(0);
    }

    @Override
    public List<Category> getAllCategories() {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Category");
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
    public Long getCategoryIdByName(String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long categoryId = null;

        try {
            tx = session.beginTransaction();

            Query query = session
                    .createQuery("select categoryId from Category where name = :name");
            query.setParameter("name", name);
            List list = query.list();
            Iterator itr = list.iterator();
            while (itr.hasNext()) {
                categoryId = (Long) itr.next();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return categoryId;
    }

    @Override
    public void deleteCategory(int categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Category where categoryId = :categoryId");
            query.setParameter("categoryId", categoryId);
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
    public void deleteAllCategories() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Category");
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
    public void updateCategoryName(Long categoryId, String name) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session
                    .createQuery("update Category set name = :name where categoryId = :category_id");
            query.setParameter("name", name);
            query.setParameter("category_id", categoryId);
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
    public void updateCategoryDesc(Long categoryId, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session
                    .createQuery(" update Category set description = :description where categoryId = :category_id");
            query.setParameter("description", description);
            query.setParameter("category_id", categoryId);
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
