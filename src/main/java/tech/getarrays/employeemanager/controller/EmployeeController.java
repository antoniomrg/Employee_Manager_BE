package tech.getarrays.employeemanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Tag(name = "Employees", description = "Operations related to employees")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }


    @Operation(
            summary = "Get all employees",
            responses = {
                    @ApiResponse(
                            description = "List of all employees retrieved successfully",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No employee found",
                            responseCode = "404"
                    )
            })
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAllEmployees());
    }

    @Operation(
            summary = "Get a specific employee by their id",
            responses = {
                    @ApiResponse(
                            description = "Employee found and retrieved successfully",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Employee with the specified ID not found",
                            responseCode = "404"
                    )
            })
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.findEmployeeById(id));
    }

    @Operation(
            summary = "Get one or more users if search matches employees' names (or part of it)",
            responses = {
                    @ApiResponse(
                            description = "One or more users found and retrieved successfully",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Search does not match any of the employees' names",
                            responseCode = "404"
                    )
            })
    @GetMapping(path = ("/find"))
    public Optional<List<Employee>> getEmployeesByNames(@RequestParam String name) {
        return employeeService.findEmployeeByName(name);
    }

    @Operation(
            summary = "Add a new employee",
            responses = {
                    @ApiResponse(
                            description = "Employee successfully added",
                            responseCode = "201"
                    ),
                    @ApiResponse(
                            description = "Invalid data provided",
                            responseCode = "400"
                    ),
            })
    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.status(201).body(newEmployee);
    }

    @Operation(
            summary = "Update data about an existing employee",
            responses = {
                    @ApiResponse(
                            description = "Employee successfully updated",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid data provided",
                            responseCode = "400"
                    ),
            })
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(newEmployee);
    }

    @Operation(
            summary = "Delete an employee",
            responses = {
                    @ApiResponse(
                            description = "Employee successfully deleted",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Invalid data provided",
                            responseCode = "400"
                    ),
            })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
