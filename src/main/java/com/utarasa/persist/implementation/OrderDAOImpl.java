package com.utarasa.persist.implementation;

import com.utarasa.domain.Item;
import com.utarasa.domain.Order;
import com.utarasa.domain.Product;
import com.utarasa.domain.User;
import com.utarasa.persist.interfaces.OrderDAO;
import com.utarasa.utils.HibernateSessionFactory;
import org.hibernate.*;

import java.math.BigDecimal;
import java.util.*;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public Long addOrder(Date dateTime, BigDecimal totalPrice, String deliveryAddress, Long userId,
            HashMap<Integer, Integer> map) {

        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long orderId = null;

        try {
            tx = session.beginTransaction();

            User user = (User) session.get(User.class, userId);
            Order order = new Order(dateTime, totalPrice, deliveryAddress, user);

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Item item = new Item();
                Product product = new Product();
                product.setProductId(entry.getKey());

                item.setProduct(product);
                item.setOrder(order);
                item.setTotalQuantity(entry.getValue());

                order.getItems().add(item);
            }

            orderId = (Long) session.save(order);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return orderId;

    }

    @Override
    public Long addOrder(Order order) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Long orderId = null;
        Item item = new Item();

        try {
            tx = session.beginTransaction();
            orderId = (Long) session.save(order);

            tx.commit();
        } catch (HibernateException e) {

            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }
        return orderId;

    }

    @Override
    public Order getOrder(Long orderId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        Order order = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order  where orderId =:orderId");
            query.setParameter("orderId", orderId);

            List list = query.list();
            order = (Order) list.get(0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("from Order");
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
    public List<Order> getAllArchiveOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order where isArchive=:flag");
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
    public List<Order> getAllActiveOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" from Order where isArchive=:flag");
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
    public List<Order> getArchiveOrdersByUser(Long user_id) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;
        List<Order> list = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, user_id);
            Query query = session.createQuery(" from Order where user = :user and isArchive=:flag");
            query.setParameter("user", user);
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
    public void updateOrderDateTime(Long orderId, String dateTime) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set dateTime = :dateTime where orderId = :orderId");
            query.setParameter("dateTime", dateTime);
            query.setParameter("orderId", orderId);
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
    public void updateOrderIsArchive(Long orderId, Integer flag) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set isArchive = :flag where orderId = :orderId");
            query.setParameter("flag", flag);
            query.setParameter("orderId", orderId);
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
    public void updateOrderStatus(Long orderId, Integer orderStatus) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set orderStatus = :orderStatus where orderId = :orderId");
            query.setParameter("orderStatus", orderStatus);
            query.setParameter("orderId", orderId);
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
    public void updateOrderTotalPrice(Long orderId, BigDecimal totalPrice) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set totalPrice = :totalPrice where orderId = :orderId");
            query.setParameter("totalPrice", totalPrice);
            query.setParameter("orderId", orderId);
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
    public void updateOrderAddr(Long orderId, String deliveryAddress) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" update Order set deliveryAddress = :delivery_address where orderId = :orderId");
            query.setParameter("deliveryAddress", deliveryAddress);
            query.setParameter("orderId", orderId);
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
    public void deleteOrder(Long orderId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery(" delete Order where orderId = :orderId");
            query.setParameter("orderId", orderId);
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
    public void deleteAllOrders() {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Query query = session.createQuery("delete Order");
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
    public void updateTotalQty(int orderId, int productId, int qty) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Order order = (Order) session.get(Order.class, orderId);

            HashSet<Item> set = new HashSet<>(order.getItems());
            Iterator<Item> iterator = set.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getProduct().getProductId() == productId) {
                    item.setTotalQuantity(qty);
                }
            }

            session.update(order);

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
    public void deleteItem(int orderId, int productId) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Order order = (Order) session.get(Order.class, orderId);

            HashSet<Item> set = new HashSet<>(order.getItems());
            Iterator<Item> iterator = set.iterator();

            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (item.getProduct().getProductId() == productId) {
                    iterator.remove();
                    session.delete(item);
                    session.evict(item);
                }
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
    }
}
