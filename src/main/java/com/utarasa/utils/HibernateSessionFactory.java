package com.utarasa.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory = null;
    private static Configuration configuration;

    static {
        try {
            configuration = new Configuration();//create hibernate configuration
            configuration.configure();//configure by hibernate.cfg.xml
        } catch (HibernateException ex) {
            throw new RuntimeException("Configuration problem: "
                    + ex.getMessage(), ex);
        }
        try {
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new RuntimeException("SessionFactoryCreate problem: "
                    + ex.getMessage(), ex);
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session getSession() throws HibernateException {
        Session s = (Session) session.get();
        // Open a new Session, if this Thread has none yet
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() throws HibernateException {
        Session s = (Session) session.get();
        session.set(null);
        if (s != null) {
            s.close();
        }
    }

}
