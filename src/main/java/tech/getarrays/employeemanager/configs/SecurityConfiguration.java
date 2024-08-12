package tech.getarrays.employeemanager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        //Disables CSRF (Cross-Site Request Forgery) protection. This is often done in REST APIs where CSRF tokens are not typically used.
//        // AbstractHttpConfigurer::disable is a method reference that disables CSRF protection.
        http.csrf(AbstractHttpConfigurer::disable)
//                // Begins the configuration of authorization rules.
//                // authorizeHttpRequests allows you to specify which requests should be authorized based on certain criteria.
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
//                        // Specifies that HTTP DELETE requests must be performed by users with the role "ADMIN".
                        authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                                // Specifies that any request matching the URL pattern /admin/** must be performed by users with at least the role "ADMIN".
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/test/**").permitAll()
//                                // Specifies that any request not previously matched by the above patterns requires authentication.
                                .anyRequest().authenticated())
//                // Configures HTTP Basic Authentication. Customizer.withDefaults() applies the default settings for HTTP Basic Authentication.
//                // This means that the application will prompt for a username and password for authentication.
                .httpBasic(Customizer.withDefaults())
//                // Configures session management to use a stateless session policy. SessionCreationPolicy.STATELESS means that the application will not create or use HTTP sessions.
//                // This is typical for REST APIs where each request is independent and should not rely on server-side sessions.
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8080"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}
