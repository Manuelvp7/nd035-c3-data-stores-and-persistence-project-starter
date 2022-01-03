package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.controller.model.EmployeeDTO;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        Employee employee = Employee.fromEmployeeDTO(employeeDTO);
        return employeeRepository.save(employee).toEmployeeDTO();
    }

    public EmployeeDTO getEmployeById(long employeId){
        return employeeRepository.findById(employeId).get().toEmployeeDTO();
    }
}
