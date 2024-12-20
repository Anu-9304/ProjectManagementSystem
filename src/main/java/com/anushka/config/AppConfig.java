package com.anushka.config;

// import java.util.Arrays;
// import java.util.Collections;

//import org.hibernate.mapping.Collection;
//import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.server.ServerWebExchange;

//import io.micrometer.common.lang.NonNull;

//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(Management->Management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated()
        .anyRequest().permitAll())
        .addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
        .csrf(csrf->csrf.disable());
        //.cors(cors -> cors.configurationSource(corsConfigrationSource()));

        
        return http.build();  
    }






    //private CorsConfigurationSource corsConfigrationSource(){
        //return new CorsConfigurationSource() {
            

            // @Override
            // @Nullable
            // public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            //     CorsConfiguration cfg= new CorsConfiguration();
            //     cfg.setAllowedOrigins(Arrays.asList(
            //         "http://localhost:3000",
            //             "http://localhost:5173",
            //             "http://localhost:4200"
            //     ));

            //     cfg.setAllowedMethods(Collections.singletonList("*"));
            //     cfg.setAllowCredentials(true);
            //     cfg.setAllowedHeaders(Collections.singletonList("*"));
            //     cfg.setExposedHeaders(Arrays.asList("Authorization"));
            //     cfg.setMaxAge(3600L);

            //     return cfg;
            // }
   
       // };
    //}
        @Bean
         PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
    }


