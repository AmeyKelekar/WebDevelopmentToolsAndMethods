package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.DAO;
import com.amey.spring.dao.UserDAO;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/managerUser.htm")
public class ManagerUserController {
	@Autowired
	UserDAO userDAO = new UserDAO();
	@SuppressWarnings("rawtypes")
	List userList = new ArrayList();
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		userList = userDAO.list();
		DAO.close();
		return new ModelAndView("listUser", "userList", userList);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		User user = userDAO.searchById(id);
		userDAO.delete(user);
		DAO.close();
		return new ModelAndView("managerSuccess");
	}
}
