package com.mightyjava.service;

import java.util.Set;

import com.mightyjava.modal.Employee;

public interface EmployeeService {
	void add(Employee employee);
	
	Set<Employee> employees();
}
