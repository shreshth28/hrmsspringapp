package com.team2.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.team2.demo.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	// just a few methods

//	@Modifying
//	@Query("UPDATE employees SET name = ?1, salary = ?2, department_id = ?3 WHERE id = ?4")
//	void setEmployeeById(String name, double salary, int department_id, int id);

//	Page<Employee> findByDepartmentId(int departmentId, Pageable pageable);
//
//	Optional<Employee> findByIdAndDepartmentId(int id, int departmentId);

}