package ies.lab3.e2.restapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    List<Employee> findByEmailId(String emailId);

}