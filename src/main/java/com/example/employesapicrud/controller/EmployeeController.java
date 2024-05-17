package com.example.employesapicrud.controller;

import com.example.employesapicrud.model.Employee;
import com.example.employesapicrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public String createNewEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Employee created in the database";
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee>emplList = new ArrayList<>();
        employeeRepository.findAll().forEach(emplList::add);
        return new ResponseEntity<List<Employee>>(emplList, HttpStatus.OK);
    }

    @GetMapping("/employees/{empid}")
    public ResponseEntity<Employee>getEmployesById(@PathVariable long empid) {
        Optional<Employee> emp = employeeRepository.findById(empid);
        if (emp.isPresent()) {
            return new ResponseEntity<Employee>(emp.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/employees/{empid}")
    public String updateEmployeeById(@PathVariable long empid, @RequestBody Employee employee){
           Optional<Employee> emp = employeeRepository.findById(empid);
           if(emp.isPresent()){
               Employee existEmp = emp.get();
               existEmp.setEmp_age(employee.getEmp_age());
               existEmp.setEmp_city(employee.getEmp_city());
               existEmp.setEmp_name(employee.getEmp_name());
               existEmp.setEmp_salary(employee.getEmp_salary());

               employeeRepository.save(existEmp);
               return "Employee details against id " + empid + "updated";

           }
           else{
               return "Employee details not found";
           }
        }
    @DeleteMapping("/employees/{empid}")
    public String deleteEmployeeByEmpid(@PathVariable long empid){
        employeeRepository.deleteById(empid);
        return "Employee Deleted Successfully";
    }

    @DeleteMapping("/employees")
    public String deleteAllEmployee(){
        employeeRepository.deleteAll();
        return "Employee deleted successfully";
    }

}
