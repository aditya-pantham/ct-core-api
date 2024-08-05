package com.commercetools.ct_core_api.security;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
    @Value("${commercetools.commerce.api.client_id}")
    String clientId;

    @Value("${commercetools.commerce.api.client_secret}")
    String clientSecret;

    @Value("${sap.commerce.api.OAUTH_CLIENT_ID}")
    String SapClientId;

    @Value("${sap.commerce.api.OAUTH_CLIENT_SECRET}")
    String SapClientSecret;

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User
                .withUsername(clientId)
                .password(encoder.encode(clientSecret))
                .build();
        UserDetails sapUser= User
                .withUsername(SapClientId)
                .password(encoder.encode(SapClientSecret))
                .build();
        return new InMemoryUserDetailsManager(user,sapUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/ct/**").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/ct/**").authenticated()
                        .anyRequest().permitAll())
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(
                        (request, response, authException)
                                -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")))
                .build();
    }
}
