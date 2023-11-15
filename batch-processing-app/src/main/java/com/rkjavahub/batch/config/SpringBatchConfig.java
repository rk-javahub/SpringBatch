/**
 * 
 */
package com.rkjavahub.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.rkjavahub.batch.entity.Employee;
import com.rkjavahub.batch.repository.EmployeeRepository;

import lombok.Data;

@Configuration
@EnableBatchProcessing
@Data
public class SpringBatchConfig {
	private JobBuilderFactory builderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private EmployeeRepository employeeRepository;

	/**
	 * Used to read data from CSV file
	 * 
	 * @return FlatFileItemReader object
	 */
	@Bean
	private FlatFileItemReader<Employee> flatFileItemReader() {
		FlatFileItemReader<Employee> flatFileItemReader = new FlatFileItemReader<Employee>();
		flatFileItemReader.setResource(new FileSystemResource("src/main/resources/EmployeeData.csv"));
		flatFileItemReader.setName("CSV READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());

		return flatFileItemReader;
	}
 
	/**
	 * LineMapper defines how to read the csv file and how to map data from csv file
	 * to the object
	 * 
	 * @return LineMapper object
	 */
	private LineMapper<Employee> lineMapper() {
		DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<>();

		// To extract data from CSV file
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("empid", "empname");

		// Map csv file data to class
		BeanWrapperFieldSetMapper<Employee> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Employee.class);

		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

		return defaultLineMapper;
	}

}
