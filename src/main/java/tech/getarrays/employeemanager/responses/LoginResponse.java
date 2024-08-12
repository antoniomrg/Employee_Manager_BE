package tech.getarrays.employeemanager.responses;

import lombok.*;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }
}
