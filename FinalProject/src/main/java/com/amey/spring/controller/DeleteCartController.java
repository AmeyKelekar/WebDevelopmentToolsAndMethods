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
@RequestMapping("/deleteCart.htm")
public class DeleteCartController {
	@Autowired
	CartDAO cartDAO = new CartDAO();
	@SuppressWarnings("rawtypes")
	List cartList = new ArrayList();
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		return doPost(request, response);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		System.out.println("Cart id is: "+id);
		Cart cart = cartDAO.getByID(id);
		if(cart!=null){
			cartDAO.delete(cart);
			HttpSession session=request.getSession();
			User user=(User)session.getAttribute("user");
			boolean flag = false;
			cartList = cartDAO.listByNameAndFlag(user.getUsername(),flag);
			DAO.close();
			return new ModelAndView("viewCart","cartList",cartList);
		}else{
			DAO.close();
			return new ModelAndView("customerError");
		}		
	}
}
