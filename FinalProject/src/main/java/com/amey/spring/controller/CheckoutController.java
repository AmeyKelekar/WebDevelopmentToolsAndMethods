package com.amey.spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.DAO;
import com.amey.spring.dao.InvoiceDAO;
import com.amey.spring.dao.PaymentDAO;
import com.amey.spring.dao.CartDAO;
import com.amey.spring.pojo.Invoice;
import com.amey.spring.pojo.Payment;
import com.amey.spring.pojo.User;
import com.amey.spring.pojo.Cart;

@Controller
@RequestMapping("/payment.htm")
public class CheckoutController {
	
	@Autowired
	PaymentDAO paymentDAO = new PaymentDAO();
	@Autowired
	CartDAO  cartDAO = new CartDAO();
	@Autowired
	InvoiceDAO invoiceDAO = new InvoiceDAO();
	
	List <Cart>cartList = new ArrayList<Cart>();
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		float total= Float.parseFloat(request.getParameter("price"));
		HttpSession session=request.getSession();			
		session.setAttribute("total", total);
		System.out.println("total Price: "+total);
		return new  ModelAndView("payment","total",total);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Payment payment = new Payment();
		payment.setMembername(request.getParameter("membername"));
		payment.setCard(request.getParameter("card"));
		payment.setCardnumber(request.getParameter("cardnumber"));
		payment.setCvv(request.getParameter("cvv"));
		payment.setAmount(Float.parseFloat(request.getParameter("amount")));
		payment.setEmail(request.getParameter("email"));
		
		paymentDAO.create(payment);
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		boolean flag = false;
		cartList = cartDAO.listByNameAndFlag(user.getUsername(),flag);
		for(Cart cart: cartList){
			cart.setFlag(true);
			cartDAO.update(cart);
			
			Invoice invoice = new Invoice();
			Date date = new Date();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			invoice.setUsername(user.getUsername());
			invoice.setAddress(user.getAddress());
			String email = request.getParameter("email");
			invoice.setEmail(email);
			invoice.setProductname(cart.getName());
			invoice.setProductprice(cart.getPrice());
			invoice.setQuantity(cart.getQuantity());
			invoice.setNote(cart.getNote());
			invoice.setRequestedDate(simpleDateFormat.format(date));
			invoice.setStatus("Waiting");
			invoice.setUpdateDate(simpleDateFormat.format(date));
			invoiceDAO.create(invoice);
		}
		
		DAO.close();
		return new ModelAndView("customerSuccess");
	}
}
