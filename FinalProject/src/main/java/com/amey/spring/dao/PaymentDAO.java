package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Invoice;
import com.amey.spring.pojo.Payment;

public class PaymentDAO extends DAO{
	public Payment create(Payment payment)
            throws MyException {
        try {
            begin();
            getSession().save(payment);
            commit();
            return payment;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to add payment " + e.getMessage());
        }
    }
	
	@SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Payment");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting products ", e);
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
