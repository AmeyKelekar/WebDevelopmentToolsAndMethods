package com.amey.spring.dao;

import java.util.logging.Logger;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DAO {
    protected DAO() {
    }
	
	@SuppressWarnings("unchecked")
	public static Session getSession() {
        Session session = (Session) DAO.session.get();
        if (session == null) {
            session = sessionFactory.openSession();
            DAO.session.set(session);
        }
        return session;
    }
	
	protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
	protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot close", e);
        }
        DAO.session.set(null);
    }

    @SuppressWarnings("unchecked")
	public static void close() {
        getSession().close();
        DAO.session.set(null);
    }
    
    private static final Logger log = Logger.getAnonymousLogger();
    @SuppressWarnings("rawtypes")
	private static final ThreadLocal session = new ThreadLocal();
    @SuppressWarnings("deprecation")
	private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
}
