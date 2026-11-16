package com.abc.spring_batch.spring_batch_demo.config;

import com.abc.spring_batch.spring_batch_demo.entity.Employee;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.LineMapper;
import org.springframework.batch.infrastructure.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.infrastructure.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.infrastructure.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing // this annotation enables Spring Batch features and provides a base configuration for setting up batch jobs in the application
// After spring boot 3, this annotation is not required anymore, but it is still used in many projects for clarity and to ensure that batch processing is enabled explicitly.
public class SpringBatchConfig {

    // The reader() method is a Spring Bean that defines a FlatFileItemReader for reading Employee objects from a CSV file.
    // configures the reader to skip the header line, set the resource to the employees.csv file, and use a line mapper to map each line to an Employee object.
    @Bean
    public FlatFileItemReader<Employee> reader(){
        FlatFileItemReader<Employee> itemReader = new FlatFileItemReader<>(lineMapper()); // Create a new instance of FlatFileItemReader for reading Employee objects from a CSV file and set the line mapper to map each line to an Employee object
        itemReader.setName("employeeReader"); // Set the name of the reader for identification purposes
        itemReader.setLinesToSkip(1); // Skip the header line (how many lines to skip at the beginning of the file)
        itemReader.setResource(new FileSystemResource("src/main/resources/employees.csv")); // Set the CSV file as the resource
        return itemReader;
    }

    // The lineMapper() method is a private method that defines a LineMapper for mapping each line of the CSV file to an Employee object.
    private LineMapper<Employee> lineMapper(){
        DefaultLineMapper<Employee> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(","); // Set the delimiter for the CSV file (comma in this case)
        lineTokenizer.setStrict(false); // Set strict mode to false to allow for missing fields
        lineTokenizer.setNames("id", "name", "username", "gender", "salary"); // Set the names of the fields in the CSV file

        BeanWrapperFieldSetMapper<Employee> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Employee.class); // Set the target type for mapping the fields to the Employee class

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
