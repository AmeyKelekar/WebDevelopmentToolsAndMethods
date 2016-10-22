package com.amey.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.amey.spring.exception.MyException;
import com.amey.spring.pojo.Announcement;

public class AnnouncementDAO extends DAO {
	public Announcement getByID(long id) throws Exception{
		try {
            begin();
            Query q = getSession().createQuery("from Announcement where id = :id");
            q.setLong("id",id);
            Announcement a = (Announcement) q.uniqueResult();
            commit();
            return a;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Can not find announcement ", e);
        }
		
	}
	
	public void create(Announcement a) throws MyException{
		try{
			begin();
			getSession().save(a);
			commit();
		}catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to add announcement " + e.getMessage());
        }		
	}
	
	public void delete(Announcement a) throws MyException{
		try{
			begin();
			getSession().delete(a);
			commit();
		}catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to delete announcement " + e.getMessage());
        }		
	}
	
	public void update(Announcement a) throws MyException{
		try{
			begin();
			getSession().update(a);
			commit();
		}catch (HibernateException e) {
            rollback();
            throw new MyException("Fail to update announcement " + e.getMessage());
        }		
	}
	
	@SuppressWarnings("rawtypes")
	public List list() throws MyException {
        try {
            begin();
            Query q = getSession().createQuery("from Announcement");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MyException("Exception while getting announcement ", e);
        }
    }
}
