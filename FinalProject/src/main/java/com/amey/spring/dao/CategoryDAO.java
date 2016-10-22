package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Category;

public class CategoryDAO extends DAO {
	public Category get(String title) throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Category where title = :title");
            q.setString("title", title);
            Category category = (Category) q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    @SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not list the categories", e);
        }
    }

    public Category create(String title) throws MyException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            return null;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws MyException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws MyException {
        try {
            begin();
            getSession().delete(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Could not delete the category", e);
        }
    }
}
