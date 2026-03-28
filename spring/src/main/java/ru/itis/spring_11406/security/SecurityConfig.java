package ru.itis.spring_11406.security;

import org.hibernate.engine.jdbc.dialect.spi.BasicDialectResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                //csrf(CsrfConfigurer::disable)
                csrf(csrf -> csrf.ignoringRequestMatchers("/signIn"))
                .formLogin((form) -> form
                        .loginPage("/signIn")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/signUp")
                        .failureUrl("/signIn?error")
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/**").permitAll());
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
//                        .requestMatchers("/signUp","/images/**", "/service/**", "/papers/**",
//                                "/css/**","/js/**",
//                                "/*.css","/*.js").permitAll().anyRequest().authenticated());
        return http.build();
    }
}
