package tech.getarrays.employeemanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.employeemanager.dtos.RegisterEmployeeDto;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.service.AuthenticationService;

@RestController
@RequestMapping("/test")
public class TestController {

    private final AuthenticationService authenticationService;

    public TestController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/json")
    public ResponseEntity<String> testJson(@RequestBody RegisterEmployeeDto dto) {
        return ResponseEntity.ok("Received: " + dto);
    }

    @PostMapping("/signup")
    public ResponseEntity<Employee> signup(@RequestBody RegisterEmployeeDto dto) {
        System.out.println("Received DTO in Test Controller: " + dto);
        Employee employee = authenticationService.signup(dto);
        return ResponseEntity.ok(employee);
    }
}