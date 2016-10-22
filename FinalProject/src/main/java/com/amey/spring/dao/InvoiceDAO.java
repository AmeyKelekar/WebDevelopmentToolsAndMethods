package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Invoice;

public class InvoiceDAO extends DAO {
	public InvoiceDAO() {

	}
	
	public Invoice create(Invoice invoice) throws MyException {
		try {
			begin();
			getSession().save(invoice);
			commit();
			return invoice;
		} catch (HibernateException e) {
			rollback();
			throw new MyException("Exception while creating invoice "+ e.getMessage());
		}
	}
	
	public void update(Invoice invoice) throws MyException{
    	try{
    		begin();
    		getSession().update(invoice);
    		commit();
    	}catch(HibernateException e) {
            rollback();
            throw new MyException("Failed to update invoice: ", e);
        }    	
    }
	
	@SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Invoice");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting invoice ", e);
        }
    }
	
	@SuppressWarnings("rawtypes")
	public List listByName(String username) throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Invoice where username =:username");
            q.setString("username", username);
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting invoice ", e);
        }
    }
	
	public Invoice getByID(long id)
            throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Invoice where id = :id");
            q.setLong("id",id);
            Invoice invoice = (Invoice) q.uniqueResult();
            commit();
            return invoice;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Cannot find product ", e);
        }
    }
}
