package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Cart;

public class CartDAO extends DAO {
	public CartDAO(){
		
	}
	public Cart create(Cart cart) throws MyException {
		try {
			begin();
			getSession().save(cart);
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new MyException("Exception while creating cart "+ e.getMessage());
		}
	}
	
	public void delete(Cart cart)
            throws MyException {
        try {
            begin();
            getSession().delete(cart);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to delete cart item", e);
        }
    }
	
	public void update(Cart cart) throws MyException{
    	try{
    		begin();
    		getSession().update(cart);
    		commit();
    	}catch(HibernateException e) {
            rollback();
            throw new MyException("Failed to update cart: ", e);
        }    	
    }
	
	@SuppressWarnings("rawtypes")
	public List listByName(String username) throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Cart where username =:username");
            q.setString("username", username);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting invoice ", e);
        }
    }
	
	@SuppressWarnings("rawtypes")
	public List listByNameAndFlag(String username, boolean flag) throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Cart where username =:username and flag =:flag");
            q.setString("username", username);
            q.setBoolean("flag",flag);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting invoice ", e);
        }
    }
	public Cart getByID(long id)
            throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Cart where id = :id");
            q.setLong("id",id);
            Cart cart = (Cart) q.uniqueResult();
            commit();
            return cart;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Cannot find product ", e);
        }
    }
}
