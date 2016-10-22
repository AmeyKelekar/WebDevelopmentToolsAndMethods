package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Product;

public class ProductDAO extends DAO {
	public Product create(Product product)
            throws MyException {
        try {
            begin();
            getSession().save(product);
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to add product " + e.getMessage());
        }
    }

    public void delete(Product product)
            throws MyException {
        try {
            begin();
            getSession().delete(product);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to delete product", e);
        }
    }
    
    
    public void update(Product product) throws MyException{
    	try{
    		begin();
    		getSession().update(product);
    		commit();
    	}catch(HibernateException e) {
            rollback();
            throw new MyException("Fail to update product: ", e);
        }    	
    }
    
    @SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Product");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting products ", e);
        }
    }
    
    public Product getByID(long id)
            throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Product where id = :id");
            q.setLong("id",id);
            Product product = (Product) q.uniqueResult();
            commit();
            return product;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Can not find product ", e);
        }
    }
}
