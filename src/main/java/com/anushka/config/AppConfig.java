package com.anushka.config;

import java.util.Arrays;
import java.util.Collections;

import org.hibernate.mapping.Collection;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(Management->Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated()
        .anyRequest().permitAll())
        .addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
        .csrf(csrf->csrf.disable())
        //.cors(cors->cors.configurationSource(corsConfigrationSource()));
        //.cors(cors -> cors.configurationSource(corsConfigurationSource()));
        .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();  
    }

//}
// private CorsConfigurationSource corsConfigurationSource(){
//     return new CorsConfigurationSource(){
//         @Override
//         public CorsConfiguration getCorsConfiguration(HttpServletRequest request){
//             CorsConfiguration cfg=new CorsConfiguration();
//             cfg.setAllowedOrigins(Arrays.asList(
//                 "http://localhost:3000",
//                 "http://localhost:5173",
//                 "http://localhost:4200"
//             ));


@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:3000",
            "http://localhost:5173",
            "http://localhost:4200"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

        //     cfg.setAllowedMethods(Collections.singletonList("*"));
        //     cfg.setAllowCredentials(true);//getAllowCredentials(true);
        //     cfg.setAllowedHeaders(Collections.singletonList("*"));
        //     cfg.setExposedHeaders(Arrays.asList("Authorization"));
        //     cfg.setMaxAge(3600L);
        //     return cfg;
        // }
//};
//}


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
