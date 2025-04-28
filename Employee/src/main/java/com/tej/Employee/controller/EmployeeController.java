package com.tej.Employee.controller;


import com.tej.Employee.dto.EmployeeDto;
import com.tej.Employee.entity.Employee;
import com.tej.Employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // test your server
    @GetMapping("/test")
    public String test(){
        return "Hello, you application is fine";
    }

    // get all employee and pagination

    @GetMapping("/employees") //1
    public ResponseEntity<Page<Employee>> getEmployees(
            @RequestParam(defaultValue = "1") int startPage,  // Default start page is 1
            @RequestParam(defaultValue = "5") int pageSize,   // Default page size is 5
            @RequestParam(defaultValue = "id") String sortBy,  // Default sort by "id"
            @RequestParam(defaultValue = "ASC") Sort.Direction dir) {  // Default sort direction is ASC

        // Validate that startPage is greater than 0
        if (startPage <= 0) {
            return ResponseEntity.badRequest().body(null); // Invalid page number
        }

        // Retrieve the page of employees based on the requested startPage and pageSize
        Page<Employee> employeesPage = employeeService.findAllEmployees(startPage, pageSize, sortBy, dir);

        // Return the paginated response
        return ResponseEntity.ok(employeesPage);
    }

    // get by id employee

    @GetMapping("/{id}") // 2
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        try {
            Employee employee = employeeService.findEmployeeById(id);
            if(employee != null){
                return ResponseEntity.status(HttpStatus.OK).body(employee);
            }else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


     // save employee

    @PostMapping() // 3
    public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.saveEmployee(employeeDto));
    }

    // update employee

    @PutMapping("/{id}") // 4
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto ){
        try {
            Employee updateData = employeeService.updateEmployee(id, employeeDto);
            if(updateData != null){
                return ResponseEntity.ok(updateData);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            System.out.println("employee not found" + e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/{id}") //5
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
         employeeService.deleteEmployeeById(id);
         return ResponseEntity.noContent().build();
    }

}
