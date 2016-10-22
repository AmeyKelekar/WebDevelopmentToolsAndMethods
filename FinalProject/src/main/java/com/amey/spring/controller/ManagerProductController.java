package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.DAO;
import com.amey.spring.dao.ProductDAO;
import com.amey.spring.pojo.Product;

@Controller
@RequestMapping("/managerProduct.htm")
public class ManagerProductController {
	@Autowired
	ProductDAO productDAO = new ProductDAO();
	@SuppressWarnings("rawtypes")
	List productList = new ArrayList();
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		productList = productDAO.list();
		DAO.close();
		return new ModelAndView("listProduct", "productList", productList);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		Product product = productDAO.getByID(id);
		DAO.close();
		return new ModelAndView("modifyProduct", "product", product);
	}
}
