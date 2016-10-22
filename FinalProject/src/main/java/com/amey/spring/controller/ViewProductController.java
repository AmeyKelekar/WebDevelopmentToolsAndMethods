package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.DAO;
import com.amey.spring.dao.ProductDAO;
import com.amey.spring.pojo.Cart;
import com.amey.spring.pojo.Product;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/viewProduct.htm")
public class ViewProductController {
	@Autowired
	ProductDAO productDAO = new ProductDAO();
	@SuppressWarnings("rawtypes")
	List productList = new ArrayList();
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		productList = productDAO.list();
		DAO.close();
		return new ModelAndView("viewProduct", "productList", productList);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView modify(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		Product product = productDAO.getByID(id);
		if(product!=null){
			HttpSession session = request.getSession();
			Cart cart = new Cart();
			User user = (User)session.getAttribute("user");
			cart.setUsername(user.getUsername());
			cart.setProductid(product.getId());
			cart.setName(product.getName());
			cart.setDescription(product.getDescription());
			cart.setPrice(product.getPrice());
			cart.setQuantity(1);
			cart.setFlag(false);
			cart.setNote(request.getParameter("note"));
			session.setAttribute("cart", cart);
			model.addAttribute("cart", cart);
			DAO.close();
			return new ModelAndView("addToCart", "cart", cart);
		}
		else{
			DAO.close();
			return new ModelAndView("customerError");
		}
	}
	
	/*@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView modify(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		Product product = productDAO.getByID(id);
		if(product!=null){
			HttpSession session = request.getSession();
			Invoice invoice = new Invoice();
			User user = (User)session.getAttribute("user");
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			
			invoice.setUsername(user.getUsername());
			invoice.setAddress(user.getAddress());
			invoice.setEmail(user.getEmail());
			invoice.setProductname(product.getName());
			invoice.setProductprice(product.getPrice());
			invoice.setRequestedDate(simpleDateFormat.format(date));
			invoice.setStatus("Waiting");
			invoice.setNote(request.getParameter("note"));
			invoice.setUpdateDate(simpleDateFormat.format(date));
			session.setAttribute("invoice", invoice);
			model.addAttribute("invoice", invoice);
			DAO.close();
			return new ModelAndView("orderConfig", "order", invoice);
		}
		else{
			DAO.close();
			return new ModelAndView("customerError");
		}
	}*/
}
