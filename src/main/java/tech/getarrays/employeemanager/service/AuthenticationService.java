package tech.getarrays.employeemanager.service;

import lombok.Data;
import lombok.Value;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.dtos.LoginEmployeeDto;
import tech.getarrays.employeemanager.dtos.RegisterEmployeeDto;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repository.EmployeeRepo;

@Service
public class AuthenticationService {
    private final EmployeeRepo employeeRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(EmployeeRepo employeeRepo,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager) {
        this.employeeRepo = employeeRepo;
        this. passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Employee signup(RegisterEmployeeDto input) {
        Employee employee = new Employee()
                .name(input.getName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()));

        return employeeRepo.save(employee);
    }

    public Employee authenticate(LoginEmployeeDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return employeeRepo.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
