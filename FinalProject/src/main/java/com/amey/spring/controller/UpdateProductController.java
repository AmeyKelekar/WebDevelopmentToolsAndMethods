package com.amey.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.ProductDAO;
import com.amey.spring.pojo.Product;

@Controller
public class UpdateProductController {
    @Autowired
	ProductDAO productDAO = new ProductDAO();
	
	@RequestMapping(value="/updateProduct.htm" , method=RequestMethod.POST)
	protected ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Product product = new Product();
		product.setId(Integer.parseInt(request.getParameter("id")));
		product.setName(request.getParameter("name"));
		product.setPrice(Float.parseFloat(request.getParameter("price")));
		product.setDescription(request.getParameter("description"));
		
		productDAO.update(product);
		return new ModelAndView("managerSuccess");
	}

}
