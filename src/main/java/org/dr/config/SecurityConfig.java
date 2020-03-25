package org.dr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Profile("server")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password(encoder().encode("userPass")).roles("USER").build());
        manager.createUser(User.withUsername("admin").password(encoder().encode("adminPass")).roles("ADMIN", "USER").build());
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Profile("server")
    @Configuration
    @Order(1)
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user/**")
                    .authorizeRequests().anyRequest().hasRole("USER")
                    .and().httpBasic().authenticationEntryPoint(userAuthEntryPoint())
                    .and().exceptionHandling().accessDeniedPage("/403");
        }

        @Bean
        public AuthenticationEntryPoint userAuthEntryPoint(){
            BasicAuthenticationEntryPoint entryPoint = new  BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("user login");
            return entryPoint;
        }
    }

    @Profile("server")
    @Configuration
    @Order(2)
    public static class App3ConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**")
                    .authorizeRequests().anyRequest().hasRole("ADMIN")
                    .and().httpBasic().authenticationEntryPoint(adminAuthEntryPoint());
        }

        @Bean
        public AuthenticationEntryPoint adminAuthEntryPoint(){
            BasicAuthenticationEntryPoint entryPoint = new  BasicAuthenticationEntryPoint();
            entryPoint.setRealmName("admin login");
            return entryPoint;
        }
    }

}