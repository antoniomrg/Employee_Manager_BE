package tech.getarrays.employeemanager.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.employeemanager.dtos.LoginEmployeeDto;
import tech.getarrays.employeemanager.dtos.RegisterEmployeeDto;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.responses.LoginResponse;
import tech.getarrays.employeemanager.service.AuthenticationService;
import tech.getarrays.employeemanager.service.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Employee> register(@Valid @RequestBody RegisterEmployeeDto registerEmployeeDto) {
        System.out.println("RegisterEmployeeDto: " + registerEmployeeDto);
        Employee registeredEmployee = authenticationService.signup(registerEmployeeDto);

        return ResponseEntity.ok(registeredEmployee);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginEmployeeDto loginEmployeeDto) {

        Employee authenticatedEmployee = authenticationService.authenticate(loginEmployeeDto);

        String jwtToken = jwtService.generateToken(authenticatedEmployee);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
