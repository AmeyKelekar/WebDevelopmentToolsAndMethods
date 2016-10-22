package com.amey.spring.controller;

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
import com.amey.spring.dao.UserDAO;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/viewAccount.htm")
public class ViewAccountController {
	@Autowired
	UserDAO userDAO=new UserDAO();
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(Model model, HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		model.addAttribute("user",user);
		return "viewAccount";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		
		user.setName(request.getParameter("name"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setAddress(request.getParameter("address"));
		
		userDAO.update(user);
		DAO.close();
		
		session.removeAttribute("user");
		session.setAttribute("user", user);
		
		return new ModelAndView("customerSuccess");
    }
}
