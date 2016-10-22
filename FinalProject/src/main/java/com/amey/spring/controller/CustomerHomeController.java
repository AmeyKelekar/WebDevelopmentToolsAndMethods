package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.AnnouncementDAO;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/customerHome.htm")
public class CustomerHomeController {
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return doPost(model, request, response);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User) request.getSession().getAttribute("user");
		model.addAttribute("user",user);
		AnnouncementDAO announcementDAO = new AnnouncementDAO();
		@SuppressWarnings("rawtypes")
		List arrayList = new ArrayList();
		arrayList = announcementDAO.list();
		return new ModelAndView("customerHome", "arrayList", arrayList);
	}
}
