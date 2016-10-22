package com.amey.spring.controller;

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
import com.amey.spring.pojo.Invoice;

@Controller
public class ConfigController {
	@Autowired
	InvoiceDAO invoiceDAO = new InvoiceDAO();
	
	@RequestMapping(value = "/config.htm", method = RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Invoice invoice=(Invoice)session.getAttribute("invoice");
		invoiceDAO.create(invoice);
		session.removeAttribute("invoice");
		DAO.close();
		return new ModelAndView("customerSuccess");
	}
}
