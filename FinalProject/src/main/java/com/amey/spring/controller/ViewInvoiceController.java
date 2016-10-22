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
import com.amey.spring.pojo.Invoice;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/viewInvoice.htm")
public class ViewInvoiceController {
	@Autowired
	InvoiceDAO invoiceDAO = new InvoiceDAO();
	@SuppressWarnings("rawtypes")
	List invoiceList = new ArrayList();
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user = (User)session.getAttribute("user");
		invoiceList = invoiceDAO.listByName(user.getUsername());
		DAO.close();
		return new ModelAndView("viewInvoice", "invoiceList", invoiceList);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		Date date=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Invoice invoice = invoiceDAO.getByID(id);
		invoice.setStatus(request.getParameter("status"));
		invoice.setUpdateDate(simpleDateFormat.format(date));
		invoiceDAO.update(invoice);		
		DAO.close();
		return new ModelAndView("managerSuccess");
	}
}
