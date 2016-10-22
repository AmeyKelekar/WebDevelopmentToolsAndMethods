package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.User;

public class UserDAO extends DAO {
	public UserDAO() {
		
    }
	public User searchByUserName(String username)
            throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from User where username = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Can not find user " + username, e);
        }
    }
    public User searchById(long id)
            throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from User where id = :id");
            q.setLong("id", id);
            User user = (User) q.uniqueResult();
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Can not find user " + id, e);
        }
    }
    
    public void update(User user)
            throws MyException {
        try {
            begin();
            getSession().update(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Can not update user: " + user.getName(), e);
        }
    }

    public User create(User user)
            throws MyException {
        try {
            begin();
            getSession().save(user);
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new MyException("Can not create user " + username, e);
            throw new MyException("Exception while creating user " + e.getMessage());
        }
    }
    
    @SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from User");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting users ", e);
        }
    }

    public void delete(User user)
            throws MyException {
        try {
            begin();
            getSession().delete(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not delete user: " + user.getName(), e);
        }
    }
}
