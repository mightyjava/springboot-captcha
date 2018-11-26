package com.mightyjava.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mightyjava.modal.Employee;
import com.mightyjava.service.EmployeeService;

@Controller
public class EmployeeController {
	
	private String message;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String add(Model model) {
		model.addAttribute("message", message);
		model.addAttribute("employee", new Employee());
		return "add";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("employee") Employee employee, HttpServletRequest request) {
		if(employee.getCaptcha().equals(request.getSession().getAttribute("captcha"))) {
			employeeService.add(employee);
			return "redirect:/list";
		} else {
			message = "Please verify captcha";
			return "redirect:/";
		}
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		message = null;
		model.addAttribute("employees", employeeService.employees());
		return "list";
	}
}
