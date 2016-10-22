package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.AnnouncementDAO;
import com.amey.spring.dao.UserDAO;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/home.htm")
public class LogInController {
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "loginForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserDAO userDao = new UserDAO();
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));		
		
		User temporaryUser = userDao.searchByUserName(user.getUsername());
		if(temporaryUser==null){
			return new ModelAndView("loginError","user",user);
		}
		
		else if(temporaryUser.getPassword().equals(user.getPassword()) && temporaryUser.getRole().equals("manager")){
			user=temporaryUser;
			HttpSession session=request.getSession();			
			session.setAttribute("user", user);
			return new ModelAndView("managerHome","user",user);
		}
		
		else if(temporaryUser.getPassword().equals(user.getPassword()) && temporaryUser.getRole().equals("customer")){
			user=temporaryUser;
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			model.addAttribute("user",user);
			AnnouncementDAO aDao = new AnnouncementDAO();
			@SuppressWarnings("rawtypes")
			List arrayList = new ArrayList();
			arrayList=aDao.list();
			return new ModelAndView("customerHome","arrayList",arrayList);
		}
		
		else{
			return new ModelAndView("loginError","user",user);
		}
    }
}
