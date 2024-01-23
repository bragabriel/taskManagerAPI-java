package com.gerenciador.tarefas.config;

import com.gerenciador.tarefas.filter.AuthenticationFilter;
import com.gerenciador.tarefas.filter.LoginFilter;
import com.gerenciador.tarefas.permission.PermissionEnum;
import com.gerenciador.tarefas.service.UserAuthenticatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Autowired
    private UserAuthenticatedService userAuthenticatedService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws  Exception {

        http.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/welcome-api").permitAll()
                            .requestMatchers(HttpMethod.GET, "/welcome-user").hasAuthority(PermissionEnum.ADMIN.toString())
                            .requestMatchers(HttpMethod.GET, "/usuarios").hasAuthority(PermissionEnum.USER.toString())
                            .requestMatchers(HttpMethod.POST, "/usuarios").hasAuthority(PermissionEnum.ADMIN.toString())
                            .requestMatchers(HttpMethod.POST, "/gerenciador-tarefas").hasAuthority(PermissionEnum.ADMIN.toString())
                            .anyRequest() //todas outras requests
                            .authenticated(); //tem que serem autenticadas
                });

        http.addFilterBefore(new LoginFilter("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
