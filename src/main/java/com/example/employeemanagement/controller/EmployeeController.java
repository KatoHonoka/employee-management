package com.example.employeemanagement.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.employeemanagement.form.SearchForm;
import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

  @Autowired
  private EmployeeService service;

  @GetMapping({"/index","/index/"})
  public String index(SearchForm form) {

    return "search";
  }
  @GetMapping("/search")
  public String search(SearchForm form, Model model) {
    // System.out.println(form);
    logger.info(form.toString());
    
    List<Employee> list = service.search(form);

    model.addAttribute("employeeList", list);
      
    return "search";
  }
  
  

  @GetMapping({"/execute","/execute/"})
  public String execute() throws SQLException {
    Employee employee = new Employee();
    employee.setName("test");
    employee.setAge(21);
    employee.setGender("男");
    employee.setDepartment_id(2);

    service.save(employee);

    Employee employee2 = service.findById(3);
    logger.info(employee2.toString());
    // logger.debug(employee2.toString());
    // logger.warn(employee2.toString());
    // System.out.println(employee2);
    service.deleteById(employee2.getId());

    service.findAll().forEach(emp -> logger.info(emp.toString()));
    // service.findAll().forEach(System.out::println);

    return "finished";
  }
  
}
