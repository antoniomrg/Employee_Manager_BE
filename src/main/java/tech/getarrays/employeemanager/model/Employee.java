package tech.getarrays.employeemanager.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)

/* To manage user details related to authentication,
 Spring Security provides an Interface named “UserDetails”
 with properties and methods that the User entity must override the implementation.*/

public class Employee implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;

    @Column(nullable = false)
    private String password;

    private String jobTitle;
    private String phone;
    private String imageUrl;
   //@Column(nullable = false, updatable = false)
    private String employeeCode;

// The method “getAuthorities()” returns the user’s roles list; it is helpful to manage permissions.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
