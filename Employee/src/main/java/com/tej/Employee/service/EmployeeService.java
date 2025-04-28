package com.tej.Employee.service;


import com.tej.Employee.dto.EmployeeDto;
import com.tej.Employee.entity.Employee;
import com.tej.Employee.mapper.EmployeeMapper;
import com.tej.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService<E> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;
    public Page<Employee> findAllEmployees(int page, int size, String sortBy, Sort.Direction dir) {
        try {
            // Create Pageable object with sorting
            Pageable pageable = PageRequest.of(page - 1, size, dir, sortBy);  // page - 1 because pages are 0-based
            return employeeRepository.findAll(pageable);
        } catch (Exception e) {
            System.out.println("An error occurred while fetching employees: " + e.getMessage() + " , " + e);
            return Page.empty();  // Return an empty Page if an error occurs
        }
    }


    public Employee saveEmployee(EmployeeDto employeeDto) {
        return employeeRepository.save(employeeMapper.EmployeeDtoToDao(employeeDto));
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("not found Employee"));
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDto){
        return employeeRepository.findById(id).map(existingEmployee ->{
            employeeMapper.updateEmployeeFromDto(employeeDto, existingEmployee);
            return employeeRepository.save(existingEmployee);
        }).orElse(null);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }


}
