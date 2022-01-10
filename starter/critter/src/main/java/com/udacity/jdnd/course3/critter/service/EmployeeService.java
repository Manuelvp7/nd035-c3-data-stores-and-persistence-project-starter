package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.controller.model.EmployeeDTO;
import com.udacity.jdnd.course3.critter.error.EmployeeNotFound;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.model.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Logger log = LoggerFactory.getLogger(EmployeeService.class);


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        Employee employee = Employee.fromEmployeeDTO(employeeDTO);
        return employeeRepository.save(employee).toEmployeeDTO();
    }

    public void updateEmployee(EmployeeDTO employeeDTO){
        Employee employee = Employee.fromEmployeeDTO(employeeDTO);
        Optional.of(employeeRepository.findById(employee.getId())).ifPresentOrElse(
            employee1 -> {
                Employee updatedEmploye = employee1.get();
                updatedEmploye.setName(employee.getName());
                updatedEmploye.setSkills(employee.getSkills());
                updatedEmploye.setDaysAvailable(employee.getDaysAvailable());
                employeeRepository.save(updatedEmploye);

            },
            () -> {
                throw new EmployeeNotFound();
            }
        );
    }

    public EmployeeDTO getEmployeById(long employeId){
        return employeeRepository.findById(employeId).get().toEmployeeDTO();
    }

    public List<EmployeeDTO> getEmployessByServiceAndTime(EmployeeRequestDTO employeeDTO){

        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        log.info("Realizando consulta con DayOfWeek = {}",dayOfWeek);
        List<Employee> employees = employeeRepository.findAllBySkillsAndDaysAvailable(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));

        return employees.stream()
            .filter(employee -> employee.getSkills().containsAll(employeeDTO.getSkills()))
            .map(employee -> employee.toEmployeeDTO())
            .collect(Collectors.toList());
    }
}
