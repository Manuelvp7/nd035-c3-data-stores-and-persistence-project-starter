package com.udacity.jdnd.course3.critter.repository.model;

import com.udacity.jdnd.course3.critter.controller.model.EmployeeDTO;
import com.udacity.jdnd.course3.critter.controller.model.ScheduleDTO;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToMany
    private List<Employee> employees;

    @ManyToMany
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public static Schedule fromDTO(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setDate(scheduleDTO.getDate());
        return schedule;
    }

    public ScheduleDTO toDTO(){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setActivities(this.getActivities());
        scheduleDTO.setDate(this.getDate());
        scheduleDTO.setEmployeeIds( this.getEmployees().stream().map( employee -> employee.getId()).collect(Collectors.toList()));
        scheduleDTO.setPetIds(this.getPets().stream().map( pet -> pet.getId()).collect(Collectors.toList()));
        return scheduleDTO;

    }
}
