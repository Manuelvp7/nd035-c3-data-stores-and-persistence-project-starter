package com.udacity.jdnd.course3.critter.repository.model;

import com.udacity.jdnd.course3.critter.controller.model.EmployeeDTO;
import org.hibernate.annotations.Nationalized;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Set;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized
    private String name;


    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass= DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public EmployeeDTO toEmployeeDTO(){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setSkills(this.getSkills());
        employeeDTO.setName(this.getName());
        employeeDTO.setDaysAvailable(this.getDaysAvailable());
        employeeDTO.setId(this.getId());

        return employeeDTO;
    }

    public static Employee fromEmployeeDTO(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setName(employeeDTO.getName());
        employee.setSkills(employeeDTO.getSkills());

        return employee;
    }
}