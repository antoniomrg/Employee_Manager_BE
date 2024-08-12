package tech.getarrays.employeemanager.dtos;

import lombok.Data;

@Data
public class LoginEmployeeDto {
    private String email;

    private String password;
}
