package com.utarasa.persist.implementation;

import com.utarasa.domain.Category;
import com.utarasa.domain.Product;
import com.utarasa.persist.interfaces.ProductDAO;
import com.utarasa.utils.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 26.05.2016.
 */
public class ProductDAOImpl implements ProductDAO {

    @Override
    public Long addProduct(String name, BigDecimal price, Integer quantity, String description, Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long productId = null;

        try {
            tx = session.beginTransaction();

            Category cat = (Category) session.get(Category.class, categoryId);
            Product pr = new Product(name, price, quantity, description, cat);
            productId = (Long) session.save(pr);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return productId;
    }

    @Override
    public boolean addReadyProduct(Product product, int categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Category> list = null;
        String query = "from com.utarasa.domain.Category where category_id=" + categoryId;

        try {
            tx = session.beginTransaction();
            Query qry = session.createQuery(query);
            list = qry.list();
            //Category cat = (Category) session.get(Category.class, categoryId);
            product.setCategory(list.get(0));
            session.save(product);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return true;
    }

    @Override
    public Product getProduct(Long product_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Product pr = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where productId = :product_id");
            query.setParameter("product_id", product_id);
            List list = query.list();
            pr = (Product) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pr;
    }

    @Override
    public Product getProductWithId(Long product_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Product pr = null;
        try {
            tx = session.beginTransaction();

            pr = (Product) session.get(Product.class, product_id);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return pr;
    }

    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;

        try {
            tx = session.beginTransaction();

            Category cat = (Category) session.get(Category.class, categoryId);
            Query query = session.createQuery("from Product where category = :category");
            query.setParameter("category", cat);
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
    public List<Product> getAllProducts() {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product");
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
    public List<Product> getAllDeletedProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where isDeleted =:flag");
            query.setParameter("flag", 1);
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
    public List<Product> getAllActiveProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Product> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Product where isDeleted =:flag");
            query.setParameter("flag", 0);
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
    public List<Product> getAllActiveProductsByCategoryId(int id) {
        List<Product> prodList = getAllActiveProducts();
        List<Product> activeList = new ArrayList<>();
        for (Product product : prodList) {
            if (product.getCategory().getCategoryId() == id) {
                activeList.add(product);
            }
        }
        return activeList;
    }

    @Override
    public List<Product> getAllArchiveProductsByCategoryId(int id) {
        List<Product> prodList = getAllDeletedProducts();
        List<Product> activeList = new ArrayList<>();
        for (Product product : prodList) {
            if (product.getCategory().getCategoryId() == id) {
                activeList.add(product);
            }
        }
        return activeList;
    }

    @Override
    public void updateProductName(Long productId, String name) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set name = :name where productId = :productId");
            query.setParameter("name", name);
            query.setParameter("productId", productId);
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
    public void updateProductIsDeleted(int productId, Integer flag) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set isDeleted = :flag where productId = :productId");
            query.setParameter("flag", flag);
            query.setParameter("productId", productId);
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
    public void updateProductPrice(Long productId, BigDecimal price) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set price = :price where productId = :productId");
            query.setParameter("price", price);
            query.setParameter("productId", productId);
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
    public void updateProductQty(Long productId, Integer quantity) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set quantity = :quantity where productId = :productId");
            query.setParameter("quantity", quantity);
            query.setParameter("productId", productId);
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
    public void updateProductDesc(Long productId, String description) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Product set description = :description where productId = :productId");
            query.setParameter("description", description);
            query.setParameter("productId", productId);
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
    public void updateProductCategory(Long productId, Category catedory_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Category cat = (Category) session.get(Category.class, catedory_id);
            Query query = session.createQuery(" update Product set category = :cat where productId = :productId");
            query.setParameter("cat", cat);
            query.setParameter("productId", productId);
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
    public void deleteProduct(int productId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Product where productId = :productId");
            query.setParameter("productId", productId);
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
    public void deleteAllProducts() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Product");
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
