package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.controller.model.ScheduleDTO;
import com.udacity.jdnd.course3.critter.error.CustomerNotFound;
import com.udacity.jdnd.course3.critter.error.EmployeeNotFound;
import com.udacity.jdnd.course3.critter.error.PetNotFound;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.model.Customer;
import com.udacity.jdnd.course3.critter.repository.model.Employee;
import com.udacity.jdnd.course3.critter.repository.model.Pet;
import com.udacity.jdnd.course3.critter.repository.model.Schedule;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;




    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public ScheduleDTO saveSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
        List<Employee> employees = Optional.ofNullable(employeeRepository.findAllById(employeeIds)).orElseThrow(EmployeeNotFound::new);
        List<Pet> pets = Optional.ofNullable(petRepository.findAllById(petIds)).orElseThrow(PetNotFound::new);

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return scheduleRepository.save(schedule).toDTO();


    }

    public List<ScheduleDTO> getAllSchedules(){
        return scheduleRepository.findAll().stream().map(schedule -> schedule.toDTO()).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleByPetId(Long petId){
        Pet pet = Optional.ofNullable(petRepository.findById(petId).get()).orElseThrow(PetNotFound::new);
        return scheduleRepository.findByPetsId(petId).stream().map(schedule -> schedule.toDTO()).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleByEmployeeId(Long employeeId){
        Employee employee = Optional.ofNullable(employeeRepository.findById(employeeId).get()).orElseThrow(EmployeeNotFound::new);
        return scheduleRepository.findByEmployeesId(employeeId).stream().map(schedule -> schedule.toDTO()).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleByCustomerId(Long customerId){
        Customer customer = Optional.ofNullable(customerRepository.findById(customerId).get()).orElseThrow(CustomerNotFound::new);
        List<Long> petsId = customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList());
        return (List<ScheduleDTO>) petsId.stream()
            .map(
                petId -> scheduleRepository.findByPetsId(petId).stream().map(schedule -> schedule.toDTO()).collect(Collectors.toList()))
            .flatMap(scheduleDTOS -> scheduleDTOS.stream()).collect(Collectors.toList());

    }



}
