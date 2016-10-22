package com.amey.spring.dao;

import org.hibernate.HibernateException;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Advert;
import com.amey.spring.pojo.User;

public class AdvertDAO extends DAO{
	public Advert create(String title, String message, User user)
            throws MyException {
        try {
            begin();
            Advert advert = new Advert(title, message, user);
            getSession().save(advert);
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            //throw new MyException("Could not create advert", e);
            throw new MyException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Advert advert)
            throws MyException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not delete advert", e);
        }
    }
}
