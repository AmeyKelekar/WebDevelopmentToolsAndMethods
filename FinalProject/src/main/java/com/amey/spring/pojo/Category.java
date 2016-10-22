package com.amey.spring.pojo;

import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class Category {
	private long id;
    private String title;
	private Set adverts = new HashSet();
	
	public Category(String title) {
        this.title = title;
        this.adverts = new HashSet();
    }
    public Category() {
    	
    }
    @SuppressWarnings("unchecked")
	public void addAdvert(Advert advert) {
        getAdverts().add(advert);
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set getAdverts() {
		return adverts;
	}
	public void setAdverts(Set adverts) {
		this.adverts = adverts;
	}
}
