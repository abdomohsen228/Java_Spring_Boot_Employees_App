package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    // add mapping for list

    @GetMapping("/list")
    public String listEmployee(Model theModel) {
        // get emps from data base

        List<Employee> employees = employeeService.findAll();

        // add to spring model
        theModel.addAttribute("employees", employees);
        return "employees/list-employees";

    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        // create attribute
        Employee theEmployee = new Employee();
        // adding
        theModel.addAttribute("employee",  theEmployee);
        return "employees/employee-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {
        // get emp for id
        Employee theEmployee = employeeService.findById(theId);
        // set new data
        theModel.addAttribute("employee",  theEmployee);
        // send over form
        return "employees/employee-form";

    }

    @PostMapping("/save")
    public String save(@ModelAttribute("employee") Employee theEmployee) {
        // save it
        employeeService.save(theEmployee);
        // return redirect
        return "redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {
        // found
        employeeService.deleteById(theId);
        // delete
        return "redirect:/employees/list";
    }



}
