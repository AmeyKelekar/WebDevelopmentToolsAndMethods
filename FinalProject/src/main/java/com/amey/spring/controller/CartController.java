package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.CartDAO;
import com.amey.spring.dao.DAO;
import com.amey.spring.pojo.Cart;
import com.amey.spring.pojo.User;


@Controller
public class CartController {
	@Autowired
	CartDAO cartDAO = new CartDAO();
	
	@SuppressWarnings("rawtypes")
	List cartList = new ArrayList();
	
	@RequestMapping(value = "/addToCart.htm", method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return doPost(request, response);
	}
	
	
	@RequestMapping(value = "/addToCart.htm", method = RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		Cart cart=(Cart)session.getAttribute("cart");
		cart.setPrice(Float.parseFloat(request.getParameter("price")));
		cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		cartDAO.create(cart);
		session.removeAttribute("cart");
		boolean flag=false;
		cartList = cartDAO.listByNameAndFlag(user.getUsername(),flag);
		DAO.close();
		return new ModelAndView("viewCart","cartList",cartList);
	}
	
	@RequestMapping(value = "/viewCart.htm", method=RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) request.getSession().getAttribute("user");	
		boolean flag=false; 
		cartList = cartDAO.listByNameAndFlag(user.getUsername(),flag);
		DAO.close();
		return new ModelAndView("viewCart", "cartList", cartList);
	}
}
