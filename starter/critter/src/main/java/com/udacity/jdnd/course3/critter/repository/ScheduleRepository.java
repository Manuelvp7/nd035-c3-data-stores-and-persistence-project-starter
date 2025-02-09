package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.repository.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByPetsId(Long petId);

    List<Schedule> findByEmployeesId(Long employeeId);


}
