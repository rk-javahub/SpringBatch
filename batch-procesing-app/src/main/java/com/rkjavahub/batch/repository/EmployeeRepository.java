/**
 * 
 */
package com.rkjavahub.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkjavahub.batch.entity.Employee;

/**
 * 
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
