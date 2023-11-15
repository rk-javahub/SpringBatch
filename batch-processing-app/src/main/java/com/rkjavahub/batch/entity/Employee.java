/**
 * 
 */
package com.rkjavahub.batch.entity;

import javax.persistence.Column;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "Employee")
@Data
public class Employee {
	@Column(name = "empid")
	private int id;
	@Column(name = "empname")
	private String name;

}
