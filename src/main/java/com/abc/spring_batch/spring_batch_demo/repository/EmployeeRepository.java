package com.abc.spring_batch.spring_batch_demo.repository;

import com.abc.spring_batch.spring_batch_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    //JpaRepository<Employee, Integer> is a Spring Data JPA interface that provides CRUD (Create, Read, Update, Delete) operations and additional methods for working with the Employee entity. The first generic parameter (Employee) specifies the type of the entity, and the second parameter (Integer) specifies the type of the entity's primary key.

}