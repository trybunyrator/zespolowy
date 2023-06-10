package controllers.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import controllers.models.Uzytkownik;
import controllers.repositories.UzytkownikRepository;

@Configuration
public class SecurityConfig {
  private final PasswordEncoder passwordEncoder;

  private final DataSource dataSource;

  public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
    this.passwordEncoder = passwordEncoder;
    this.dataSource = dataSource;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passwordEncoder);
  }

  @Bean
  public static PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.cors().and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/h2/**").permitAll()
        .antMatchers("/").hasAnyRole("ADMIN", "USER")
        .antMatchers("/getAllCategory").hasAnyRole("USER", "ADMIN")
        .antMatchers("/addNewUser1").hasAnyRole("USER")
        .antMatchers("/login").permitAll()
        .antMatchers(HttpMethod.POST, "/register1").permitAll()
        .antMatchers("/info").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()
        .and()
        .httpBasic();

    return http.build();
  }

  // Ustawienia CORS
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
