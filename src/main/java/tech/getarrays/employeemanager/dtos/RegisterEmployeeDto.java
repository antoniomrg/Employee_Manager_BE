package tech.getarrays.employeemanager.dtos;

//import lombok.*;
//
//@Data
//@Builder
//public class RegisterEmployeeDto {
//    private String email;
//
//    private String password;
//
//    private String name;
//}

public class RegisterEmployeeDto {
    private String email;
    private String password;
    private String name;

    // Default no-argument constructor
    public RegisterEmployeeDto() {}

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RegisterEmployeeDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
