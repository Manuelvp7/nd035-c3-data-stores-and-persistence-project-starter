package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.repository.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM employee e " +
            "INNER JOIN employee_days_available eda ON e.id = eda.employee_id " +
            "WHERE eda.days_available LIKE :day ")
    List<Employee> findAllBySkillsAndDaysAvailable(@Param("day")  String dayOfWeek);

}
