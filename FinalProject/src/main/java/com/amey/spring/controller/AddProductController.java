package com.amey.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.DAO;
import com.amey.spring.dao.ProductDAO;
import com.amey.spring.pojo.Product;

@Controller
@RequestMapping("/addProduct.htm")
public class AddProductController {
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return new  ModelAndView("addProduct");
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ProductDAO productDAO = new ProductDAO();
		
		Product product=new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Float.parseFloat(request.getParameter("price")));
		product.setDescription(request.getParameter("description"));
		
		productDAO.create(product);
		DAO.close();
		
		return new ModelAndView("managerSuccess");
	}
}
