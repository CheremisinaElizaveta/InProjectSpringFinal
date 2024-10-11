package com.example.InProject.config;

import com.example.InProject.model.RoleEnum;
import com.example.InProject.model.UserModel;
import com.example.InProject.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!userRepository.existsByUsername("admin")) {
            UserModel user = new UserModel();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Qwerty_123"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
            userRepository.save(user);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
                    UserModel user = userRepository.findByUsername(username);
                    if (user == null) {
                        throw new UsernameNotFoundException("Такой пользователь не существует!");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            user.isActive(),
                            true,
                            true,
                            true,
                            user.getRoles()
                    );
                }
        ).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/admin/users").hasAuthority("ADMIN")
                                .requestMatchers("/login", "/registration").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customSuccessHandler()) // используем кастомный обработчик
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (request, response, authentication) -> {
            String redirectUrl; // здесь хранится URL, на который нужно перенаправить
            // Определяем URL перенаправления в зависимости от роли пользователя
            if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))) {
                redirectUrl = "/admin/users";
            } else if (authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("MANAGER"))) {
                redirectUrl = "/categories/all";
            } else {
                redirectUrl = "/orders/all"; // Для остальных пользователей
            }
            // Выполняем перенаправление
            response.sendRedirect(redirectUrl);
        };
    }
   /* private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser(){
        if (!userRepository.existsByUsername("admin")) {
            UserModel user = new UserModel();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Qwerty_123"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
            userRepository.save(user);
        }
    }

    @PostConstruct
    public void createDefaultUser2(){
        if (!userRepository.existsByUsername("manager")) {
            UserModel user = new UserModel();
            user.setUsername("manager");
            user.setPassword(passwordEncoder.encode("Manager_123"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.MANAGER));
            userRepository.save(user);
        }
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
                    UserModel user = userRepository.findByUsername(username);
                    if (user == null) {
                        throw new UsernameNotFoundException("Такой пользователь не существует!");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            user.isActive(),
                            true,
                            true,
                            true,
                            user.getRoles()
                    );
                }
        ).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login", "/registration").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/orders/all").permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();

    }*/
}

