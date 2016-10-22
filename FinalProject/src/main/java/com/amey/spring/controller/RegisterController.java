package com.amey.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.UserDAO;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/register.htm")
public class RegisterController {
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "registerForm";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserDAO userDAO = new UserDAO();
		User user=new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));	
		user.setName(request.getParameter("name"));
		user.setAddress(request.getParameter("address"));
		user.setEmail(request.getParameter("email"));		
		user.setRole("customer");
		
		User temporaryUser = userDAO.searchByUserName(user.getUsername());
		
		if(temporaryUser!=null){
			return new ModelAndView("registerError","userr",user);
		}
		else{
			userDAO.create(user);
			user=userDAO.searchByUserName(user.getUsername());
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return new ModelAndView("registerSuccess");
		}	
	}
}
