package com.team2.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.team2.demo.model.Employee;
import com.team2.demo.service.EmployeeService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeController {

	private final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	// autowire the EmployeeService class
	@Autowired
	EmployeeService service;

	@GetMapping("/")
	private String baseUrl() {
		log.info("baseUrl controller");
		return "welcome";
	}

// creating a get mapping that retrieves all the Employee detail from the database
	@GetMapping("/employees")
	private List<Employee> getAllEmployees() {
		log.info("getAllEmployees controller");
		return service.getAllEmployees();
	}

// creating a get mapping that retrieves the detail of a specific Employee
	@GetMapping("/employee/{id}")
	private Employee getEmployee(@PathVariable("id") int id) {
		log.info("getEmployee controller");

		return service.getEmployeeById(id);
	}

// creating a post mapping to post details of Employee

	@PostMapping("/employee") // http://localhost:8090/department/5/employee
	public Employee createEmployee(@RequestBody Employee employee) {

		log.info("createEmployee controller");

		service.saveEmployee(employee);

		return employee;
	}

	// creating put mapping that updates the Employee detail

	// update only name
	@PutMapping("/employee/{id}")
	private Employee updateEmployee(@PathVariable(value = "id") int id, @RequestBody Employee employee) {
		log.info("updateEmployee controller");
		service.update(employee, id);
		return employee;
	}

// creating a delete mapping that deletes a specified Employee

	@DeleteMapping("/employee/{id}")
	private void deleteEmployee(@PathVariable("id") int id) {
		log.info("deleteEmployee controller");
		service.delete(id);
	}

}