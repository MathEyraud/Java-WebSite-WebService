package af.cmr.indyli.akdemia.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import af.cmr.indyli.akdemia.business.service.IUserService;
import af.cmr.indyli.akdemia.ws.service.JwtAuthFilter;

/**
 * Security configuration class for Akdemia Web Services.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter authFilter;
    private final IUserService userDetailsService;

    /**
     * Constructor to initialize the SecurityConfig class.
     *
     * @param authFilter       JwtAuthFilter instance for JWT authentication.
     * @param userDetailsService IUserService instance for user details service.
     */
    public SecurityConfig(JwtAuthFilter authFilter, IUserService userDetailsService) {
        this.authFilter = authFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http HttpSecurity instance for configuring security.
     * @return SecurityFilterChain for the specified HTTP security configuration.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) -> request.requestMatchers("/users/generateToken", "/users/register",
                        "/users/resetpwd/**", "/swagger-ui/**", "/error/**").permitAll().anyRequest().permitAll())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    /**
     * Creates a bean for PasswordEncoder.
     *
     * @return BCryptPasswordEncoder bean instance.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates a bean for AuthenticationProvider.
     *
     * @return DaoAuthenticationProvider bean instance.
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Creates a bean for AuthenticationManager.
     *
     * @param config AuthenticationConfiguration instance for configuring authentication.
     * @return AuthenticationManager bean instance.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
