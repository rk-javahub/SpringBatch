/**
 * 
 */
package com.rkjavahub.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.rkjavahub.batch.entity.Employee;

/**
 * 
 */
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
	@Override
	public Employee process(Employee employee) throws Exception {
		return employee;
	}
}
