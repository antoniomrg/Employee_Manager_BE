package tech.getarrays.employeemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findEmployeeById(Long id);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    Optional<List<Employee>> findEmployeeByName(@Param("name") String name);

    Optional<Employee> findByEmail(String email);
}
